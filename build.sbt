name := "Qwixx"

version := "0.1"

scalaVersion := "2.12.12"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"
libraryDependencies += "org.openjfx" % "javafx" % "12.0.2" pomOnly()
libraryDependencies += "org.scalafx" %% "scalafx" % "15.0.1-R20"

libraryDependencies += "com.google.inject" % "guice" % "4.2.3"
libraryDependencies += "net.codingwell" %% "scala-guice" % "4.2.11"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.3.0"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.8.1"

// Determine OS version of JavaFX binaries
lazy val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux") => "linux"
  case n if n.startsWith("Mac") => "mac"
  case n if n.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform!")
}

// Add JavaFX dependencies
lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
libraryDependencies ++= javaFXModules.map( m=>
  "org.openjfx" % s"javafx-$m" % "15.0.1" classifier osName
)

coverageExcludedPackages := ".*GraphicUI.*"