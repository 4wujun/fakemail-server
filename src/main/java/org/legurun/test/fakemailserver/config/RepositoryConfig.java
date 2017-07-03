package org.legurun.test.fakemailserver.config;

/*
 * Copyright (C) 2017 Patrice Le Gurun
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.cache.jcache.JCacheRegionFactory;
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

/**
 * Configuration for data access with JPA/Hibernate.
 *
 * <p>The data model is managed with Liquibase.
 *
 * @author patrice
 * @since 2017
 * @see <a href="http://www.liquibase.org/">Liquibase</a>
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "org.legurun.test.fakemailserver.dao")
public class RepositoryConfig {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			LoggerFactory.getLogger(RepositoryConfig.class);

	/**
	 * Execution environment.
	 */
	@Autowired
	private Environment environment;

	/**
	 * Get the data source.
	 * @return Data source
	 */
	@Bean
	public DataSource dataSource() {
		LOG.trace("Initialize dataSource");
		final BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(
				environment.getRequiredProperty("jdbc.driverClassName"));
		ds.setUrl(environment.getRequiredProperty("jdbc.url"));
		ds.setUsername(environment.getRequiredProperty("jdbc.username"));
		ds.setPassword(environment.getRequiredProperty("jdbc.password"));
		ds.setValidationQuery(
				environment.getRequiredProperty("dbcp.validationQuery"));
		return ds;
	}

	/**
	 * Get the transaction manager.
	 * @param entityManagerFactory Entity manager factory
	 * @return Transaction manager
	 */
	@Bean
	@Autowired
	public JpaTransactionManager transactionManager(
			final EntityManagerFactory entityManagerFactory) {
		LOG.trace("Initialize sessionFactory");
		final JpaTransactionManager htm = new JpaTransactionManager();
		htm.setEntityManagerFactory(entityManagerFactory);
		return htm;
	}

	/**
	 * Get the entity manager factory.
	 * @return Entity manager factory
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean
			entityManagerFactoryBean() {
		LOG.trace("Initialize entityManagerFactoryBean");
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
				new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan(
				"org.legurun.test.fakemailserver.model");
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		entityManagerFactoryBean.setJpaProperties(jpaProperties());
		return entityManagerFactoryBean;
	}

	/**
	 * Get the JPA adapter.
	 * @return JPA adapter
	 */
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		LOG.trace("Initialize jpaVendorAdapter");
		return new HibernateJpaVendorAdapter();
	}

	/**
	 * Get the JPA configuration properties.
	 * @return Configuration properties
	 */
	@SuppressWarnings("checkstyle:multiplestringliterals")
	@Bean
	public Properties jpaProperties() {
		LOG.trace("Initialize hibernateProperties");
		final Properties properties = new Properties();
		properties.put(AvailableSettings.DIALECT,
				environment.getRequiredProperty("hibernate.dialect"));
		properties.put(AvailableSettings.SHOW_SQL,
				environment.getRequiredProperty("hibernate.show_sql"));
		properties.put(AvailableSettings.FORMAT_SQL,
				environment.getRequiredProperty("hibernate.format_sql"));
		properties.put(AvailableSettings.GENERATE_STATISTICS,
				environment.getRequiredProperty(
						"hibernate.generate_statistics"));
		properties.put(AvailableSettings.HBM2DDL_AUTO,
				environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		if (environment.getProperty("hibernate.hbm2ddl.import_files")
				!= null) {
			properties.put(AvailableSettings.HBM2DDL_LOAD_SCRIPT_SOURCE,
					environment.getProperty("hibernate.hbm2ddl.import_files"));
		}
		properties.put(AvailableSettings.CACHE_REGION_FACTORY,
				JCacheRegionFactory.class);
		properties.put(AvailableSettings.USE_QUERY_CACHE, true);
		properties.put(AvailableSettings.USE_SECOND_LEVEL_CACHE, true);
		return properties;
	}

	/**
	 * Get the Liquibase engine.
	 * @return Liquibase engine
	 */
	@Conditional(LiquibaseCondition.class)
	@Bean
	public SpringLiquibase liquibase() {
		LOG.trace("Initialize liquibase");
		final SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:/liquibase/changelog-master.xml");
		liquibase.setDataSource(dataSource());
		return liquibase;
	}

	/**
	 * Liquibase engine activation condition.
	 * @author patrice
	 * @since 2017
	 */
	private static class LiquibaseCondition implements Condition {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean matches(final ConditionContext context,
				final AnnotatedTypeMetadata metadata) {
			final Environment env = context.getEnvironment();
			return env != null
					&& env.getProperty("liquibase.enabled",
							Boolean.class, true);
		}
	}
}
