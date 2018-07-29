package org.grupomontevideo.escala.posgradopostulaciones.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.LogPostulaciones;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Postulacion;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Universidad;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.WordpressUser;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.pk.LogPostulacionesPK;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.HibernateUtil;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.LogDao;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.PostulacionDao;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.UniversidadDao;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.WordpressUserDao;
import org.hibernate.Session;

/**
 * Created by Daniel B. on 18/07/2016.
 */
public abstract class AbstractController {

   String PARAMETERS = System.getProperty("user.dir") + File.separatorChar + "config" + File.separatorChar + "parameters.properties";

   protected Session getSession() {
      return HibernateUtil.getSession();
   }

   protected String getProperty(String key) throws IOException {
      InputStream input = null;
      try {
         Properties prop = new Properties();
         input = new FileInputStream(PARAMETERS);
         prop.load(input);
         return prop.getProperty(key);
      } finally {
         if (input != null) {
            input.close();
         }
      }
   }

   protected Map<String, String> getHeadersInfo(HttpServletRequest request) {

      Map<String, String> map = new HashMap<String, String>();

      Enumeration headerNames = request.getHeaderNames();
      while (headerNames.hasMoreElements()) {
         String key = (String) headerNames.nextElement();
         String value = request.getHeader(key);
         map.put(key, value);
      }

      return map;
   }

   protected final LogPostulaciones agregarAlLog(Postulacion postulacion, String ip, String ua, String method) {
      final LogDao logDao = new LogDao(this.getSession());
      final LogPostulacionesPK pk = new LogPostulacionesPK();
      pk.setUser(postulacion.getUsuario());
      pk.setPostulacion(postulacion);
      pk.setFechaCreacion(postulacion.getFechaPostulacion());
      pk.setFechaModificacion(new java.sql.Timestamp(System.currentTimeMillis()));
      final LogPostulaciones log = new LogPostulaciones();
      log.setPk(pk);
      log.setIp(ip);
      log.setUserAgent(ua);
      log.setDetalles(method);
      return logDao.add(log);
   }

   protected final LogPostulaciones agregarAlLog(WordpressUser userModificador, Postulacion postulacion, String ip, String ua, String method) {
      final LogDao logDao = new LogDao(this.getSession());
      final LogPostulacionesPK pk = new LogPostulacionesPK();
      pk.setUser(userModificador);
      pk.setPostulacion(postulacion);
      pk.setFechaCreacion(postulacion.getFechaPostulacion());
      pk.setFechaModificacion(new java.sql.Timestamp(System.currentTimeMillis()));
      final LogPostulaciones log = new LogPostulaciones();
      log.setPk(pk);
      log.setIp(ip);
      log.setUserAgent(ua);
      log.setDetalles(method);
      return logDao.add(log);
   }

   protected Pair<WordpressUser, Postulacion> getUsuarioYPostulacion(String userId, String postId) {
      return Pair.of(getUsuario(userId), getPostulacion(postId));
   }

   protected WordpressUser getUsuario(String userId) {
      WordpressUser user = null;
      if (StringUtils.isNumeric(userId)) {
         user = new WordpressUserDao(this.getSession()).get(Long.valueOf(userId));
      }
      return user;
   }

   protected Postulacion getPostulacion(String postId) {
      Postulacion postulacion = null;
      if (StringUtils.isNumeric(postId)) {
         postulacion = new PostulacionDao(getSession()).get(Integer.valueOf(postId));
      }
      return postulacion;
   }

   protected Universidad getUniversidad(String univId) {
      Universidad universidad = null;
      if (StringUtils.isNumeric(univId)) {
         universidad = new UniversidadDao(getSession()).get(Integer.valueOf(univId));
      }
      return universidad;
   }

   public static final Timestamp convertToTimestamp(String date, int hora, int minuto, int segundo, int milisegundo) {
      if (StringUtils.isBlank(date)) {
         return null;
      }
      final java.sql.Date sqlDate = convert(date);
      final Date date1 = new Date(sqlDate.getTime());
      final Calendar cal = Calendar.getInstance();
      cal.setTime(date1);
      cal.set(Calendar.HOUR_OF_DAY, hora);
      cal.set(Calendar.MINUTE, minuto);
      cal.set(Calendar.SECOND, segundo);
      cal.set(Calendar.MILLISECOND, milisegundo);
      return new Timestamp(cal.getTimeInMillis());
   }

   public static final java.sql.Date convert(String date) {
      if (date == null) {
         return null;
      }
      SimpleDateFormat sdf;
      try {
         if (date.indexOf("-") == 4) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
         } else {
            sdf = new SimpleDateFormat("dd-MM-yyyy");
         }
         Date parsed = sdf.parse(date);
         java.sql.Date sql = new java.sql.Date(parsed.getTime());
         return sql;
      } catch (ParseException ex) {
         try {
            if (date.indexOf("/") == 4) {
               sdf = new SimpleDateFormat("yyyy/MM/dd");
            } else {
               sdf = new SimpleDateFormat("dd/MM/yyyy");
            }
            Date parsed = sdf.parse(date);
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            return sql;
         } catch (ParseException ex1) {
            return new java.sql.Date(new Date().getTime());
         }
      }
   }

}
