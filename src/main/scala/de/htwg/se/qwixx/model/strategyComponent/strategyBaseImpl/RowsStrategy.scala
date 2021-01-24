package de.htwg.se.qwixx.model.strategyComponent.strategyBaseImpl

import de.htwg.se.qwixx.model.blockComponent.blockBaseImpl.Row
import de.htwg.se.qwixx.model.strategyComponent.rowStrategyInterface
import de.htwg.se.qwixx.util.GameColors

object RowsStrategy extends rowStrategyInterface{

  var strategy = strategyClassic

  /**
   * Selects the desired block-strategy using a keyword.
   */
  def setStrategy(x: String): Unit = x match {
    case "classic" => strategy = strategyClassic
    case "random" => strategy = strategyRandom
  }

  /**
   * This strategy creates the original Qwixx playing field.
   * Two times in the normal and two times in the reversed order.
   * @return A list with the generated rows.
   */
  def strategyClassic: Array[Row] = {
    val raws = new Array[Row](4)
    for (r <- 1 to 2) {
      raws(r - 1) = new Row(r, GameColors.ROW_COLORS(r - 1), "sorted")
    }
    for (r <- 3 to 4) {
      raws(r - 1) = new Row(r, GameColors.ROW_COLORS(r - 1), "reversed")
    }
    raws
  }

  /**
   * This strategy creates the playing field with a random order of the numbers.
   * @return A list with the generated rows.
   */
  def strategyRandom: Array[Row] = {
    val diceColors = Array(GameColors.ROW_1_BACKGROUND, GameColors.ROW_2_BACKGROUND,
      GameColors.ROW_3_BACKGROUND, GameColors.ROW_4_BACKGROUND)
    val raws = new Array[Row](4)
    for (r <- 1 to 4) {
      raws(r - 1) = new Row(r, diceColors(r - 1), "random")
    }
    raws
  }

}
