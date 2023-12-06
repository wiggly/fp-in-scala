/*
import sbt.Keys.libraryDependencies
import sbt._

object Dependencies extends AutoPlugin {

  object autoImport {
    implicit class DependencySettings(val project: Project) extends AnyVal {
      def withDependencies : Project = project
        .settings(libraryDependencies ++= deps.value)
        .settings(libraryDependencies ++= testDeps.value)
    }
  }

  // Versions
  val fs2Version = "2.5.10"

  val pureconfigVersion = "0.13.0"

  val catsVersion = "2.2.0"
  val catsEffectVersion = "2.5.3"

  val scalaTestVersion = "3.2.9"

  lazy val deps = Def.setting(Seq(

//    "org.typelevel" %% "cats-kernel" % catsVersion,
//    "org.typelevel" %% "cats-core" % catsVersion,

//    "org.typelevel" %% "cats-effect-kernel" % catsEffectVersion,
//    "org.typelevel" %% "cats-effect-std" % catsEffectVersion,
//    "org.typelevel" %% "cats-effect" % catsEffectVersion,

//    "co.fs2" %% "fs2-core" % fs2Version,
//    "co.fs2" %% "fs2-io" % fs2Version
  ))

  lazy val testDeps = Def.setting(Seq(
//    "org.scalatest" %% "scalatest" % scalaTestVersion
  ).map(_ % Test))
}
*/