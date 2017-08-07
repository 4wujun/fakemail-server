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

package org.legurun.test.fakemailserver.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Main initializer code.
 *
 * @author patrice
 * @since 2017
 */
public class AppInitializer
		extends AbstractAnnotationConfigDispatcherServletInitializer {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			LoggerFactory.getLogger(AppInitializer.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {
			RootConfig.class, RepositoryConfig.class,
			CacheConfig.class, MelodyConfig.class,
			SubEthaSMTPConfig.class
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {
			WebMvcConfig.class
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] {
			"/"
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {
			new CharacterEncodingFilter("UTF-8"),
			new ForwardedHeaderFilter(),
			new CommonsRequestLoggingFilter()
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void onStartup(final ServletContext servletContext)
			throws ServletException {
		LOG.info("Use external configuration file "
				+ System.getProperty("externalConfigurationLocation"));
		super.onStartup(servletContext);
	}
}
