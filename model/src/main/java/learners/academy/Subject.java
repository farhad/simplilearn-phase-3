package learners.academy;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Subject {
    private Long id;
    private String title;
    private String description;
}
