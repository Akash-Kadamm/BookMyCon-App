package com.ioffice.demo.config.postgresql;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

/*
 * ****  Postgresql configurations ********
 * 
 * */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "db1EntityManagerFactory",
		transactionManagerRef = "db1TransactionManager",
		basePackages = "com.ioffice.demo.repo.postgresql"
		
		)
public class DataBase1Config {

	
	/*
	 * Create DataSource object which make database connection.
	 * 
	 * @param 
	 * @return DataSource object
	 * */
	@Bean
	@ConfigurationProperties(prefix="db1.datasource")
	public DataSource db1DataSource() {
		return DataSourceBuilder.create().build();
	}
	
	
	/*
	 * Creating EntityManagerFactoryBean object which provides all the operation of database.
	 * 
	 * @param EntityManagerFactoryBuilder
	 * @return LocalContainerEntityManagerFactoryBean object
	 * 
	 * */
	@Bean
	public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(
			EntityManagerFactoryBuilder builder
			)
	{
		HashMap<String , Object> properties=new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		return  builder
				.dataSource(db1DataSource())
				.packages("com.ioffice.demo.model")
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
	@Bean
	public PlatformTransactionManager db1TransactionManager(
			@Qualifier("db1EntityManagerFactory")
			EntityManagerFactory entityManagerFactory
			)
	{
		return new JpaTransactionManager(entityManagerFactory);
	}
}
