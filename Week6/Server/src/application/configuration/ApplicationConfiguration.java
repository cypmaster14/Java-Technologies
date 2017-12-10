package application.configuration;

import javax.security.auth.login.Configuration;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by SilviuG on 20.11.2017.
 */
public class ApplicationConfiguration {

    private static final String APPLICATION_PROPERTIES_PATH_ENVIRONMENT_KEY = "APPLICATION_CONFIG_PATH";
    private static final Properties properties;

    // use static initializer to read the configuration file when the class is loaded
    static {
        String applicationPropertiesPath = System.getenv(APPLICATION_PROPERTIES_PATH_ENVIRONMENT_KEY);
        properties = new Properties();
        try (InputStream inputStream = new FileInputStream(applicationPropertiesPath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file " + System.getenv(applicationPropertiesPath), e);
        }
    }

    public static Map<String, String> getConfiguration() {
        // ugly workaround to get String as generics
        Map<String, String> map = new HashMap<>((Map) properties);
        // prevent the returned configuration from being modified
        return Collections.unmodifiableMap(map);
    }


    public static String getConfigurationValue(String key) {
        return properties.getProperty(key);
    }

    // private constructor to prevent initialization
    private ApplicationConfiguration() {
    }

}
