package org.grupomontevideo.escala.posgrado.service.impl;

import java.util.List;

import org.grupomontevideo.escala.posgrado.model.entity.Postulacion;
import org.grupomontevideo.escala.posgrado.model.entity.Universidad;
import org.grupomontevideo.escala.posgrado.model.entity.WordpressCoordinadorMetaData;
import org.grupomontevideo.escala.posgrado.model.entity.WordpressUser;
import org.grupomontevideo.escala.posgrado.persistence.WordpressUserDao;
import org.grupomontevideo.escala.posgrado.service.WordpressCoordinadorMetaDataService;
import org.grupomontevideo.escala.posgrado.service.WordpressUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Daniel Baharian
 * @since 8 ago 2018
 */
@Component
public class WordpressUserServiceImpl implements WordpressUserService {

   private final WordpressUserDao wordpressUserDao;

   @Autowired
   private WordpressCoordinadorMetaDataService wordpressCoordinadorMetaDataService;

   @Autowired
   public WordpressUserServiceImpl(WordpressUserDao wordpressUserDao) {
      this.wordpressUserDao = wordpressUserDao;
   }

   @Override
   public WordpressUser obtener(Long id) {
       return wordpressUserDao.findById(id).orElse(null);
   }

   @Override
   public boolean verificarSiEsCoordinador(WordpressUser user, Universidad universidad) {
      WordpressCoordinadorMetaData metaData = wordpressCoordinadorMetaDataService.obtenerMetaDataPorUsuario(user.getId(), universidad.getId());
      if (metaData != null) {
         return metaData.g
      }
   }

   @Override
   public boolean esCoordinador(WordpressUser user) {
      return false;
   }

   @Override
   public List<Postulacion> obtenerPostulacionesPorUniversidadOrigen(WordpressUser user, Universidad universidad) {
      return null;
   }

   @Override
   public List<Postulacion> getPostulacionesPorUniversidadDestino(WordpressUser user, Universidad universidad) {
      return null;
   }

   @Override
   public Universidad obtenerUniversidadDeCoordinador(WordpressUser user) {
      return null;
   }

   @Override
   public List<WordpressUser> obtenerCoordinadoresPorUniversidad(Universidad universidad) {
      return null;
   }

   @Override
   public boolean verificarSiEsSecretario(WordpressUser user) {
      return false;
   }
}
