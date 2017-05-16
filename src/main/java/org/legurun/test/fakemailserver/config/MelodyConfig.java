package org.legurun.test.fakemailserver.config;

/*-
 * #%L
 * Fakemail server
 * %%
 * Copyright (C) 2017 Patrice Le Gurun
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
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

import net.bull.javamelody.MonitoringSpringAdvisor;
import net.bull.javamelody.SpringDataSourceFactoryBean;

@Configuration
public class MelodyConfig {
	private static final Logger LOG = LoggerFactory.getLogger(MelodyConfig.class);

	@Bean
	public SpringDataSourceFactoryBean monitoringDataSourceFactoryBean() {
		LOG.trace("Initialisation monitoringDataSourceFactoryBean");
		final SpringDataSourceFactoryBean factory = new SpringDataSourceFactoryBean();
		factory.setTargetName("dataSource");
		return factory;
	}

	@Bean
	public MonitoringSpringAdvisor springServiceMonitoringAdvisor() {
		LOG.trace("Initialisation springServiceMonitoringAdvisor");
		final MonitoringSpringAdvisor advisor = new MonitoringSpringAdvisor();
		advisor.setPointcut(new AnnotationMatchingPointcut(Service.class));
		return advisor;
	}

	@Bean
	public MonitoringSpringAdvisor springRepositoryMonitoringAdvisor() {
		LOG.trace("Initialisation springRepositoryMonitoringAdvisor");
		final MonitoringSpringAdvisor advisor = new MonitoringSpringAdvisor();
		advisor.setPointcut(new AnnotationMatchingPointcut(Repository.class));
		return advisor;
	}

	@Bean
	public MonitoringSpringAdvisor springControllerMonitoringAdvisor() {
		LOG.trace("Initialisation springControllerMonitoringAdvisor");
		final MonitoringSpringAdvisor advisor = new MonitoringSpringAdvisor();
		advisor.setPointcut(new AnnotationMatchingPointcut(Controller.class));
		return advisor;
	}

	@Bean
	public HttpSessionListener javaMelodyListener() {
		LOG.trace("Initialisation javaMelodyListener");
		return new net.bull.javamelody.SessionListener();
	}

	@Bean
	public Filter javaMelodyFilter() {
		LOG.trace("Initialisation javaMelodyFilter");
		return new net.bull.javamelody.MonitoringFilter();
	}

}
