package application.configuration;

/**
 * Created by SilviuG on 20.11.2017.
 */
public enum ConfigurationType {
    CONNECTION_PER_SESSION,
    SINGLETON_CONNECTION,
    CONNECTION_POOL;

    public static ConfigurationType getConfigurationType(String configurationType) {
        return ConfigurationType.valueOf(configurationType);
    }
}
