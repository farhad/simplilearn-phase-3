package learners.academy.config;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DbConnectionConfig implements AppConfig {
    private String username;
    private String password;
    private String mysqlPort;
    private String databaseName;
}
