package org.grupomontevideo.escala.posgrado.service.impl;

import java.util.Optional;

import org.grupomontevideo.escala.posgrado.model.entity.EstadoPostulacion;
import org.grupomontevideo.escala.posgrado.model.enums.EnumEstadoPostulacion;
import org.grupomontevideo.escala.posgrado.persistence.EstadoPostulacionDao;
import org.grupomontevideo.escala.posgrado.service.EstadoPostulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Daniel Baharian
 * @since 7 ago 2018
 */
@Component
public class EstadoPostulacionServiceImpl implements EstadoPostulacionService {

   private final EstadoPostulacionDao dao;

   @Autowired
   public EstadoPostulacionServiceImpl(EstadoPostulacionDao dao) {
      this.dao = dao;
   }

   @Override
   public EstadoPostulacion obtenerEstado(EnumEstadoPostulacion estado) {
      Optional<EstadoPostulacion> optional = dao.findById(estado.getId());
      return optional.orElse(null);
   }
}
