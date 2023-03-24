val qyndrixVersion = "3.0.0"

ThisBuild / scalaVersion := "3.2.2"

Global / onChangedBuildSource := ReloadOnSourceChanges

import org.scalajs.linker.interface.OutputPatterns

lazy val root: Project = project
  .in(file("."))
  .aggregate(qyndrix.js, qyndrix.jvm, qyndrix.native)
  .settings(
    publish := {},
    publishLocal := {}
  )

lazy val qyndrix = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .in(file("."))
  .settings(
    // Shared settings
    name := "qyndrix",
    version := qyndrixVersion,
    semanticdbEnabled := true,
    libraryDependencies ++= Seq(
      // For number stuff
      "org.typelevel" %%% "spire" % "0.18.0",
      "org.scala-lang.modules" %%% "scala-parser-combinators" % "2.2.0",
      // For command line parsing
      "com.github.scopt" %%% "scopt" % "4.1.0",
      // Used by ScalaTest
      "org.scalactic" %%% "scalactic" % "3.2.15",
      "org.scalatest" %%% "scalatest" % "3.2.15" % Test
    ),
    scalacOptions ++= Seq(
      "-deprecation", // Emit warning and location for usages of deprecated APIs.
      "-encoding",
      "utf-8", // Specify character encoding used by source files.
      "-feature", // Emit warning and location for usages of features that should be imported explicitly.
      "-unchecked", // Enable additional warnings where generated code depends on assumptions.
      // Above options from https://tpolecat.github.io/2017/04/25/scalac-flags.html
      "-language:implicitConversions",
      "-language:adhocExtensions",
      // "-explain",
      "-print-lines"
    ),

    // From https://www.scalatest.org/user_guide/using_the_runner
    // Suppress output from successful tests
    Test / testOptions += Tests
      .Argument(TestFrameworks.ScalaTest, "-oNCXELOPQRM")
  )
  .jvmSettings(
    // JVM-specific settings
    Compile / mainClass := Some("qyndrix.Main"),
    assembly / mainClass := Some("qyndrix.Main"),
    assembly / assemblyJarName := s"qyndrix-$qyndrixVersion.jar",
    // Necessary for tests to be able to access src/main/resources
    Test / fork := true
  )
  .jsSettings(
    // JS-specific settings
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "2.4.0"
    ),
    // Where the compiled JS is output
    Compile / fastOptJS / artifactPath := baseDirectory.value.getParentFile / "pages" / "qyndrix.js",
    Compile / fullOptJS / artifactPath := baseDirectory.value.getParentFile / "pages" / "qyndrix.js"
  )
  .nativeSettings(
    // Scala Native-specific settings
    nativeConfig ~= {
      _.withEmbedResources(true)
    }
  )
