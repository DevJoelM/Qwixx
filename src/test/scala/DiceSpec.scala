package src.test.scala

import main.scala.Dice
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/////////////////////////////////////////////////////////////
// FileName: DiceSpec.scala
// FileType: Scala-Test Source file
// Author: Joel Merath, Tim Disch
// Created On : 06.11.2020
// Last Modified On : 06.11.2020
/////////////////////////////////////////////////////////////

class DiceSpec extends AnyWordSpec with Matchers {
  "A Dice" should {
    "have a value" in {
      Dice(0,"#FFFFFF",5).ID should be(0)
      Dice(0,"#FFFFFF",5).color should be("#FFFFFF")
      Dice(0,"#FFFFFF",5).value should be(5)
    }
  }
}
