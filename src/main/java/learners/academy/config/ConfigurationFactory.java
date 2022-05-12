package learners.academy.config;

public class ConfigurationFactory {

    public static DbConnectionConfig createDbConnectionConfig(PropertyFileLoader loader) {
        String username = loader.get(Constants.USERNAME);
        String password = loader.get(Constants.PASSWORD);
        String port = loader.get(Constants.PORT);
        String databaseName = loader.get(Constants.DATABASE_NAME);
        return new DbConnectionConfig(username, password, port, databaseName);
    }
}
