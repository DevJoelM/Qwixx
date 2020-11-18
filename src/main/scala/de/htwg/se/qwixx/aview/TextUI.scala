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

  def scanCommands(): Boolean = { //param.: bool //return: String //rec
    println()
    print(visualizePlayground())
    breakable {
      while (true) {
        val cmd = scala.io.StdIn.readLine().split(" ")
        if (cmd(0) == "") {
          break
        } else if (cmd.size == 1 && cmd(0) == "t") {
          controller.throwDices()
        } else if (cmd.size == 1 && cmd(0) == "exit") {
          break
        } else if (cmd(2) == "l") {
          print(controller.lockRow(cmd(0).toInt - 1,cmd(1).toInt - 1)._2)
        } else {
          val res = controller.isFieldCheckable(cmd(0).toInt - 1,cmd(1).toInt - 1,cmd(2).toInt - 1)
          if(res._1) {
            print(controller.checkField(cmd(0).toInt - 1, cmd(1).toInt - 1, cmd(2).toInt - 1)._2)
          } else {
            print(res._2)
          }
        }
        print("\n")
        if(controller.checkIfGameIsEnded()){
          print(visualizePlayground())
          print("\nGame Finished!")
          break()
        } else {
          print(visualizePlayground())
        }
      }
    }
    true
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

/*
 def scanCommands(): Boolean = { //param.: bool //return: String //rec
    println()
    print(visualizePlayground())
    breakable {
      while (true) {
        val cmd = scala.io.StdIn.readLine().split(" ")
        if (cmd(0) == "") {
          break
        } else if (cmd.size == 1 && cmd(0) == "t") {
          dices.ThrowDice(dices.dices)
        } else if (cmd.size == 1 && cmd(0) == "exit") {
          break
        } else if (cmd(2) == "l") {
          playerList(cmd(0).toInt - 1).block.rawList(cmd(1).toInt - 1).lockRaw();
        } else {
          playerList(cmd(0).toInt - 1).block.rawList(cmd(1).toInt - 1).checkField(cmd(2).toInt - 1)
        }
        print("\n")
        if(checkIfGameIsEnded()){
          print(visualizePlayground())
          print("\nGame Finished!")
          break()
        } else {
          print(visualizePlayground())
        }
      }
    }
    true
  }
* */