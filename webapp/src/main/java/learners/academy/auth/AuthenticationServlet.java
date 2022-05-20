package learners.academy.auth;

import com.mysql.cj.util.StringUtils;
import learners.academy.user.IUserController;
import learners.academy.user.UserKeys;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/auth", "/auth/login"}, name = "auth")
public class AuthenticationServlet extends HttpServlet {

    @Inject
    private IUserController userController;

    private static final String ATTR_AUTH_ERROR = "auth_error";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/auth": {
                req.getRequestDispatcher("/auth.jsp").forward(req, resp);
                break;
            }

            case "/auth/login": {
                authenticateAndRedirectUser(req, resp);
                break;
            }
        }
    }

    private void authenticateAndRedirectUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var map = new HashMap<String, String>();
        map.put(UserKeys.USERNAME, req.getParameter(UserKeys.USERNAME));
        map.put(UserKeys.PASSWORD, req.getParameter(UserKeys.PASSWORD));
        var viewState = userController.find(map);

        if (StringUtils.isEmptyOrWhitespaceOnly(viewState.getErrorMessage())) {
            req.getSession(false).removeAttribute(ATTR_AUTH_ERROR);
            req.getSession().setAttribute(UserKeys.SESSION_ID, viewState.getData().get(0).fullName());
            resp.sendRedirect(req.getContextPath() + "/dashboard");
        } else {
            req.getSession(false).setAttribute(ATTR_AUTH_ERROR, viewState.getErrorMessage());
            resp.sendRedirect(req.getContextPath() + "/auth");
        }
    }
}
