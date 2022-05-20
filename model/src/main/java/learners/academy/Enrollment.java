package learners.academy;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Enrollment {
    private Long id;
    private Student student;
    private Course course;
}
