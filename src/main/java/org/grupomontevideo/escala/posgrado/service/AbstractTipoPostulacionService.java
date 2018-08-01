package org.grupomontevideo.escala.posgrado.service;

import java.util.ArrayList;
import java.util.List;

import org.grupomontevideo.escala.posgrado.model.entity.Tipo;

/**
 * @author Daniel Baharian
 * @since 1 ago 2018
 */
public interface AbstractTipoPostulacionService<T extends Tipo, PK extends Integer> {

   String TIPO = "TT";

   T get(PK pk);

   List<T> obtenerTodas();

   default List<T> getByOrigenYDestino(String tipo) {
      List<T> lista = new ArrayList();
      for (T t : obtenerTodas()) {
         String ood = t.getOrigenODestino();
         if ((TIPO.equalsIgnoreCase(ood) || tipo.equalsIgnoreCase(ood)) && t.getHabilitado()) {
            lista.add(t);
         }
      }
      return lista;
   }
}
