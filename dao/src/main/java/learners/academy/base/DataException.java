package learners.academy.base;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class DataException extends Exception {
    @NonNull
    private String message;
}
