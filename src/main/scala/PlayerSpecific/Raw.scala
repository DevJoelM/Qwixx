package PlayerSpecific

/////////////////////////////////////////////////////////////
// FileName: Raw.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 29.10.2020
// Last Modified On : 29.10.2020
/////////////////////////////////////////////w////////////////

class Raw (ID:Integer, val colorName:String){

  val fieldList = fillFieldList()
  var locked:Boolean = false
  val pointsPerCheck = Array(2,3,6,10,15,21,28,36,45,55,66,78)

  def fillFieldList(): Array[Field] = {
    val fields = new Array[Field](11)
    for(r <- 2 to 12){
      fields(r-2) = new Field(r,false,false)
    }
    return fields
  }
  def lockRaw(): Unit ={
    locked = true
    updateFields()
  }
  def checkField(index:Int): Unit ={
    fieldList(index).checkedState = true
    updateFields()
  }
  def updateFields():Unit= {
    if(locked){
      for (f <- fieldList) {
        if(f.checkedState==false){
          f.blockedState = true
        }
      }
    }
    var foundLastCheckedField = false
    for (f <- fieldList.reverse){
      if(!foundLastCheckedField && f.checkedState){
        foundLastCheckedField = true
      }
      if(foundLastCheckedField && f.checkedState==false){
        f.blockedState = true
      }
    }
  }
  def getRawPoints(): Int ={
    var checkCount = 0
    for(f <- fieldList){
      if(f.checkedState) {
        checkCount += 1
      }
    }
    if(locked){
      checkCount += 1
    }
    var res = 0
    if(checkCount != 0) {
      res = pointsPerCheck(checkCount-1)
    }
    res
  }
}
