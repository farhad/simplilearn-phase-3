package learners.academy.config;

public class EnvVariableReader {

    public static String get(String key) {
        return System.getenv(key);
    }
}
