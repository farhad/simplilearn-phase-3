package learners.academy.teacher;

import learners.academy.Teacher;
import learners.academy.base.ViewState;

public interface ITeacherController {

    ViewState<Teacher> getTeachersList();
}
