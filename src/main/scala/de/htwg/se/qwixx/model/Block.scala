package de.htwg.se.qwixx.model

/////////////////////////////////////////////////////////////
// FileName: Block.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 29.10.2020
// Last Modified On : 11.11.2020
/////////////////////////////////////////////////////////////

class Block {
  val rowList = createRowList()

  //Rows
  def createRowList(): Array[Row] = {
    val avaibleRowColorNames = Array("Red","Yellow","Green","Blue")
    val raws = new Array[Row](4)
    for(r <- 1 to 4){
      raws(r-1) = new Row(r,avaibleRowColorNames(r-1))
    }
    return raws
  }
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
      splittedPoints = (rowList(idx).colorName,rowList(idx).getRowPoints()) :: splittedPoints
    }
    splittedPoints
  }
}