package learners.academy;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Course {
    private Long id;
    private String title;
    private String description;
    private Subject subject;
    private Teacher teacher;
}
