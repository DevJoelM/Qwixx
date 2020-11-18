package de.htwg.se.qwixx

import de.htwg.se.qwixx.aview.{GraphicUI, TextUI}
import de.htwg.se.qwixx.controller.Controller

import scala.io.Source

/////////////////////////////////////////////////////////////
// FileName: Main.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 29.10.2020
// Last Modified On : 29.10.2020
/////////////////////////////////////////////////////////////

object UserInterfaces extends Enumeration {
  type UserInterfaces = Value
  val TextUI = Value(1)
  val GraphicUI = Value(2)
}

object Main {

  def main(args: Array[String]): Unit = {
    val controller = new Controller
    runInitializationScreen() match {
      case 1 =>{
        val tui = new TextUI(controller)
        tui.scanCommands()
      }
      case 2 =>{
        new GraphicUI(controller)
      }
    }
  }

  def runInitializationScreen(): Int = {
    for (line <- Source.fromFile("images/asciiTitle.txt").getLines) {
      println()
      print(line)
    }
    val id = checkInput("t")
    id
  }

  def checkInput(default:String): Int ={
    print("\n\nTo run, enter <T/t> for TextUI or <G/g> for GraphicUI. \n(T/G)?: ")
    var cmd = ""
    if(default=="") {
       cmd = scala.io.StdIn.readLine()
    } else {
       cmd = default
    }
    var ui = 0
    if(cmd.equals("T")||cmd.equals("t")){
      ui = UserInterfaces.TextUI.id
      pr.info("TextUI will be initialized...")
    } else if(cmd.equals("G")||cmd.equals("g")){
      ui = UserInterfaces.GraphicUI.id
      pr.info("TextUI will be initialized...")
    } else {
      pr.error("Input not allowed!")
      ui = checkInput("")
    }
    ui
  }

}