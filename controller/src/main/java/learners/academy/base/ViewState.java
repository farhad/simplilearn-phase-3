package learners.academy.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ViewState<T> {
    private String errorMessage;
    private List<T> data;
}
