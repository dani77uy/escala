package org.grupomontevideo.escala.posgradopostulaciones.persistence;

import java.util.Collection;

import org.grupomontevideo.escala.posgradopostulaciones.model.entity.EstadoPostulacion;
import org.hibernate.Session;

/**
 * Created by Daniel B. on 19/07/2016.
 */
public class EstadoPostulacionDao extends AbstractDao<EstadoPostulacion, Integer> {

   public EstadoPostulacionDao(Session session) {
      super(session);
   }

   public EstadoPostulacion get(Integer pk) {
      return super.get(pk, EstadoPostulacion.class);
   }

   @Override
   protected EstadoPostulacion edit(EstadoPostulacion estadoPostulacion) {
      return null;
   }

   @Override
   protected boolean remove(EstadoPostulacion estadoPostulacion) {
      return false;
   }

   public Collection<EstadoPostulacion> getAll() {
      return super.getAll(EstadoPostulacion.class);
   }
}
