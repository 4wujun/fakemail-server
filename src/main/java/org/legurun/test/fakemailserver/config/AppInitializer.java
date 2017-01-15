package org.legurun.test.fakemailserver.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	private static final Logger LOG = LoggerFactory.getLogger(AppInitializer.class);

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class, RepositoryConfig.class, MelodyConfig.class, SubEthaSMTPConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new HiddenHttpMethodFilter(), new CharacterEncodingFilter() };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		LOG.info("Use external configuration file " + System.getProperty("externalConfigurationLocation"));
		super.onStartup(servletContext);
	}
}
