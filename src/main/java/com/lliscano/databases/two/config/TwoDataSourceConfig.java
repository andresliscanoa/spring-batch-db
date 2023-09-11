package com.lliscano.databases.two.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class TwoDataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.two")
    public DataSourceProperties twoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource twoDataSource() {
        return twoDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
