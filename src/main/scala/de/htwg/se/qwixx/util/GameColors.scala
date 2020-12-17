package de.htwg.se.qwixx.util

import java.awt.Color

object GameColors {

  val ROW_BACKGROUND = new Color(217, 219, 235)
  val ROW_BACKGROUND_DARKER = new Color(245, 246, 255)
  val FOREGROUND = new Color(145,148,177)

  val ROW_1_BACKGROUND = new Color(62, 101, 213)
  val ROW_1_FOREGROUND = new Color(78, 124, 255)

  val ROW_2_BACKGROUND = new Color(96, 62, 213)
  val ROW_2_FOREGROUND = new Color(130, 78, 255)

  val ROW_3_BACKGROUND = new Color(213, 78, 62)
  val ROW_3_FOREGROUND = new Color(255, 102, 78)

  val ROW_4_BACKGROUND = new Color(213, 127, 62)
  val ROW_4_FOREGROUND = new Color(255, 155, 78)

  val DICE_COLORS = Array(GameColors.ROW_1_BACKGROUND, GameColors.ROW_2_BACKGROUND, GameColors.ROW_3_BACKGROUND,
    GameColors.ROW_4_BACKGROUND, GameColors.ROW_BACKGROUND, GameColors.ROW_BACKGROUND)

  val ROW_COLORS = Array(GameColors.ROW_1_BACKGROUND, GameColors.ROW_2_BACKGROUND, GameColors.ROW_3_BACKGROUND, GameColors.ROW_4_BACKGROUND)
  val ROW_COLORS_FOREGROUND = Array(GameColors.ROW_1_FOREGROUND, GameColors.ROW_2_FOREGROUND, GameColors.ROW_3_FOREGROUND, GameColors.ROW_4_FOREGROUND)

}
