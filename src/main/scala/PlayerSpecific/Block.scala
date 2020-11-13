package PlayerSpecific

/////////////////////////////////////////////////////////////
// FileName: Block.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 29.10.2020
// Last Modified On : 11.11.2020
/////////////////////////////////////////////////////////////

class Block {

  val rawList = createRawList()

  def createRawList(): Array[Raw] = {
    val avaibleRawColorNames = Array("Red","Yellow","Green","Blue")
    val raws = new Array[Raw](4)
    for(r <- 1 to 4){
      raws(r-1) = new Raw(r,avaibleRawColorNames(r-1))
    }
    return raws
  }
  def getCommulatedPoints(): Int ={
    var res = 0
    for(r <- rawList){
      res += r.getRawPoints()
    }
    res
  }
  def getLockedRaws(): Int = {
    var res = 0
    for(r <- rawList){
      if(r.locked){
        res += 1
      }
    }
    res
  }
}