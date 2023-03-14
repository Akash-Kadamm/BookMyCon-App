package com.dbMigration.demo.dabaseConfig;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;


/*
 * ***** Mysql Database Configurations ******
 *
 * */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2TransactionManager",
        basePackages = "com.dbMigration.demo.mysql"

)
public class DataBase2Config {

    /*
     * Create DataSource object which make database connection.
     *
     * @param
     * @return DataSource object
     * */
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "db2.datasource")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().build();
    }


    /*
     * Creating EntityManagerFactoryBean object which provides all the operation of database.
     *
     * @param EntityManagerFactoryBuilder
     * @return LocalContainerEntityManagerFactoryBean object
     *
     * */
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(
            EntityManagerFactoryBuilder builder
    ) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        return builder
                .dataSource(db2DataSource())
                .packages("com.dbMigration.demo.payload")
                .properties(properties)
                .build();
    }


    /*
     * Creating Transaction Manager bean for making transactions.
     *
     * @param EntityManagerFactory object
     * @return JpaTransactionManager object
     *
     * */
    @Primary
    @Bean
    public PlatformTransactionManager db2TransactionManager(
            @Qualifier("db2EntityManagerFactory")
            EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}