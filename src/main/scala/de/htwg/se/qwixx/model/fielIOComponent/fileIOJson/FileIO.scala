package de.htwg.se.qwixx.model.fielIOComponent.fileIOJson

import com.google.inject.Guice
import de.htwg.se.qwixx.QwixxModule
import de.htwg.se.qwixx.controller.controllerComponent.ControllerInterface
import de.htwg.se.qwixx.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.qwixx.model.blockComponent.blockBaseImpl.Row
import de.htwg.se.qwixx.model.fielIOComponent.FileIOInterface
import play.api.libs.json.{JsValue, Json}

import java.awt.Color

import scala.io.Source

//JSON

class FileIO extends FileIOInterface{

  override def loadGame(): ControllerInterface = {
    val source: String = Source.fromFile("save_json.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val injector = Guice.createInjector(new QwixxModule)
    val controllerInt = injector.getInstance(classOf[ControllerInterface])

    val rowNodes = (json \\ "game")

    var s: List[Row] = List()
    for (r <- (rowNodes.toArray)) {

      val rowList: List[JsValue] = (r).as[List[JsValue]]
      for (rr <- rowList) {
        val rowIdx = (rr \\ "Ridx") (0).as[String].toInt
        val rowColor = (rr \\ "rowColor") (0).as[String]
        val strat = (rr \\ "strat") (0).as[String]
        val tempRow = new Row(rowIdx, Color.decode(rowColor), strat)

        for (f <- (rr \\ "checked").toArray) {
          val fieldList: List[JsValue] = (f).as[List[JsValue]]
          for(ff <- fieldList) {
            tempRow.checkField((ff \\ "Fidx") (0).as[String].toInt)
          }
        }
        s = tempRow :: s
      }
    }
    controllerInt.getController().playerList(0).block.rowList = s.toArray
    controllerInt
  }

  override def saveGame(controller: Controller): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("save_json.json"))
    pw.write(Json.prettyPrint(gameToJson(controller)))
    pw.close
  }

  def gameToJson(controller: Controller)={
    Json.obj(
      "game" -> Json.toJson(
        controller.playerList(0).block.rowList.zipWithIndex.map{case (r,i)=>{
          rowToJson(r,i+1)
        }}
      )
    )
  }

  def rowToJson(r:Row, idx:Int)={

      "row" -> Json.obj(
        "Ridx" -> r.rowIdx.toString,
        "rowColor" -> ("#"+Integer.toHexString(r.colorName.getRGB()).substring(2)),
        "strat" -> r.strat,
        "checked" ->Json.toJson(
          for {
            f <- r.fieldList if f.checkedState
          } yield {
              Json.obj(
                "Fidx" -> f.fieldIdx.toString
              )
          }
        )
      )
  }
}