package com.example.springtomcattest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@PropertySource("classpath:META-INF/context.properties")
@ConfigurationProperties(prefix = "context")
@Data
public class JndiContext {

  //  @Value("${context.driverClassName}")
  private String driverClassName;
  //  @Value("${context.url}")
  private String url;
}
