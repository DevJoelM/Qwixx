package de.htwg.se.qwixx.aview.GraphicUI

import de.htwg.se.qwixx.controller.Controller
import de.htwg.se.qwixx.util.GameColors

import java.awt.Color
import scala.swing.Swing.LineBorder
import scala.swing._

class Field(value:String,color:Color, foregroundColor:Color,controller: Controller) extends BoxPanel(Orientation.Horizontal) {

  background = color
  border  = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 5)

  val label = {
      new Label() {
        text = value
        font = new Font("Arial", 1, 25)
        foreground = foregroundColor
        preferredSize = new Dimension(40, 40)
        background = GameColors.ROW_1_FOREGROUND
      }
  }

  val cell = new BoxPanel(Orientation.Vertical) {


    preferredSize = new Dimension(50, 50)
      contents += new GridPanel(1,1){
        background = color
        contents += label
      }


  }

  contents += cell
}
