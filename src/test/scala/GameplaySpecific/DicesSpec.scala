package GameplaySpecific

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
  "A Dice" should {
    "should have" in {
      val d = new Dices
      d.colorsHex shouldBe a[Array[_]]
      d.colorsName shouldBe a[Array[_]]
      d.dices shouldBe a[Array[_]]

      var test: Array[Dice] = d.ThrowDice(null)
      var comb: List[Dice] = List()
      for (c <- 0 to 5) {
        for (v <- 1 to 6) {
          comb = Dice(c, d.colorsHex(c), d.colorsName(c), v) :: comb
        }
      }
      test should contain atLeastOneElementOf (comb.toArray[Dice])
      d.ThrowDice(test)
      test shouldBe a[Array[_]]
    }
  }
}