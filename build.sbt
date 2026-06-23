ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.7"

lazy val root = (project in file("."))
  .settings(
    name := "scala-with-cats"
  )

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core"            % "2.13.0",
  "org.typelevel" %% "cats-laws"            % "2.13.0" % Test,
  "org.typelevel" %% "discipline-core"      % "1.7.0"  % Test,
  "org.scalacheck" %% "scalacheck"          % "1.19.0" % Test,
)
