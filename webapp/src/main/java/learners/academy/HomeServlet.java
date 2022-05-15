package learners.academy;

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
        var session = req.getSession();
        if (session.getAttribute("session_id") == null) {
            req.getSession().setAttribute("session_id", "1");
            resp.sendRedirect("auth.jsp");
        } else {
            resp.getWriter().println("already logged in");
        }
    }
}
