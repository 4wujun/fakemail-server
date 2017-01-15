package org.legurun.test.fakemailserver.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		LOG.trace("Initialisation sessionFactory");
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(sessionFactory);
		return htm;
	}

	@Bean
	@Autowired
	public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
		LOG.trace("Initialisation hibernateTemplate");
		HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
		return hibernateTemplate;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LOG.trace("Initialisation sessionFactory");
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setHibernateProperties(hibernateProperties());
		sessionFactory.setPackagesToScan("org.legurun.test.fakemailserver.model");
		return sessionFactory;
	}

	@Bean
	public Properties hibernateProperties() {
		LOG.trace("Initialisation hibernateProperties");
		Properties properties = new Properties();
		properties.put(AvailableSettings.DIALECT, environment.getRequiredProperty("hibernate.dialect"));
		properties.put(AvailableSettings.SHOW_SQL, environment.getRequiredProperty("hibernate.show_sql"));
		properties.put(AvailableSettings.FORMAT_SQL, environment.getRequiredProperty("hibernate.format_sql"));
		properties.put(AvailableSettings.GENERATE_STATISTICS,
				environment.getRequiredProperty("hibernate.generate_statistics"));
		properties.put(AvailableSettings.HBM2DDL_AUTO, environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}
}
