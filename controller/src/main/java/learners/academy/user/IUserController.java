package learners.academy.user;

import learners.academy.User;
import learners.academy.base.IController;
import learners.academy.base.ViewState;

import java.util.Map;

public interface IUserController extends IController<User> {
    ViewState<User> find(Map<String, String> map);
}
