package de.htwg.se.qwixx.model.blockComponent.blockBaseImpl

import de.htwg.se.qwixx.model.blockComponent.rowInterface
import de.htwg.se.qwixx.model.strategyComponent.strategyBaseImpl.RowStrategy

import scala.swing.Color

/////////////////////////////////////////////////////////////
// FileName: Row.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 29.10.2020
// Last Modified On : 18.11.2020
/////////////////////////////////////////////////////////////

class Row(val rowIdx: Integer, val colorName: Color, val strat: String) extends rowInterface {

  val rowStrategy = RowStrategy
  rowStrategy.setStrategy(strat)
  val fieldList = rowStrategy.strategy
  var locked: Boolean = false

  /**
   * This function closes a row as soon as the corresponding
   * field is clicked. The row can no longer be edited.
   *
   * @return The status and a corresponding message.
   */
  def lockRow(): (Boolean, String) = {

    if (!locked) {
      if (getCheckedFieldCount() >= 1) {
        locked = true
        updateFields()
        (true, String.format("Row (%s) successfully locked!", (rowIdx + 1).toString))
      }
      else {
        (false, String.format("Not enough fields in row (%s) checked!", (rowIdx + 1).toString))
      }
    }
    else {
      (false, String.format("Row (%s) is already locked!", (rowIdx + 1).toString))
    }
  }

  /**
   * This function returns a list of all checked fields.
   *
   * @return List of fields.
   */
  def getCheckedFields(): List[Field] = {
    var s: List[Field] = List()
    for (f <- fieldList) {
      if (f.checkedState) {
        s = f :: s
      }
    }
    s
  }

  /**
   * This function checks a field in the current row.
   * This gives the player points and the field is blocked
   * for the rest of the game.
   *
   * @param fieldIdx Index of the field.
   * @return
   */
  def checkField(fieldIdx: Int): (Boolean, String) = {
    if (!fieldList(fieldIdx).blockedState) {
      if (!fieldList(fieldIdx).checkedState) {
        fieldList(fieldIdx).checkedState = true
        updateFields()
        (true, "Field successfully checked!")
      } else {
        (true, "Field was already checked!")
      }

    } else {
      (false, "Field is blocked!")
    }
  }

  /**
   * This function updates each field according to its current status.
   * It is taken into account whether it is checked or blocked.
   *
   * @return Array of fields.
   */
  def updateFields(): Array[Field] = {
    if (locked) {
      for (f <- fieldList.toList) {
        if (!f.checkedState) {
          f.blockedState = true
        }
      }
    }
    var foundLastCheckedField = false
    for (f <- fieldList.reverse) {
      if (f.undoState) {
        f.checkedState = false
        f.undoState = false
      }
      if (!foundLastCheckedField && f.checkedState) {
        foundLastCheckedField = true
      }
      if (foundLastCheckedField && f.checkedState == false) {
        f.blockedState = true
      } else {
        f.blockedState = false
      }
      if (locked) {
        if (!f.checkedState) {
          f.blockedState = true
        }
      }
    }
    fieldList
  }

  /**
   * This function returns the number of checked fields in the current row.
   *
   * @return
   */
  def getCheckedFieldCount(): Int = {
    fieldList.count(_.checkedState)
  }

  /**
   * This function determines the number of points in the current row.
   * The points increase exponentially with the number of fields checked.
   *
   * @return Points.
   */
  def getRowPoints(): Int = {
    val pointsPerCheck = Array(2, 3, 6, 10, 15, 21, 28, 36, 45, 55, 66, 78)
    var checkCount = 0
    for (f <- fieldList) {
      if (f.checkedState) {
        checkCount += 1
      }
    }
    if (locked) {
      checkCount += 1
    }
    var res = 0
    if (checkCount != 0) {
      res = pointsPerCheck(checkCount - 1)
    }
    res
  }

  /**
   * This function returns the remaining open fields.
   * These are summarized as tuples in a list.
   *
   * @return List of Tuples (Field-Index, Fields-Value).
   */
  def getOpenFields(): List[(Int, Int)] = {
    var s: List[(Int, Int)] = List()
    for (f <- fieldList.toList.reverse) {
      if (!f.checkedState && !f.blockedState) {
        s = (f.fieldIdx, f.value) :: s
      }
    }
    s
  }

  /**
   * This function returns all fields in the current row.
   * These are summarized as tuples in a list.
   *
   * @return List of Tuples (Field-Index, Fields-Value).
   */
  def getAllFields(): List[(Int, Int)] = {
    var s: List[(Int, Int)] = List()
    for (f <- fieldList.toList) {
      s = (f.fieldIdx, f.value) :: s
    }
    s
  }
}
