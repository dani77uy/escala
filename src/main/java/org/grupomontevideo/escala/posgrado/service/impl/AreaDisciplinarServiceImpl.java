package org.grupomontevideo.escala.posgrado.service.impl;

import java.util.List;
import java.util.Optional;

import org.grupomontevideo.escala.posgrado.model.entity.AreaDisciplinar;
import org.grupomontevideo.escala.posgrado.persistence.AreaDisciplinarDao;
import org.grupomontevideo.escala.posgrado.service.AreaDisciplinarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

/**
 * @author Daniel Baharian
 * @since 1 ago 2018
 */
@Component
public class AreaDisciplinarServiceImpl implements AreaDisciplinarService {

   private final AreaDisciplinarDao dao;

   @Autowired
   public AreaDisciplinarServiceImpl(AreaDisciplinarDao dao) {
      this.dao = dao;
   }

   @Override
   public AreaDisciplinar get(Integer integer) {
      final Optional<AreaDisciplinar> optional = dao.findById(integer);
      return optional.orElse(null);
   }

   @Override
   public List<AreaDisciplinar> obtenerTodas() {
      return Lists.newArrayList(dao.findAll());
   }

}
