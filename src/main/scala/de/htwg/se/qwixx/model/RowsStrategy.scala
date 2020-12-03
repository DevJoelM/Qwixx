package de.htwg.se.qwixx.model

object RowsStrategy {

  var strategy = strategyClassic

  def setStrategy(x:String) = x match {
    case "classic" => strategy = strategyClassic
    case "random" => strategy = strategyRandom
  }

  def strategyClassic: Array[Row] = {
    val avaibleRowColorNames = Array("Red","Yellow","Green","Blue")
    val raws = new Array[Row](4)
    for(r <- 1 to 2){
      raws(r-1) = new Row(r,avaibleRowColorNames(r-1),"sorted")
    }
    for(r <- 3 to 4){
      raws(r-1) = new Row(r,avaibleRowColorNames(r-1),"reversed")
    }
    raws
  }

  def strategyRandom: Array[Row] = {
    val avaibleRowColorNames = Array("Red","Yellow","Green","Blue")
    val raws = new Array[Row](4)
    for(r <- 1 to 4){
      raws(r-1) = new Row(r,avaibleRowColorNames(r-1),"random")
    }
    raws
  }

}
