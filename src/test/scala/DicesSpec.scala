package src.test.scala
import main.scala.Dices

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/////////////////////////////////////////////////////////////
// FileName: DicesSpec.scala
// FileType: Scala-Test Source file
// Author: Joel Merath, Tim Disch
// Created On : 06.11.2020
// Last Modified On : 06.11.2020
/////////////////////////////////////////////////////////////

class DicesSpec extends AnyWordSpec with Matchers {
  "A Date" should {
    "have a year" in {
      val d  = new Dices
      d.ThrowDice() shouldBe a[List[_]]
    }
  }
}
