package learners.academy;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Course {
    private Long id;
    private Long subjectId;
    private Long teacherId;
    private String subjectTitle;
    private String subjectDescription;
    private String teacherFirstName;
    private String teacherLastName;
    private String title;
    private String description;
}
