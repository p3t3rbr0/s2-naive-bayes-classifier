name := "naive-bayes-classifier"
version := "0.1.0"
scalaVersion := "2.13.8"

// Dependences
libraryDependencies ++= Seq(
  "com.formdev" % "flatlaf" % "2.4"
)

javaOptions ++= Seq(
  "-Dawt.useSystemAAFontSettings=on",
  "-Dswing.aatext=true"
)

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-deprecation",
  "-feature",
  "-unchecked",
)

mainClass in Compile := Some("app.Main")
mainClass in assembly := Some("app.Main")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

fork := true
fork in Test := true
