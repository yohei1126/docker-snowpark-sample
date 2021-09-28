# Docker code example for Snowpark app

This Docker image helps you to run SQL andd show the result using Snowpark. 

## Set up

* Install Docker https://www.docker.com/

## Build a image

```
docker build -t docker-snowpark-sample .
```

## Run a container from the image

* Required environment variables
  * `URL`: Snowflake URL you are accessing to. It must be like `https://xxx.ap-southast-1.snowflakecomputing.com` 
  * `USER`: Your Snowflake user name
  * `PASSWORD`: password

```
docker run --rm \
  -e URL="https://your-account-name.region.snowflakecomputing.com" \
  -e USER="your-user" \
  -e PASSWORD="your-password" \
  docker-snowpark-sample
```

## Optional environment variables

* Main code is as follows.
* So you can optionally specify environment variables
  * ROLE
  * WAREHOUSE
  * DB
  * SCHEMA

```
object Main extends App {
  val url = sys.env.get("URL").get
  val user = sys.env.get("USER").get
  val password = sys.env.get("PASSWORD").get
  val role = sys.env.get("ROLE").getOrElse("SYSADMIN")
  val warehouse = sys.env.get("WAREHOUSE").getOrElse("COMPUTE_WH")
  val db = sys.env.get("DB").getOrElse("DEMO_DB")
  val schema = sys.env.get("SCHEMA").getOrElse("INFORMATION_SCHEMA")
  val sql = sys.env.get("SQL").getOrElse("SELECT * FROM views")
  val configs = Map(
      "URL" -> url,
      "USER" -> user,
      "PASSWORD" -> password,
      "ROLE" -> role,
      "WAREHOUSE" -> warehouse,
      "DB" -> db,
      "SCHEMA" -> schema
  )
  val session = Session.builder.configs(configs).create
  val df = session.sql(sql)
  df.show()
}
```
