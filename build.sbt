name := """agoetschm"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

val webjars = Seq(
  //  "org.webjars.bower" % "jquery" % "3.1.0",
  //  "org.webjars.bower" % "materialize" % "0.97.6"
)

//routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "com.typesafe.play" %% "play-slick" % "0.8.0",
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc4",
  "com.typesafe.play" %% "play-mailer" % "5.0.0"
) ++ webjars


//fork in run := true
resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"