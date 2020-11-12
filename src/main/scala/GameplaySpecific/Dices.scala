package GameplaySpecific

import scala.util.Random

/////////////////////////////////////////////////////////////
// FileName: Dices.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On: 06.11.2020
// Last Modified On : 06.11.2020
/////////////////////////////////////////////////////////////

class Dices {

  val colorsHex = Array("#FFFFFF","#FFFFFF","#FF0000","#FFFF00","#00FF00","#0000FF")
  val colorsName = Array("W","W","R","Y","G","B")
  val dices: Array[Dice] = ThrowDice(null)

  def ThrowDice(existingDices:Array[Dice]): Array[Dice] = {
    val random = new Random
    val dices = new Array[Dice](6)
    for(dice <- 0 to 5){
      if(existingDices==null) {
        dices(dice) = new Dice(dice, colorsHex(dice),colorsName(dice), random.nextInt(5) + 1)
      } else {
        existingDices(dice) = new Dice(dice, colorsHex(dice),colorsName(dice), random.nextInt(5) + 1)
      }
    }
    dices
  }
}
