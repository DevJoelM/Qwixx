
package de.htwg.se.qwixx.controller
import de.htwg.se.qwixx.model.{Dice, Dices, Player}
import de.htwg.se.qwixx.util.Observable

/////////////////////////////////////////////////////////////
// FileName: Controller.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 11.11.2020
// Last Modified On : 18.11.2020
/////////////////////////////////////////////////////////////

class Controller() extends Observable {

  val playerList = createPlayers()
  val dices = new Dices()

  //Runtime
  def checkIfGameIsEnded(): Boolean = {
    var ended = false
    for(p <- playerList){
      if(p.block.getLockedRows() == 2){
        ended = true
      }
    }
    ended
  }

  //Field commands
  def checkField(playerID:Int, rowID:Int, fieldID: Int): (Boolean,String) = {
    val checkable = isCombinationCheckable(playerID,rowID,fieldID)
    if(checkable._1){
      val row = playerList(playerID).block.rowList(rowID)
      row.checkField(fieldID)
      row.updateFields()
    }
    checkable
  }

  def isFieldCheckable(playerID:Int, rowID:Int, fieldID:Int) : (Boolean,String) = {
    val openFields = playerList(playerID).block.rowList(rowID).getOpenFields()
    val row = playerList(playerID).block.rowList(rowID)
    if(openFields.find(_._1 == fieldID)==None){
      (false, String.format("Row (%s), Field (%s) is not checkable!", rowID.toString,
        row.fieldList(rowID).value.toString))
    } else {
      (true, String.format("Row (%s), Field (%s) is checkable!", rowID.toString,
        row.fieldList(rowID).value.toString))
    }
  }

  def isCombinationCheckable(playerID:Int, rowID:Int, fieldID:Int): (Boolean, String)= {
    dices.updateDiceCombinations()
    val row = playerList(playerID).block.rowList(rowID)
    val checkable = isFieldCheckable(playerID,rowID,fieldID)
    if(checkable._1){
      val passedCombination = (row.colorName,row.fieldList(fieldID).value)
      val cCombination = dices.combinations.find(_._1 == passedCombination)
      val dCombination = dices.combinations.find(_._1 ==
        (dices.defaultDices(0).colorName,row.fieldList(fieldID).value))
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

  //Row commands
  def lockRow(playerID:Int, rowID:Int): (Boolean,String) = {
    playerList(playerID).block.rowList(rowID).lockRow()
  }

  //Dice commands
  def throwDices(): Unit = {
    dices.throwDices()
    dices.throwDices()
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
