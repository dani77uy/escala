package org.grupomontevideo.escala.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Daniel Baharian
 * @since 1 sep 2018
 */
@Configuration
@EnableWebMvc
public class ConfiguracionEstaticos implements WebMvcConfigurer {

   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry
            .addResourceHandler("/webjars/**", "/img/**", "/css/**", "/js/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/", "classpath:/static/img/", "classpath:/static/css/",
                  "classpath:/static/js/");
   }
}
