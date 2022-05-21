package learners.academy.course;

import com.mysql.cj.util.StringUtils;
import learners.academy.Course;
import learners.academy.Subject;
import learners.academy.Teacher;
import learners.academy.base.IController;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/course", "/course/add", "/course/edit", "/course/save", "/course/delete"}, name = "course")
public class CourseServlet extends HttpServlet {

    @Inject
    private @Named("Course") IController<Course> courseController;

    @Inject
    private @Named("Subject") IController<Subject> subjectController;

    @Inject
    private @Named("Teacher") IController<Teacher> teacherController;

    private static final String ATTR_COURSES_LIST = "coursesList";
    private static final String ATTR_SUBJECTS_LIST = "subjectsList";
    private static final String ATTR_SELECTED_SUBJECT_ID = "selectedSubjectId";
    private static final String ATTR_TEACHERS_LIST = "teachersList";
    private static final String ATTR_SELECTED_TEACHER_ID = "selectedTeacherId";
    private static final String ATTR_COURSE = "course";
    private static final String ATTR_FETCH_ERROR = "fetch_error";
    private static final String ATTR_SAVE_ERROR = "save_error";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        switch (req.getServletPath()) {
            case "/course": {
                displayCoursesList(req, resp);
                break;
            }

            case "/course/add": {
                displayAddCoursePage(req, resp);
                break;
            }

            case "/course/edit": {
                displayEditCoursePage(req, resp);
                break;
            }

            case "/course/save": {
                saveCourseAndUpdateCoursesList(req, resp);
                break;
            }

            case "/course/delete": {
                deleteCourseAndUpdateCourseList(req, resp);
                break;
            }
        }
    }

    private void displayAddCoursePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var subjects = subjectController.getList().getData();
        var teachers = teacherController.getList().getData();
        req.setAttribute(ATTR_SUBJECTS_LIST, subjects);
        req.setAttribute(ATTR_TEACHERS_LIST, teachers);
        req.getRequestDispatcher("/course_update.jsp").forward(req, resp);
    }

    private void displayEditCoursePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var course = buildFromRequest(req);
        req.setAttribute(ATTR_COURSE, course);

        var subjects = subjectController.getList().getData();
        req.setAttribute(ATTR_SUBJECTS_LIST, subjects);
        req.setAttribute(ATTR_SELECTED_SUBJECT_ID, course.getSubject().getId());

        var teachers = teacherController.getList().getData();
        req.setAttribute(ATTR_TEACHERS_LIST, teachers);
        req.setAttribute(ATTR_SELECTED_TEACHER_ID, course.getTeacher().getId());

        req.getRequestDispatcher("/course_update.jsp").forward(req, resp);
    }

    private void deleteCourseAndUpdateCourseList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var course = buildFromRequest(req);
        if (!StringUtils.isEmptyOrWhitespaceOnly(req.getParameter(CourseKeys.ID))) {
            req.setAttribute(ATTR_SAVE_ERROR, courseController.delete(course).getErrorMessage());
        }

        var listViewState = courseController.getList();
        req.setAttribute(ATTR_FETCH_ERROR, listViewState.getErrorMessage());
        req.setAttribute(ATTR_COURSES_LIST, listViewState.getData());
        resp.sendRedirect(req.getContextPath());
    }

    private void saveCourseAndUpdateCoursesList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var course = buildFromRequest(req);

        if (StringUtils.isEmptyOrWhitespaceOnly(req.getParameter(CourseKeys.ID))) {
            req.setAttribute(ATTR_SAVE_ERROR, courseController.add(course).getErrorMessage());
        } else {
            req.setAttribute(ATTR_SAVE_ERROR, courseController.update(course).getErrorMessage());
        }

        req.removeAttribute(ATTR_COURSE);

        var listViewState = courseController.getList();
        req.setAttribute(ATTR_FETCH_ERROR, listViewState.getErrorMessage());
        req.setAttribute(ATTR_COURSES_LIST, listViewState.getData());
        resp.sendRedirect(req.getContextPath() + "/course");
    }

    private void displayCoursesList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var viewState = courseController.getList();
        req.setAttribute(ATTR_FETCH_ERROR, viewState.getErrorMessage());
        req.setAttribute(ATTR_COURSES_LIST, viewState.getData());
        req.getRequestDispatcher("/course.jsp").forward(req, resp);
    }

    private Course buildFromRequest(HttpServletRequest request) {
        Long id = null;
        if (!StringUtils.isEmptyOrWhitespaceOnly(request.getParameter(CourseKeys.ID))) {
            id = Long.valueOf(request.getParameter(CourseKeys.ID));
        }

        return Course.builder()
                .id(id)
                .subject(Subject.builder().id(Long.valueOf(request.getParameter(CourseKeys.SUBJECT_ID))).build())
                .teacher(Teacher.builder().id(Long.valueOf(request.getParameter(CourseKeys.TEACHER_ID))).build())
                .title(request.getParameter(CourseKeys.COURSE_TITLE))
                .description(request.getParameter(CourseKeys.COURSE_DESCRIPTION))
                .build();
    }
}
