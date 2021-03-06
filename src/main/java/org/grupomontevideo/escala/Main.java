package org.grupomontevideo.escala;

import java.util.concurrent.ExecutionException;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.grupomontevideo.escala.util.cache.PropertiesCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

   private static final Logger LOG = LoggerFactory.getLogger(Main.class);

   private static final String HOST_LOCAL = "http://localhost";

   private static final String HOST_GM = "http://grupomontevideo.org";

   @Autowired
   private ErrorAttributes errorAttributes;

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

   /*  @Bean
     public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {

           @Override
           public void addCorsMappings(CorsRegistry registry) {
           }
        };
     }
  */
 /*  @Bean
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
   }*/
/*
   @Bean
   public AppErrorController appErrorController() {
      return new AppErrorController(errorAttributes);
   }*/

   public static void main(String[] args) {
      LOG.info("Iniciando aplicación de ESCALA");
      SpringApplication.run(Main.class, args);
   }

   private static final class DataSourceProperties {

      public String getUrl() throws ExecutionException {
         return PropertiesCache.INSTANCE.getMySQLProperty("mysql.url");
      }

      public String getUsername() throws ExecutionException {
         return PropertiesCache.INSTANCE.getMySQLProperty("mysql.username");
      }

      public String getPassword() throws ExecutionException {
         return PropertiesCache.INSTANCE.getMySQLProperty("mysql.password");
      }

      /*public String getDriverClassName() throws ExecutionException {
         return PropertiesCache.INSTANCE.getMySQLProperty("mysql.driver-class-name");
      }*/
   }
}
