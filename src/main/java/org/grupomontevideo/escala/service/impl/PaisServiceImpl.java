package org.grupomontevideo.escala.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.grupomontevideo.escala.model.Pais;
import org.grupomontevideo.escala.repository.PaisRepository;
import org.grupomontevideo.escala.service.PaisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Daniel Baharian
 * @since 25 ago 2018
 */
@Component
public class PaisServiceImpl implements PaisService {

   private static final Logger LOGGER = LoggerFactory.getLogger(PaisServiceImpl.class);

   @Autowired
   private PaisRepository paisRepository;

   @Autowired
   private EntityManager entityManager;

   @Override
   public Pais agregar(Pais pais) {
      return paisRepository.save(pais);
   }

   @Override
   public List<Pais> agregar(Iterable<Pais> paises) {
      return (List<Pais>) paisRepository.saveAll(paises);
   }

   @Override
   @Transactional
   public void agregarEnOtroIdioma(Iterable<Pais> paises, String idioma) {
      LOGGER.debug("AGREGANDO PAISES EN: ", idioma);
      for (Pais pais : paises) {
         LOGGER.debug("Agregando país: {}", pais.getCodigoAbreviacion());
         paisRepository.agregarPaisEnOtroIdioma(entityManager, pais, idioma);
      }

   }

}
