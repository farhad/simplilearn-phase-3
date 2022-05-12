package learners.academy.data;

import learners.academy.config.DbConnectionConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnector {

    private static final String jdbcDriverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:";

    private volatile static MysqlConnector instance;
    private static final Object mutex = new Object();

    public static MysqlConnector getInstance() {
        MysqlConnector result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null) {
                    instance = result = new MysqlConnector();
                }
            }
        }

        return result;
    }

    public Connection connect(DbConnectionConfig dbConnectionConfig) throws ClassNotFoundException, SQLException {
        Class.forName(jdbcDriverName);
        String dbUrl = url + dbConnectionConfig.getMysqlPort() + "/" + dbConnectionConfig.getDatabaseName();
        return DriverManager.getConnection(dbUrl, dbConnectionConfig.getUsername(), dbConnectionConfig.getPassword());
    }

}
