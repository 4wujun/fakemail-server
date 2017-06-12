package org.legurun.test.fakemailserver.config;

/*******************************************************************************
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
 ******************************************************************************/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ResourceLoader;

@Configuration
@EnableCaching
@PropertySource(value = "classpath:application.properties")
@PropertySource(value = "file:${externalConfigurationLocation}",
		ignoreResourceNotFound = true)
@ComponentScan(basePackages = "org.legurun.test.fakemailserver.service")
public class RootConfig implements ResourceLoaderAware {
	private static final Logger LOG = LoggerFactory.getLogger(RootConfig.class);

	private ResourceLoader resourceLoader;

	@Bean
	public CacheManager cacheManager() {
		LOG.trace("Initialisation cacheManager");
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		LOG.trace("Initialisation ehCacheCacheManager");
		EhCacheManagerFactoryBean cacheManagerFactory = new EhCacheManagerFactoryBean();
		cacheManagerFactory.setAcceptExisting(true);
		cacheManagerFactory.setShared(true);
		cacheManagerFactory.setConfigLocation(resourceLoader.getResource("classpath:ehcache.xml"));
		return cacheManagerFactory;
	}

	@Bean
	public MessageSource messageSource() {
		LOG.trace("Initialisation messageSource");
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		LOG.trace("Initialisation defaultAdvisorAutoProxyCreator");
		return new DefaultAdvisorAutoProxyCreator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
}
