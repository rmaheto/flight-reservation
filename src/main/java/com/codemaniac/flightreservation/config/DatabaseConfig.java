package com.codemaniac.flightreservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.codemaniac.flightreservation.repository")
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(getDbUrl());
        dataSource.setUsername(getDbUsername());
        dataSource.setPassword(getDbPassword());
        return dataSource;
    }

    private String getDbUrl() {
        return "jdbc:mysql://flightrsvdb.cxnuarwowrnu.us-east-1.rds.amazonaws.com:3306/flight-reservation";
    }

    private String getDbUsername() {
        Properties props = loadProperties();
        return props.getProperty("db.username");
    }

    private String getDbPassword() {
        Properties props = loadProperties();
        return props.getProperty("db.password");
    }

    private Properties loadProperties() {
        try {
            String applicationPath = new File("").getAbsolutePath();
            String keysPath = new File(applicationPath).getParentFile().getAbsolutePath() + File.separator + "keys";
            String filePath = new File(keysPath, "frs_keys.properties").getAbsolutePath();
            Properties props = new Properties();
            props.load(new FileReader(filePath));
            return props;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database properties file", e);
        }
    }
}
