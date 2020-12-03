package de.htwg.se.qwixx.model

import scala.util.Random

object RowStrategy {

  var strategy = strategySorted

  def setStrategy(x:String) = x match {
    case "sorted" => strategy = strategySorted
    case "reversed" => strategy = strategyReversed
    case "random" => strategy = strategyRandomized
  }

  def strategySorted: Array[Field] = {
    val possibleValues_strategySorted = Array(2,3,4,5,6,7,8,9,10,11,12)
    val fields = new Array[Field](11)
    for(r <- 2 to 12){
        fields(r-2) = new Field(r-2,possibleValues_strategySorted(r-2),false,false)
    }
    fields
  }

  def strategyReversed: Array[Field] = {
    val possibleValues_strategyReversed = Array(12,11,10,9,8,7,6,5,4,3,2)
    val fields = new Array[Field](11)
    for(r <- 12 to 2 by -1){
      fields(r-2) = new Field(r-2,possibleValues_strategyReversed(r-2),false,false)
    }
    fields
  }

  def strategyRandomized: Array[Field] = {
    val possibleValues_strategyRandomized = Array(2,3,4,5,6,7,8,9,10,11,12)
    val randomizedValues = Random.shuffle(possibleValues_strategyRandomized.toList)
    val fields = new Array[Field](11)
    for(r <- 12 to 2 by -1){
      fields(r-2) = new Field(r-2,randomizedValues(r-2),false,false)
    }
    fields
  }

}
