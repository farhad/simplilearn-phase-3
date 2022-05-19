package learners.academy.subject;

import learners.academy.Subject;
import learners.academy.base.DataException;
import learners.academy.base.IController;
import learners.academy.base.ViewState;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@Named("Subject")
@SessionScoped
public class SubjectController implements IController<Subject> {

    @Inject
    private SubjectDao dao;

    @Override
    public ViewState<Subject> getList() {
        try {
            return new ViewState<>(null, dao.getAll());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<Subject> add(Subject subject) {
        try {
            var rowsAffected = dao.insert(subject);
            String message = null;
            if (rowsAffected == 0) {
                message = "failed to insert subject";
            }
            return new ViewState<>(message, new ArrayList<>());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<Subject> update(Subject subject) {
        try {
            var rowsAffected = dao.update(subject);
            String message = null;
            if (rowsAffected == 0) {
                message = "failed to update subject";
            }
            return new ViewState<>(message, new ArrayList<>());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<Subject> delete(Subject subject) {
        try {
            var rowsAffected = dao.delete(subject.getId());
            String message = null;
            if (rowsAffected == 0) {
                message = "failed to delete subject";
            }
            return new ViewState<>(message, new ArrayList<>());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }
}
