package de.htwg.se.qwixx.util

/////////////////////////////////////////////////////////////
// FileName: ObserverPattern.scala
// FileType: Scala Source file
// Author: Marko Boger
// Last Modified On : 26.04.2017
/////////////////////////////////////////////////////////////

class TestObject extends Observer {
  def update:Unit = println("Ping")
}
object ObserverPattern {
  val observable = new Observable
  val observer1 = new TestObject
  val observer2 = new TestObject
  observable.add(observer1)
  observable.add(observer2)
  observable.notifyObservers

  observable.remove(observer1)
  observable.notifyObservers
  observable.remove(observer2)
  observable.notifyObservers
}