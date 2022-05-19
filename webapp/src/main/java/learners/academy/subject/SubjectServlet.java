package learners.academy.subject;

import com.mysql.cj.util.StringUtils;
import learners.academy.Subject;
import learners.academy.base.IController;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/subject", "/subject/add", "/subject/edit", "/subject/save", "/subject/delete"}, name = "subject")
public class SubjectServlet extends HttpServlet {

    @Inject
    private @Named("Subject") IController<Subject> controller;

    private static final String ATTR_SUBJECTS_LIST = "subjectsList";
    private static final String ATTR_SUBJECT = "subject";
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
            case "/subject": {
                displaySubjectsList(req, resp);
                break;
            }

            case "/subject/add": {
                displayAddSubjectPage(req, resp);
                break;
            }

            case "/subject/edit": {
                displayEditSubjectPage(req, resp);
                break;
            }

            case "/subject/save": {
                saveSubjectAndUpdateSubjectsList(req, resp);
                break;
            }

            case "/subject/delete": {
                deleteSubjectAndUpdateSubjectList(req, resp);
                break;
            }
        }
    }

    private void displayAddSubjectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/subject_update.jsp").forward(req, resp);
    }

    private void displayEditSubjectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var subject = buildFromRequest(req);
        req.setAttribute(ATTR_SUBJECT, subject);
        req.getRequestDispatcher("/subject_update.jsp").forward(req, resp);
    }

    private void deleteSubjectAndUpdateSubjectList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var subject = buildFromRequest(req);

        if (!StringUtils.isEmptyOrWhitespaceOnly(req.getParameter(SubjectKeys.ID))) {
            req.setAttribute(ATTR_SAVE_ERROR, controller.delete(subject).getErrorMessage());
        }

        var listViewState = controller.getList();
        req.setAttribute(ATTR_FETCH_ERROR, listViewState.getErrorMessage());
        req.setAttribute(ATTR_SUBJECTS_LIST, listViewState.getData());
        resp.sendRedirect(req.getContextPath());
    }

    private void saveSubjectAndUpdateSubjectsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var subject = buildFromRequest(req);

        if (StringUtils.isEmptyOrWhitespaceOnly(req.getParameter(SubjectKeys.ID))) {
            req.setAttribute(ATTR_SAVE_ERROR, controller.add(subject).getErrorMessage());
        } else {
            req.setAttribute(ATTR_SAVE_ERROR, controller.update(subject).getErrorMessage());
        }

        req.removeAttribute(ATTR_SUBJECT);

        var listViewState = controller.getList();
        req.setAttribute(ATTR_FETCH_ERROR, listViewState.getErrorMessage());
        req.setAttribute(ATTR_SUBJECTS_LIST, listViewState.getData());
        resp.sendRedirect(req.getContextPath());
    }

    private void displaySubjectsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var viewState = controller.getList();
        req.setAttribute(ATTR_FETCH_ERROR, viewState.getErrorMessage());
        req.setAttribute(ATTR_SUBJECTS_LIST, viewState.getData());
        req.getRequestDispatcher("/subject.jsp").forward(req, resp);
    }

    private Subject buildFromRequest(HttpServletRequest request) {
        Long id = null;
        if (!StringUtils.isEmptyOrWhitespaceOnly(request.getParameter(SubjectKeys.ID))) {
            id = Long.valueOf(request.getParameter(SubjectKeys.ID));
        }

        return Subject.builder()
                .id(id)
                .title(request.getParameter(SubjectKeys.TITLE))
                .description(request.getParameter(SubjectKeys.DESCRIPTION))
                .build();
    }

}
