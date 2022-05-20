package learners.academy.enrollment;

import learners.academy.Enrollment;
import learners.academy.base.DataException;
import learners.academy.base.IController;
import learners.academy.base.IDao;
import learners.academy.base.ViewState;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@Named("Enrollment")
@SessionScoped
public class EnrollmentController implements IController<Enrollment> {

    @Inject
    private @Named("EnrollmentDao") IDao<Enrollment> dao;

    @Override
    public ViewState<Enrollment> getList() {
        try {
            return new ViewState<>(null, dao.getAll());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<Enrollment> add(Enrollment enrollment) {
        try {
            var rowsAffected = dao.insert(enrollment);
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
    public ViewState<Enrollment> update(Enrollment enrollment) {
        try {
            var rowsAffected = dao.update(enrollment);
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
    public ViewState<Enrollment> delete(Enrollment enrollment) {
        try {
            var rowsAffected = dao.delete(enrollment.getId());
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
