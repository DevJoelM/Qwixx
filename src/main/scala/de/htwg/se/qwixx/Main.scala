package de.htwg.se.qwixx

import de.htwg.se.qwixx.aview.{TextUI, UIType}
import de.htwg.se.qwixx.controller.Controller

import scala.io.Source

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
    runInitializationScreen()
    val ui = UIType(scala.io.StdIn.readLine(), controller)
    controller.updateGame()

    while (!controller.checkIfGameIsEnded()) {
      ui.isInstanceOf[TextUI] match {
        case true => print(ui.run(scala.io.StdIn.readLine()))
        case false => ui.run(null)
      }
    }

  }

  def runInitializationScreen(): Unit = {

    for (line <- Source.fromFile("images/asciiTitle.txt").getLines) {
      println()
      print(line)
    }
    print("\n\nTo run, enter <T/t> for TextUI or <G/g> for GraphicUI. \n(T/G)?: ")

  }
}