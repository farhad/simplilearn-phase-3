package learners.academy.enrollment;

import com.mysql.cj.util.StringUtils;
import learners.academy.Course;
import learners.academy.Enrollment;
import learners.academy.Student;
import learners.academy.base.IController;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/enrollment", "/enrollment/add", "/enrollment/edit", "/enrollment/save", "/enrollment/delete"}, name = "enrollment")
public class EnrollmentServlet extends HttpServlet {

    private static final String ATTR_ENROLLMENTS_LIST = "enrollmentsList";
    private static final String ATTR_COURSES_LIST = "coursesList";
    private static final String ATTR_STUDENTS_LIST = "studentsList";
    private static final String ATTR_SELECTED_COURSE_ID = "selectedCourseId";
    private static final String ATTR_SELECTED_STUDENT_ID = "selectedStudentId";
    private static final String ATTR_ENROLLMENT = "enrollment";
    private static final String ATTR_FETCH_ERROR = "fetch_error";
    private static final String ATTR_SAVE_ERROR = "save_error";

    @Inject
    private @Named("Enrollment") IController<Enrollment> enrollmentController;

    @Inject
    private @Named("Course") IController<Course> courseController;

    @Inject
    private @Named("Student") IController<Student> studentController;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        switch (req.getServletPath()) {
            case "/enrollment": {
                displayEnrollmentsList(req, resp);
                break;
            }

            case "/enrollment/add": {
                displayAddEnrollmentPage(req, resp);
                break;
            }

            case "/enrollment/edit": {
                displayEditEnrollmentPage(req, resp);
                break;
            }

            case "/enrollment/save": {
                saveEnrollmentAndUpdateEnrollmentsList(req, resp);
                break;
            }

            case "/enrollment/delete": {
                deleteEnrollmentAndUpdateEnrollmentList(req, resp);
                break;
            }
        }
    }

    private void displayAddEnrollmentPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var students = studentController.getList().getData();
        var courses = courseController.getList().getData();
        req.setAttribute(ATTR_STUDENTS_LIST, students);
        req.setAttribute(ATTR_COURSES_LIST, courses);
        req.getRequestDispatcher("/enrollment_update.jsp").forward(req, resp);
    }

    private void displayEditEnrollmentPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var enrollment = buildFromRequest(req);
        req.setAttribute(ATTR_ENROLLMENT, enrollment);

        var courses = courseController.getList().getData();
        req.setAttribute(ATTR_COURSES_LIST, courses);
        req.setAttribute(ATTR_SELECTED_COURSE_ID, enrollment.getCourse().getId());

        var students = studentController.getList().getData();
        req.setAttribute(ATTR_STUDENTS_LIST, students);
        req.setAttribute(ATTR_SELECTED_STUDENT_ID, enrollment.getStudent().getId());

        req.getRequestDispatcher("/enrollment_update.jsp").forward(req, resp);
    }

    private void deleteEnrollmentAndUpdateEnrollmentList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var enrollment = buildFromRequest(req);
        if (!StringUtils.isEmptyOrWhitespaceOnly(req.getParameter(EnrollmentKeys.ID))) {
            req.setAttribute(ATTR_SAVE_ERROR, enrollmentController.delete(enrollment).getErrorMessage());
        }

        var viewState = enrollmentController.getList();
        req.setAttribute(ATTR_FETCH_ERROR, viewState.getErrorMessage());
        req.setAttribute(ATTR_ENROLLMENTS_LIST, viewState.getData());
        resp.sendRedirect(req.getContextPath());
    }

    private void saveEnrollmentAndUpdateEnrollmentsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var enrollment = buildFromRequest(req);

        if (StringUtils.isEmptyOrWhitespaceOnly(req.getParameter(EnrollmentKeys.ID))) {
            req.setAttribute(ATTR_SAVE_ERROR, enrollmentController.add(enrollment).getErrorMessage());
        } else {
            req.setAttribute(ATTR_SAVE_ERROR, enrollmentController.update(enrollment).getErrorMessage());
        }

        req.removeAttribute(ATTR_ENROLLMENT);

        var viewState = enrollmentController.getList();
        req.setAttribute(ATTR_FETCH_ERROR, viewState.getErrorMessage());
        req.setAttribute(ATTR_ENROLLMENTS_LIST, viewState.getData());
        resp.sendRedirect(req.getContextPath());
    }

    private void displayEnrollmentsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var viewState = enrollmentController.getList();
        req.setAttribute(ATTR_FETCH_ERROR, viewState.getErrorMessage());
        req.setAttribute(ATTR_ENROLLMENTS_LIST, viewState.getData());
        req.getRequestDispatcher("/enrollment.jsp").forward(req, resp);
    }

    private Enrollment buildFromRequest(HttpServletRequest request) {
        Long id = null;
        if (!StringUtils.isEmptyOrWhitespaceOnly(request.getParameter(EnrollmentKeys.ID))) {
            id = Long.valueOf(request.getParameter(EnrollmentKeys.ID));
        }

        return Enrollment.builder().id(id)
                .student(Student.builder().id(Long.valueOf(request.getParameter(EnrollmentKeys.STUDENT_ID))).build())
                .course(Course.builder().id(Long.valueOf(request.getParameter(EnrollmentKeys.COURSE_ID))).build()).build();
    }
}
