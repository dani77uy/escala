package org.grupomontevideo.escala.posgradopostulaciones.main;

import org.grupomontevideo.escala.posgradopostulaciones.controller.MailTestController;
import org.grupomontevideo.escala.posgradopostulaciones.controller.administradores.CoordinadorController;
import org.grupomontevideo.escala.posgradopostulaciones.controller.administradores.SecretariaController;
import org.grupomontevideo.escala.posgradopostulaciones.controller.administradores.UsuarioController;
import org.grupomontevideo.escala.posgradopostulaciones.controller.pdf.PdfController;
import org.grupomontevideo.escala.posgradopostulaciones.controller.postulacion.AreaDisciplinarController;
import org.grupomontevideo.escala.posgradopostulaciones.controller.postulacion.MaestriaController;
import org.grupomontevideo.escala.posgradopostulaciones.controller.postulacion.PostulacionController;
import org.grupomontevideo.escala.posgradopostulaciones.controller.postulacion.UniversidadController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan(basePackageClasses = {MaestriaController.class, AreaDisciplinarController.class,
      UniversidadController.class, PostulacionController.class, PdfController.class, MailTestController.class,
      CoordinadorController.class, SecretariaController.class, UsuarioController.class})
@EnableAutoConfiguration
public class Main {

   private static final Logger LOG = LoggerFactory.getLogger(Main.class);

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

}
