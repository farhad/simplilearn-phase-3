package learners.academy.enrollment;

import learners.academy.Course;
import learners.academy.Enrollment;
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

@Named("EnrollmentDao")
@Dependent
public class EnrollmentDao implements IDao<Enrollment> {

    @Inject
    private IDataSourceConnector dataSourceConnector;

    @Override
    public List<Enrollment> getAll() throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "SELECT e.id, e.student_id AS student_id, s.first_name AS student_first_name, s.last_name AS student_last_name, "
                    + " e.course_id AS course_id, c.title AS course_title, c.description AS course_description"
                    + " FROM Enrollments AS e, Students AS s, Courses AS c"
                    + " WHERE e.student_id = s.id AND e.course_id = c.id"
                    + " ORDER BY e.id";

            var resultSet = connection.createStatement().executeQuery(query);
            var enrollments = new ArrayList<Enrollment>();
            while (resultSet.next()) {
                var student = Student.builder()
                        .id(resultSet.getLong(EnrollmentKeys.STUDENT_ID))
                        .firstName(resultSet.getString(EnrollmentKeys.STUDENT_FIRST_NAME))
                        .lastName(resultSet.getString(EnrollmentKeys.STUDENT_LAST_NAME))
                        .build();
                var course = Course.builder()
                        .id(resultSet.getLong(EnrollmentKeys.COURSE_ID))
                        .title(resultSet.getString(EnrollmentKeys.COURSE_TITLE))
                        .description(resultSet.getString(EnrollmentKeys.COURSE_DESCRIPTION))
                        .build();

                var enrollment = Enrollment.builder()
                        .id(resultSet.getLong(EnrollmentKeys.ID))
                        .student(student)
                        .course(course)
                        .build();
                enrollments.add(enrollment);
            }
            return enrollments;
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public int update(Enrollment row) throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "UPDATE Enrollments SET student_id = ?, course_id = ? WHERE id = ?;";
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, row.getStudent().getId());
            statement.setLong(2, row.getCourse().getId());
            statement.setLong(3, row.getId());
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public int insert(Enrollment row) throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "INSERT INTO Enrollments(student_id, course_id) VALUES(?,?);";
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, row.getStudent().getId());
            statement.setLong(2, row.getCourse().getId());
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public int delete(long id) throws DataException {
        try (var connection = dataSourceConnector.connect()) {
            var query = "DELETE FROM Enrollments WHERE id = ?;";
            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            return statement.executeUpdate();
        } catch (Exception exception) {
            throw new DataException(exception);
        }
    }

    @Override
    public List<Enrollment> find(Map<String, String> params) throws DataException {
        return null;
    }
}
