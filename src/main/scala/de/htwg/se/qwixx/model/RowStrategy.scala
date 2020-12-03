package de.htwg.se.qwixx.model

object RowStrategy {

  var strategy = strategySorted

  def setStrategy(x:String) = x match {
    case "s" => strategy = strategySorted
    case "r" => strategy = strategyReversed
  }

  def strategySorted: Array[Field] = {
    val possibleValues = Array(2,3,4,5,6,7,8,9,10,11,12)
    val fields = new Array[Field](11)
    for(r <- 2 to 12){
        fields(r-2) = new Field(r-2,possibleValues(r-2),false,false)
    }
    fields
  }

  def strategyReversed: Array[Field] = {
    val possibleValues = Array(12,11,10,9,8,7,6,5,4,3,2)
    val fields = new Array[Field](11)
    for(r <- 12 to 2 by -1){
      fields(r-2) = new Field(r-2,possibleValues(r-2),false,false)
    }
    fields
  }

}
