package de.htwg.se.qwixx

import com.google.inject.Guice
import de.htwg.se.qwixx.aview.GraphicUI.GraphicUI
import de.htwg.se.qwixx.aview.TextUI
import de.htwg.se.qwixx.controller.controllerComponent.ControllerInterface

/////////////////////////////////////////////////////////////
// FileName: Main.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 29.10.2020
// Last Modified On : 18.11.2020
/////////////////////////////////////////////////////////////

object Main {

  def main(args: Array[String]): Unit = {

    val UIType: Boolean = if (System.getenv("UI_TYPE").equals("gui")) true else false

    val injector = Guice.createInjector(new QwixxModule)
    val controller = injector.getInstance(classOf[ControllerInterface])

    val tui = new TextUI(controller)
    if(UIType){
      val gui = new GraphicUI(controller)
    }

    controller.updateGame

    while (!controller.checkIfGameIsEnded) {
      print(tui.run(scala.io.StdIn.readLine()))
    }

  }
}