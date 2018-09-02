package org.grupomontevideo.escala.util;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.grupomontevideo.escala.model.Pais;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * @author D.Baharian
 * @since 29 may 2018
 */
public enum CsvReaderPaises {

   ESPANOL(0),
   INGLES(1),
   PORTUGUES(2);

   private final int pais;

   CsvReaderPaises(int pais) {
      this.pais = pais;
   }

   private static final String PATH_GENERAL_PAISES = System.getProperty("user.dir") + File.separator + "config" + File.separator + "paises" + File.separator;

   private static final Logger LOGGER = LoggerFactory.getLogger(CsvReaderPaises.class);

   public List<Pais> loadPaises() {
      try {
         CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
         CsvMapper mapper = new CsvMapper();
         final String path;
         switch (pais) {
            case 0: {
               path = PATH_GENERAL_PAISES + "espanol.csv";
               break;
            }
            case 1: {
               path = PATH_GENERAL_PAISES + "ingles.csv";
               break;
            }
            case 2: {
               path = PATH_GENERAL_PAISES + "portugues.csv";
               break;
            }
            default: {
               throw new IllegalArgumentException("Archivo seleccionado no existente");
            }
         }
         File file = new File(path);
         MappingIterator<Pais> readValues = mapper.readerFor(Pais.class).with(bootstrapSchema).readValues(file);
         return readValues.readAll();
      } catch (Exception e) {
         LOGGER.error("Error occurred while loading object list from file " + PATH_GENERAL_PAISES, e);
         return Collections.emptyList();
      }
   }
}
