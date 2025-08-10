package Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class ReadConfig {

    private final Properties properties;

    public ReadConfig() throws IOException {

        File srcFile = new File("src/test/java/properties/config.properties");
        FileInputStream fis = new FileInputStream(srcFile);

        properties = new Properties();
        properties.load(fis);

    }

    public String getBrowser() {

        return properties.getProperty("BROWSER").toLowerCase();

    }

    public String getBaseUrl() {

        return properties.getProperty("BASE_URL").toLowerCase();

    }

}
