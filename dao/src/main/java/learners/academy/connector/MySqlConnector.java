package learners.academy.connector;

import learners.academy.base.AppConfig;
import learners.academy.factory.DBConnectionConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

public class MySqlConnector implements DataSourceConnector {

    private static final String jdbcDriverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:";

    private volatile static MySqlConnector instance;
    private static final Object mutex = new Object();

    public static MySqlConnector getInstance() {
        MySqlConnector result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null) {
                    instance = result = new MySqlConnector();
                }
            }
        }

        return result;
    }


    @Override
    public Optional<Connection> connect(AppConfig appConfig) {
        try {
            if (!(appConfig instanceof DBConnectionConfig))
                return Optional.empty();

            Class.forName(jdbcDriverName);
            DBConnectionConfig dbConnectionConfig = (DBConnectionConfig) appConfig;
            String dbUrl = url + dbConnectionConfig.getPort() + "/" + dbConnectionConfig.getDatabaseName();
            Connection connection = DriverManager.getConnection(dbUrl, dbConnectionConfig.getUsername(), dbConnectionConfig.getPassword());
            return Optional.of(connection);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

}
