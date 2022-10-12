package com.techbridge.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ ServiceConfig.class, PersistenceConfig.class })
@ComponentScan(basePackages = "/com/techbridge/database/copy/")
public class RootAppConfig {

}
