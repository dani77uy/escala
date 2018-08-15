package org.grupomontevideo.escala.config;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniel Baharian
 * @since 14 ago 2018
 */
/*
@Configuration
@PropertySource("file:${app.home}/config/parametros.properties")
@ConfigurationProperties(prefix = "mysql")
@Getter
@Setter */
public class MySqlConfigParametros {

   @NotBlank
   @Value("${url}")
   private String url;

   @NotBlank
   @Value("${username}")
   private String username;

   @NotBlank
   @Value("${password}")
   private String password;
}
