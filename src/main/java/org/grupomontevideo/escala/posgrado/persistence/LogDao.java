package org.grupomontevideo.escala.posgrado.persistence;

import java.io.Serializable;

import org.grupomontevideo.escala.posgrado.model.entity.LogPostulaciones;
import org.hibernate.Session;

/**
 * Created by Daniel B. on 02/08/2016.
 */
public class LogDao extends AbstractDao {

   public LogPostulaciones add(LogPostulaciones lp) {
      getSession().beginTransaction();
      getSession().save(lp);
      getSession().getTransaction().commit();
      return lp;
   }

   public LogDao(Session session) {
      super(session);
   }

   @Override
   protected boolean remove(Serializable serializable) {
      return false;
   }
}
