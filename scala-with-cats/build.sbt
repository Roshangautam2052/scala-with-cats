ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.6"

lazy val root = (project in file("."))
  .settings(
    name := "scala-with-cats"
  )

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.3" cross CrossVersion.full)

libraryDependencies += "org.typelevel" %% "cats-core" % "2.13.0"
libraryDependencies += "org.typelevel" %% "cats-laws" % "2.1.1"
libraryDependencies += "org.typelevel" %% "discipline-core" % "1.0.0"
libraryDependencies += "org.typelevel" %% "discipline-scalatest" % "2.1.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2"

scalacOptions ++= Seq(
  "-Xfatal-warnings"
)