package de.htwg.se.qwixx.model.gameComponent

import de.htwg.se.qwixx.model.gameComponent.gameBaseImpl.Dice

import scala.swing.Color

/////////////////////////////////////////////////////////////
// FileName: gameInterface.scala
// FileType: Scala Source file (Trait)
// Author: Joel Merath, Tim Disch
// Created On : 25.12.2020
// Last Modified On : 20.01.2021
/////////////////////////////////////////////////////////////

trait gameInterface {

  def throwDices(): Boolean

  def updateDiceCombinations(): List[((Color, Int), (Dice, Dice))]

}
