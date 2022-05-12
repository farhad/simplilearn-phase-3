package learners.academy.config;

import lombok.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "from")
@ToString
@EqualsAndHashCode
public class PropertyFileLoader {

    @NonNull
    private String fileUri;

    private Properties properties = new Properties();

    public String get(String propertyName) throws RuntimeException {
        try (FileInputStream inputStream = new FileInputStream(fileUri)) {
            properties.load(inputStream);
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
