package example
import com.snowflake.snowpark.Session

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
