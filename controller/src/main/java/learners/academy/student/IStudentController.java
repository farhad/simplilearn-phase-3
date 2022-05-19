package learners.academy.student;

import learners.academy.Student;
import learners.academy.base.ViewState;

import java.io.Serializable;

public interface IStudentController extends Serializable {

    ViewState<Student> getStudentsList();

    ViewState<Student> addStudent(Student student);

    ViewState<Student> updateStudent(Student student);

    ViewState<Student> deleteStudent(Student student);
}
