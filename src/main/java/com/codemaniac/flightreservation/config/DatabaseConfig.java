package com.codemaniac.flightreservation.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.codemaniac.flightreservation.repository")
@RequiredArgsConstructor
public class DatabaseConfig {
    private final PropertiesLoader propertiesLoader;
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(propertiesLoader.getProperty("db.username"));
        dataSource.setPassword(propertiesLoader.getProperty("db.password"));
        return dataSource;
    }
}
