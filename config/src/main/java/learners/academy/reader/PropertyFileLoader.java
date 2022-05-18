package learners.academy.reader;

import learners.academy.base.ConfigKeys;
import lombok.*;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "from")
@ToString
@EqualsAndHashCode
@Named
@Dependent
public class PropertyFileLoader implements ConfigReader {

    private String fileUri = System.getenv(ConfigKeys.CONFIG_FILE_PATH);

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
