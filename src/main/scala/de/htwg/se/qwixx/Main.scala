package de.htwg.se.qwixx

import de.htwg.se.qwixx.aview.{TextUI, UIType}
import de.htwg.se.qwixx.controller.Controller

/////////////////////////////////////////////////////////////
// FileName: Main.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 29.10.2020
// Last Modified On : 18.11.2020
/////////////////////////////////////////////////////////////

object Main {

  def main(args: Array[String]): Unit = {

    val controller = new Controller
    //runInitializationScreen()

    val tui = UIType("T", controller)
    val gui = UIType("G", controller)

    controller.updateGame()

    while (controller.gameState.state) {
      tui.isInstanceOf[
        TextUI] match {
        case true => print(tui.run(scala.io.StdIn.readLine()))
      }
    }

  }

  //def runInitializationScreen(): Unit = {
  //
  //  for (line <- Source.fromFile("images/asciiTitle.txt").getLines) {
  //    println()
  //    print(line)
  //  }
  //  info("\n\nTo run, enter <T/t> for TextUI or <G/g> for GraphicUI. \n(T/G)?: ")
  //
  //}
}