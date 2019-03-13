name := "scalatestplus-mockito"

organization := "org.scalatestplus"

version := "1.0.0-SNAP1"

homepage := Some(url("https://github.com/scalatest/scalatestplus-mockito"))

licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

developers := List(
  Developer(
    "bvenners",
    "Bill Venners",
    "bill@artima.com",
    url("https://github.com/bvenners")
  ),
  Developer(
    "cheeseng",
    "Chua Chee Seng",
    "cheeseng@amaseng.com",
    url("https://github.com/cheeseng")
  )
)

crossScalaVersions := List("2.10.7", "2.11.12", "2.12.8", "2.13.0-M5")

libraryDependencies ++= Seq(
  "org.mockito" % "mockito-core" % "1.10.19",
  "org.scalatest" %% "scalatest" % "3.1.0-SNAP8"
)

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  Some("publish-releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

pgpSecretRing := file((Path.userHome / ".gnupg" / "secring.gpg").getAbsolutePath)

pgpPassphrase := None
