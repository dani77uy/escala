package org.grupomontevideo.escala.posgrado.service.impl;

import java.util.List;

import org.grupomontevideo.escala.posgrado.model.entity.Convocatoria;
import org.grupomontevideo.escala.posgrado.persistence.ConvocatoriaDao;
import org.grupomontevideo.escala.posgrado.service.ConvocatoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Daniel Baharian
 * @since 7 ago 2018
 */
@Component
public class ConvocatoriaServiceImpl implements ConvocatoriaService {

   private final ConvocatoriaDao dao;

   @Autowired
   public ConvocatoriaServiceImpl(ConvocatoriaDao dao) {
      this.dao = dao;
   }

   @Override
   public List<Convocatoria> obtenerTodas() {
      return (List<Convocatoria>) dao.findAll();
   }
}
