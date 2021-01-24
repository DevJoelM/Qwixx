
package de.htwg.se.qwixx.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.qwixx.controller.controllerComponent.ControllerInterface
import de.htwg.se.qwixx.model.blockComponent.blockBaseImpl.Block

import de.htwg.se.qwixx.model.gameComponent.gameBaseImpl.{Dice, Dices, Player}
import de.htwg.se.qwixx.util.GameState

import scala.swing.Color
import scala.util.{Failure, Success, Try}

/////////////////////////////////////////////////////////////
// FileName: Controller.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 11.11.2020
// Last Modified On : 18.11.2020
/////////////////////////////////////////////////////////////

class Controller() extends ControllerInterface {

  var playerList = createPlayers("classic")
  val dices = new Dices()

  val undoManager = new UndoManager
  val gameState = GameState

  /**
   * This function updates and reloads the entire Game.
   */
  def updateGame(): Unit = {
    notifyObservers
  }

  /**
   * This function checks if the Game ended based on the locked Rows of an Player.
   *
   * @return
   */
  def checkIfGameIsEnded(): Boolean = {
    var ended = false
    for (p <- playerList) {
      if (p.block.getLockedRows() == 2) {
        gameState.handle(false)
        ended = true
      }
    }
    ended
  }

  /**
   * This function checks a field depending on the player, the row and the field.
   *
   * @param playerID
   * @param rowID
   * @param fieldID
   * @return
   */
  def checkField(playerID: Int, rowID: Int, fieldID: Int): (Boolean, String) = {
    val checkable = isCombinationCheckable(playerID, rowID, fieldID)
    if (checkable._1) {
      val row = playerList(playerID).block.rowList(rowID)
      undoManager.doCheck(new SetCommand(this, playerID, rowID, fieldID))
      row.updateFields()
    }
    notifyObservers
    checkable
  }

  /**
   * This function checks whether a field can be checked.
   * This depends on whether a field is free and not blocked by another.
   *
   * @param playerID
   * @param rowID
   * @param fieldID
   * @return
   */
  def isFieldCheckable(playerID: Int, rowID: Int, fieldID: Int): (Boolean, String) = {
    val openFields = playerList(playerID).block.rowList(rowID).getAllFields()
    val row = playerList(playerID).block.rowList(rowID)
    Try(openFields(fieldID)) match {
      case Success(value) => {
        val rowIdStr = value._1.toString
        if (openFields.find(_._1 == fieldID) == None) {
          (false, String.format("Row (%s), Field (%s) is not checkable!", rowIdStr,
            fieldID.toString))
        } else {
          (true, String.format("Row (%s), Field (%s) is checkable!", rowIdStr,
            row.fieldList(rowID).value.toString))
        }
      }
      case Failure(exception) =>
        (false, String.format("Row (%s), Field (%s) is not an option!", rowID.toString,
          exception.getMessage))
    }
  }

  /**
   * This function checks whether a dice combination is possible for a selected field or not.
   *
   * @param playerID
   * @param rowID
   * @param fieldID
   * @return
   */
  def isCombinationCheckable(playerID: Int, rowID: Int, fieldID: Int): (Boolean, String) = {
    dices.updateDiceCombinations()
    val row = playerList(playerID).block.rowList(rowID)
    val checkable = isFieldCheckable(playerID, rowID, fieldID)
    if (checkable._1) {
      val passedCombination = (row.colorName, row.fieldList(fieldID).value)
      val comb = dices.updateDiceCombinations()
      val dCombination = comb.find(_._1 == passedCombination)

      if (dCombination != None) {
        return (true, String.format("Combination %s worked!", dCombination.get._1))
      }
      else {
        return (false, String.format("Combination %s doesn't work!", passedCombination))
      }
    }
    (checkable._1, checkable._2)
  }

  /**
   * This function closes a row, provided that the conditions are met.
   *
   * @param playerID
   * @param rowID
   * @return
   */
  def lockRow(playerID: Int, rowID: Int): (Boolean, String) = {
    notifyObservers
    if (checkIfGameIsEnded()) {
      System.exit(0)
    }
    playerList(playerID).block.rowList(rowID).lockRow()
  }

  /**
   * This function re-rolls all dice.
   */
  def throwDices(): Unit = {
    dices.throwDices()
    dices.throwDices()
    notifyObservers
  }

  /**
   * This function returns all dices.
   *
   * @return
   */
  def getDicesList(): List[Dice] = {
    dices.defaultDices.toList ++ dices.coloredDices.toList
  }

  /**
   * This function returns all possible dice combinations.
   *
   * @return
   */
  def getDiceCombinations(): List[((Color, Int), (Dice, Dice))] = {
    dices.updateDiceCombinations()
  }

  /**
   * This function creates new players based on a given block strategy.
   *
   * @param strat
   * @return
   */
  def createPlayers(strat: String): List[Player] = {
    var players: List[Player] = List()
    for (player <- 0 to 0) {
      players = Player(player, "", new Block(strat)) :: players
    }
    players.sortBy(_.ID)
  }

  /**
   * This function returns all players as a List.
   *
   * @return
   */
  def getPlayerList(): List[Player] = {
    playerList
  }

  /**
   * This function returns the Playername by ID.
   *
   * @param playerID
   * @return
   */
  def getPlayerName(playerID: Int): String = {
    playerList(playerID).name
  }

  /**
   * This function returns the commulated points of an player by ID.
   *
   * @param playerID
   * @return
   */
  def getPlayerPoints(playerID: Int): Int = {
    playerList(playerID).block.getCommulatedPoints()
  }

  /**
   * This function returns the points of an Player splitted by color.
   *
   * @param playerID
   * @return
   */
  def getPlayerSplittedPoints(playerID: Int): List[(String, Int)] = {
    playerList(playerID).block.getSplittedPoints()
  }

  /**
   * This function returns the UndoManager.
   *
   * @return
   */
  def getUndoManager(): UndoManager = {
    this.undoManager
  }

  /**
   * This function returns the GameState of the Game.
   *
   * @return
   */
  override def getGameState() = gameState

  /**
   * This function returns itself (Controller).
   *
   * @return
   */
  override def getController(): Controller = this

  /**
   * This function saves the current Game as XML.
   */
  def saveGameXml(): Unit = {
    import de.htwg.se.qwixx.model.fielIOComponent.fileIOxml.FileIO
    val fio = new FileIO
    fio.saveGame(this)
  }

  /**
   * This function loads the last saved Game as XML.
   */
  def loadGameXml(): Unit = {
    import de.htwg.se.qwixx.model.fielIOComponent.fileIOxml.FileIO
    val fio = new FileIO
    loadGame(fio.loadGame().getController())
  }

  /**
   * This function saves the current Game as JSON.
   */
  def saveGameJson(): Unit = {
    import de.htwg.se.qwixx.model.fielIOComponent.fileIOJson.FileIO
    val fio = new FileIO
    fio.saveGame(this)
  }

  /**
   * This function loads the last saved Game as JSON.
   */
  def loadGameJson(): Unit = {
    import de.htwg.se.qwixx.model.fielIOComponent.fileIOJson.FileIO
    val fio = new FileIO
    loadGame(fio.loadGame().getController())
  }

  /**
   * This function loads the Game based on given XML/JSON.
   *
   * @param contr
   */
  def loadGame(contr: Controller): Unit = {
    playerList(0).block.rowList = contr.playerList(0).block.rowList sortBy (_.rowIdx)
    for (r <- playerList(0).block.rowList) {
      r.updateFields()
    }
    updateGame()
  }

}
