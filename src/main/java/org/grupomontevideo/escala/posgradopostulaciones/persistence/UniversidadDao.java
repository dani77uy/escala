package org.grupomontevideo.escala.posgradopostulaciones.persistence;

import java.util.Collection;
import java.util.List;

import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Universidad;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * Created by T_DanielB6 on 18/07/2016.
 */
public class UniversidadDao extends AbstractDao<Universidad, Integer> {

   public UniversidadDao(Session session) {
      super(session);
   }

   @Override
   protected Universidad edit(Universidad universidad) {
      return null;
   }

   @Override
   protected boolean remove(Universidad universidad) {
      return false;
   }

   public Collection<Universidad> getAllOrderByCountryPriority() {
      String hql = "select u, p.idPais, p.prioridad from org.grupomontevideo.escala.posgradopostulaciones.model.entity.Universidad u,"
            + " org.grupomontevideo.escala.posgradopostulaciones.model.entity.PrioriodadesDePais p where u.idPais = p.idPais "
            + "order by p.prioridad, u.id";
      final Query query = getSession().createQuery(hql);
      List l = query.getResultList();
      return l;
   }

   public Universidad get(Integer pk) {
      return super.get(pk, Universidad.class);
   }

}
