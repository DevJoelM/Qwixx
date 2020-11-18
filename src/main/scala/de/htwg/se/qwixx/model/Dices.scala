package de.htwg.se.model

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
  val colorsName = Array("White","White","Red","Yellow","Green","Blue")
  val defaultDices: Array[Dice] = throwDices(false)
  val coloredDices: Array[Dice] = throwDices(true)

  def initDices(): Unit ={
    throwDices(true)
    throwDices(true)
  }

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
      throwSpecificDices(coloredDices, 4)
    } else {
      throwSpecificDices(defaultDices, 2)
    }
  }
  def getDiceCombinations(): List[(Dice,Dice)] ={
    var comb :List[(Dice,Dice)] = List()
    for(dd <- defaultDices){
      for(dd_secound <- defaultDices){
        if(dd.ID != dd_secound.ID){
          comb = (dd,dd_secound) :: comb
        }
      }
      for(cd <- coloredDices){
        comb = (dd,cd) :: comb
      }
    }
    comb
  }
}


/*
 val dices: Array[Dice] = throwDices(null)

  def throwDices(existingDices:Array[Dice]): Array[Dice] = {
    val random = new Random
    val dices = new Array[Dice](6)
    for(diceIdx <- 0 to 5){
      if(existingDices==null) {
        dices(diceIdx) = new Dice(diceIdx, colorsHex(diceIdx),colorsName(diceIdx), random.nextInt(5) + 1)
      } else {
        existingDices(diceIdx) = new Dice(diceIdx, colorsHex(diceIdx),colorsName(diceIdx), random.nextInt(5) + 1)
      }
    }
    dices
  }

    val defaultDices: Array[Dice] = throwDices(null)




   def throwDices(colored:Boolean,existingDices:Array[Dice]): Array[Dice] = {
    val random = new Random
    val dices = new Array[Dice](6)
    for(diceIdx <- 0 to 5){
      if(existingDices==null) {
        dices(diceIdx) = new Dice(diceIdx, colorsHex(diceIdx),colorsName(diceIdx), random.nextInt(5) + 1)
      } else {
        existingDices(diceIdx) = new Dice(diceIdx, colorsHex(diceIdx),colorsName(diceIdx), random.nextInt(5) + 1)
      }
    }
    dices
  }
* */