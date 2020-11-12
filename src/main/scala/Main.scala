package main.scala

import PlayerSpecific.Block
import UserInterface._

/////////////////////////////////////////////////////////////
// FileName: Main.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 29.10.2020
// Last Modified On : 29.10.2020
/////////////////////////////////////////////////////////////

object Main {

  def main(args: Array[String]): Unit = {

    val n = new TextUI()
    n.visualizePlayground();
    n.scanCommands()

  }
}