package org.grupomontevideo.escala.util.cache;

import static java.util.concurrent.TimeUnit.HOURS;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @author Daniel Baharian
 * @since 16/07/2017
 */
public enum PropertiesCache {

   INSTANCE;

   public static final String USER_DIR = System.getProperty("user.dir") + "/config/";

   private static final String PARAMETERS = USER_DIR + "parametros.properties";

   private static final String MENSAJES_ES = USER_DIR + "mensajes_es.properties";

   private static final String MYSQL_CONFIG = USER_DIR + "parametros.properties";

   private final LoadingCache<String, Properties> cache;

   PropertiesCache() {
      this.cache = CacheBuilder.newBuilder().maximumSize(10).expireAfterAccess(12, HOURS).build(new PropsLoader());
   }

   public Properties getMainProperties() throws ExecutionException {
      return cache.get(PARAMETERS);
   }

   public String getMainProperty(String key) throws ExecutionException {
      final Properties properties = getMainProperties();
      Object value = properties.get(key);
      return value != null ? value.toString() : "";
   }

   public String getMySQLProperty(String key) throws ExecutionException {
      Properties properties = cache.get(MYSQL_CONFIG);
      return properties.getProperty(key);
   }

   public Properties getMensajesEspanolProperties() throws ExecutionException {
      return cache.get(MENSAJES_ES);
   }

   public String getMensajesEsProperty(String key) throws ExecutionException {
      final Properties properties = getMensajesEspanolProperties();
      Object value = properties.get(key);
      return value != null ? value.toString() : "";
   }

   private static class PropsLoader extends CacheLoader<String, Properties> {

      @Override
      public Properties load(String key) throws Exception {
         try (final InputStream input = new FileInputStream(key)) {
            final Properties properties = new Properties();
            properties.load(input);
            return properties;
         }
      }
   }

}
