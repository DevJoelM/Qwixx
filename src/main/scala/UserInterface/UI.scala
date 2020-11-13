
package UserInterface

import GameplaySpecific.{Dice, Dices}
import PlayerSpecific.Player

/////////////////////////////////////////////////////////////
// FileName: UI.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 11.11.2020
// Last Modified On : 11.11.2020
/////////////////////////////////////////////////////////////

class UI () {

  val playerList = createPlayers()
  val dices = new Dices()

  def createPlayers():List[Player] = {
    var players: List[Player] = List()
    for(player <- 0 to 1){
      players = Player(player,"") :: players
    }
    return players.sortBy(_.ID)
  }

}
