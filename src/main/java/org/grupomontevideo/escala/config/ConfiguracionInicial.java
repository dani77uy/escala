package org.grupomontevideo.escala.config;

import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.grupomontevideo.escala.service.PaisService;
import org.grupomontevideo.escala.util.CsvReaderPaises;
import org.grupomontevideo.escala.util.cache.PropertiesCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Daniel Baharian
 * @since 25 ago 2018
 */
@ComponentScan
@Configuration
public class ConfiguracionInicial {

   private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguracionInicial.class);

   @Autowired
   private PaisService paisService;

   @PostConstruct
   public void init() throws ExecutionException {
      final String ejecutar = PropertiesCache.INSTANCE.getMainProperty("reiniciar_bdd");
      if (Boolean.valueOf(ejecutar)) {
         paisService.agregar(CsvReaderPaises.ESPANOL.loadPaises());
         paisService.agregarEnOtroIdioma(CsvReaderPaises.PORTUGUES.loadPaises(), "pt");
         paisService.agregarEnOtroIdioma(CsvReaderPaises.INGLES.loadPaises(), "en");
      }
   }

}
