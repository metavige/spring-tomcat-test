package com.example.springtomcattest;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jndi.JndiObjectFactoryBean;

@SpringBootApplication
@ComponentScan
public class SpringTomcatTestApplication {

  @Autowired
  private JndiContext jndiContext;

  public static void main(String[] args) {

    SpringApplication.run(SpringTomcatTestApplication.class, args);
  }

  @Bean(destroyMethod = "")
  public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {

    JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
    bean.setJndiName("java:comp/env/jdbc/test");
    bean.setExpectedType(DataSource.class);
    bean.afterPropertiesSet();
    return (DataSource) bean.getObject();
  }

  @Bean
  public TomcatServletWebServerFactory servletContainerFactory() {

    TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory() {

      @Override
      protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {

        tomcat.enableNaming();
        return super.getTomcatWebServer(tomcat);
      }

      @Override
      protected void postProcessContext(Context context) {

        ContextResource resource = new ContextResource();
        resource.setName("jdbc/test");
        resource.setType(DataSource.class.getName());

        resource.setProperty("driverClassName", jndiContext.getDriverClassName());
        resource.setProperty("url", jndiContext.getUrl());

        //        resource.setProperty("driverClassName", "org.h2.Driver");
        //        resource.setProperty("url", "jdbc:h2:mem:testdb");

        context.getNamingResources().addResource(resource);
      }
    };

    return tomcatServletWebServerFactory;
  }

}
