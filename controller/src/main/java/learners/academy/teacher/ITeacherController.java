package learners.academy.teacher;

import learners.academy.Teacher;
import learners.academy.base.ViewState;

public interface ITeacherController {

    ViewState<Teacher> getTeachersList();

    ViewState<Teacher> addTeacher(Teacher teacher);

    ViewState<Teacher> updateTeacher(Teacher teacher);

    ViewState<Teacher> deleteTeacher(Teacher teacher);
}
