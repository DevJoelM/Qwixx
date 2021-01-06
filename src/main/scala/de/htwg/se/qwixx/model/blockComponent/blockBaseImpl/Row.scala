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

class Row (val rowIdx:Integer, val colorName:Color, val strat:String) extends rowInterface{

  //Validate RowStrategy
  val rowStrategy = RowStrategy
  rowStrategy.setStrategy(strat)

  val fieldList = rowStrategy.strategy

  var locked:Boolean = false

  //Row
  def lockRow(): (Boolean,String) = {

    if(!locked){
      if(getCheckedFieldCount()>=1) {
        locked = true
        updateFields()
        (true, String.format("Row (%s) successfully locked!", (rowIdx+1).toString))
      }
      else
      {
        (false, String.format("Not enough fields in row (%s) checked!", (rowIdx+1).toString))
      }
    }
    else
    {
      (false, String.format("Row (%s) is already locked!", (rowIdx+1).toString))
    }
  }

  //Field
  def checkField(fieldIdx:Int): (Boolean,String) ={
    if(!fieldList(fieldIdx).blockedState) {
      if(!fieldList(fieldIdx).checkedState) {
        fieldList(fieldIdx).checkedState = true
        updateFields()
        (true,"Field successfully checked!")
      } else {
        (true,"Field was already checked!")
      }

    } else {
      (false,"Field is blocked!")
    }
  }
  def updateFields() : Array[Field] = {
    if (locked) {
      for (f <- fieldList.toList) {
        if (!f.checkedState) {
          f.blockedState = true
        }
      }
    }
    var foundLastCheckedField = false
    for (f <- fieldList.reverse) {
      if(f.undoState){
        f.checkedState = false
        f.undoState = false
      }
      if (!foundLastCheckedField && f.checkedState) {
        foundLastCheckedField = true
      }
      if (foundLastCheckedField && f.checkedState == false) {
        f.blockedState = true
      } else{
        f.blockedState = false
      }
      if(locked){
        if (!f.checkedState) {
          f.blockedState = true
        }
      }
    }
    fieldList
  }
  def getCheckedFieldCount(): Int = {
    fieldList.count(_.checkedState)
  }

  //Points
  def getRowPoints(): Int ={
    val pointsPerCheck = Array(2,3,6,10,15,21,28,36,45,55,66,78)
    var checkCount = 0
    for(f <- fieldList){
      if(f.checkedState) {
        checkCount += 1
      }
    }
    if(locked){
      checkCount += 1
    }
    var res = 0
    if(checkCount != 0) {
      res = pointsPerCheck(checkCount-1)
    }
    res
  }
  def getOpenFields(): List[(Int,Int)] = {
    var s : List[(Int,Int)] = List()
    for(f <- fieldList.toList.reverse){
      if(!f.checkedState && !f.blockedState){
        s = (f.fieldIdx,f.value) :: s
      }
    }
    s
  }
  def getAllFields(): List[(Int,Int)] = {
    var s : List[(Int,Int)] = List()
    for(f <- fieldList.toList){
        s = (f.fieldIdx,f.value) :: s
    }
    s
  }
}