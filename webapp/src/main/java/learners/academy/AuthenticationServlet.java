package learners.academy;

import learners.academy.base.ConfigKeys;
import learners.academy.connector.MySqlConnector;
import learners.academy.factory.ConfigurationFactory;
import learners.academy.factory.DBConnectionConfig;
import learners.academy.reader.EnvVariableReader;
import learners.academy.reader.PropertyFileLoader;
import learners.academy.user.UserDao;
import learners.academy.user.UserKeys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

@WebServlet(urlPatterns = "/auth", name = "auth")
public class AuthenticationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("auth.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var username = req.getParameter("username");
        var password = req.getParameter("password");

        if (username == null) {
            req.setAttribute("error", "username is empty");
            req.getRequestDispatcher("auth").forward(req, resp);
        } else if (password == null) {
            req.setAttribute("error", "password is empty");
            req.getRequestDispatcher("auth").forward(req, resp);
        } else {

            PropertyFileLoader propertyFileLoader = PropertyFileLoader.from(new EnvVariableReader().get(ConfigKeys.CONFIG_FILE_PATH));
            DBConnectionConfig dbConnectionConfig = ConfigurationFactory.createDbConnectionConfig(propertyFileLoader);
            Optional<Connection> connection = new MySqlConnector().connect(dbConnectionConfig);
            if (connection.isPresent()) {
                try {
                    var map = new HashMap<String, String>();
                    map.put(UserKeys.USERNAME, username);
                    map.put(UserKeys.PASSWORD, password);
                    var user = new UserDao(connection.get()).find(map);
                    if (user.isPresent()) {
                        if (user.get().getUserName().equals(username) && user.get().getPassword().equals(password)) {
                            req.getSession().setAttribute("session_id", "1");
                            resp.sendRedirect("dashboard");
                        } else {
                            req.setAttribute("error", "invalid username or password");
                            req.getRequestDispatcher("auth").forward(req, resp);
                        }
                    } else {
                        req.setAttribute("error", "invalid username or password");
                        req.getRequestDispatcher("auth").forward(req, resp);
                    }
                    connection.get().close();
                } catch (Exception exception) {
                    try {
                        connection.get().close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                req.setAttribute("error", "cannot access database");
                req.getRequestDispatcher("auth").forward(req, resp);
            }
        }
    }
}
