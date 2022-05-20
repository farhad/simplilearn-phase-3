package learners.academy.base;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.SQLException;

@RequiredArgsConstructor
@Getter
@Setter
public class DataException extends Exception {
    @NonNull
    private Exception exception;

    public String getMessage() {
        if (exception instanceof SQLException) {
            return "failed to perform database operation. please try again! ---- " + exception.getMessage();
        }

        return exception.getLocalizedMessage();
    }
}
