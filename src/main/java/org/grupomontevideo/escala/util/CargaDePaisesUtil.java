package org.grupomontevideo.escala.util;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.grupomontevideo.escala.model.Pais;
import org.grupomontevideo.escala.util.cache.PropertiesCache;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author Daniel Baharian
 * @since 25 ago 2018
 */
public enum CargaDePaisesUtil {

   INSTANCE;

   public List<Pais> getJsonPaises() throws IOException {
      final String path = PropertiesCache.USER_DIR + "paises/";
      final String jsonEspanol = path + "paises_espanol.json";
      final String jsonPortugues = path + "paises_portugues.json";
      final String jsonIngles = path + "paises_ingles.json";
      final Type reviewTypeEspanol = new TypeToken<List<Pais>>() {

      }.getType();
      final Gson gson = new Gson();
      final List<Pais> paisesEspanol;
      try (FileReader fileReaderEspanol = new FileReader(jsonEspanol)) {
         paisesEspanol = gson.fromJson(fileReaderEspanol, reviewTypeEspanol);
      }
      return paisesEspanol;
   }
}
