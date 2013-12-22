scalaVersion := "2.10.2"

organization := "nu.rinu"

name := "serverman"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(
  "nu.rinu" %% "sutil" % "0.0.7-SNAPSHOT",
  "org.specs2" %% "specs2" % "1.13"
)

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

licenses := Seq("BSD-style" -> url("http://www.opensource.org/licenses/bsd-license.php"))

homepage := Some(url("https://github.com/rinunu/sutil"))

publishArtifact in Test := false

pomExtra :=
  <parent>
    <groupId>org.sonatype.oss</groupId>
    <artifactId>oss-parent</artifactId>
    <version>7</version>
  </parent>
    <scm>
      <connection>scm:git:git@github.com:rinunu/serverman.git</connection>
      <developerConnection>scm:git:git@github.com:rinunu/serverman.git</developerConnection>
      <url>git@github.com:rinunu/serverman.git</url>
    </scm>
    <developers>
      <developer>
        <id>rinunu</id>
        <name>Rintaro Tsuchihashi</name>
        <url>https://github.com/rinunu</url>
      </developer>
    </developers>

useGpg := true
