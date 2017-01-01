package org.legurun.test.fakemailserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import net.bull.javamelody.MonitoringSpringAdvisor;
import net.bull.javamelody.SpringDataSourceBeanPostProcessor;

@Configuration
@ComponentScan(basePackages="org.legurun.test.fakemailserver.service")
public class RootConfig {
	private static final Logger LOG = LoggerFactory.getLogger(RootConfig.class);

	@Bean
	public SpringDataSourceBeanPostProcessor monitoringDataSourceBeanPostProcessor() {
		LOG.trace("Initialisation monitoringDataSourceBeanPostProcessor");
		final SpringDataSourceBeanPostProcessor processor = new SpringDataSourceBeanPostProcessor();
		processor.setExcludedDatasources(null);
		return processor;
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
