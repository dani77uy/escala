package org.grupomontevideo.escala.posgradopostulaciones.persistence;

import java.util.Collection;

import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Convocatoria;
import org.hibernate.Session;

/**
 * Created by Daniel B. on 22/07/2016.
 */
public class ConvocatoriaDao extends AbstractDao<Convocatoria, Integer> {

   public ConvocatoriaDao(Session session) {
      super(session);
   }

   public Collection<Convocatoria> getAll() {
      return super.getAll(Convocatoria.class);
   }

   @Override
   protected boolean remove(Convocatoria convocatoria) {
      return false;
   }
}
