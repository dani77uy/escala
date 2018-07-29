package org.grupomontevideo.escala.posgradopostulaciones.persistence;

import java.util.ArrayList;
import java.util.Collection;

import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Maestria;
import org.hibernate.Session;

/**
 * Created by Daniel B. on 18/07/2016.
 */
public class MaestriaDao extends AbstractDao<Maestria, Integer> {

   public MaestriaDao(Session session) {
      super(session);
   }

   public Maestria get(Integer pk) {
      return super.get(pk, Maestria.class);
   }

   @Override
   protected Maestria edit(Maestria maestria) {
      return null;
   }

   @Override
   protected boolean remove(Maestria maestria) {
      return false;
   }

   public Collection<Maestria> getByOrigenYDestino(String tipo) {
      Collection<Maestria> lista = new ArrayList<>();
      for (Maestria ad: getAll(Maestria.class)) {
         String ood = ad.getOrigenODestino();
         if (("TT".equalsIgnoreCase(ood) || tipo.equalsIgnoreCase(ood)) && ad.getHabilitado()) {
            lista.add(ad);
         }
      }
      return lista;
   }
}
