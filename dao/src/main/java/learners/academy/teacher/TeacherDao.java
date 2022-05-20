package learners.academy.teacher;

import learners.academy.Teacher;
import learners.academy.base.DataException;
import learners.academy.base.IDao;
import learners.academy.connector.IDataSourceConnector;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named("TeacherDao")
@Dependent
public class TeacherDao implements IDao<Teacher> {

    @Inject
    private IDataSourceConnector dataSourceConnector;

    @Override
    public List<Teacher> getAll() throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "SELECT * FROM Teachers ORDER BY last_name, first_name;";
            var resultSet = connection.createStatement().executeQuery(query);
            var teachers = new ArrayList<Teacher>();
            while (resultSet.next()) {
                teachers.add(Teacher.builder()
                        .id(resultSet.getLong(TeacherKeys.ID))
                        .firstName(resultSet.getString(TeacherKeys.FIRST_NAME))
                        .lastName(resultSet.getString(TeacherKeys.LAST_NAME))
                        .bio(resultSet.getString(TeacherKeys.BIO))
                        .build());
            }

            return teachers;
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public int update(Teacher row) throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "UPDATE Teachers SET first_name = ?, last_name = ?,  bio = ? WHERE id = ?;";
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, row.getFirstName());
            statement.setString(2, row.getLastName());
            statement.setString(3, row.getBio());
            statement.setLong(4, row.getId());
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public int insert(Teacher row) throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "INSERT INTO Teachers(first_name, last_name, bio) VALUES(?,?,?);";
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, row.getFirstName());
            statement.setString(2, row.getLastName());
            statement.setString(3, row.getBio());
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public int delete(long id) throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "DELETE FROM Teachers WHERE id = ?;";
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public List<Teacher> find(Map<String, String> params) throws DataException {
        return null;
    }
}
