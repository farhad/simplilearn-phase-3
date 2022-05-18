package learners.academy.factory;

import learners.academy.base.ConfigKeys;
import learners.academy.reader.ConfigReader;

public class ConfigurationFactory {

    public static DBConnectionConfig createDbConnectionConfig(ConfigReader configReader) {
        String username = configReader.get(ConfigKeys.USERNAME);
        String password = configReader.get(ConfigKeys.PASSWORD);
        String port = configReader.get(ConfigKeys.PORT);
        String databaseName = configReader.get(ConfigKeys.DATABASE_NAME);
        return new DBConnectionConfig(username, password, port, databaseName);
    }
}
