package de.htwg.se.qwixx.util

/////////////////////////////////////////////////////////////
// FileName: Observable.scala
// FileType: Scala Source file
// Author: Marko Boger
// Last Modified On : 26.04.2017
/////////////////////////////////////////////////////////////

trait Observer {
  def update: Unit
}

class Observable {
  var subscribers: Vector[Observer] = Vector()
  def add(s: Observer): Unit = subscribers = subscribers :+ s
  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)
  def notifyObservers: Unit = subscribers.foreach(o => o.update)
}