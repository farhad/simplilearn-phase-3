package learners.academy.user;

import learners.academy.User;
import learners.academy.base.Dao;
import learners.academy.base.DataException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static learners.academy.user.UserConsts.*;

@RequiredArgsConstructor
public class UserDao implements Dao<User> {

    @NonNull
    private Connection connection;


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
        if (params.containsKey(USERNAME) && params.containsKey(PASSWORD)) {
            var query = new StringBuilder()
                    .append("SELECT * FROM Users WHERE username =")
                    .append("'")
                    .append(params.get(USERNAME))
                    .append("'")
                    .append(" AND password =")
                    .append("'")
                    .append(params.get(PASSWORD))
                    .append("'")
                    .append(";").toString();

            ResultSet resultSet;
            try {
                resultSet = connection.createStatement().executeQuery(query);

                Optional<User> user = Optional.empty();
                while (resultSet.next()) {
                    user = Optional.of(new User(
                            resultSet.getString(COLUMN_ID),
                            resultSet.getString(COLUMN_FIRST_NAME),
                            resultSet.getString(COLUMN_LAST_NAME),
                            resultSet.getString(COLUMN_USER_NAME),
                            resultSet.getString(COLUMN_PASS_WORD),
                            resultSet.getString(COLUMN_ASSIGNED_ROLE)
                    ));
                }

                return user;

            } catch (SQLException e) {
                throw DataException.of(e.getMessage());
            }
        }

        return Optional.empty();
    }
}
