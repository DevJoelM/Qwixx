package de.htwg.se.qwixx.controller.controllerComponent

import de.htwg.se.qwixx.model.gameComponent.gameBaseImpl.{Dice, Player}
import de.htwg.se.qwixx.util.Observable

import scala.swing.{Color, Publisher}

trait ControllerInterface extends Observable with Publisher{

  def updateGame: Unit
  def checkIfGameIsEnded: Boolean
  def checkField(playerID:Int, rowID:Int, fieldID: Int): (Boolean,String)
  def isFieldCheckable(playerID:Int, rowID:Int, fieldID:Int) : (Boolean,String)
  def isCombinationCheckable(playerID:Int, rowID:Int, fieldID:Int): (Boolean, String)
  def lockRow(playerID:Int, rowID:Int): (Boolean,String)
  def throwDices(): Unit
  def getDicesList(): List[Dice]
  def getDiceCombinations(): List[((Color,Int),(Dice,Dice))]
  def createPlayers(strat: String):List[Player]
  def getPlayerName(playerID:Int):String
  def getPlayerPoints(playerID:Int):Int
  def getPlayerSplittedPoints(playerID:Int):List[(String,Int)]

}
