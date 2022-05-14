package learners.academy.factory;

import learners.academy.base.Constants;
import learners.academy.reader.PropertyFileLoader;

public class ConfigurationFactory {

    public static DBConnectionConfig createDbConnectionConfig(PropertyFileLoader loader) {
        String username = loader.get(Constants.USERNAME);
        String password = loader.get(Constants.PASSWORD);
        String port = loader.get(Constants.PORT);
        String databaseName = loader.get(Constants.DATABASE_NAME);
        return new DBConnectionConfig(username, password, port, databaseName);
    }
}
