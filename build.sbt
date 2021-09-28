scalaVersion := "2.12.15"
organization := "com.example"
name := "snowpark-docker-sample"
resolvers += "OSGeo Release Repository" at "https://repo.osgeo.org/repository/release/"
libraryDependencies ++= Seq(
    "com.snowflake" % "snowpark" % "0.6.0",
)
enablePlugins(JavaAppPackaging)
