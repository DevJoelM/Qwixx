
name := "Qwixx"

version := "0.1"

scalaVersion := "2.12.12"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"
libraryDependencies += "org.scalafx" %% "scalafx" % "15.0.1-R20"

libraryDependencies += "com.google.inject" % "guice" % "4.2.3"
libraryDependencies += "net.codingwell" %% "scala-guice" % "4.2.11"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.3.0"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.8.1"


coverageExcludedPackages := ".*GraphicUI.*"

