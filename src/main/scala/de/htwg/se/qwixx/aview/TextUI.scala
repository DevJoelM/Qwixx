package de.htwg.se.qwixx.aview

import de.htwg.se.qwixx.controller.controllerComponent.ControllerInterface
import de.htwg.se.qwixx.util.Observer

import scala.util.control.Breaks._
import scala.util.control.Exception.allCatch

/////////////////////////////////////////////////////////////
// FileName: TextUI.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 11.11.2020
// Last Modified On : 18.11.2020
/////////////////////////////////////////////////////////////

class TextUI(controller: ControllerInterface) extends Observer with UIType {

  controller.add(this)

  def update: Unit = {
    print(visualizePlayground())
  }

  override def run (input:String): String = {
    processInputCommands(input)
  }

  def toInt(s: String): Option[Int] = allCatch.opt(s.toInt)

  /**
   * Method to returns a message to a given command.
   * @return Message
   */
  def processInputCommands(pcmd:String): String = {
    val strBuilder = new StringBuilder
    breakable {
      val cmd = pcmd.split(" ")

      var cmd0Int: Option[Int] = None
      var cmd1Int: Option[Int] = None
      var cmd2Int: Option[Int] = None

      if(cmd.size == 3) {
        cmd0Int = toInt(cmd(0))
        cmd1Int = toInt(cmd(1))
        cmd2Int = toInt(cmd(2))
      }

      //-- INPUTS ------------------------------------------------------
      if (cmd(0) == "" || cmd.size == 2 || cmd.size > 3) {
        strBuilder.append("\nInput not allowed!\n")
        break
      } else if (cmd.size == 1 && cmd(0) == "t") {
        controller.throwDices()}
      else if (cmd.size == 1 && cmd(0) == "loadxml") {
        controller.getController().loadGameXml()}
        else if (cmd.size == 1 && cmd(0) == "savexml") {
        controller.getController().saveGameXml()
      }
      else if (cmd.size == 1 && cmd(0) == "loadjson") {
        controller.getController().loadGameJson()
        }
      else if (cmd.size == 1 && cmd(0) == "savejson") {
        controller.getController().saveGameJson()
      }
      else if (cmd.size == 1 && cmd(0) == "undo") {
        controller.getUndoManager().undoCheck
        controller.updateGame
      } else if (cmd.size == 1 && cmd(0) == "redo") {
        controller.getUndoManager().redoStep
        controller.updateGame
      }  else if (cmd.size == 1 && cmd(0) == "exit") {
        controller.getGameState().handle(false)
        break()
      }else if (cmd(2) == "l") {
        strBuilder.append("\n" + controller.lockRow(cmd0Int.get - 1,cmd1Int.get - 1)._2)
      }
      //----------------------------------------------------------------
      else {
        val res = controller.isFieldCheckable(cmd0Int.get - 1,cmd1Int.get - 1,cmd2Int.get - 1)
        if(res._1) {
          strBuilder.append("\n" + controller.checkField(cmd0Int.get - 1, cmd1Int.get - 1, cmd2Int.get - 1)._2)
          break
        } else {
          strBuilder.append(res._2)
        }
      }
      strBuilder.append("\n")
      if(controller.checkIfGameIsEnded){
        strBuilder.append("\nGame Finished!")
        break()
      }
    }
    strBuilder.append("\n")
    strBuilder.toString()
  }

  /**
   * Method to return the playing field from Qwixx as a string.
   * @return Multiline string for output.
   */
  def visualizePlayground(): String = {
    val sb = new StringBuilder()
    sb.clear()
    val pad = "   "
    for(c <- controller.getPlayerList()){
      println()
      sb.append("".padTo(70,"─").mkString+"\n")
      sb.append(("PLAYER " + (c.ID+1).toString))
      sb.append("".padTo(50," ").mkString + "Points: "+ controller.getPlayerPoints(c.ID) + "\n")
      sb.append("".padTo(70,"─").mkString+"\n")
      sb.append("Index".padTo(8," ").mkString+"|  ")
      sb.append("1    2    3    4    5    6    7    8     9   10   11   L\n")
      sb.append("".padTo(70,"─").mkString+"\n")
      var rCount = 1
      for (row <- c.block.rowList){
        sb.append(rCount.toString.padTo(8," ").mkString+"|  ")
        for (field <- row.fieldList){
          if(field.blockedState){
            sb.append("⬜".padTo(2," ").mkString + pad)
          } else if(!field.checkedState){
            sb.append(field.value.toString.padTo(2," ").mkString + "   ")
          } else {
            sb.append("⬛".padTo(2," ").mkString + pad)
          }
        }
        if(row.locked){
          sb.append("\uD83D\uDD12".padTo(3," ").mkString + pad)
        } else {
          sb.append("\uD83D\uDD13".padTo(3," ").mkString + pad)
        }
        rCount += 1
        sb.append("\n")
      }
    }
    sb.append("".padTo(70,"─").mkString+"\n")
    for(dice <- controller.getDicesList()){
      sb.append(dice.colorName+":"+dice.value+pad)
    }
    sb.toString()
  }
}