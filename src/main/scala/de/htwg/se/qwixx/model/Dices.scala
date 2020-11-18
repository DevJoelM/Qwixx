package de.htwg.se.qwixx.model

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
  val colorsHex = Array("#FFFFFF","#FFFFFF","#FF0000","#FFFF00","#00FF00","#0000FF")
  val colorsName = Array("White","White","Red","Yellow","Green","Blue")
  val defaultDices: Array[Dice] = throwDices(false)
  val coloredDices: Array[Dice] = throwDices(true)
  var combinations: List[((String,Int),(Dice, Dice))] = List()

  def throwDices(colored:Boolean): Array[Dice] = {
    val random = new Random

    def throwSpecificDices(dices:Array[Dice],len:Int): Array[Dice] = {
      val newDices = new Array[Dice](len)
      for (diceIdx <- 0 to len-1) {
        val idxOffset = getIndexOffset(len)
        val nd = Dice(diceIdx+idxOffset, colorsHex(diceIdx+idxOffset), colorsName(diceIdx+idxOffset), random.nextInt(5) + 1)
        if (dices == null) {
          newDices(diceIdx) = nd
        } else if(dices != null) {
          dices(diceIdx) = nd
        }
      }
      newDices
    }

    def getIndexOffset(len:Int): Int ={
      if(len == 2) 0 else 2
    }

    if(colored){
      throwSpecificDices(coloredDices, cDiceCount)
    } else {
      throwSpecificDices(defaultDices, dDiceCount)
    }
  }

  def updateDiceCombinations(): Unit ={
    for(dd <- defaultDices){
      for(dd_secound <- defaultDices){
        if(dd.ID != dd_secound.ID){
          combinations = ((dd.colorName,dd.value+dd_secound.value),(dd,dd_secound)) :: combinations
        }
      }
      for(cd <- coloredDices){
        combinations = ((cd.colorName,dd.value+cd.value),(dd,cd)) :: combinations
      }
    }
  }

}