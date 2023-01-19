name := "rc210view"

organization := "net.wa9nnn"


ThisBuild / scalaVersion := "2.13.10"

val logbackVersion = "1.4.5"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "4.3.5" % Test,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",

  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "ch.qos.logback" % "logback-core" % logbackVersion,
)

