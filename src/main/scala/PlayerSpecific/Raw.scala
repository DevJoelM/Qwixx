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

  def fillFieldList(): Array[Field] = {
    val fields = new Array[Field](11)
    for(r <- 2 to 12){
      fields(r-2) = new Field(r,false,false)
    }
    return fields
  }

  def getCheckedFields(): Int = {
    return 0
  }
}
