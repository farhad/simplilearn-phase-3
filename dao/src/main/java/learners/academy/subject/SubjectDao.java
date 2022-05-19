package learners.academy.subject;

import learners.academy.Subject;
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

@Named("SubjectDao")
@Dependent
public class SubjectDao implements IDao<Subject> {

    @Inject
    private IDataSourceConnector dataSourceConnector;

    @Override
    public List<Subject> getAll() throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "SELECT * FROM Subjects ORDER BY title DESC;";
            var resultSet = connection.createStatement().executeQuery(query);
            var subjects = new ArrayList<Subject>();
            while (resultSet.next()) {
                subjects.add(Subject.builder()
                        .id(resultSet.getLong(SubjectKeys.ID))
                        .title(resultSet.getString(SubjectKeys.TITLE))
                        .description(resultSet.getString(SubjectKeys.DESCRIPTION))
                        .build());
            }
            return subjects;
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public int update(Subject row) throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "UPDATE Subjects SET title = ?, `description` = ? WHERE id = ?;";
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, row.getTitle());
            statement.setString(2, row.getDescription());
            statement.setLong(3, row.getId());
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public int insert(Subject row) throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "INSERT INTO Subjects (title, `description`) VALUES(?,?);";
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, row.getTitle());
            statement.setString(2, row.getDescription());
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public int delete(long id) throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "DELETE FROM Subjects WHERE id = ?;";
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public Optional<Subject> find(Map<String, String> params) throws DataException {
        return Optional.empty();
    }
}
