package org.grupomontevideo.escala.posgradopostulaciones.persistence;

import java.util.ArrayList;
import java.util.Collection;

import org.grupomontevideo.escala.posgradopostulaciones.model.entity.AreaDisciplinar;
import org.hibernate.Session;

/**
 * Created by Daniel B. on 18/07/2016.
 */
public class AreaDisciplinarDao extends AbstractDao<AreaDisciplinar, Integer> {

   public AreaDisciplinarDao(Session session) {
      super(session);
   }

   public AreaDisciplinar get(Integer pk) {
      return super.get(pk, AreaDisciplinar.class);
   }

   @Override
   protected AreaDisciplinar edit(AreaDisciplinar areaDisciplinar) {
      return null;
   }

   @Override
   protected boolean remove(AreaDisciplinar areaDisciplinar) {
      return false;
   }

   public Collection<AreaDisciplinar> getByOrigenYDestino(String tipo) {
      Collection<AreaDisciplinar> lista = new ArrayList<>();
      for (AreaDisciplinar ad: getAll(AreaDisciplinar.class)) {
         String ood = ad.getOrigenODestino();
         if (("TT".equalsIgnoreCase(ood) || tipo.equalsIgnoreCase(ood)) && ad.getHabilitado()) {
            lista.add(ad);
         }
      }
      return lista;
   }
}
