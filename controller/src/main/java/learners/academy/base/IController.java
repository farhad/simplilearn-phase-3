package learners.academy.base;

import java.io.Serializable;

public interface IController<T> extends Serializable {

    ViewState<T> getList();

    ViewState<T> add(T item);

    ViewState<T> update(T item);

    ViewState<T> delete(T item);
}
