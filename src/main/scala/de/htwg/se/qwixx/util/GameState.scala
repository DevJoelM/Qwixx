package de.htwg.se.qwixx.util

import de.htwg.se.qwixx.util.Logger.info

/////////////////////////////////////////////////////////////
// FileName: GameState.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 04.12.2020
// Last Modified On : 04.12.2020
/////////////////////////////////////////////////////////////

object GameState {

  var state = gameOnState

  def handle(e: Boolean) = {
    e match {
      case true => state = gameOnState
      case false => state = gameOffState
    }
  }

  def gameOnState: Boolean = {
    println(info("Game is currently running!"))
    true
  }

  def gameOffState: Boolean = {
    println(info("Game stopped!"))
    false
  }

}

