package de.htwg.se.qwixx.model.fielIOComponent

import de.htwg.se.qwixx.controller.controllerComponent.ControllerInterface
import de.htwg.se.qwixx.controller.controllerComponent.controllerBaseImpl.Controller

trait FileIOInterface {

  def loadGame():ControllerInterface
  def saveGame(controller:Controller):Unit

}
