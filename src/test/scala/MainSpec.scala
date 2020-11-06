package src.test.scala

import main.scala.Main
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/////////////////////////////////////////////////////////////
// FileName: MainSpec.scala
// FileType: Scala-Test Source file
// Author: Joel Merath, Tim Disch
// Created On : 05.11.2020
// Last Modified On : 05.11.2020
/////////////////////////////////////////////////////////////

class MainSpec extends AnyWordSpec with Matchers {
  "Main" should {
    "Create a Qwixx block" in {
      val block = Main.visualizePlayground()
      block should startWith ("Qwixx-Game\n")
      block should include("       ┌──┬──┬──┬──┬──┬──┬──┬──┬──┬──┬──┐ ┌───┐\n")
      block should include("       ├──┼──┼──┼──┼──┼──┼──┼──┼──┼──┼──┤ ├───┤\n")
      block should endWith("       └──┴──┴──┴──┴──┴──┴──┴──┴──┴──┴──┘ └───┘\n")
      block should include("Green  │12│11│10│9 │8 │7 │6 │5 │4 │3 │2 │ │ L │\n")
      block should include("Blue   │12│11│10│9 │8 │7 │6 │5 │4 │3 │2 │ │ L │\n")
      block should include("Yellow │2 │3 │4 │5 │6 │7 │8 │9 │10│11│12│ │ L │\n")
      block should include("Red    │2 │3 │4 │5 │6 │7 │8 │9 │10│11│12│ │ L │\n")
    }
    "Have a specific size" in {
      val blocklength = Main.visualizePlayground()
      blocklength should have size 443
    }
  }
}
