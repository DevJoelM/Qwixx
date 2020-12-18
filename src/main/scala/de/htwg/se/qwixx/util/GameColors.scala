package de.htwg.se.qwixx.util

import java.awt.Color

object GameColors {

  val FONTSTYLE = "Sawasdee"

  //DARK
  val ROW_BACKGROUND = Color.decode("#1C2040")
  val ROW_BACKGROUND_DARKER = new Color(18, 22, 52)
  val FOREGROUND = Color.decode("#D8DCFF")
  val FOREGROUND_BRIGHTER = new Color(165,168,197)

  //COLOR 1
 //val ROW_1_BACKGROUND = new Color(62, 101, 213)
 //val ROW_1_FOREGROUND = new Color(78, 124, 255)
 //val ROW_1_Blocked = new Color(24, 34, 76)
 //val ROW_2_BACKGROUND = new Color(96, 62, 213)
 //val ROW_2_FOREGROUND = new Color(130, 78, 255)
 //val ROW_2_Blocked = new Color(29, 28, 76)
 //val ROW_3_BACKGROUND = new Color(213, 78, 62)
 //val ROW_3_FOREGROUND = new Color(255, 102, 78)
 //val ROW_3_Blocked = new Color(47, 30, 53)
 //val ROW_4_BACKGROUND = new Color(213, 127, 62)
 //val ROW_4_FOREGROUND = new Color(255, 155, 78)
 //val ROW_4_Blocked = new Color(47, 38, 53)

  //COLOR 2
  val ROW_1_BACKGROUND = Color.decode("#A25A6C")
  val ROW_1_FOREGROUND = Color.decode("#9D435A")
  val ROW_1_Blocked = Color.decode("#211A36")
  val ROW_2_BACKGROUND = Color.decode("#C08552")
  val ROW_2_FOREGROUND = Color.decode("#F3B886")
  val ROW_2_Blocked = Color.decode("#2C2738")
  val ROW_3_BACKGROUND = Color.decode("#5EA884")
  val ROW_3_FOREGROUND = Color.decode("#91DBB7")
  val ROW_3_Blocked = Color.decode("#1D2C40")
  val ROW_4_BACKGROUND = Color.decode("#1C6E8C")
  val ROW_4_FOREGROUND = Color.decode("#65B4D1")
  val ROW_4_Blocked = Color.decode("#132341")

  //COLOR 3
   //val ROW_1_BACKGROUND = Color.decode("#BCE784")
   //val ROW_1_FOREGROUND = Color.decode("#E3FFBE")
   //val ROW_1_Blocked = Color.decode("#2B3640")


  //val ROW_2_BACKGROUND = Color.decode("#5DD39E")
  //val ROW_2_FOREGROUND = Color.decode("#B4FFDD")
  //val ROW_2_Blocked = Color.decode("#162845")
//
//
  //val ROW_3_BACKGROUND = Color.decode("#348AA7")
  //val ROW_3_FOREGROUND = Color.decode("#9AE5FF")
  //val ROW_3_Blocked = Color.decode("#1D2C40")
//
//
  //val ROW_4_BACKGROUND = Color.decode("#67597A")
  //val ROW_4_FOREGROUND = Color.decode("#65B4D1")
  //val ROW_4_Blocked = Color.decode("#1E203E")


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
