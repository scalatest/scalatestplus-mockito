import java.io.PrintWriter
import scala.io.Source

name := "mockito-5.18"

organization := "org.scalatestplus"

version := "3.2.19.0"

homepage := Some(url("https://github.com/scalatest/scalatestplus-mockito"))

licenses := List(
  "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
)

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

scalaVersion := "2.13.16"

crossScalaVersions := List("2.11.12", "2.12.20", "2.13.16", "3.3.6")

libraryDependencies ++= Seq(
  "org.mockito" % "mockito-core" % "5.18.0",
  "org.scalatest" %% "scalatest-core" % "3.2.19",
  "org.scalatest" %% "scalatest-funsuite" % "3.2.19" % "test"
)

import scala.xml.{Node => XmlNode, NodeSeq => XmlNodeSeq, _}
import scala.xml.transform.{RewriteRule, RuleTransformer}

// skip dependency elements with a scope
pomPostProcess := { (node: XmlNode) =>
  new RuleTransformer(new RewriteRule {
    override def transform(node: XmlNode): XmlNodeSeq = node match {
      case e: Elem
          if e.label == "dependency"
            && e.child.exists(child => child.label == "scope") =>
        def txt(label: String): String =
          "\"" + e.child
            .filter(_.label == label)
            .flatMap(_.text)
            .mkString + "\""
        Comment(
          s""" scoped dependency ${txt("groupId")} % ${txt("artifactId")} % ${txt(
            "version"
          )} % ${txt("scope")} has been omitted """
        )
      case _ => node
    }
  }).transform(node).head
}

enablePlugins(SbtOsgi)

osgiSettings

OsgiKeys.exportPackage := Seq("org.scalatestplus.mockito.*")

OsgiKeys.importPackage := Seq(
  "org.scalatest.*",
  "org.scalactic.*",
  "scala.*;version=\"$<range;[==,=+);$<replace;" + scalaBinaryVersion.value + ";-;.>>\"",
  "*;resolution:=optional"
)

OsgiKeys.additionalHeaders := Map(
  "Bundle-Name" -> "ScalaTestPlusMockito",
  "Bundle-Description" -> "ScalaTest+Mockito is an open-source integration library between ScalaTest and Mockito for Scala projects.",
  "Bundle-DocURL" -> "http://www.scalatest.org/",
  "Bundle-Vendor" -> "Artima, Inc."
)

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  Some("publish-releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

Test / publishArtifact := false

pomIncludeRepository := { _ =>
  false
}

pomExtra := (
  <scm>
    <url>https://github.com/scalatest/scalatestplus-mockito</url>
    <connection>scm:git:git@github.com:scalatest/scalatestplus-mockito.git</connection>
    <developerConnection>
      scm:git:git@github.com:scalatest/scalatestplus-mockito.git
    </developerConnection>
  </scm>
)

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

// Temporary disable publishing of doc in dotty, can't get it to build.
Compile / packageDoc / publishArtifact := !scalaBinaryVersion.value.startsWith("3")

def docTask(docDir: File, resDir: File, projectName: String): File = {
  val docLibDir = docDir / "lib"
  val htmlSrcDir = resDir / "html"
  val cssFile = docLibDir / "template.css"
  val addlCssFile = htmlSrcDir / "addl.css"

  val css = Source.fromFile(cssFile).mkString
  val addlCss = Source.fromFile(addlCssFile).mkString

  if (!css.contains("pre.stHighlighted")) {
    val writer = new PrintWriter(cssFile)

    try {
      writer.println(css)
      writer.println(addlCss)
    }
    finally { writer.close }
  }

  if (projectName.contains("scalatest")) {
    (htmlSrcDir * "*.gif").get.foreach { gif =>
      IO.copyFile(gif, docLibDir / gif.name)
    }
  }
  docDir
}

Compile / doc := docTask((Compile / doc).value,
                          (Compile / sourceDirectory).value,
                          name.value)

Compile / doc / scalacOptions := Seq("-doc-title", s"ScalaTest + Mockito ${version.value}", 
                                       "-sourcepath", baseDirectory.value.getAbsolutePath(), 
                                       "-doc-source-url", s"https://github.com/scalatest/releases-source/blob/main/scalatestplus-mockito/${version.value}€{FILE_PATH}.scala")