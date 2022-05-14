package learners.academy.connector;

import learners.academy.base.AppConfig;

import java.sql.Connection;
import java.util.Optional;

public interface DataSourceConnector {
    Optional<Connection> connect(AppConfig appConfig);
}
