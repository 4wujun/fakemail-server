/*
 * Copyright (C) 2017-2018 Patrice Le Gurun
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

package org.legurun.test.fakemailserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

/**
 * Main class for Spring Boot.
 * @author patlenain
 * @since 2017
 */
@SpringBootApplication(scanBasePackages = {
		"org.legurun.test.fakemailserver.service",
		"org.legurun.test.fakemailserver.controller",
		"org.legurun.test.fakemailserver.config" })
@PropertySource(value = "file:${externalConfigurationLocation}",
		ignoreResourceNotFound = true)
public class Application extends SpringBootServletInitializer {

	/**
	 * Main method.
	 * @param args Command-line arguments
	 */
	@SuppressWarnings("checkstyle:UncommentedMain")
	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
