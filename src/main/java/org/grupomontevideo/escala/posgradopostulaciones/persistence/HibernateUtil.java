package org.grupomontevideo.escala.posgradopostulaciones.persistence;

import java.io.File;
import java.text.MessageFormat;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.AreaDisciplinar;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Convocatoria;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.EstadoPostulacion;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.LogPostulaciones;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Maestria;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Postulacion;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.PrioriodadesDePais;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Universidad;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.WordpressCoordinadorMetaData;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.WordpressUser;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.WordpressUserRol;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.WorpressRol;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

   private static final String HIBERNATE_CFG = System.getProperty("user.dir") + File.separatorChar + "config" + File.separatorChar
         + "hibernate.cfg.xml";

   private static final SessionFactory sessionFactory;

   private static final org.apache.logging.log4j.Logger LOG = LogManager.getLogger(HibernateUtil.class);

   static {
      try {
         File f = new File(HIBERNATE_CFG);
         if (f.exists() && f.canRead()) {
            Configuration configuration = new Configuration().configure(f);
            configuration.addAnnotatedClass(Maestria.class);
            configuration.addAnnotatedClass(AreaDisciplinar.class);
            configuration.addAnnotatedClass(Universidad.class);
            configuration.addAnnotatedClass(PrioriodadesDePais.class);
            configuration.addAnnotatedClass(Postulacion.class);
            configuration.addAnnotatedClass(WordpressUser.class);
            configuration.addAnnotatedClass(EstadoPostulacion.class);
            configuration.addAnnotatedClass(Convocatoria.class);
            configuration.addAnnotatedClass(LogPostulaciones.class);
            configuration.addAnnotatedClass(WordpressCoordinadorMetaData.class);
            configuration.addAnnotatedClass(WordpressUserRol.class);
            configuration.addAnnotatedClass(WorpressRol.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
         } else {
            sessionFactory = null;
            LOG.error(MessageFormat.format("No se econtró el archivo {1}, ruta: {2}", HIBERNATE_CFG, f.getAbsolutePath()));
         }
      } catch (Throwable ex) {
         LOG.error("Error al cargar Hibernate Util.", ex);
         ex.printStackTrace();
         throw new ExceptionInInitializerError(ex);
      }
   }

   public static Session getSession() throws HibernateException {
      return sessionFactory.openSession();
   }

   public static void shutdown() {
      // Close caches and connection pools
      if (Objects.nonNull(sessionFactory)) {
         sessionFactory.close();
      }
   }

}