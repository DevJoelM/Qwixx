package de.htwg.se.qwixx.model.gameComponent

import de.htwg.se.qwixx.model.gameComponent.gameBaseImpl.Dice

import scala.swing.Color

trait gameInterface {

  def throwDices(): Boolean

  def updateDiceCombinations(): List[((Color, Int), (Dice, Dice))]

}
