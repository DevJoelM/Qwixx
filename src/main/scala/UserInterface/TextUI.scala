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
    var run = true
    while(run){
      print(visualizePlayground())
      val cmd = scala.io.StdIn.readLine().split(" ")
      if(cmd.size==1 && cmd(0) == "t"){
        dices.ThrowDice(dices.dices)
      } else if(cmd.size==1 && cmd(0) == "exit"){
        run = false
      }else if(cmd(2).toString=="l") {
        playerList(cmd(0).toInt - 1).block.rawList(cmd(1).toInt - 1).locked = true;
      } else {
        playerList(cmd(0).toInt - 1).block.rawList(cmd(1).toInt - 1).fieldList(cmd(2).toInt - 1).checkedState = true;
      }
      print("\n")
      print(visualizePlayground())
      print("\n\n")
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
  def visualizePlayground(): String = {
    var sb = new StringBuilder()
    for(p <- playerList){
      sb.append(("[Player " + (p.ID+1).toString + "] ").padTo(70,"-").mkString+"\n")
      for (raw <- p.block.rawList){
        sb.append(raw.colorName.padTo(8," ").mkString+"|  ")
        for (field <- raw.fieldList){
          if(!field.checkedState){
            sb.append(field.value.toString.padTo(2," ").mkString + "   ")
          } else {
            sb.append("❎".padTo(2," ").mkString + "   ")
          }
        }
        if(raw.locked){
          sb.append("[❎]".padTo(3," ").mkString + "   ")
        } else {
          sb.append("[ ]".padTo(3," ").mkString + "   ")
        }
        sb.append("\n")
      }
    }
    sb.append("".padTo(70,"-").mkString+"\n")
    for(dice <- dices.dices){
      sb.append(dice.colorName+":"+dice.value+"  ")
    }
    sb.toString()
  }
}