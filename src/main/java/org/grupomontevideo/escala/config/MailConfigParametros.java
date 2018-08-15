package org.grupomontevideo.escala.config;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Daniel Baharian
 * @since 14 ago 2018
 */
/*
@Configuration
@PropertySource("file:${app.home}/config/parametros.properties")
@ConfigurationProperties(prefix = "mail") */
public class MailConfigParametros {

   @Value("${enviar:true}")
   private Boolean enviar;

   @NotBlank
   @Value("${from}")
   private String from;

   @NotBlank
   @Value("${host}")
   private String host;

   @NotBlank
   @Value("${username}")
   private String username;

   @NotBlank
   @Value("${password}")
   private String password;

   @Value("${subjectPosgrado:Confirmaci\u00f3n de Postulaci\u00f3n ESCALA POSGRADO}")
   private String asuntoPosgrado;

   @Value("${subjectDocente:Confirmaci\u00f3n de Postulaci\u00f3n ESCALA DOCENTE}")
   private String asuntoDocente;

   @Value("${enviocambiodeestado.secretaria:false}")
   private Boolean cambioEstado;

   @NestedConfigurationProperty
   private final Smtp smtp = new Smtp();

   public Boolean getEnviar() {
      return enviar;
   }

   public void setEnviar(Boolean enviar) {
      this.enviar = enviar;
   }

   public String getFrom() {
      return from;
   }

   public void setFrom(String from) {
      this.from = from;
   }

   public String getHost() {
      return host;
   }

   public void setHost(String host) {
      this.host = host;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getAsuntoPosgrado() {
      return asuntoPosgrado;
   }

   public void setAsuntoPosgrado(String asuntoPosgrado) {
      this.asuntoPosgrado = asuntoPosgrado;
   }

   public String getAsuntoDocente() {
      return asuntoDocente;
   }

   public void setAsuntoDocente(String asuntoDocente) {
      this.asuntoDocente = asuntoDocente;
   }

   public Boolean getCambioEstado() {
      return cambioEstado;
   }

   public void setCambioEstado(Boolean cambioEstado) {
      this.cambioEstado = cambioEstado;
   }

   public Smtp getSmtp() {
      return this.smtp;
   }

   @ConfigurationProperties(prefix = "smtp")
   private static class Smtp {

      @Value("${auth:false}")
      @NestedConfigurationProperty
      private Boolean auth;

      @Value("${ssl.enable:false}")
      private Boolean sslEnable;

      @NestedConfigurationProperty
      private final SocketFactory socketFactory = new SocketFactory();

      public Boolean getAuth() {
         return auth;
      }

      public Boolean getSslEnable() {
         return sslEnable;
      }

      public SocketFactory getSocketFactory() {
         return socketFactory;
      }

      public void setAuth(Boolean auth) {
         this.auth = auth;
      }

      public void setSslEnable(Boolean sslEnable) {
         this.sslEnable = sslEnable;
      }

      @ConfigurationProperties(prefix = "socketFactory")
      private static class SocketFactory {

         @NotBlank
         @Value("${port}")
         @Pattern(regexp = "\\d+")
         private String port;

         @Value("${class:javax.net.ssl.SSLSocketFactory}")
         private String socketFactoryClass;

         public String getPort() {
            return port;
         }

         public void setPort(String port) {
            this.port = port;
         }

         public String getSocketFactoryClass() {
            return socketFactoryClass;
         }

         public void setSocketFactoryClass(String socketFactoryClass) {
            this.socketFactoryClass = socketFactoryClass;
         }
      }

   }

}
