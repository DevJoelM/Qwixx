package de.htwg.se.qwixx.aview

import de.htwg.se.qwixx.controller.Controller
import scala.util.control.Breaks._

/////////////////////////////////////////////////////////////
// FileName: TextUI.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 11.11.2020
// Last Modified On : 18.11.2020
/////////////////////////////////////////////////////////////

class TextUI(controller: Controller) {

  def processInputCommands(pcmd:String, stringBuilder: StringBuilder): String = {
    breakable {
      val cmd = pcmd.split(" ")
      if (cmd(0) == "" || cmd.size == 2 || cmd.size > 3) {
        stringBuilder.append("\nInput not allowed!\n")
        break
      } else if (cmd.size == 1 && cmd(0) == "t") {
        controller.throwDices()
      } else if (cmd(2) == "l") {
        stringBuilder.append("\n" + controller.lockRow(cmd(0).toInt - 1,cmd(1).toInt - 1)._2)
      } else {
        val res = controller.isFieldCheckable(cmd(0).toInt - 1,cmd(1).toInt - 1,cmd(2).toInt - 1)
        if(res._1) {
          stringBuilder.append("\n" + controller.checkField(cmd(0).toInt - 1, cmd(1).toInt - 1, cmd(2).toInt - 1)._2)
        } else {
          stringBuilder.append(res._2)
        }
      }
      stringBuilder.append("\n")
      if(controller.checkIfGameIsEnded()){
        stringBuilder.append(visualizePlayground())
        stringBuilder.append("\nGame Finished!")
        break()
      } else {
        stringBuilder.append(visualizePlayground())
      }
    }
    stringBuilder.toString()
  }

  /**
   * Method to return the playing field from Qwixx as a string.
   * @return Multiline string for output.
   */
  def visualizePlayground(): String = {
    var sb = new StringBuilder()
    val pad = "   "
    for(c <- controller.playerList){
      println()
      sb.append("".padTo(70,"─").mkString+"\n")
      sb.append(("PLAYER " + (c.ID+1).toString))
      sb.append("".padTo(50," ").mkString + "Points: "+ controller.getPlayerPoints(c.ID) + "\n")
      sb.append("".padTo(70,"─").mkString+"\n")
      sb.append("Index".padTo(8," ").mkString+"|  ")
      sb.append("1    2    3    4    5    6    7    8     9   10   11   L\n")
      sb.append("".padTo(70,"─").mkString+"\n")
      for (row <- c.block.rowList){
        sb.append(row.colorName.padTo(8," ").mkString+"|  ")
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