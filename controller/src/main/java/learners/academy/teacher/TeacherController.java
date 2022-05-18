package learners.academy.teacher;

import learners.academy.Teacher;
import learners.academy.base.DataException;
import learners.academy.base.ViewState;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named
@SessionScoped
public class TeacherController implements ITeacherController, Serializable {

    @Inject
    private TeacherDao dao;

    @Override
    public ViewState<Teacher> getTeachersList() {
        try {
            return new ViewState<>(null, dao.getAll());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }
}
