package learners.academy.student;

import com.mysql.cj.util.StringUtils;
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

@WebServlet(urlPatterns = {"/student", "/student/add", "/student/edit", "/student/save", "/student/delete"}, name = "student")
public class StudentServlet extends HttpServlet {

    @Inject
    @Named("Student")
    private IController<Student> controller;

    private static final String ATTR_STUDENTS_LIST = "studentsList";
    private static final String ATTR_STUDENT = "student";
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
            case "/student": {
                displayStudentsList(req, resp);
                break;
            }

            case "/student/add": {
                displayAddStudentPage(req, resp);
                break;
            }

            case "/student/edit": {
                displayEditStudentPage(req, resp);
                break;
            }

            case "/student/save": {
                saveStudentAndUpdateStudentsList(req, resp);
                break;
            }

            case "/student/delete": {
                deleteStudentAndUpdateStudentList(req, resp);
                break;
            }
        }
    }

    private void displayAddStudentPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/student_update.jsp").forward(req, resp);
    }

    private void displayEditStudentPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var student = buildFromRequest(req);
        req.setAttribute(ATTR_STUDENT, student);
        req.getRequestDispatcher("/student_update.jsp").forward(req, resp);
    }

    private void deleteStudentAndUpdateStudentList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var student = buildFromRequest(req);

        if (!StringUtils.isEmptyOrWhitespaceOnly(req.getParameter(StudentKeys.ID))) {
            req.setAttribute(ATTR_SAVE_ERROR, controller.delete(student).getErrorMessage());
        }

        var listViewState = controller.getList();
        req.setAttribute(ATTR_FETCH_ERROR, listViewState.getErrorMessage());
        req.setAttribute(ATTR_STUDENTS_LIST, listViewState.getData());
        resp.sendRedirect(req.getContextPath() + "/student");
    }

    private void saveStudentAndUpdateStudentsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var student = buildFromRequest(req);

        if (StringUtils.isEmptyOrWhitespaceOnly(req.getParameter(StudentKeys.ID))) {
            req.setAttribute(ATTR_SAVE_ERROR, controller.add(student).getErrorMessage());
        } else {
            req.setAttribute(ATTR_SAVE_ERROR, controller.update(student).getErrorMessage());
        }

        req.removeAttribute(ATTR_STUDENT);

        var listViewState = controller.getList();
        req.setAttribute(ATTR_FETCH_ERROR, listViewState.getErrorMessage());
        req.setAttribute(ATTR_STUDENTS_LIST, listViewState.getData());
        resp.sendRedirect(req.getContextPath() + "/student");
    }

    private void displayStudentsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var viewState = controller.getList();
        req.setAttribute(ATTR_FETCH_ERROR, viewState.getErrorMessage());
        req.setAttribute(ATTR_STUDENTS_LIST, viewState.getData());
        req.getRequestDispatcher("/student.jsp").forward(req, resp);
    }

    private Student buildFromRequest(HttpServletRequest req) {
        Long id = null;
        if (!StringUtils.isEmptyOrWhitespaceOnly(req.getParameter(StudentKeys.ID))) {
            id = Long.valueOf(req.getParameter(StudentKeys.ID));
        }

        return Student.builder()
                .id(id)
                .firstName(req.getParameter(StudentKeys.FIRST_NAME))
                .lastName(req.getParameter(StudentKeys.LAST_NAME))
                .build();
    }
}
