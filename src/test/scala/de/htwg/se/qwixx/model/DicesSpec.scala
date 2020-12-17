package de.htwg.se.qwixx.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


/////////////////////////////////////////////////////////////
// FileName: DicesSpec.scala
// FileType: Scala-Test Source file
// Author: Joel Merath, Tim Disch
// Created On : 06.11.2020
// Last Modified On : 18.11.2020
/////////////////////////////////////////////////////////////

class DicesSpec extends AnyWordSpec with Matchers {

  "A Dice" should {
    "should have" in {
      val d = new Dices
      //d.colorsHex shouldBe a[Array[_]]
      d.colorsName shouldBe a[Array[_]]
      d.cDiceCount should be(4)
      d.dDiceCount should be(2)
      d.coloredDices shouldBe a[Array[_]]
      d.defaultDices shouldBe a[Array[_]]
      d.throwDices() should be(true)
      d.updateDiceCombinations()
      d.defaultDices
    }
  }
}