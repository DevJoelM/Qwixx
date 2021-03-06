package de.htwg.se.qwixx.model.blockComponent

import de.htwg.se.qwixx.model.blockComponent.blockBaseImpl.{Block, Field, Row}
import de.htwg.se.qwixx.model.strategyComponent.strategyBaseImpl.RowsStrategy
import de.htwg.se.qwixx.util.GameColors
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/////////////////////////////////////////////////////////////
// FileName: RowSpec.scala
// FileType: Scala-Test Source file
// Author: Joel Merath, Tim Disch
// Created On : 12.11.2020
// Last Modified On : 18.11.2020
/////////////////////////////////////////////////////////////

class RowSpec extends AnyWordSpec with Matchers {
  "A Row" should {
    "should " in {
      val d = new Row(1, GameColors.ROW_1_BACKGROUND, "sorted")
      d.lockRow() shouldBe a[Array[_]]
      d.checkField(1) shouldBe a[Array[_]]
      d.locked should be(false)
      d.getOpenFields() shouldBe a[List[_]]
      d.updateFields() shouldBe a[Array[Field]]
      d.getCheckedFieldCount() should be(1)
      d.getRowPoints() should be(2)
      d.locked = true
      d.getRowPoints() should be(3)
    }
  }
  "Row" should {
    "updateFields" in {
      val d = new Row(0, GameColors.ROW_1_BACKGROUND, "sorted" )
      d.locked = true
      d.updateFields().toList foreach {
        f => {
          f.checkedState should (equal(true) or equal(false))
          f.blockedState should (equal(true) or equal(false))
        }
      }
    }
  }
  "Row" should {
    "lockRow" in {
      val d = new Row(0, GameColors.ROW_1_BACKGROUND, "sorted")
      for(c <- 0 to 4){
        d.fieldList(c).checkedState = true
      }
      d.lockRow()
      d.locked = true
      d.lockRow()
    }
  }
  "Row" should {
    "checkField" in {
      val d = new Row(0, GameColors.ROW_1_BACKGROUND, "sorted")
      d.fieldList(0).blockedState = true
      d.fieldList(0).checkedState = true
      d.checkField(0)
      d.fieldList(0).blockedState = false
      d.checkField(0)
    }
  }
  "Row" should {
    "getCheckedFields" in {
      val d = new Row(0, GameColors.ROW_1_BACKGROUND, "sorted")
      d.fieldList(0).checkedState = true
      d.getCheckedFields() shouldBe a[List[_]]
    }
  }
  "RowStrategy" should {
    "randomize" in {
      val d = new Row(0, GameColors.ROW_1_BACKGROUND, "random")
      d.fieldList(0).checkedState = true
      d.getCheckedFields() shouldBe a[List[_]]

    }
  }
  "RowsStrategy" should {
    "random" in {
      val rowsStrategy = RowsStrategy
      rowsStrategy.setStrategy("random")

    }
  }

}