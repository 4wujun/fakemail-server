package org.legurun.test.fakemailserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableCaching
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages="org.legurun.test.fakemailserver.service")
public class RootConfig {
	private static final Logger LOG = LoggerFactory.getLogger(RootConfig.class);

	@Bean
	public CacheManager cacheManager() {
		LOG.trace("Initialisation cacheManager");
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		LOG.trace("Initialisation ehCacheCacheManager");
		EhCacheManagerFactoryBean cacheManagerFactory = new EhCacheManagerFactoryBean();
		return cacheManagerFactory;
	}
}
