package learners.academy.teacher;

import learners.academy.Teacher;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/teacher", "/teacher/add", "/teacher/edit", "/teacher/save", "/teacher/delete"}, name = "teacher")
public class TeacherServlet extends HttpServlet {

    @Inject
    private ITeacherController controller;

    private static final String ATTR_TEACHERS_LIST = "teachersList";
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
            case "/teacher": {
                displayTeachersList(req, resp);
                break;
            }

            case "/teacher/add": {
                displayAddTeacherPage(req, resp);
                break;
            }

            case "/teacher/edit": {
                displayEditTeacherPage(req, resp);
                break;
            }

            case "/teacher/save": {
                saveTeacherAndUpdateTeachersList(req, resp);
                break;
            }

            case "/teacher/delete": {
                displayDeleteTeacherConfirm(req, resp);
                break;
            }
        }
    }

    private void displayAddTeacherPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/teacher_update.jsp").forward(req, resp);
    }

    private void displayEditTeacherPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/teacher_update.jsp").forward(req, resp);
    }

    private void displayDeleteTeacherConfirm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void saveTeacherAndUpdateTeachersList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var teacher = Teacher.builder()
                .id(req.getParameter(TeacherKeys.ID))
                .firstName(req.getParameter(TeacherKeys.FIRST_NAME))
                .lastName(req.getParameter(TeacherKeys.LAST_NAME))
                .bio(req.getParameter(TeacherKeys.BIO))
                .build();

        var saveViewState = controller.addTeacher(teacher);
        req.setAttribute(ATTR_SAVE_ERROR, saveViewState.getErrorMessage());

        var listViewState = controller.getTeachersList();
        req.setAttribute(ATTR_FETCH_ERROR, listViewState.getErrorMessage());
        req.setAttribute(ATTR_TEACHERS_LIST, listViewState.getData());
        resp.sendRedirect(req.getContextPath());
    }

    private void displayTeachersList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var viewState = controller.getTeachersList();
        req.setAttribute(ATTR_FETCH_ERROR, viewState.getErrorMessage());
        req.setAttribute(ATTR_TEACHERS_LIST, viewState.getData());
        req.getRequestDispatcher("/teacher.jsp").forward(req, resp);
    }
}