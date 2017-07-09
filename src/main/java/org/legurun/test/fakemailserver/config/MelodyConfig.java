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
import javax.servlet.Filter;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import net.bull.javamelody.MonitoringSpringAdvisor;
import net.bull.javamelody.SpringContext;
import net.bull.javamelody.SpringDataSourceFactoryBean;

/**
 * Configure JavaMelody.
 *
 * @author patrice
 * @since 2017
 * @see <a href="https://github.com/javamelody/javamelody">Java Melody</a>
 */
@Configuration
public class MelodyConfig {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			LoggerFactory.getLogger(MelodyConfig.class);

	/**
	 * Get the data source factory.
	 * @return Data source factory
	 */
	@Bean
	public SpringDataSourceFactoryBean monitoringDataSourceFactoryBean() {
		LOG.trace("Initialize monitoringDataSourceFactoryBean");
		final SpringDataSourceFactoryBean factory =
				new SpringDataSourceFactoryBean();
		factory.setTargetName("dataSource");
		return factory;
	}

	/**
	 * Get the spring context.
	 * @return Spring context
	 */
	@Bean
	public SpringContext javamelodySpringContext() {
		LOG.trace("Initialize javamelodySpringContext");
		return new SpringContext();
	}

	/**
	 * Get the service monitoring.
	 * @return Service monitoring advisor
	 */
	@Bean
	public MonitoringSpringAdvisor springServiceMonitoringAdvisor() {
		LOG.trace("Initialize springServiceMonitoringAdvisor");
		final MonitoringSpringAdvisor advisor =
				new MonitoringSpringAdvisor();
		advisor.setPointcut(
				new AnnotationMatchingPointcut(Service.class));
		return advisor;
	}

	/**
	 * Get the repository monitoring.
	 * @return Repository monitoring advisor
	 */
	@Bean
	public MonitoringSpringAdvisor springRepositoryMonitoringAdvisor() {
		LOG.trace("Initialize springRepositoryMonitoringAdvisor");
		final MonitoringSpringAdvisor advisor =
				new MonitoringSpringAdvisor();
		advisor.setPointcut(
				new AnnotationMatchingPointcut(Repository.class));
		return advisor;
	}

	/**
	 * Get the controller monitoring.
	 * @return Controller monitoring advisor
	 */
	@Bean
	public MonitoringSpringAdvisor springControllerMonitoringAdvisor() {
		LOG.trace("Initialize springControllerMonitoringAdvisor");
		final MonitoringSpringAdvisor advisor =
				new MonitoringSpringAdvisor();
		advisor.setPointcut(
				new AnnotationMatchingPointcut(Controller.class));
		return advisor;
	}

	/**
	 * Get the REST controller monitoring.
	 * @return REST controller monitoring advisor
	 */
	@Bean
	public MonitoringSpringAdvisor springRestControllerMonitoringAdvisor() {
		LOG.trace("Initialize springRestControllerMonitoringAdvisor");
		final MonitoringSpringAdvisor advisor =
				new MonitoringSpringAdvisor();
		advisor.setPointcut(
				new AnnotationMatchingPointcut(RestController.class));
		return advisor;
	}

	/**
	 * Get the JavaMelody session listener.
	 * @return Session listener
	 */
	@Bean
	public HttpSessionListener javaMelodyListener() {
		LOG.trace("Initialize javaMelodyListener");
		return new net.bull.javamelody.SessionListener();
	}

	/**
	 * Get the JavaMelody filter.
	 * @return Filter
	 */
	@Bean
	public Filter javaMelodyFilter() {
		LOG.trace("Initialize javaMelodyFilter");
		return new net.bull.javamelody.MonitoringFilter();
	}

}
