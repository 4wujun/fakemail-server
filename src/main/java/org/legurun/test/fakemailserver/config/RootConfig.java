package org.legurun.test.fakemailserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@EnableCaching
@PropertySources({
	@PropertySource(value="classpath:application.properties"),
	@PropertySource(value="file:${externalConfigurationLocation}", ignoreResourceNotFound=true)
})
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

	@Bean
	public MessageSource messageSource() {
		LOG.trace("Initialisation messageSource");
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}
