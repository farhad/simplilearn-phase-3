package learners.academy.student;

import learners.academy.Student;
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
import java.util.Optional;

@Named
@Dependent
public class StudentDao implements IDao<Student> {

    @Inject
    private IDataSourceConnector dataSourceConnector;

    @Override
    public List<Student> getAll() throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "SELECT * FROM Students ORDER BY last_name, first_name;";
            var resultSet = connection.createStatement().executeQuery(query);
            var students = new ArrayList<Student>();
            while (resultSet.next()) {
                students.add(Student.builder()
                        .id(resultSet.getLong(StudentKeys.ID))
                        .firstName(resultSet.getString(StudentKeys.FIRST_NAME))
                        .lastName(resultSet.getString(StudentKeys.LAST_NAME))
                        .build());
            }

            return students;
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public int update(Student row) throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "UPDATE Students SET first_name = ?, last_name = ? WHERE id = ?;";
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, row.getFirstName());
            statement.setString(2, row.getLastName());
            statement.setLong(3, row.getId());
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public int insert(Student row) throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "INSERT INTO Students(first_name, last_name) VALUES(?,?);";
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, row.getFirstName());
            statement.setString(2, row.getLastName());
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public int delete(long id) throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "DELETE FROM Students WHERE id = ?;";
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public Optional<Student> find(Map<String, String> params) throws DataException {
        return Optional.empty();
    }
}
