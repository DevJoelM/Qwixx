package de.htwg.se.qwixx.aview

import de.htwg.se.qwixx.controller.Controller

trait UIType {
  def run(input:String):String = {new String()}
}

object UIType {
  def apply(uiType: String, controller: Controller) = uiType match {
    case "t" | "T" => new TextUI(controller)
    case "g" | "G" => new GraphicUI(controller)
  }
}

