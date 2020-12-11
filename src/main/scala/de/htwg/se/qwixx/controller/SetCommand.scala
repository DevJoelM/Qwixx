package de.htwg.se.qwixx.controller

trait Command {
  def doCheck:Unit
  def undoCheck:Unit
  def redoCheck:Unit
}

class SetCommand(controller: Controller, playerID:Int, rowID:Int, fieldID: Int) extends Command {
  override def doCheck: Unit = controller.playerList(playerID).block.rowList(rowID).checkField(fieldID)
  override def undoCheck: Unit = {
    controller.playerList(playerID).block.rowList(rowID).fieldList(fieldID).undoState = true
    controller.playerList(playerID).block.rowList(rowID).updateFields()
  }
  override def redoCheck: Unit = controller.playerList(playerID).block.rowList(rowID).checkField(fieldID)
}

