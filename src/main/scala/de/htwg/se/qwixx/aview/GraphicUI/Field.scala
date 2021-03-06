package de.htwg.se.qwixx.aview.GraphicUI

import de.htwg.se.qwixx.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.qwixx.model.blockComponent.blockBaseImpl.{Field, Row}
import de.htwg.se.qwixx.util.GameColors

import java.awt.Color
import javax.swing.border.EmptyBorder
import scala.swing._
import scala.swing.event.MousePressed

class FieldUI(x:Int, y:Int, controller: Controller, field: Field, fieldColor:Color, fieldColor_Blocked:Color) extends BoxPanel(Orientation.Horizontal) {
  border = new EmptyBorder(0,0,5,0)
  background = GameColors.ROW_BACKGROUND_DARKER

  listenTo(mouse.clicks)

  val label = {
      new Label() {
        text = field.value.toString
        font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
        foreground = fieldColor
        preferredSize = new Dimension(40, 40)
        background = GameColors.ROW_1_FOREGROUND
        if(field.blockedState) {
          //foreground = Color.WHITE
          foreground = GameColors.ROW_BACKGROUND_DARKER
        }else if(field.checkedState) {
          foreground = GameColors.ROW_BACKGROUND_DARKER
        }
      }
  }

  val cell = new BoxPanel(Orientation.Vertical) {
    preferredSize = new Dimension(65, 50)
      contents += new GridPanel(1,1){
        if(field.blockedState) {
          background = fieldColor_Blocked
        } else if (!field.checkedState){
          background = GameColors.ROW_BACKGROUND_DARKER
        }else if(field.checkedState){
          background = fieldColor
        }
        contents += label
      }
  }

  reactions += {
    case e: MousePressed => {
      controller.checkField(0,y,field.fieldIdx)
    }
  }

  contents += cell

}

class LockUI(y:Int,controller: Controller, row: Row, fieldColor:Color) extends BoxPanel(Orientation.Horizontal) {

  border = new EmptyBorder(0,0,5,0)
  background = GameColors.ROW_BACKGROUND_DARKER

  listenTo(mouse.clicks)

  val label = {
    new Label() {
      font = new Font(GameColors.FONTSTYLE, 0, 20)
      if(row.locked){
        foreground = GameColors.ROW_BACKGROUND_DARKER
        text = "X"
      }else{
        text = "O"
        foreground = fieldColor
      }

      preferredSize = new Dimension(40, 40)
    }
  }

  val cell = new BoxPanel(Orientation.Vertical) {
    preferredSize = new Dimension(50, 50)
    contents += new GridPanel(1,1){
      if(row.locked) {
        background = fieldColor
      } else {
        background = GameColors.ROW_BACKGROUND_DARKER
      }
      contents += label
    }
  }

  reactions += {
    case e: MousePressed => {
      controller.lockRow(0,y)
      controller.updateGame()
    }
  }

  contents += cell

}
