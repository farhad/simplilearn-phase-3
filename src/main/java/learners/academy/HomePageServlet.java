package learners.academy;

import learners.academy.config.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "", name = "HomePageServlet")
public class HomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().print("Learner's Academy Home Page");

        PropertyFileLoader propertyFileLoader = PropertyFileLoader.from(EnvVariableReader.get(Constants.CONFIG_FILE_PATh));
        DbConnectionConfig dbConnectionConfig = ConfigurationFactory.createDbConnectionConfig(propertyFileLoader);

    }
}
