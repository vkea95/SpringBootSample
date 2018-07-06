package com.vkea.sbs.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.vkea.sbs.repository")
@PropertySource(ignoreResourceNotFound = false, value = {"classpath:application.properties"})
public class ElasticSearchConfig {
}
