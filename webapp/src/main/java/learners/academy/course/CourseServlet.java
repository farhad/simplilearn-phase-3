package learners.academy.course;

import com.mysql.cj.util.StringUtils;
import learners.academy.Course;
import learners.academy.base.BaseController;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/course", "/course/add", "/course/edit", "/course/save", "/course/delete"}, name = "course")
public class CourseServlet extends HttpServlet {

    @Inject
    private BaseController<Course> controller;

    private static final String ATTR_COURSES_LIST = "coursesList";
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
        req.getRequestDispatcher("/course_update.jsp").forward(req, resp);
    }

    private void displayEditCoursePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var course = buildFromRequest(req);
        req.setAttribute(ATTR_COURSE, course);
        req.getRequestDispatcher("/course_update.jsp").forward(req, resp);
    }

    private void deleteCourseAndUpdateCourseList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var course = buildFromRequest(req);
        if (!StringUtils.isEmptyOrWhitespaceOnly(req.getParameter(CourseKeys.ID))) {
            req.setAttribute(ATTR_SAVE_ERROR, controller.delete(course).getErrorMessage());
        }

        var listViewState = controller.getList();
        req.setAttribute(ATTR_FETCH_ERROR, listViewState.getErrorMessage());
        req.setAttribute(ATTR_COURSES_LIST, listViewState.getData());
        resp.sendRedirect(req.getContextPath());
    }

    private void saveCourseAndUpdateCoursesList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var course = buildFromRequest(req);

        if (StringUtils.isEmptyOrWhitespaceOnly(req.getParameter(CourseKeys.ID))) {
            req.setAttribute(ATTR_SAVE_ERROR, controller.add(course).getErrorMessage());
        } else {
            req.setAttribute(ATTR_SAVE_ERROR, controller.update(course).getErrorMessage());
        }

        req.removeAttribute(ATTR_COURSE);

        var listViewState = controller.getList();
        req.setAttribute(ATTR_FETCH_ERROR, listViewState.getErrorMessage());
        req.setAttribute(ATTR_COURSES_LIST, listViewState.getData());
        resp.sendRedirect(req.getContextPath());
    }

    private void displayCoursesList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var viewState = controller.getList();
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
                .subjectId(Long.valueOf(request.getParameter(CourseKeys.SUBJECT_ID)))
                .subjectTitle(request.getParameter(CourseKeys.SUBJECT_TITLE))
                .subjectDescription(request.getParameter(CourseKeys.SUBJECT_DESCRIPTION))
                .teacherId(Long.valueOf(request.getParameter(CourseKeys.TEACHER_ID)))
                .teacherFirstName(request.getParameter(CourseKeys.TEACHER_FIRST_NAME))
                .teacherLastName(request.getParameter(CourseKeys.TEACHER_LAST_NAME))
                .title(request.getParameter(CourseKeys.COURSE_TITLE))
                .description(request.getParameter(CourseKeys.COURSE_DESCRIPTION))
                .build();
    }
}
