package de.htwg.se.qwixx.model.blockComponent

/////////////////////////////////////////////////////////////
// FileName: blockInterface.scala
// FileType: Scala Source file (Trait)
// Author: Joel Merath, Tim Disch
// Created On: 10.01.2021
// Last Modified On : 10.01.2021
/////////////////////////////////////////////////////////////

trait blockInterface {

  def getLockedRows(): Int

  def getCommulatedPoints(): Int

  def getSplittedPoints(): List[(String, Int)]

}
