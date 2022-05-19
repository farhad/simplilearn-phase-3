package learners.academy.subject;

import learners.academy.Subject;
import learners.academy.base.DataException;
import learners.academy.base.ViewState;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@Named
@SessionScoped
public class SubjectController implements ISubjectController {

    @Inject
    private SubjectDao dao;

    @Override
    public ViewState<Subject> getSubjectsList() {
        try {
            return new ViewState<>(null, dao.getAll());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<Subject> addSubject(Subject subject) {
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
    public ViewState<Subject> updateSubject(Subject subject) {
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
    public ViewState<Subject> deleteSubject(Subject subject) {
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
