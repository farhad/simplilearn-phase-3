package learners.academy.factory;

import learners.academy.base.AppConfig;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DBConnectionConfig implements AppConfig {
    private String username;
    private String password;
    private String port;
    private String databaseName;
}
