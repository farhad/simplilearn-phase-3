package learners.academy.connector;

import java.sql.Connection;

public interface IDataSourceConnector {
    Connection connect();
}
