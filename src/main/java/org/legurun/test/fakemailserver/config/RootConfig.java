package org.legurun.test.fakemailserver.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="org.legurun.test.fakemail.service")
public class RootConfig {

}
