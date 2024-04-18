package com.pridanov.mcs2.config;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
public class PostgresTestContainersInitializer implements  ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Container
    private static final JdbcDatabaseContainer POSTGRES_CONTAINER = new PostgreSQLContainer<>("postgres:12.2");

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        POSTGRES_CONTAINER.start();
        TestPropertyValues.of("spring.datasource.url=" + POSTGRES_CONTAINER.getJdbcUrl())
                .and("spring.datasource.username=" + POSTGRES_CONTAINER.getUsername())
                .and("spring.datasource.password=" + POSTGRES_CONTAINER.getPassword())
                .applyTo(configurableApplicationContext.getEnvironment());
    }

}
