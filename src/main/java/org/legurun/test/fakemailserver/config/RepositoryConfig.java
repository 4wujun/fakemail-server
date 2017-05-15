package org.legurun.test.fakemailserver.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "org.legurun.test.fakemailserver.dao")
public class RepositoryConfig {
	private static final Logger LOG = LoggerFactory.getLogger(RepositoryConfig.class);

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		LOG.trace("Initialisation dataSource");
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		ds.setUrl(environment.getRequiredProperty("jdbc.url"));
		ds.setUsername(environment.getRequiredProperty("jdbc.username"));
		ds.setPassword(environment.getRequiredProperty("jdbc.password"));
		ds.setValidationQuery(environment.getRequiredProperty("dbcp.validationQuery"));
		return ds;
	}

	@Bean
	@Autowired
	public JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
		LOG.trace("Initialisation sessionFactory");
		JpaTransactionManager htm = new JpaTransactionManager();
		htm.setEntityManagerFactory(entityManagerFactory);
		return htm;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LOG.trace("Initialisation entityManagerFactoryBean");
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan("org.legurun.test.fakemailserver.model");
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		entityManagerFactoryBean.setJpaProperties(jpaProperties());
		return entityManagerFactoryBean;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		LOG.trace("Initialisation jpaVendorAdapter");
		return new HibernateJpaVendorAdapter();
	}

	@Bean
	public Properties jpaProperties() {
		LOG.trace("Initialisation hibernateProperties");
		Properties properties = new Properties();
		properties.put(AvailableSettings.DIALECT, environment.getRequiredProperty("hibernate.dialect"));
		properties.put(AvailableSettings.SHOW_SQL, environment.getRequiredProperty("hibernate.show_sql"));
		properties.put(AvailableSettings.FORMAT_SQL, environment.getRequiredProperty("hibernate.format_sql"));
		properties.put(AvailableSettings.GENERATE_STATISTICS,
				environment.getRequiredProperty("hibernate.generate_statistics"));
		properties.put(AvailableSettings.HBM2DDL_AUTO, environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		if (environment.getProperty("hibernate.hbm2ddl.import_files") != null) {
			properties.put(AvailableSettings.HBM2DDL_LOAD_SCRIPT_SOURCE, environment.getProperty("hibernate.hbm2ddl.import_files"));
		}
		return properties;
	}

	@Conditional(LiquibaseCondition.class)
	@Bean
	public SpringLiquibase liquibase() {
		LOG.trace("Initialisation liquibase");
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:/liquibase/changelog-master.xml");
		liquibase.setDataSource(dataSource());
		return liquibase;
	}

	private static class LiquibaseCondition implements Condition {
		@Override
		public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
			Environment env = context.getEnvironment();
			return env != null && env.getProperty("liquibase.enabled", Boolean.class, true);
		}
	}
}
