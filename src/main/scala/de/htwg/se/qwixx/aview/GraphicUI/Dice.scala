package de.htwg.se.qwixx.aview.GraphicUI

import de.htwg.se.qwixx.model.gameComponent.gameBaseImpl.Dice
import de.htwg.se.qwixx.util.GameColors

import javax.swing.border.EmptyBorder
import scala.swing._

class DiceUI(dice:Dice) extends BoxPanel(Orientation.Horizontal) {

  background = GameColors.ROW_BACKGROUND_DARKER
  border  = new EmptyBorder(0,10,0,0)

  listenTo(mouse.clicks)

  val label = {
    new Label() {
      text = dice.value.toString
      font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
      if(dice.color == GameColors.ROW_BACKGROUND){
        foreground = GameColors.FOREGROUND
      }else {
        foreground = GameColors.ROW_BACKGROUND_DARKER
      }
      preferredSize = new Dimension(40, 40)
      background = GameColors.ROW_1_FOREGROUND
    }
  }

  val cell = new BoxPanel(Orientation.Vertical) {

    preferredSize = new Dimension(60, 40)
    contents += new GridPanel(1,1){

      background = dice.color
      contents += label
    }
  }

  contents += cell

}
