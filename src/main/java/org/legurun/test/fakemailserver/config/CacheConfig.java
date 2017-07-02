package org.legurun.test.fakemailserver.config;

import java.io.IOException;

/******************************************************************************
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
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.jcache.JCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CacheConfig {
	private static final Logger LOG = LoggerFactory.getLogger(CacheConfig.class);

	@Bean
	public CacheManager cacheManager() throws IOException {
		LOG.trace("Initialisation cacheManager");
		JCacheCacheManager cacheManager = new JCacheCacheManager();
		cacheManager.setCacheManager(jcacheManager().getObject());
		return cacheManager;
	}

	@Bean
	public JCacheManagerFactoryBean jcacheManager() throws IOException {
		LOG.trace("Initialisation jcacheManager");
		JCacheManagerFactoryBean cacheManagerFactory = new JCacheManagerFactoryBean();
		cacheManagerFactory.setCacheManagerUri(new ClassPathResource("ehcache.xml").getURI());
		return cacheManagerFactory;
	}
}
