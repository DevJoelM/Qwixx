package main.scala

import main.scala.Main.tablePart.tablePart

/////////////////////////////////////////////////////////////
// FileName: Main.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 29.10.2020
// Last Modified On : 29.10.2020
/////////////////////////////////////////////////////////////

object Main {
  def main(args: Array[String]): Unit = {
    println(visualizePlayground())
  }

  /**
   * Method to return the playing field from Qwixx as a string.
   * @return Multiline string for output.
   */
  def visualizePlayground(): String = {
    val colors = Array("Red", "Yellow", "Green", "Blue")
    var sb = new StringBuilder()
    sb.append("Qwixx-Game\n")
    visualizeTableSections(sb,tablePart.TOP)
    for (r <- 1 to 4) {
      visualizeRow(sb, r, colors.apply(r-1))
      if(r<=3){
        visualizeTableSections(sb,tablePart.MID)
      }
    }
    visualizeTableSections(sb,tablePart.BOTTOM)
    sb.toString()
  }

  /**
   * Sub-method that generates the individual lines.
   * @param sb StringBuilder reference.
   * @param count Current row-count.
   * @param color Color-name for row.
   */
  def visualizeRow(sb: StringBuilder, count:Int, color: String) : Unit = {
    sb.append(color.padTo(7," ").mkString)
    if (count<=2) {
      for (i <- 2 to 12) {
        sb.append("│" + i.toString.padTo(2, " ").mkString)
      }
    } else {
      for (i <- 12 to 2 by -1) {
        sb.append("│" + i.toString.padTo(2, " ").mkString)
      }
    }
    sb.append("│ │ L │\n")
  }

  object tablePart extends Enumeration{
    type tablePart = Value
    val TOP, MID, BOTTOM = Value
  }

  /**
   * Sub-method which visually formats the table as a string.
   * @param sb StringBuilder reference.
   * @param tp Enumeration for the tablepart.
   */
  def visualizeTableSections(sb: StringBuilder, tp:tablePart): Unit ={
    tp match{
      case tablePart.TOP =>
        sb.append("       ┌──┬──┬──┬──┬──┬──┬──┬──┬──┬──┬──┐ ┌───┐\n")
      case tablePart.MID =>
        sb.append("       ├──┼──┼──┼──┼──┼──┼──┼──┼──┼──┼──┤ ├───┤\n")
      case tablePart.BOTTOM =>
        sb.append("       └──┴──┴──┴──┴──┴──┴──┴──┴──┴──┴──┘ └───┘\n")
    }
  }
}