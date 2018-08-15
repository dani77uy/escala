package org.grupomontevideo.escala.config;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Value;
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
@Getter
@Setter*/
public class GeneralConfigParametros {

   @Value("${modo}")
   @Pattern(regexp = "LOCAL")
   private String modo;

   @Value("${link.reglamento}")
   private String reglamento;
}
