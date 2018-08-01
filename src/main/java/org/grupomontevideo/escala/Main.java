package org.grupomontevideo.escala;

import java.util.concurrent.ExecutionException;

import javax.sql.DataSource;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.grupomontevideo.escala.docente.controller.AppErrorController;
import org.grupomontevideo.escala.posgrado.controller.MailTestController;
import org.grupomontevideo.escala.posgrado.controller.administradores.CoordinadorController;
import org.grupomontevideo.escala.posgrado.controller.administradores.SecretariaController;
import org.grupomontevideo.escala.posgrado.controller.administradores.UsuarioController;
import org.grupomontevideo.escala.posgrado.controller.pdf.PdfController;
import org.grupomontevideo.escala.posgrado.controller.postulacion.AreaDisciplinarController;
import org.grupomontevideo.escala.posgrado.controller.postulacion.MaestriaController;
import org.grupomontevideo.escala.posgrado.controller.postulacion.PostulacionController;
import org.grupomontevideo.escala.posgrado.controller.postulacion.UniversidadController;
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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan(basePackageClasses = { MaestriaController.class, AreaDisciplinarController.class, UniversidadController.class,
      PostulacionController.class, PdfController.class, MailTestController.class, CoordinadorController.class, SecretariaController.class,
      UsuarioController.class })
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
            //registry.addMapping("/**").allowedOrigins(HOST_GM, HOST_LOCAL).allowedMethods("GET", "POST").allowCredentials(false).maxAge(3600);
            registry.addMapping("/obtener_areas").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/obtener_programas").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/obtener_universidades_por_prioridad").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/agregar_postulacion").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/lista_postulaciones_por_usuario").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/editar_postulacion").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/obtener_postulacion").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/modificar_estado_postulacion").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/todas_por_universidad").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/todas_por_fechas").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/todas_por_estado").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/crear_archivo_impresion").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/obtener_postulaciones_con_filtros").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/coordinador/postulaciones_por_universidad_origen").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/coordinador/postulaciones_por_universidad_destino").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/coordinador/verificar_si_esta_firmada").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/coordinador/aprobar_postulacion_origen").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/coordinador/aprobar_postulacion_destino").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/coordinador/rechazar_postulacion_origen").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/coordinador/rechazar_postulacion_destino").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/coordinador/obtener_formularios_aprobados").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/coordinador/obtener_formularios_rechazados").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/coordinador/obtener_postulaciones_confirmadas").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/coordinador/obtener_universidad").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/coordinador/verificar_si_es_coordinador").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/aprobar_postulacion").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/rechazar_postulacion").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_aprobadas_destino").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_aprobadas_destino_por_universidad").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_aprobadas_destino_por_convocatoria").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_aprobadas_destino_por_rango_fechas").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_aprobadas_destino_por_pais_origen").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_aprobadas_destino_por_pais_destino").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_rechazadas_destino").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_rechazadas_destino_por_universidad").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_rechazadas_destino_por_rango_fechas").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_rechazadas_destino_por_pais_origen").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_rechazadas_destino_por_pais_destino").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_aprobadas_secretaria").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_aprobadas_secretaria_por_universidad").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_aprobadas_secretaria_por_convocatoria").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_aprobadas_secretaria_por_rango_fechas").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_aprobadas_secretaria_por_pais_origen").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_aprobadas_secretaria_por_pais_destino").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_rechazadas_secretaria").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_por_nombre").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_por_apellido").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/postulaciones_por_documento").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/coordinadores_por_universidad").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/universidades_por_pais").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/verificar_si_es_secretario").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/todas").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/secretaria/estados").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/usuario/obtener").allowedOrigins(HOST_LOCAL, HOST_GM);
            registry.addMapping("/mail_test/send_from_gmail").allowedOrigins(HOST_LOCAL, HOST_GM);
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
