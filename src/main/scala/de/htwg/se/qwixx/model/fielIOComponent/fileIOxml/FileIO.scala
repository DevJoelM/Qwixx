package de.htwg.se.qwixx.model.fielIOComponent.fileIOxml

import com.google.inject.Guice
import de.htwg.se.qwixx.QwixxModule
import de.htwg.se.qwixx.controller.controllerComponent.ControllerInterface
import de.htwg.se.qwixx.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.qwixx.model.blockComponent.blockBaseImpl.Row
import de.htwg.se.qwixx.model.fielIOComponent.FileIOInterface

import java.awt.Color
import java.io.{File, PrintWriter}
import scala.xml.{Elem, PrettyPrinter}

/////////////////////////////////////////////////////////////
// FileName: FileIO.scala (XML)
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On: 10.01.2021
// Last Modified On : 10.01.2021
/////////////////////////////////////////////////////////////


class FileIO extends FileIOInterface {

  /**
   * This function loads the last played / saved game status
   * of the game from an XML file.
   *
   * @return a ControllerInterfaces based Object.
   */
  override def loadGame: ControllerInterface = {

    val file = scala.xml.XML.loadFile("exports/save.xml")
    val root = file \\ "game"
    val rowNodes = root \\ "Row"

    val injector = Guice.createInjector(new QwixxModule)
    val controllerInt = injector.getInstance(classOf[ControllerInterface])

    var s: List[Row] = List()
    for (r <- rowNodes) {
      val rowIdx = (r \\ "@Ridx").text.toInt
      val rowColor = (r \\ "@rowColor").text
      val strat = (r \\ "@strat").text
      val tempRow = new Row(rowIdx, Color.decode(rowColor), strat)
      for (f <- r \\ "Field") {
        val fieldIdx = (f \\ "@Fidx").text
        tempRow.checkField(fieldIdx.toInt)
      }
      s = tempRow :: s
    }
    controllerInt.getController().playerList(0).block.rowList = s.toArray
    controllerInt
  }

  /**
   * This function saves the current game status in an XML file.
   *
   * @param controller
   */
  def saveGame(controller: Controller): Unit = {
    val printWriter = new PrintWriter(new File("exports/save.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gameToXML(controller))
    printWriter.write(xml)
    printWriter.close()
  }

  /**
   * This function wraps the whole game in an XML structure.
   *
   * @param controller
   * @return
   */
  def gameToXML(controller: Controller): Elem = {
    <game>
      {controller.playerList(0).block.rowList.zipWithIndex.map { case (r, i) => {
      rowToXML(r, i + 1)
    }
    }}
    </game>
  }

  /**
   * This function includes a row in an XML structure.
   *
   * @param r   Row
   * @param idx Index of the row.
   * @return
   */
  def rowToXML(r: Row, idx: Int) = <Row Ridx={idx.toString} rowColor={"#" + Integer.toHexString(r.colorName.getRGB()).substring(2)} strat={r.strat.toString}>
    {r.fieldList.map(f => {
      if (f.checkedState) {
        fieldToXML(f.fieldIdx)
      }
    })}
  </Row>

  /**
   * This function includes a field as XML structure.
   *
   * @param idx Index of the field.
   * @return
   */
  def fieldToXML(idx: Int) = <Field Fidx={idx.toString}/>
}