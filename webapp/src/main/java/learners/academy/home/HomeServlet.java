package learners.academy.home;

import com.mysql.cj.util.StringUtils;
import learners.academy.user.UserKeys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "", name = "home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        if (req.getSession() != null
                && req.getSession().getAttribute(UserKeys.SESSION_ID) != null
                && !StringUtils.isEmptyOrWhitespaceOnly(req.getSession().getAttribute(UserKeys.SESSION_ID).toString())) {
            resp.sendRedirect("student");
        } else if (req.getSession() == null
                || req.getSession().getAttribute(UserKeys.SESSION_ID) == null) {
            resp.sendRedirect("auth");
        }
    }
}
