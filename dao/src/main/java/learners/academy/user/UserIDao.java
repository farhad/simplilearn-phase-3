package learners.academy.user;

import learners.academy.User;
import learners.academy.base.DataException;
import learners.academy.base.IDao;
import learners.academy.connector.IDataSourceConnector;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static learners.academy.user.UserKeys.*;

@Named
@Dependent
public class UserIDao implements IDao<User> {

    @Inject
    private IDataSourceConnector dataSourceConnector;

    @Override
    public List<User> getAll() throws DataException {
        return null;
    }

    @Override
    public int update(User row) throws DataException {
        return 0;
    }

    @Override
    public int insert(User row) throws DataException {
        return 0;
    }

    @Override
    public int delete(long id) throws DataException {
        return 0;
    }

    @Override
    public Optional<User> find(Map<String, String> params) throws DataException {
        if (params.get(USERNAME) != null && params.get(PASSWORD) != null) {
            var query = MessageFormat.format(
                    "SELECT * FROM Users WHERE username = ''{0}'' AND password = ''{1}''",
                    params.get(USERNAME),
                    params.get(PASSWORD));

            ResultSet resultSet;
            try (var connection = dataSourceConnector.connect()) {
                resultSet = connection.createStatement().executeQuery(query);
                Optional<User> user = Optional.empty();
                while (resultSet.next()) {
                    user = Optional.of(new User(
                            resultSet.getString(ID),
                            resultSet.getString(FIRST_NAME),
                            resultSet.getString(LAST_NAME),
                            resultSet.getString(USERNAME),
                            resultSet.getString(PASSWORD),
                            resultSet.getString(ASSIGNED_ROLE)
                    ));
                }

                return user;

            } catch (SQLException e) {
                throw new DataException(e);
            }
        }

        return Optional.empty();
    }
}
