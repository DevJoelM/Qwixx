package de.htwg.se.qwixx.model.gameComponent

import de.htwg.se.qwixx.model.blockComponent.blockBaseImpl.Block
import de.htwg.se.qwixx.model.gameComponent.gameBaseImpl.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/////////////////////////////////////////////////////////////
// FileName: PlayerSpec.scala
// FileType: Scala-Test Source file
// Author: Joel Merath, Tim Disch
// Created On : 05.11.2020
// Last Modified On : 05.11.2020
/////////////////////////////////////////////////////////////

class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player" when {
    "not set to any value" should {
      val d = Player(1, "Test", new Block("classic"))
      "have values" in {
        d shouldBe a [Player]
        d.ID should be(1)
        d.name should be("Test")
        d.block shouldBe a[Block]
        assert(d.block.isInstanceOf[Block])
      }
    }
  }
}
