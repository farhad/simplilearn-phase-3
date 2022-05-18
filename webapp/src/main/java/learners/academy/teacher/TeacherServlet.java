package learners.academy.teacher;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/teacher", name = "teacher")
public class TeacherServlet extends HttpServlet {

    @Inject
    private ITeacherController controller;

    private static final String ATTR_TEACHERS_LIST = "teachersList";
    private static final String ATTR_ERROR = "error";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        if (req.getPathInfo() == null) {
            displayTeachersList(req, resp);
        }
    }

    private void displayTeachersList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var viewState = controller.getTeachersList();
        req.setAttribute(ATTR_ERROR, viewState.getErrorMessage());
        req.setAttribute(ATTR_TEACHERS_LIST, viewState.getData());
        req.getRequestDispatcher("teacher.jsp").forward(req, resp);
    }
}
