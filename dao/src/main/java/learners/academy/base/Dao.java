package learners.academy.base;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Dao<T> {

    List<T> getAll() throws DataException;

    int update(T row) throws DataException;

    int insert(T row) throws DataException;

    int delete(long id) throws DataException;

    Optional<T> find(Map<String, String> params) throws DataException;
}
