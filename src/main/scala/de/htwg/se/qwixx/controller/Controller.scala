
package de.htwg.se.qwixx.controller
import de.htwg.se.qwixx.model.{Dice, Dices, Player}

/////////////////////////////////////////////////////////////
// FileName: Controller.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 11.11.2020
// Last Modified On : 18.11.2020
/////////////////////////////////////////////////////////////

class Controller() {

  val playerList = createPlayers()
  val dices = new Dices()

  //Runtime
  def checkIfGameIsEnded(): Boolean = {
    var ended = false
    for(p <- playerList){
      if(p.block.getLockedRaws() == 2){
        ended = true
      }
    }
    ended
  }

  //Field commands
  def checkField(playerID:Int, rawID:Int, fieldID: Int): (Boolean,String) = {
    val checkable = isCombinationCheckable(playerID,rawID,fieldID)
    if(checkable._1){
      val raw = playerList(playerID).block.rowList(rawID)
      raw.checkField(fieldID)
      raw.updateFields()
    }
    checkable
  }

  def isFieldCheckable(playerID:Int, rowID:Int, fieldID:Int) : (Boolean,String) = {
    val openFields = playerList(playerID).block.rowList(rowID).getOpenFields()
    val raw = playerList(playerID).block.rowList(rowID)
    if(openFields.find(_._1 == fieldID)==None){
      (false, String.format("Row (%s), Field (%s) is not checkable!", rowID.toString,
        raw.fieldList(rowID).value.toString))
    } else {
      (true, String.format("Row (%s), Field (%s) is checkable!", rowID.toString,
        raw.fieldList(rowID).value.toString))
    }
  }

  def isCombinationCheckable(playerID:Int, rowID:Int, fieldID:Int): (Boolean, String)= {
    dices.updateDiceCombinations()
    val raw = playerList(playerID).block.rowList(rowID)
    val checkable = isFieldCheckable(playerID,rowID,fieldID)
    if(checkable._1){
      val passedCombination = (raw.colorName,raw.fieldList(fieldID).value)
      val cCombination = dices.combinations.find(_._1 == passedCombination)
      val dCombination = dices.combinations.find(_._1 ==
        (dices.defaultDices(0).colorName,raw.fieldList(fieldID).value))
      if(dCombination!=None){
        return (true, String.format("Combination %s works!",dCombination.get._1))
      } else  if(cCombination!=None) {
        return (true, String.format("Combination %s works!",cCombination.get._1))
      } else {
        return (false, String.format("Combination %s doesn't work!",passedCombination))
      }
    }
    (checkable._1,checkable._2)
  }

  //Raw commands
  def lockRow(playerID:Int, rowID:Int): (Boolean,String) = {
    playerList(playerID).block.rowList(rowID).lockRow()
  }

  //Dice commands
  def throwDices(): Unit = {
    dices.throwDices(false)
    dices.throwDices(true)
    dices.updateDiceCombinations()
  }

  def getDicesList(): List[Dice] = {
    dices.defaultDices.toList++dices.coloredDices.toList
  }

  def getDiceCombinations(): List[((String,Int),(Dice,Dice))] = {
    dices.updateDiceCombinations()
    dices.combinations
  }

  //Player commands
  def createPlayers():List[Player] = {
    var players: List[Player] = List()
    for(player <- 0 to 0){
      players = Player(player,"") :: players
    }
    players.sortBy(_.ID)
  }

  def getPlayerName(playerID:Int):String = {
    playerList(playerID).name
  }

  def getPlayerPoints(playerID:Int):Int = {
    playerList(playerID).block.getCommulatedPoints()
  }

  def getPlayerSplittedPoints(playerID:Int):List[(String,Int)] = {
    playerList(playerID).block.getSplittedPoints()
  }

}