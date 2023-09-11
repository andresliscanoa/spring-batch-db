package com.lliscano.databases.one.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.lliscano.databases.one.repository"},
        entityManagerFactoryRef = "oneEntityManagerFactory",
        transactionManagerRef = "oneTransactionManager"
)
public class OneConfig {
    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilderOne() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean oneEntityManagerFactory(
            @Qualifier("oneDataSource") DataSource dataSource,
            @Qualifier("entityManagerFactoryBuilderOne") EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.lliscano.databases.one.entity")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager oneTransactionManager(
            @Qualifier("oneEntityManagerFactory") LocalContainerEntityManagerFactoryBean oneEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(oneEntityManagerFactory.getObject()));
    }
}
