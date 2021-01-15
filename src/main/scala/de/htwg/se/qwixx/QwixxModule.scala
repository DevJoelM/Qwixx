package de.htwg.se.qwixx

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.qwixx.controller.controllerComponent.ControllerInterface
import de.htwg.se.qwixx.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.qwixx.model.fielIOComponent.FileIOInterface
import de.htwg.se.qwixx.model.fielIOComponent._
import de.htwg.se.qwixx.model.gameComponent.gameBaseImpl.Dices
import de.htwg.se.qwixx.model.gameComponent.gameInterface
import net.codingwell.scalaguice.ScalaModule

class QwixxModule extends AbstractModule with ScalaModule {

  val title = "Qwixx"

  override def configure() = {

    bindConstant().annotatedWith(Names.named("Qwixx")).to(title)

    bind[ControllerInterface].to[Controller]
    bind[gameInterface].to[Dices]

    bind[FileIOInterface].to[fileIOJson.FileIO]
    //bind[FileIOInterface].to[fileIOxml.FileIO]

  }
}