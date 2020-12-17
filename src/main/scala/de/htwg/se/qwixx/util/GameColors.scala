package de.htwg.se.qwixx.util

import java.awt.Color

object GameColors {

  val FONTSTYLE = "Sawasdee"

  val ROW_BACKGROUND = new Color(36, 40, 70)
  val ROW_BACKGROUND_DARKER = new Color(18, 22, 52)
  val FOREGROUND = new Color(86,91,128)
  val FOREGROUND_BRIGHTER = new Color(165,168,197)

  val ROW_1_BACKGROUND = new Color(62, 101, 213)
  val ROW_1_FOREGROUND = new Color(78, 124, 255)
  val ROW_1_Blocked = new Color(24, 34, 76)

  val ROW_2_BACKGROUND = new Color(96, 62, 213)
  val ROW_2_FOREGROUND = new Color(130, 78, 255)
  val ROW_2_Blocked = new Color(29, 28, 76)


  val ROW_3_BACKGROUND = new Color(213, 78, 62)
  val ROW_3_FOREGROUND = new Color(255, 102, 78)
  val ROW_3_Blocked = new Color(47, 30, 53)


  val ROW_4_BACKGROUND = new Color(213, 127, 62)
  val ROW_4_FOREGROUND = new Color(255, 155, 78)
  val ROW_4_Blocked = new Color(47, 38, 53)


  val DICE_COLORS = Array(GameColors.ROW_1_BACKGROUND, GameColors.ROW_2_BACKGROUND, GameColors.ROW_3_BACKGROUND,
    GameColors.ROW_4_BACKGROUND, GameColors.ROW_BACKGROUND, GameColors.ROW_BACKGROUND)
  val DICE_COLORS_REV = Array(GameColors.ROW_BACKGROUND, GameColors.ROW_BACKGROUND,GameColors.ROW_1_BACKGROUND, GameColors.ROW_2_BACKGROUND, GameColors.ROW_3_BACKGROUND,
    GameColors.ROW_4_BACKGROUND )
  val COLORED_DICE_COLORS = Array(GameColors.ROW_1_BACKGROUND, GameColors.ROW_2_BACKGROUND, GameColors.ROW_3_BACKGROUND,
    GameColors.ROW_4_BACKGROUND)
  val ROW_COLORS = Array(GameColors.ROW_1_BACKGROUND, GameColors.ROW_2_BACKGROUND, GameColors.ROW_3_BACKGROUND, GameColors.ROW_4_BACKGROUND)
  val ROW_COLORS_FOREGROUND = Array(GameColors.ROW_1_FOREGROUND, GameColors.ROW_2_FOREGROUND, GameColors.ROW_3_FOREGROUND, GameColors.ROW_4_FOREGROUND)
  val ROW_COLORS_BLOCKED = Array(GameColors.ROW_1_Blocked, GameColors.ROW_2_Blocked, GameColors.ROW_3_Blocked,
    GameColors.ROW_4_Blocked)
}
