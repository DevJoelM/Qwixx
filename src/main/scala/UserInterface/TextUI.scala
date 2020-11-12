
package UserInterface

/////////////////////////////////////////////////////////////
// FileName: TextUI.scala
// FileType: Scala Source file
// Author: Joel Merath, Tim Disch
// Created On : 11.11.2020
// Last Modified On : 11.11.2020
/////////////////////////////////////////////////////////////

class TextUI()
  extends UI() {

  def scanCommands(): Boolean = {
    while(true){
      val cmd = scala.io.StdIn.readLine().split(" ")
      if(cmd.size==1 && cmd(0) == "t"){
        dices.ThrowDice(dices.dices)
      } else if(cmd(2).toString=="l") {
        playerList(cmd(0).toInt - 1).block.rawList(cmd(1).toInt - 1).locked = true;
      } else {
        playerList(cmd(0).toInt - 1).block.rawList(cmd(1).toInt - 1).fieldList(cmd(2).toInt - 1).checkedState = true;
      }
      println()
      visualizePlayground()
    }
    true
  }

  def runCommands(): Boolean = {
    true
  }

  /**
   * Method to return the playing field from Qwixx as a string.
   * @return Multiline string for output.
   */
  def visualizePlayground(): Unit = {
    for(p <- playerList){
      println(("[Player " + (p.ID+1).toString + "] ").padTo(70,"-").mkString)
      for (raw <- p.block.rawList){
        print(raw.colorName.padTo(8," ").mkString+"|  ")
        for (field <- raw.fieldList){
          if(!field.checkedState){
            print(field.value.toString.padTo(2," ").mkString + "   ")
          } else {
            print("❎".padTo(2," ").mkString + "   ")
          }
        }
        //println()

        if(raw.locked){
          print("[❎]".padTo(3," ").mkString + "   ")
        } else {
          print("[ ]".padTo(3," ").mkString + "   ")
        }
        println()
      }
    }
    println("".padTo(70,"-").mkString)
    for(dice <- dices.dices){
      print(dice.colorName+":"+dice.value+"  ")
    }
  }
}