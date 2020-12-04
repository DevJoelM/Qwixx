package de.htwg.se.qwixx.controller

/////////////////////////////////////////////////////////////
// FileName: UndoManager.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 04.12.2020
// Last Modified On : 04.12.2020
/////////////////////////////////////////////////////////////

class UndoManager {

  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def doCheck(command: Command) = {
    undoStack = command :: undoStack
    command.doCheck
  }

  def undoCheck = {
    undoStack match {
      case Nil =>
      case head :: stack => {
        head.undoCheck
        undoStack = stack
        redoStack = head :: redoStack
      }
    }
  }

  def redoStep() = {
    redoStack match {
      case Nil =>
      case head::stack => {
        head.redoCheck
        redoStack=stack
        undoStack=head::undoStack
      }
    }
  }

}