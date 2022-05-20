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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static learners.academy.user.UserKeys.*;

@Named("UserDao")
@Dependent
public class UserDao implements IDao<User> {

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
    public List<User> find(Map<String, String> params) throws DataException {
        if (params.get(USERNAME) != null && params.get(PASSWORD) != null) {
            try (var connection = dataSourceConnector.connect()) {
                var users = new ArrayList<User>();
                var query = MessageFormat.format(
                        "SELECT * FROM Users WHERE username = ''{0}'' AND password = ''{1}'' ;",
                        params.get(USERNAME),
                        params.get(PASSWORD));

                ResultSet resultSet = connection.createStatement().executeQuery(query);
                while (resultSet.next()) {
                    users.add(User.builder()
                            .id(resultSet.getLong(ID))
                            .firstName(resultSet.getString(FIRST_NAME))
                            .lastName(resultSet.getString(LAST_NAME))
                            .userName(resultSet.getString(USERNAME))
                            .password(resultSet.getString(PASSWORD))
                            .assignedRole(resultSet.getString(ASSIGNED_ROLE)).build());
                }

                return users;

            } catch (SQLException e) {
                throw new DataException(e);
            }
        }

        return new ArrayList<>();
    }
}
