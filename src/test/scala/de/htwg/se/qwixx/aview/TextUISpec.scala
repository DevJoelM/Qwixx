package de.htwg.se.qwixx.aview

import de.htwg.se.qwixx.controller.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/////////////////////////////////////////////////////////////
// FileName: TextUISpec.scala
// FileType: Scala-Test Source file
// Author: Joel Merath, Tim Disch
// Created On : 12.11.2020
// Last Modified On : 18.11.2020
/////////////////////////////////////////////////////////////

class TextUISpec extends AnyWordSpec with Matchers {

  "A UI" when {
    "TextUI is used" should {
      val ui = new Controller
      val tui = new TextUI(ui)
      val sb = new StringBuilder
      "should print" in {
        ui.playerList shouldBe a[List[_]]
        tui.visualizePlayground() should include("PLAYER 1")
        tui.visualizePlayground() should include("1")
        tui.visualizePlayground() should include("Points:")
        tui.visualizePlayground() should include("Index")
        tui.visualizePlayground() should include("White")
        tui.visualizePlayground() should include("Color2")
        tui.visualizePlayground() should include("Color3")
        tui.visualizePlayground() should include("Color4")
        tui.visualizePlayground() should include("")
        ui.playerList(0).block.rowList(0).locked = true
        tui.visualizePlayground() should include("\uD83D\uDD13 ")
        tui.visualizePlayground() should include("\uD83D\uDD12 ")
        ui.playerList(0).block.rowList(0).fieldList(0).blockedState = true
        tui.visualizePlayground() should include("⬜")
        ui.playerList(0).block.rowList(1).fieldList(3).checkedState = true
        tui.visualizePlayground() should include("⬛")
      }
    }
    "Textcommands are used" should {
      val ui = new Controller
      val tui = new TextUI(ui)
      "should process commands" in {

        for (i <- 0 to 11){
          val m = ui.isCombinationCheckable(0, 0, i)
          m shouldBe a[(_,_)]
        }
        for (i <- 0 to 11) {
          val m = ui.checkField(0, 0, i)
          m shouldBe a[(_,_)]
        }

        tui.processInputCommands("t")
        tui.processInputCommands("") should include("\nInput not allowed!\n")
        tui.processInputCommands("1 1 1")

        tui.processInputCommands("undo")
        tui.processInputCommands("redo")



        tui.processInputCommands("1 1 l")
        ui.playerList(0).block.rowList(0).locked = true
        ui.playerList(0).block.rowList(1).locked = true
        ui.playerList(0).block.rowList(0).fieldList(0).checkedState=true
        tui.processInputCommands("1 1 2")

        tui.processInputCommands("exit")
        ui.gameState.handle(false)
      }
    }
  }
}