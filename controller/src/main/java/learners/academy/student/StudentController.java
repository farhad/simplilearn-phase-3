package learners.academy.student;

import learners.academy.Student;
import learners.academy.base.DataException;
import learners.academy.base.ViewState;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@Named
@SessionScoped
public class StudentController implements IStudentController {

    @Inject
    private StudentDao dao;

    @Override
    public ViewState<Student> getStudentsList() {
        try {
            return new ViewState<>(null, dao.getAll());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<Student> addStudent(Student student) {
        try {
            var rowsAffected = dao.insert(student);
            String message = null;
            if (rowsAffected == 0) {
                message = "failed to insert student";
            }
            return new ViewState<>(message, new ArrayList<>());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<Student> updateStudent(Student student) {
        try {
            var rowsAffected = dao.update(student);
            String message = null;
            if (rowsAffected == 0) {
                message = "failed to update student";
            }
            return new ViewState<>(message, new ArrayList<>());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<Student> deleteStudent(Student student) {
        try {
            var rowsAffected = dao.delete(student.getId());
            String message = null;
            if (rowsAffected == 0) {
                message = "failed to delete student";
            }
            return new ViewState<>(message, new ArrayList<>());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }
}
