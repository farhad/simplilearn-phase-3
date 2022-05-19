package learners.academy.teacher;

import learners.academy.Teacher;
import learners.academy.base.DataException;
import learners.academy.base.IController;
import learners.academy.base.ViewState;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@Named("Teacher")
@SessionScoped
public class TeacherController implements IController<Teacher> {

    @Inject
    private TeacherDao dao;

    @Override
    public ViewState<Teacher> getList() {
        try {
            return new ViewState<>(null, dao.getAll());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<Teacher> add(Teacher teacher) {
        try {
            var rowsAffected = dao.insert(teacher);
            String message = null;
            if (rowsAffected == 0) {
                message = "failed to insert teacher";
            }
            return new ViewState<>(message, new ArrayList<>());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<Teacher> update(Teacher teacher) {
        try {
            var rowsAffected = dao.update(teacher);
            String message = null;
            if (rowsAffected == 0) {
                message = "failed to update teacher";
            }
            return new ViewState<>(message, new ArrayList<>());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<Teacher> delete(Teacher teacher) {
        try {
            var rowsAffected = dao.delete(teacher.getId());
            String message = null;
            if (rowsAffected == 0) {
                message = "failed to delete teacher";
            }
            return new ViewState<>(message, new ArrayList<>());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }
}
