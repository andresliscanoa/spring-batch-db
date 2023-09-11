package com.lliscano.databases.two.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        basePackages = {"com.lliscano.databases.two.repository"},
        entityManagerFactoryRef = "twoEntityManagerFactory",
        transactionManagerRef = "twoTransactionManager"
)
public class TwoConfig {
    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilderTwo() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean twoEntityManagerFactory(
            @Qualifier("twoDataSource") DataSource dataSource,
            @Qualifier("entityManagerFactoryBuilderTwo") EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.lliscano.databases.two.entity")
                .build();
    }

    @Bean
    public PlatformTransactionManager twoTransactionManager(
            @Qualifier("twoEntityManagerFactory") LocalContainerEntityManagerFactoryBean twoEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(twoEntityManagerFactory.getObject()));
    }
}
