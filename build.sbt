name := "dblargedataloader"
organization := "org.dsnkostic"
 
version := "1.0" 
      
lazy val `dblargedataloader` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  guice,
  javaJpa,
  "org.hibernate" % "hibernate-core" % "5.4.9.Final",
  "org.postgresql" % "postgresql" % "42.3.1"
)
      