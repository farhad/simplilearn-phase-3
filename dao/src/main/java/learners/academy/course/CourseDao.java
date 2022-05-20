package learners.academy.course;

import learners.academy.Course;
import learners.academy.Subject;
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

@Named("CourseDao")
@Dependent
public class CourseDao implements IDao<Course> {

    @Inject
    private IDataSourceConnector dataSourceConnector;

    @Override
    public List<Course> getAll() throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "SELECT c.id, c.title AS course_title, c.description AS course_description, s.id AS subject_id, s.title AS subject_title, "
                    + " s.description AS subject_description, t.id AS teacher_id, t.first_name AS teacher_first_name, t.last_name AS teacher_last_name"
                    + " FROM Courses AS c, Subjects AS s, Teachers AS t"
                    + " WHERE c.subject_id = s.id AND c.teacher_id = t.id"
                    + " ORDER BY c.title, c.description;";
            var resultSet = connection.createStatement().executeQuery(query);
            var courses = new ArrayList<Course>();
            while (resultSet.next()) {
                var subject = Subject.builder()
                        .id(resultSet.getLong(CourseKeys.SUBJECT_ID))
                        .title(resultSet.getString(CourseKeys.SUBJECT_TITLE))
                        .description(resultSet.getString(CourseKeys.SUBJECT_DESCRIPTION)).build();

                var teacher = Teacher.builder()
                        .id(resultSet.getLong(CourseKeys.TEACHER_ID))
                        .firstName(resultSet.getString(CourseKeys.TEACHER_FIRST_NAME))
                        .lastName(resultSet.getString(CourseKeys.TEACHER_LAST_NAME))
                        .build();

                courses.add(Course.builder()
                        .id(resultSet.getLong(CourseKeys.ID))
                        .title(resultSet.getString(CourseKeys.COURSE_TITLE))
                        .description(resultSet.getString(CourseKeys.COURSE_DESCRIPTION))
                        .subject(subject)
                        .teacher(teacher)
                        .build());
            }

            return courses;
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public int update(Course row) throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "UPDATE Courses SET subject_id = ?, teacher_id = ?, title = ?, `description` = ? WHERE id = ?;";
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, row.getSubject().getId());
            statement.setLong(2, row.getTeacher().getId());
            statement.setString(3, row.getTitle());
            statement.setString(4, row.getDescription());
            statement.setLong(5, row.getId());
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public int insert(Course row) throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "INSERT INTO Courses(subject_id, teacher_id, title, `description`) VALUES(?,?,?,?);";
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, row.getSubject().getId());
            statement.setLong(2, row.getTeacher().getId());
            statement.setString(3, row.getTitle());
            statement.setString(4, row.getDescription());
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public int delete(long id) throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "DELETE FROM Courses WHERE id = ?;";
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public List<Course> find(Map<String, String> params) throws DataException {
        return null;
    }
}
