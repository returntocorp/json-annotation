organization := "com.returntocorp"

name := "json-annotation"

version := "0.3"

scalaVersion := "2.12.2"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
)

bintrayOrganization := Some("returntocorp")
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-reflect" % _)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.6.1" % Test,
  "org.specs2" %% "specs2-core" % "4.0.0" % Test
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation")

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/returntocorp/json-annotation</url>
  <licenses>
    <license>
      <name>MIT</name>
      <url>http://opensource.org/licenses/MIT</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:returntocorp/json-annotation.git</url>
    <connection>scm:git:git@github.com:returntocorp/json-annotation.git</connection>
  </scm>
  <developers>
    <developer>
      <id>martinraison</id>
      <name>Martin Raison</name>
      <url>https://github.com/martinraison</url>
    </developer>
  </developers>)
