name := "Akka-Assignment"

version := "0.1"

scalaVersion := "2.13.3"

val AkkaVersion = "2.6.9"
libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % AkkaVersion % Test

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % "test"