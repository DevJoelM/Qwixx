package PlayerSpecific

/////////////////////////////////////////////////////////////
// FileName: UI.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 27.10.2020
// Last Modified On : 11.11.2020
/////////////////////////////////////////////////////////////

case class Player (ID:Int, name:String) {
  val currentScore = 0
  val isActivePlayer = false
  val block = new Block()
}
