package learners.academy.reader;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EnvVariableReader implements ConfigReader {

    public String get(String key) {
        return System.getenv(key);
    }
}
