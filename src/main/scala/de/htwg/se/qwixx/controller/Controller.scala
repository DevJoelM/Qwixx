
package de.htwg.se.qwixx.controller

import de.htwg.se.qwixx.model.{Dice, Dices, Player}

/////////////////////////////////////////////////////////////
// FileName: Controller.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 11.11.2020
// Last Modified On : 11.11.2020
/////////////////////////////////////////////////////////////

class Controller () {

  val playerList = createPlayers()
  val dices = new Dices()

  def createPlayers():List[Player] = {
    var players: List[Player] = List()
    for(player <- 0 to 0){
      players = Player(player,"") :: players
    }
    players.sortBy(_.ID)
  }
  def checkIfGameIsEnded(): Boolean ={
    var ended = false
    for(p <- playerList){
      if(p.block.getLockedRaws() == 2){
        ended = true
      }
    }
    ended
  }
  //Game commands
  def checkField(playerID:Int, rawID:Int, fieldID: Int): (Boolean,String) ={
    playerList(playerID).block.rowList(rawID).checkField(fieldID)
  }
  def lockRaw(playerID:Int, rawID:Int): (Boolean,String) ={
    playerList(playerID).block.rowList(rawID).lockRow()
  }
  def throwDices(): Unit ={
    dices.throwDices(false)
    dices.throwDices(true)
  }
  def getDicesList(): List[Dice] ={
    dices.defaultDices.toList++dices.coloredDices.toList
  }
  //Player Data
  def getPlayerName(playerID:Int):String={
    playerList(playerID).name
  }
  def getPlayerPoints(playerID:Int):Int={
    playerList(playerID).block.getCommulatedPoints()
  }
  def getPlayerSplittedPoints(playerID:Int):List[(String,Int)]={
    playerList(playerID).block.getSplittedPoints()
  }

}
