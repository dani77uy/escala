package org.grupomontevideo.escala;

import java.util.concurrent.ExecutionException;

import javax.sql.DataSource;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.grupomontevideo.escala.controller.AppErrorController;
import org.grupomontevideo.escala.util.cache.PropertiesCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication

public class Main {

   private static final Logger LOG = LoggerFactory.getLogger(Main.class);

   @Autowired
   private ErrorAttributes errorAttributes;

   private static final String HOST_LOCAL = "http://localhost";

   private static final String HOST_GM = "http://grupomontevideo.org";

   public static void main(String[] args) {
      LOG.info("Iniciando aplicación de ESCALA POSGRADO");
      SpringApplication.run(Main.class, args);
   }

   @Bean
   public WebMvcConfigurer corsConfigurer() {
      return new WebMvcConfigurerAdapter() {

         @Override
         public void addCorsMappings(CorsRegistry registry) {
         }
      };
   }

   @Bean
   @Primary
   @ConfigurationProperties(prefix = "spring.datasource")
   public DataSource primaryDataSource() throws ExecutionException {
      final DataSourceProperties dataSourceProperties = new DataSourceProperties();
      return DataSourceBuilder
            .create()
            .url(dataSourceProperties.getUrl())
            .username(dataSourceProperties.getUsername())
            .password(dataSourceProperties.getPassword())
            .build();
   }

   @Bean
   public TomcatServletWebServerFactory containerFactory() {
      return new TomcatServletWebServerFactory() {

         protected void customizeConnector(Connector connector) {
            int maxSize = 50000000;
            super.customizeConnector(connector);
            connector.setMaxPostSize(maxSize);
            connector.setMaxSavePostSize(maxSize);
            if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol) {
               ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(maxSize);
               LOG.info("Set MaxSwallowSize " + maxSize);
            }
         }
      };

   }

   @Bean
   public AppErrorController appErrorController() {
      return new AppErrorController(errorAttributes);
   }

   private static final class DataSourceProperties {

      public String getUrl() throws ExecutionException {
         return PropertiesCache.INSTANCE.getMainProperty("mysql.url");
      }

      public String getUsername() throws ExecutionException {
         return PropertiesCache.INSTANCE.getMainProperty("mysql.username");
      }

      public String getPassword() throws ExecutionException {
         return PropertiesCache.INSTANCE.getMainProperty("mysql.password");
      }

      public String getDriverClassName() throws ExecutionException {
         return PropertiesCache.INSTANCE.getMainProperty("mysql.driver-class-name");
      }
   }

}
