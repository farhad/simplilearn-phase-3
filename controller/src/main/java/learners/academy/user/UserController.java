package learners.academy.user;

import learners.academy.User;
import learners.academy.base.DataException;
import learners.academy.base.IDao;
import learners.academy.base.ViewState;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Map;

@Named
@SessionScoped
public class UserController implements IUserController {

    @Inject
    private @Named("UserDao") IDao<User> dao;

    @Override
    public ViewState<User> getList() {
        try {
            return new ViewState<>(null, dao.getAll());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<User> add(User item) {
        try {
            var rowsAffected = dao.insert(item);
            String message = null;
            if (rowsAffected == 0) {
                message = "failed to insert user";
            }
            return new ViewState<>(message, new ArrayList<>());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<User> update(User item) {
        try {
            var rowsAffected = dao.update(item);
            String message = null;
            if (rowsAffected == 0) {
                message = "failed to update user";
            }
            return new ViewState<>(message, new ArrayList<>());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<User> delete(User item) {
        try {
            var rowsAffected = dao.delete(item.getId());
            String message = null;
            if (rowsAffected == 0) {
                message = "failed to delete user";
            }
            return new ViewState<>(message, new ArrayList<>());
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }

    @Override
    public ViewState<User> find(Map<String, String> map) {
        try {
            var data = dao.find(map);
            String errorMessage = null;
            if (data.isEmpty()) {
                errorMessage = "invalid username or password";
            }
            return new ViewState<>(errorMessage, data);
        } catch (DataException e) {
            return new ViewState<>(e.getMessage(), new ArrayList<>());
        }
    }
}
