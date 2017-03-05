package org.legurun.test.fakemailserver.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.legurun.test.fakemailserver.controller")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	private static final Logger LOG = LoggerFactory.getLogger(WebMvcConfig.class);

	@Bean
	public ViewResolver viewResolver() {
		LOG.trace("Initialisation viewResolver");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		LOG.trace("Add resource handlers");
		registry.addResourceHandler("/static/**")
			.addResourceLocations("/css/", "/images/", "/js/", "/theme/")
			.resourceChain(true);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		LOG.trace("Try to configure JSON date format ...");
		for (HttpMessageConverter<?> converter : converters) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				LOG.trace("JSON formatter found ! Configuring it");
				MappingJackson2HttpMessageConverter c = (MappingJackson2HttpMessageConverter) converter;
				ObjectMapper objectMapper = c.getObjectMapper();
				objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			}
		}
		super.extendMessageConverters(converters);
	}
}
