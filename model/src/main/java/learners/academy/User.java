package learners.academy;


import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String assignedRole;
}
