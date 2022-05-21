#### Deployment Steps

1. create the database on MySQL using the `docs/ddl.sql` .

2. create a properties file, e.g `local.properties` with the following values

```
username=[your_mysql_username]
password=[your_mysql_password]
port=3306[ or your_mysql_port]
database_name=learners_academy

```

3. create an environment variable named `simplilearn_phase3_config_file_path`
   and assign the properties file path to it.
4. Install and run an `Apache TomEE` v8+ instance. Note that `Tomcat` won't work as we're using `Weld` for CDI.
5. run `mvn clean install && mvn -pl webapp tomcat7:deploy`
6. open `http://localhost:8080/lms`
7. login with `admin` and `admin`.
