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

//XML

class FileIO extends FileIOInterface{

  override def loadGame: ControllerInterface = {

    val file = scala.xml.XML.loadFile("save.xml")
    val root = (file \\ "game")
    val rowNodes = (root \\ "Row")

    val injector = Guice.createInjector(new QwixxModule)
    val controllerInt = injector.getInstance(classOf[ControllerInterface])

    var s : List[Row] = List()
    for (r <- rowNodes) {
      val rowIdx = (r \\ "@Ridx").text.toInt
      val rowColor = (r \\ "@rowColor").text
      val strat = (r \\ "@strat").text
      val tempRow = new Row(rowIdx,Color.decode(rowColor),strat)
      for(f <- (r \\ "Field")){
        val fieldIdx = (f \\ "@Fidx").text
        tempRow.checkField(fieldIdx.toInt)
      }
      s = tempRow :: s
    }
    controllerInt.getController().playerList(0).block.rowList = s.toArray
    controllerInt
  }

  def saveGame(controller:Controller): Unit = {
    val printWriter = new PrintWriter(new File("save.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gameToXML(controller))
    printWriter.write(xml)
    printWriter.close()
  }

  def gameToXML(controller: Controller): Elem = {
    <game>
      {
      controller.playerList(0).block.rowList.zipWithIndex.map{case (r,i)=>{
        rowToXML(r,i+1)
      }}
      }
    </game>
  }

  def rowToXML(r:Row, idx:Int) = <Row Ridx={idx.toString} rowColor={"#"+Integer.toHexString(r.colorName.getRGB()).substring(2)} strat={r.strat.toString}>{r.fieldList.map(f => {
    if(f.checkedState) {
      fieldToXML(f.fieldIdx)
    }
  })}</Row>
  def fieldToXML(idx:Int) = <Field Fidx ={idx.toString}/>
}