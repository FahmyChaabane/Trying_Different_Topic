package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;


@Configuration
public class DbEnvConfiguration {

    @Autowired
    private Environment env;

    Logger logger = LoggerFactory.getLogger(DbEnvConfiguration.class);

    @Bean
    @Profile("dev")
    public void methodRunDev() {
        logger.info("{}", env.getProperty("app.message"));
        logger.info("{}", env.getProperty("spring.datasource.url"));
    }

    @Bean
    @Profile("prod")
    public void methodRunProd() {
        logger.info("{}", env.getProperty("app.message"));
        logger.info("{}", env.getProperty("spring.datasource.url"));
    }


}
