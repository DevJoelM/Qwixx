package de.htwg.se.qwixx.aview

import de.htwg.se.qwixx.aview.GraphicUI.GraphicUI
import de.htwg.se.qwixx.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.qwixx.util.Logger.error

trait UIType {
  def run(input:String):String = {new String()}
}

object UIType {
  def apply(uiType: String, controller: Controller) = uiType match {
    case "t" | "T" => new TextUI(controller)
    case "g" | "G" => new GraphicUI(controller)
    case _ => {
      error("Input not allowed! Default (TextUI) initialized!")
      new TextUI(controller)
    }
  }
}