package learners.academy.connector;

import learners.academy.factory.ConfigurationFactory;
import learners.academy.reader.ConfigReader;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.DriverManager;

@Named
@Dependent
public class MySqlConnector implements IDataSourceConnector {

    @Inject
    private ConfigReader configReader;

    private static final String jdbcDriverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:";

    @Override
    public Connection connect() {
        try {
            Class.forName(jdbcDriverName);
            var config = ConfigurationFactory.createDbConnectionConfig(configReader);
            String connectionString = url + config.getPort() + "/" + config.getDatabaseName();
            return DriverManager.getConnection(connectionString, config.getUsername(), config.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
