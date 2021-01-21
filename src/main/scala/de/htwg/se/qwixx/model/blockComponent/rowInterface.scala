package de.htwg.se.qwixx.model.blockComponent

import de.htwg.se.qwixx.model.blockComponent.blockBaseImpl.Field

/////////////////////////////////////////////////////////////
// FileName: rowInterface.scala
// FileType: Scala Source file (Trait)
// Author: Joel Merath, Tim Disch
// Created On: 10.01.2021
// Last Modified On : 10.01.2021
/////////////////////////////////////////////////////////////

trait rowInterface {

  def lockRow(): (Boolean, String)

  def checkField(fieldIdx: Int): (Boolean, String)

  def updateFields(): Array[Field]

  def getCheckedFieldCount(): Int

  def getRowPoints(): Int

  def getOpenFields(): List[(Int, Int)]

  def getAllFields(): List[(Int, Int)]

}
