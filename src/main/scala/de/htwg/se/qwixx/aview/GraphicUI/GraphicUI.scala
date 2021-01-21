package de.htwg.se.qwixx.aview.GraphicUI

import de.htwg.se.qwixx.aview.UIType
import de.htwg.se.qwixx.controller.controllerComponent.ControllerInterface
import de.htwg.se.qwixx.util.{GameColors, Observer}

import scala.swing.Swing.LineBorder
import scala.swing._
import scala.swing.event.{MousePressed, MouseReleased}

/////////////////////////////////////////////////////////////
// FileName: GraphicUI.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 11.11.2020
// Last Modified On : 11.11.2020
/////////////////////////////////////////////////////////////

class GraphicUI(controller: ControllerInterface) extends Frame with UIType with Observer {

  listenTo(controller)
  controller.add(this)

  def update: Unit = {
    redrawPlayground()
  }

  resizable =  false

  title = "Qwixx: Digital"

  redrawPlayground()
  pack()
  visible = true

  def redrawPlayground():Unit={
    contents.drop(0)
    contents = new GridBagPanel() {
      def constraints(
                       x: Int,
                       y: Int,
                       gridwidth: Int = 1,
                       gridheight: Int = 1,
                       weightx: Double = 0.0,
                       weighty: Double = 0.0,
                       fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.None): Constraints = {
        val c = new Constraints
        c.gridx = x
        c.gridy = y
        c.gridwidth = gridwidth
        c.gridheight = gridheight
        c.weightx = weightx
        c.weighty = weighty
        c.fill = fill
        c
      }

      //border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 10)

      add(
        new GridPanel(4, 1) {
          border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 0)
          //background = GameColors.ROW_BACKGROUND_DARKER
          minimumSize = new Dimension(1000, 1000)
          var y = 0
          for (r <- controller.getPlayerList()(0).block.rowList) {
            var x = 0
            contents += new BoxPanel(Orientation.Horizontal) {
              contents += new GridPanel(1, 11) {
                background = GameColors.ROW_BACKGROUND
                maximumSize = new Dimension(1000, 100)
                for (f <- r.fieldList) {
                  contents += new FieldUI(x,y,controller.getController(),f, r.colorName,GameColors.ROW_COLORS_BLOCKED(y))
                  x += 1
                }
                contents += new LockUI(y,controller.getController(),r, r.colorName)
              }
            }
            y += 1
          }
        },
        constraints(0, 0, gridheight = 4, gridwidth = 12, fill = GridBagPanel.Fill.Both)
      )

      add(
        new GridPanel(6, 1) {
          //border = LineBorder(GameColors.ROW_BACKGROUND, 10)
          maximumSize = new Dimension(70,40)
          val dices = controller.getDicesList()
          for(x <- 2 to 5){
            contents += new DiceUI(controller.getController(),dices(x))
          }
          for(x <- 0 to 1){
            contents += new DiceUI(controller.getController(),dices(x))
          }
        },
        constraints(14, 0, gridheight = 7, gridwidth = 2 ,fill = GridBagPanel.Fill.Both)
      )

      background = GameColors.ROW_BACKGROUND

      add(
        new GridPanel(2, 1) {

          border = LineBorder(GameColors.ROW_BACKGROUND, 10)
          background = GameColors.ROW_BACKGROUND_DARKER
          contents += new GridBagPanel() {

            border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 5)
            background = GameColors.ROW_BACKGROUND_DARKER

            def constraints(
                             x: Int,
                             y: Int,
                             gridwidth: Int = 1,
                             gridheight: Int = 1,
                             weightx: Double = 0.0,
                             weighty: Double = 0.0,
                             fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.None): Constraints = {
              val c = new Constraints
              c.gridx = x
              c.gridy = y
              c.gridwidth = gridwidth
              c.gridheight = gridheight
              c.weightx = weightx
              c.weighty = weighty
              c.fill = fill
              c
            }

            val scoreWidth = 85

            add(new Label() {
              text = "POINTS"
              font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
              foreground = GameColors.FOREGROUND
              border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
              preferredSize = new Dimension(100, 10)
            }, constraints(0, 0, gridwidth = 2, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1, 1) {
              preferredSize = new Dimension(scoreWidth, 10)
              border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
              background = GameColors.ROW_1_Blocked
              contents += new Label() {
                text = controller.getPlayerList()(0).block.rowList(0).getRowPoints().toString
                foreground = GameColors.ROW_1_BACKGROUND
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
              }
            }, constraints(2, 0, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1,1){
              background = GameColors.ROW_BACKGROUND_DARKER
              contents += new Label() {
                text = "+"
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
                preferredSize = new Dimension(25, 50)
                foreground = GameColors.FOREGROUND
              }
            }, constraints(3, 0, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1, 1) {
              preferredSize = new Dimension(scoreWidth, 50)
              border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
              background = GameColors.ROW_2_Blocked
              contents += new Label() {
                text = controller.getPlayerList()(0).block.rowList(1).getRowPoints().toString

                foreground = GameColors.ROW_2_BACKGROUND
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
              }
            }, constraints(4, 0,fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1,1){
              background = GameColors.ROW_BACKGROUND_DARKER
              contents += new Label() {
                text = "+"
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
                preferredSize = new Dimension(25, 50)
                foreground = GameColors.FOREGROUND
              }
            }, constraints(5, 0, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1, 1) {
              preferredSize = new Dimension(scoreWidth, 50)
              border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
              background = GameColors.ROW_3_Blocked
              contents += new Label() {
                text = controller.getPlayerList()(0).block.rowList(2).getRowPoints().toString
                foreground = GameColors.ROW_3_BACKGROUND
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
              }
            }, constraints(6, 0, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1,1){
              background = GameColors.ROW_BACKGROUND_DARKER
              contents += new Label() {
                text = "+"
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
                preferredSize = new Dimension(25, 50)
                foreground = GameColors.FOREGROUND
              }
            }, constraints(7, 0, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1, 1) {
              preferredSize = new Dimension(scoreWidth, 50)
              border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
              background = GameColors.ROW_4_Blocked
              contents += new Label() {
                text = controller.getPlayerList()(0).block.rowList(3).getRowPoints().toString
                foreground = GameColors.ROW_4_BACKGROUND
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
              }
            }, constraints(8, 0, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1,1){
              background = GameColors.ROW_BACKGROUND_DARKER
              contents += new Label() {
                text = "-"
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
                preferredSize = new Dimension(25, 50)
                foreground = GameColors.FOREGROUND
              }
            }, constraints(9, 0, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1, 1) {
              preferredSize = new Dimension(scoreWidth, 10)
              border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
              background = GameColors.ROW_BACKGROUND
              contents += new Label() {
                text = "X"
                foreground = GameColors.FOREGROUND
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
              }
            }, constraints(10, 0, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1,1){
              background = GameColors.ROW_BACKGROUND_DARKER
              contents += new Label() {
                text = "="
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
                preferredSize = new Dimension(25, 50)
                foreground = GameColors.FOREGROUND
              }
            }, constraints(11, 0, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1, 1) {
              border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
              preferredSize = new Dimension(100, 10)
              background = GameColors.ROW_BACKGROUND
              contents += new Label() {
                text = controller.getPlayerList()(0).block.getCommulatedPoints().toString
                foreground = GameColors.FOREGROUND
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
              }
            }, constraints(12, 0,fill = GridBagPanel.Fill.Both))
          }
          contents += new GridBagPanel() {

            border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 5)
            background = GameColors.ROW_BACKGROUND_DARKER

            def constraints(
                             x: Int,
                             y: Int,
                             gridwidth: Int = 1,
                             gridheight: Int = 1,
                             weightx: Double = 0.0,
                             weighty: Double = 0.0,
                             fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.None): Constraints = {
              val c = new Constraints
              c.gridx = x
              c.gridy = y
              c.gridwidth = gridwidth
              c.gridheight = gridheight
              c.weightx = weightx
              c.weighty = weighty
              c.fill = fill
              c
            }

            add(new Label() {
              text = "FAILS"
              font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
              foreground = GameColors.FOREGROUND
              border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
              preferredSize = new Dimension(100, 10)
            }, constraints(0, 0, gridwidth = 2, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1, 1) {
              preferredSize = new Dimension(50, 10)
              border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
              background = GameColors.ROW_BACKGROUND
              contents += new Label() {
                text = "X"
                foreground = GameColors.FOREGROUND
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
              }
            }, constraints(2, 0, gridwidth = 2, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1, 1) {
              preferredSize = new Dimension(50, 50)
              border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
              background = GameColors.ROW_BACKGROUND
              contents += new Label() {
                text = "X"
                foreground = GameColors.FOREGROUND
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
              }
            }, constraints(4, 0, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1, 1) {
              preferredSize = new Dimension(50, 50)
              preferredSize = new Dimension(50, 50)
              border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
              background = GameColors.ROW_BACKGROUND
              contents += new Label() {
                text = "X"
                foreground = GameColors.FOREGROUND
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
              }
            }, constraints(5, 0, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1, 1) {
              preferredSize = new Dimension(50, 50)
              border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
              background = GameColors.ROW_BACKGROUND
              contents += new Label() {
                text = "X"
                foreground = GameColors.FOREGROUND
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
              }
            }, constraints(6, 0, fill = GridBagPanel.Fill.Both))


            add(new GridPanel(1, 1) {
              preferredSize = new Dimension(150, 10)
              background = GameColors.ROW_BACKGROUND_DARKER
            }, constraints(7, 0, gridwidth = 1, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1, 1) {
              preferredSize = new Dimension(50, 50)
              border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
              background = GameColors.ROW_BACKGROUND
              contents += new Label() {
                listenTo(mouse.clicks)
                text = "<"
                foreground = GameColors.FOREGROUND
                font = new Font("Arial", 1, 25)
                reactions += {
                  case e: MousePressed => {
                    foreground = GameColors.FOREGROUND_BRIGHTER
                  }
                  case e: MouseReleased => {
                    background = GameColors.ROW_BACKGROUND
                    controller.getUndoManager().undoCheck
                    redrawPlayground()
                    controller.updateGame
                  }
                }
              }
            }, constraints(8, 0, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1, 1) {
              preferredSize = new Dimension(50, 50)
              border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
              background = GameColors.ROW_BACKGROUND
              contents += new Label() {
                listenTo(mouse.clicks)
                text = ">"
                foreground = GameColors.FOREGROUND
                font = new Font("Arial", 1, 25)
                reactions += {

                  case e: MousePressed => {
                    foreground = GameColors.FOREGROUND_BRIGHTER
                  }
                  case e: MouseReleased => {
                    background = GameColors.ROW_BACKGROUND
                    controller.getUndoManager().redoStep()
                    redrawPlayground()
                    controller.updateGame
                  }
                }
              }
            }, constraints(9, 0, fill = GridBagPanel.Fill.Both))

            add(new GridPanel(1, 1) {
              border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
              preferredSize = new Dimension(200, 10)
              background = GameColors.ROW_BACKGROUND
              contents += new Label() {
                listenTo(mouse.clicks)
                text = "DICE!"
                foreground = GameColors.FOREGROUND
                font = new Font(GameColors.FONTSTYLE, 1, GameColors.SECOUND_FONTSIZE)
                reactions += {

                  case e: MousePressed => {
                    foreground = GameColors.FOREGROUND_BRIGHTER
                  }
                  case e: MouseReleased => {
                    background = GameColors.ROW_BACKGROUND
                    controller.throwDices()
                    redrawPlayground()
                  }
                }
              }
            }, constraints(13, 0, gridwidth = 2, fill = GridBagPanel.Fill.Both))
          }
        },
        constraints(0, 5, gridwidth = 13, gridheight = 2, fill = GridBagPanel.Fill.Both)
      )
    }
  }

}
