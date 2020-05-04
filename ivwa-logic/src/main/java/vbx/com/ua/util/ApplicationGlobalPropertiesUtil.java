package vbx.com.ua.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationGlobalPropertiesUtil {
    private static ApplicationGlobalPropertiesUtil instance;
    private final String PROPERTIES_NAME = "application-global-properties.properties";
    private Properties properties;

    public ApplicationGlobalPropertiesUtil() {
        this.init();
    }

    private void init() {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_NAME)) {
            this.properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ApplicationGlobalPropertiesUtil getInstance() {
        if (instance == null) {
            instance = new ApplicationGlobalPropertiesUtil();
        }
        return instance;
    }

    public String getProperty(final String key) {
        return this.properties.getProperty(key);
    }
}
