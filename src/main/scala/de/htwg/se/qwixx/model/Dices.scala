package de.htwg.se.qwixx.model

import de.htwg.se.qwixx.util.GameColors

import scala.collection.mutable.ListBuffer
import scala.swing.Color
import scala.util.Random

/////////////////////////////////////////////////////////////
// FileName: Dices.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On: 06.11.2020
// Last Modified On : 18.11.2020
/////////////////////////////////////////////////////////////

class Dices {

  val cDiceCount: Int = 4
  val dDiceCount: Int = 2

  val colorsName = Array("White","White","Color1","Color2","Color3","Color4")
  val defaultDices: Array[Dice] = Array.ofDim[Dice](2)
  val coloredDices: Array[Dice] = Array.ofDim[Dice](4)
  throwDices()

  def throwDices(): Boolean = {
    val random = new Random

    def throwSpecificDices(dices:Array[Dice],len:Int): Array[Dice] = {
      val newDices = new Array[Dice](len)
      for (diceIdx <- 0 to len-1) {
        val idxOffset = getIndexOffset(len)
        val nd = Dice(diceIdx+idxOffset, GameColors.DICE_COLORS_REV(diceIdx+idxOffset), colorsName(diceIdx+idxOffset), random.nextInt(6) + 1)
        if(dices != Option(null)) {
          dices(diceIdx) = nd
        }
      }
      newDices
    }

    def getIndexOffset(len:Int): Int ={
      if(len == 2) 0 else 2
    }
    throwSpecificDices(coloredDices, cDiceCount)
    throwSpecificDices(defaultDices, dDiceCount)
    true
  }

  def updateDiceCombinations(): List[((Color,Int),(Dice, Dice))] = {
    var combinations = new ListBuffer[((Color, Int), (Dice, Dice))]()
    for(c <- coloredDices){
      combinations += (((c.color,defaultDices(0).value + defaultDices(1).value),(defaultDices(0), defaultDices(1))))
    }
    for (d <- defaultDices){
      for(c <- coloredDices){
        combinations += (((c.color,d.value + c.value),(d, c)))
      }
    }
    combinations.toList
  }

}

//combinations = ((dd.color,dd.value+dd_secound.value),(dd,dd_secound)) :: combinationsor(dd <- defaultDices){
//  for(dd_secound <- defaultDices){
//    if(dd.ID != dd_secound.ID){
//      combinations = ((dd.color,dd.value+dd_secound.value),(dd,dd_secound)) :: combinations
//    }
//  }
//  for(cd <- coloredDices){
//    combinations = ((cd.color,dd.value+cd.value),(dd,cd)) :: combinations
//  }
//}

/*
*
 var combinations: List[((Color, Int), (Dice, Dice))] = List()
    for(c <- GameColors.COLORED_DICE_COLORS){
      combinations = ((c,defaultDices(0).value + defaultDices(1).value),(defaultDices(0), defaultDices(1))) :: combinations
    }
    for (d <- defaultDices){
      for(c <- coloredDices){
        combinations = ((c.color,d.value + c.value),(d, c)) :: combinations
      }
    }
    combinations
* */