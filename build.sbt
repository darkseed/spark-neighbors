name := "spark-neighbors"

organization := "com.github.karlhigley"

description := "Spark-based approximate nearest neighbor search using locality-sensitive hashing"

scalaVersion := "2.10.5"

sparkVersion := "1.6.0"

sparkComponents := Seq("core", "mllib")

val testSparkVersion = settingKey[String]("The version of Spark to test against.")

testSparkVersion := sys.props.get("spark.testVersion").getOrElse(sparkVersion.value)

libraryDependencies ++= Seq(
  "org.scalanlp" %% "breeze" % "0.12",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % testSparkVersion.value % "test" force(),
  "org.apache.spark" %% "spark-mllib" % testSparkVersion.value % "test" force(),
  "org.scala-lang" % "scala-library" % scalaVersion.value % "compile"
)

// This is necessary because of how we explicitly specify Spark dependencies
// for tests rather than using the sbt-spark-package plugin to provide them.
spIgnoreProvided := true

parallelExecution in Test := false

publishArtifact in Test := false

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath + "/.m2/repository")))