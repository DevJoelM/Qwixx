package UserInterface
import java.io.ByteArrayInputStream

import PlayerSpecific.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.io.StdIn

/////////////////////////////////////////////////////////////
// FileName: TextUISpec.scala
// FileType: Scala-Test Source file
// Author: Joel Merath, Tim Disch
// Created On : 12.11.2020
// Last Modified On : 12.11.2020
/////////////////////////////////////////////////////////////

class TextUISpec extends AnyWordSpec with Matchers {
  "A UI" when {
    "TextUI is used" should {
      val ui = new UI
      val tui = new TextUI
      "have values" in {
        ui.playerList shouldBe a[List[Player]]
        tui.visualizePlayground() should include("[Player 1] -----------------------------------------------------------\n")
        tui.visualizePlayground() should include("Red")
        tui.visualizePlayground() should include("Yellow")
        tui.visualizePlayground() should include("Green")
        tui.visualizePlayground() should include("Blue")
        tui.visualizePlayground() should include("----------------------------------------------------------------------\n")
        tui.playerList(0).block.rawList(0).fieldList(0).checkedState = true
        tui.playerList(0).block.rawList(0).locked = true
        tui.visualizePlayground() should include("❎")

      }
    }
  }
}