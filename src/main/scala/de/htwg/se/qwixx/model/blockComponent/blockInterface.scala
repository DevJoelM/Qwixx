package de.htwg.se.qwixx.model.blockComponent

trait blockInterface {

  def getLockedRows(): Int

  def getCommulatedPoints(): Int

  def getSplittedPoints(): List[(String, Int)]

}
