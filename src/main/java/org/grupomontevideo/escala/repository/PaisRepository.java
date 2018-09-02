package org.grupomontevideo.escala.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.grupomontevideo.escala.model.Pais;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Daniel Baharian
 * @since 25 ago 2018
 */
public interface PaisRepository extends CrudRepository<Pais, Integer> {

   default int agregarPaisEnOtroIdioma(EntityManager entityManager, Pais pais, String idioma) {
      final boolean esPt = "pt".equalsIgnoreCase(idioma);
      final boolean esEn = "en".equalsIgnoreCase(idioma);
      String sql = null;
      if (esPt) {
         sql = "update org.grupomontevideo.escala.model.Pais p set p.nombrePortugues = :nombre where p.codigoAbreviacion = :cod";
      } else if (esEn) {
         sql = "update org.grupomontevideo.escala.model.Pais p set p.nombreIngles = :nombre where p.codigoAbreviacion = :cod";
      }
      if (sql != null) {
         final Query query = entityManager.createQuery(sql);
         if (esPt) {
            query.setParameter("nombre", pais.getNombrePortugues());
         } else if (esEn) {
            query.setParameter("nombre", pais.getNombreIngles());
         }
         query.setParameter("cod", pais.getCodigoAbreviacion());
         return query.executeUpdate();
      }
      return 0;
   }
}
