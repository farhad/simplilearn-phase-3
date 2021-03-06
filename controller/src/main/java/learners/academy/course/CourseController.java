package learners.academy.course;

import learners.academy.Course;
import learners.academy.base.DataException;
import learners.academy.base.IController;
import learners.academy.base.IDao;
import learners.academy.base.ViewState;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@Named("Course")
@SessionScoped
public class CourseController implements IController<Course> {

    @Inject
    private @Named("CourseDao") IDao<Course> dao;

    @Override
    public ViewState<Course> getList() {
        try {
            return new ViewState<>(null, dao.getAll());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<Course> add(Course item) {
        try {
            var rowsAffected = dao.insert(item);
            String message = null;
            if (rowsAffected == 0) {
                message = "failed to insert course";
            }
            return new ViewState<>(message, new ArrayList<>());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<Course> update(Course item) {
        try {
            var rowsAffected = dao.update(item);
            String message = null;
            if (rowsAffected == 0) {
                message = "failed to update course";
            }
            return new ViewState<>(message, new ArrayList<>());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<Course> delete(Course item) {
        try {
            var rowsAffected = dao.delete(item.getId());
            String message = null;
            if (rowsAffected == 0) {
                message = "failed to delete course";
            }
            return new ViewState<>(message, new ArrayList<>());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }
}
