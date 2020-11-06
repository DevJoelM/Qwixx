package main.scala

import scala.util.Random

/////////////////////////////////////////////////////////////
// FileName: Dices.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On: 06.11.2020
// Last Modified On : 06.11.2020
/////////////////////////////////////////////////////////////

class Dices {

  val colors = Array("#FFFFFF","#FFFFFF","#FF0000","#FFFF00","#00FF00","#0000FF")
  val dices: List[Dice] = ThrowDice()

  def ThrowDice(): List[Dice] = {
    val random = new Random
    var d: List[Dice] = List()
    for(dice <- 0 to 5){
      d = Dice(dice, colors(dice), random.nextInt(5)+1) :: d
    }
    d
  }
}
