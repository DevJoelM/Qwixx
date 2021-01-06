package de.htwg.se.qwixx.model.blockComponent.blockBaseImpl

import de.htwg.se.qwixx.model.blockComponent.blockInterface
import de.htwg.se.qwixx.model.strategyComponent.strategyBaseImpl.RowsStrategy

/////////////////////////////////////////////////////////////
// FileName: Block.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 29.10.2020
// Last Modified On : 11.11.2020
/////////////////////////////////////////////////////////////

class Block (val strat: String) extends blockInterface{

  //Validate RowsStrategy
  val rowsStrategy = RowsStrategy
  rowsStrategy.setStrategy(strat)

  val rowList = rowsStrategy.strategy

  def getLockedRows(): Int = {
    var res = 0
    for(r <- rowList){
      if(r.locked){
        res += 1
      }
    }
    res
  }
  //Points
  def getCommulatedPoints(): Int ={
    var res = 0
    for(r <- rowList){
      res += r.getRowPoints()
    }
    res
  }
  def getSplittedPoints(): List[(String,Int)] = {
    var splittedPoints: List[(String, Int)] = List()
    for (idx <- 0 to 3){
      splittedPoints = (rowList(idx).colorName.toString,rowList(idx).getRowPoints()) :: splittedPoints
    }
    splittedPoints
  }
}