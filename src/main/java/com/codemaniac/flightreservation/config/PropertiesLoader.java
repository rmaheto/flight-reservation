package com.codemaniac.flightreservation.config;

import com.codemaniac.flightreservation.exception.FileLoadException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    private Properties props;

    public PropertiesLoader() {
        props = loadProperties();
    }

    public String getProperty(String propertyName) {
        return props.getProperty(propertyName);
    }
    private Properties loadProperties() {
        Properties allProps = new Properties();
        // Load properties from the first file
        Properties file1Props = loadPropertiesFromFile("frs_keys.properties");
        allProps.putAll(file1Props);
        return allProps;
    }


    private Properties loadPropertiesFromFile(String fileName) {
        try {
            String applicationPath = new File("").getAbsolutePath();
            String keysPath = new File(applicationPath).getParentFile().getAbsolutePath() + File.separator + "keys";
            String filePath = new File(keysPath, fileName).getAbsolutePath();
            try (FileReader fileReader = new FileReader(filePath)) {
                Properties props = new Properties();
                props.load(fileReader);
                return props;
            }
        } catch (IOException e) {
            throw new FileLoadException("Failed to load properties file: " + fileName, e);
        }
    }
}
