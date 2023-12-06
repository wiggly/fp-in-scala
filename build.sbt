val root = (project in file("."))
  .settings(
    name := "Functional Programming in Scala",
    moduleName := "fp-in-scala",
    scalaVersion := "3.2.2",
    run / fork := true,
    Test / parallelExecution := false
  )
