package org.legurun.test.fakemailserver.config;

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
}
