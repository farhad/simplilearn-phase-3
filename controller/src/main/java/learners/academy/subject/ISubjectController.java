package learners.academy.subject;

import learners.academy.Subject;
import learners.academy.base.ViewState;

import java.io.Serializable;

public interface ISubjectController extends Serializable {

    ViewState<Subject> getSubjectsList();

    ViewState<Subject> addSubject(Subject Subject);

    ViewState<Subject> updateSubject(Subject Subject);

    ViewState<Subject> deleteSubject(Subject Subject);
}
