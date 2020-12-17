package de.htwg.se.qwixx.aview.GraphicUI

import de.htwg.se.qwixx.aview.UIType
import de.htwg.se.qwixx.controller.Controller
import de.htwg.se.qwixx.util.GameColors

import java.awt.Color
import scala.swing.Swing.LineBorder
import scala.swing._

/////////////////////////////////////////////////////////////
// FileName: GraphicUI.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 11.11.2020
// Last Modified On : 11.11.2020
/////////////////////////////////////////////////////////////

class GraphicUI(controller: Controller) extends Frame with UIType {

  listenTo(controller)
  title = "Qwixx: Digital"

  menuBar = new MenuBar {
    contents += new Menu("Game") {
      contents += new MenuItem(Action("Dice") {
        controller.throwDices()
      })
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
  }

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

    add(
      new GridPanel(4, 1) {
        border = LineBorder(GameColors.ROW_BACKGROUND, 10)
        background = GameColors.ROW_BACKGROUND
        minimumSize = new Dimension(1000, 1000)
        for (r <- controller.playerList(0).block.rowList) {
          contents += new BoxPanel(Orientation.Horizontal) {
            contents += new GridPanel(1, 11) {
              background = GameColors.ROW_BACKGROUND
              maximumSize = new Dimension(1000, 100)
              for (f <- r.fieldList) {
                contents += new Field(f.value.toString,
                  r.colorName,
                  Color.WHITE,
                  controller)
              }
              contents += new Field("\uD83D\uDD12",
                r.colorName,
                Color.WHITE,
                controller)
            }
          }
        }
      },
      constraints(0, 0, gridheight = 4, gridwidth = 12, fill = GridBagPanel.Fill.Both)
    )

    add(
      new GridPanel(6, 1) {
        border = LineBorder(GameColors.ROW_BACKGROUND, 10)
        background = Color.ORANGE
        for (x <- controller.getDicesList()) {
          background = new Color(18, 22, 52)
          contents += new Field(x.value.toString,
            x.color,
            Color.WHITE,
            controller) {}
        }
      },
      constraints(14, 0, gridheight = 7, fill = GridBagPanel.Fill.Both)
    )

    add(
      new GridPanel(2, 1) {

        border = LineBorder(GameColors.ROW_BACKGROUND, 10)
        background = GameColors.ROW_BACKGROUND_DARKER
        contents += new GridBagPanel() {

          border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 5)
          background = GameColors.ROW_BACKGROUND

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

          val scoreWidth = 77

          add(new Label() {
            text = "POINTS"
            font = new Font("Arial", 1, 20)
            foreground = GameColors.FOREGROUND
            border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
            preferredSize = new Dimension(100, 10)
          }, constraints(0, 0, gridwidth = 2, fill = GridBagPanel.Fill.Both))

          add(new GridPanel(1, 1) {
            preferredSize = new Dimension(scoreWidth, 10)
            border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
            background = GameColors.ROW_1_BACKGROUND
            contents += new Label() {
              text = controller.playerList(0).block.rowList(0).getRowPoints().toString
              foreground = GameColors.ROW_BACKGROUND_DARKER
              font = new Font("Arial", 1, 20)
            }
          }, constraints(2, 0, fill = GridBagPanel.Fill.Both))

          add(new GridPanel(1,1){
            background = GameColors.ROW_BACKGROUND_DARKER
            contents += new Label() {
              text = "+"
              font = new Font("Arial", 1, 25)
              preferredSize = new Dimension(25, 50)
              foreground = GameColors.FOREGROUND
            }
          }, constraints(3, 0, fill = GridBagPanel.Fill.Both))

          add(new GridPanel(1, 1) {
            preferredSize = new Dimension(scoreWidth, 50)
            border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
            background = GameColors.ROW_2_BACKGROUND
            contents += new Label() {
              text = controller.playerList(0).block.rowList(1).getRowPoints().toString

              foreground = GameColors.ROW_BACKGROUND_DARKER
              font = new Font("Arial", 1, 20)
            }
          }, constraints(4, 0,fill = GridBagPanel.Fill.Both))

          add(new GridPanel(1,1){
            background = GameColors.ROW_BACKGROUND_DARKER
            contents += new Label() {
              text = "+"
              font = new Font("Arial", 1, 25)
              preferredSize = new Dimension(25, 50)
              foreground = GameColors.FOREGROUND
            }
          }, constraints(5, 0, fill = GridBagPanel.Fill.Both))

          add(new GridPanel(1, 1) {
            preferredSize = new Dimension(scoreWidth, 50)
            border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
            background = GameColors.ROW_3_BACKGROUND
            contents += new Label() {
              text = controller.playerList(0).block.rowList(2).getRowPoints().toString
              foreground = GameColors.ROW_BACKGROUND_DARKER
              font = new Font("Arial", 1, 20)
            }
          }, constraints(6, 0, fill = GridBagPanel.Fill.Both))

          add(new GridPanel(1,1){
            background = GameColors.ROW_BACKGROUND_DARKER
            contents += new Label() {
              text = "+"
              font = new Font("Arial", 1, 25)
              preferredSize = new Dimension(25, 50)
              foreground = GameColors.FOREGROUND
            }
          }, constraints(7, 0, fill = GridBagPanel.Fill.Both))

          add(new GridPanel(1, 1) {
            preferredSize = new Dimension(scoreWidth, 50)
            border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
            background = GameColors.ROW_4_BACKGROUND
            contents += new Label() {
              text = controller.playerList(0).block.rowList(3).getRowPoints().toString
              foreground = GameColors.ROW_BACKGROUND_DARKER
              font = new Font("Arial", 1, 20)
            }
          }, constraints(8, 0, fill = GridBagPanel.Fill.Both))

          add(new GridPanel(1,1){
            background = GameColors.ROW_BACKGROUND_DARKER
            contents += new Label() {
              text = "-"
              font = new Font("Arial", 1, 25)
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
              font = new Font("Arial", 1, 20)
            }
          }, constraints(10, 0, fill = GridBagPanel.Fill.Both))

          add(new GridPanel(1,1){
            background = GameColors.ROW_BACKGROUND_DARKER
            contents += new Label() {
              text = "="
              font = new Font("Arial", 1, 25)
              preferredSize = new Dimension(25, 50)
              foreground = GameColors.FOREGROUND
            }
          }, constraints(11, 0, fill = GridBagPanel.Fill.Both))

          add(new GridPanel(1, 1) {
            border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
            preferredSize = new Dimension(100, 10)
            background = GameColors.ROW_BACKGROUND
            contents += new Label() {
              text = controller.playerList(0).block.getCommulatedPoints().toString
              foreground = GameColors.FOREGROUND
              font = new Font("Arial", 1, 20)
            }
          }, constraints(12, 0,fill = GridBagPanel.Fill.Both))
        }
        contents += new GridBagPanel() {

          border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 5)
          background = GameColors.ROW_BACKGROUND

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
            font = new Font("Arial", 1, 20)
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
              font = new Font("Arial", 1, 20)
            }
          }, constraints(2, 0, gridwidth = 2, fill = GridBagPanel.Fill.Both))

          add(new GridPanel(1, 1) {
            preferredSize = new Dimension(50, 50)
            border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
            background = GameColors.ROW_BACKGROUND
            contents += new Label() {
              text = "X"
              foreground = GameColors.FOREGROUND
              font = new Font("Arial", 1, 20)
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
              font = new Font("Arial", 1, 20)
            }
          }, constraints(5, 0, fill = GridBagPanel.Fill.Both))

          add(new GridPanel(1, 1) {
            preferredSize = new Dimension(50, 50)
            border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
            background = GameColors.ROW_BACKGROUND
            contents += new Label() {
              text = "X"
              foreground = GameColors.FOREGROUND
              font = new Font("Arial", 1, 20)
            }
          }, constraints(6, 0, fill = GridBagPanel.Fill.Both))

          add(new GridPanel(1, 1) {
            preferredSize = new Dimension(210, 10)
            background = GameColors.ROW_BACKGROUND_DARKER
          }, constraints(7, 0, gridwidth = 3, fill = GridBagPanel.Fill.Both))

          add(new GridPanel(1, 1) {
            border = LineBorder(GameColors.ROW_BACKGROUND_DARKER, 2)
            preferredSize = new Dimension(200, 10)
            background = GameColors.ROW_BACKGROUND
            contents += new Label() {
              text = "DICE!"
              foreground = GameColors.FOREGROUND
              font = new Font("Arial", 1, 20)
            }
          }, constraints(10, 0, gridwidth = 2, fill = GridBagPanel.Fill.Both))
        }
      },
      constraints(0, 5, gridwidth = 13, gridheight = 2, fill = GridBagPanel.Fill.Both)
    )
  }

  pack()
  visible = true
}
