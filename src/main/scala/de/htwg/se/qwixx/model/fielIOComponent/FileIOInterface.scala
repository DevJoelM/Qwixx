package de.htwg.se.qwixx.model.fielIOComponent

import de.htwg.se.qwixx.controller.controllerComponent.ControllerInterface
import de.htwg.se.qwixx.controller.controllerComponent.controllerBaseImpl.Controller

/////////////////////////////////////////////////////////////
// FileName: FileIOInterface.scala
// FileType: Scala Source file (Trait)
// Author: Joel Merath, Tim Disch
// Created On: 10.01.2021
// Last Modified On : 10.01.2021
/////////////////////////////////////////////////////////////

trait FileIOInterface {

  def loadGame():ControllerInterface
  def saveGame(controller:Controller):Unit

}
