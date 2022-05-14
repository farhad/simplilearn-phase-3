package learners.academy.reader;

import lombok.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "from")
@ToString
@EqualsAndHashCode
public class PropertyFileLoader implements ConfigReader {

    @NonNull
    private String fileUri;

    private Properties properties = new Properties();

    public String get(String key) throws RuntimeException {
        try (FileInputStream inputStream = new FileInputStream(fileUri)) {
            properties.load(inputStream);
            return properties.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
