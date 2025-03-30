package test;

import java.util.ResourceBundle;

/**
 * Variables for tests
 *
 * @author Anna
 */

public class EnvironmentVariables {
    private static ResourceBundle config;

    public static String getString(String name) {
        try {
            if (config == null) {
                final String configName = System.getProperty(SystemPropertyConst.ENVIRONMENT_NAME, "testCreds");
                System.out.println("Environment read from {}" + configName);
                config = ResourceBundle.getBundle(configName);
            }
            return config.getString(name);
        } catch (Exception e) {
            System.out.println("Error with Environment variables");
            throw e;
        }
    }
}
