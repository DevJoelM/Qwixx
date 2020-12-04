
package de.htwg.se.qwixx.controller

import de.htwg.se.qwixx.model.{Block, Dice, Dices, Player}
import de.htwg.se.qwixx.util.Logger.info
import de.htwg.se.qwixx.util.{GameState, Observable}

/////////////////////////////////////////////////////////////
// FileName: Controller.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 11.11.2020
// Last Modified On : 18.11.2020
/////////////////////////////////////////////////////////////

class Controller() extends Observable {

  var playerList = createPlayers("classic")
  val dices = new Dices()

  val undoManager = new UndoManager
  val gameState = GameState

  //Runtime
  def updateGame(): Unit = {
    notifyObservers
  }

  def checkIfGameIsEnded(): Boolean = {
    var ended = false
    for(p <- playerList){
      if(p.block.getLockedRows() == 2){
        gameState.handle(false)
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
      //row.checkField(fieldID)
      undoManager.doCheck(new SetCommand(this,playerID,rowID,fieldID))
      row.updateFields()
    }
    notifyObservers
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
      val comb = dices.updateDiceCombinations()
      val cCombination = comb.find(_._1 == passedCombination)
      val dCombination = comb.find(_._1 ==
        (dices.defaultDices(0).colorName,row.fieldList(fieldID).value))
      if(dCombination!=None){
        return (true, String.format("Combination %s worked!",dCombination.get._1))
      } else  if(cCombination!=None) {
        return (true, String.format("Combination %s worked!",cCombination.get._1))
      } else {
        return (false, String.format("Combination %s doesn't work!",passedCombination))
      }
    }
    (checkable._1,checkable._2)
  }

  //Row commands
  def lockRow(playerID:Int, rowID:Int): (Boolean,String) = {
    notifyObservers
    playerList(playerID).block.rowList(rowID).lockRow()
  }

  //Dice commands
  def throwDices(): Unit = {
    dices.throwDices()
    dices.throwDices()
    notifyObservers
  }
  def getDicesList(): List[Dice] = {
    dices.defaultDices.toList++dices.coloredDices.toList
  }

  def getDiceCombinations(): List[((String,Int),(Dice,Dice))] = {
    dices.updateDiceCombinations()
  }

  //Player commands
  def createPlayers(strat: String):List[Player] = {
    var players: List[Player] = List()
    for(player <- 0 to 0){
      players = Player(player,"", new Block(strat)) :: players
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
