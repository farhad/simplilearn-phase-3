package learners.academy;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Teacher {
    private String id;
    private String firstName;
    private String lastName;
    private String bio;
}
