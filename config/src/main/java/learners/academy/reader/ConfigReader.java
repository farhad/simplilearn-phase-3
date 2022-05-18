package learners.academy.reader;

import java.io.Serializable;

public interface ConfigReader extends Serializable {
    String get(String key);
}
