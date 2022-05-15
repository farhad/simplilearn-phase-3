package learners.academy.factory;

import learners.academy.base.ConfigKeys;
import learners.academy.reader.PropertyFileLoader;

public class ConfigurationFactory {

    public static DBConnectionConfig createDbConnectionConfig(PropertyFileLoader loader) {
        String username = loader.get(ConfigKeys.USERNAME);
        String password = loader.get(ConfigKeys.PASSWORD);
        String port = loader.get(ConfigKeys.PORT);
        String databaseName = loader.get(ConfigKeys.DATABASE_NAME);
        return new DBConnectionConfig(username, password, port, databaseName);
    }
}
