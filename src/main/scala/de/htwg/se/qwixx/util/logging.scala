package de.htwg.se.qwixx.util

/////////////////////////////////////////////////////////////
// FileName: Logger.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 04.12.2020
// Last Modified On : 04.12.2020
/////////////////////////////////////////////////////////////

object Logger {
  def info(msg: String): Unit = println(s"INFO: $msg")
  def error(msg: String): Unit = println(s"ERROR: $msg")
}
