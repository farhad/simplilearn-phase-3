package learners.academy.student;

import learners.academy.Student;
import learners.academy.base.DataException;
import learners.academy.base.IController;
import learners.academy.base.IDao;
import learners.academy.base.ViewState;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@Named("Student")
@SessionScoped
public class StudentController implements IController<Student> {

    @Inject
    private @Named("StudentDao") IDao<Student> dao;

    @Override
    public ViewState<Student> getList() {
        try {
            return new ViewState<>(null, dao.getAll());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<Student> add(Student student) {
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
    public ViewState<Student> update(Student student) {
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
    public ViewState<Student> delete(Student student) {
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
