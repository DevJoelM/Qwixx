package de.htwg.se.qwixx.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/////////////////////////////////////////////////////////////
// FileName: BlockSpec.scala
// FileType: Scala-Test Source file
// Author: Joel Merath, Tim Disch
// Created On : 05.11.2020
// Last Modified On : 18.11.2020
/////////////////////////////////////////////////////////////

class BlockSpec extends AnyWordSpec with Matchers {
  "A Block" should {
    "should look like" in {
      val b  = new Block("classic")
      //b.createRowList() shouldBe a [Array[_]]
      b.getLockedRows() should be(0)
      b.getCommulatedPoints() should be(0)
      b.getSplittedPoints() shouldBe a[List[_]]
      b.rowList(0).locked = true
      b.getLockedRows() should be(1)
    }
  }
}