package de.htwg.se.qwixx.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/////////////////////////////////////////////////////////////
// FileName: ControllerSpec.scala
// FileType: Scala-Test Source file
// Author: Joel Merath, Tim Disch
// Created On : 12.11.2020
// Last Modified On : 12.11.2020
/////////////////////////////////////////////////////////////

class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" when {
    "TextUI is used" should {
      val ui = new Controller

      "should print" in {
        ui.getDiceCombinations() shouldBe a[List[_]]
        ui.getPlayerName(0) shouldBe a [String]
        ui.getPlayerPoints(0) shouldBe a [Int]
        ui.getPlayerSplittedPoints(0) shouldBe a [List[_]]
        ui.checkField(0,0,1) shouldBe a [(Boolean,String)]
        val x = ui.isCombinationCheckable(0,0,0)
        ui.isCombinationCheckable(0,0,20) shouldBe a[(_,_)]
        x._2 should include ("work")
        x._1 shouldBe a [Boolean]
        for (i <- 0 to 11){
          var m =  ui.isCombinationCheckable(0, 0, i)
          m shouldBe a[(_,_)]
        }
        for (i <- 0 to 11) {
          var m =  ui.checkField(0, 0, i)
          m shouldBe a[(_,_)]
        }
      }
    }
  }
}