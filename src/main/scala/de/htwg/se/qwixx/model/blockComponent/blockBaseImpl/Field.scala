package de.htwg.se.qwixx.model.blockComponent

/////////////////////////////////////////////////////////////
// FileName: Field.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Dischs
// Created On : 29.10.2020
// Last Modified On : 29.10.2020
/////////////////////////////////////////////////////////////

class Field (val fieldIdx:Int, val value:Int, var checkedState: Boolean,var blockedState:Boolean,var undoState: Boolean){}
