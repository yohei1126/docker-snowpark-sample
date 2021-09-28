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
[main]  INFO (Logging.scala:22) - Closing stderr and redirecting to stdout
[main]  INFO (Logging.scala:22) - Done closing stderr and redirecting to stdout
[main]  INFO (Logging.scala:22) - Actively querying parameter snowpark_lazy_analysis from server.
[main]  INFO (Logging.scala:22) - Execute query [queryID: 019f413a-3200-4639-0000-0000800d21a1]  SELECT  *  FROM (select * from views) LIMIT 10
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
|"TABLE_CATALOG"  |"TABLE_SCHEMA"      |"TABLE_NAME"           |"TABLE_OWNER"  |"VIEW_DEFINITION"  |"CHECK_OPTION"  |"IS_UPDATABLE"  |"INSERTABLE_INTO"  |"IS_SECURE"  |"CREATED"  |"LAST_ALTERED"  |"COMMENT"                                           |
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
|DEMO_DB          |INFORMATION_SCHEMA  |TABLES                 |NULL           |NULL               |NONE            |NO              |NO                 |NO           |NULL       |NULL            |The tables defined in this database that are ac...  |
|DEMO_DB          |INFORMATION_SCHEMA  |COLUMNS                |NULL           |NULL               |NONE            |NO              |NO                 |NO           |NULL       |NULL            |The columns of tables defined in this database ...  |
|DEMO_DB          |INFORMATION_SCHEMA  |SCHEMATA               |NULL           |NULL               |NONE            |NO              |NO                 |NO           |NULL       |NULL            |The schemas defined in this database that are a...  |
|DEMO_DB          |INFORMATION_SCHEMA  |SEQUENCES              |NULL           |NULL               |NONE            |NO              |NO                 |NO           |NULL       |NULL            |The sequences defined in this database that are...  |
|DEMO_DB          |INFORMATION_SCHEMA  |VIEWS                  |NULL           |NULL               |NONE            |NO              |NO                 |NO           |NULL       |NULL            |The views defined in this database that are acc...  |
|DEMO_DB          |INFORMATION_SCHEMA  |TABLE_PRIVILEGES       |NULL           |NULL               |NONE            |NO              |NO                 |NO           |NULL       |NULL            |The privileges on tables defined in this databa...  |
|DEMO_DB          |INFORMATION_SCHEMA  |USAGE_PRIVILEGES       |NULL           |NULL               |NONE            |NO              |NO                 |NO           |NULL       |NULL            |The usage privileges on sequences defined in th...  |
|DEMO_DB          |INFORMATION_SCHEMA  |DATABASES              |NULL           |NULL               |NONE            |NO              |NO                 |NO           |NULL       |NULL            |The databases that are accessible to the curren...  |
|DEMO_DB          |INFORMATION_SCHEMA  |REPLICATION_DATABASES  |NULL           |NULL               |NONE            |NO              |NO                 |NO           |NULL       |NULL            |The databases for replication that are accessib...  |
|DEMO_DB          |INFORMATION_SCHEMA  |FUNCTIONS              |NULL           |NULL               |NONE            |NO              |NO                 |NO           |NULL       |NULL            |The user-defined functions defined in this data...  |
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
  val sql = sys.env.get("SQL").getOrElse("select * from views")
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
