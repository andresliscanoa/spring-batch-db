package com.lliscano.one.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class OneDataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.one")
    public DataSourceProperties oneDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource oneDataSource() {
        return oneDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
