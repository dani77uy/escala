package org.grupomontevideo.escala.posgrado.service.impl;

import java.util.List;
import java.util.Optional;

import org.grupomontevideo.escala.posgrado.model.entity.Maestria;
import org.grupomontevideo.escala.posgrado.persistence.MaestriaDao;
import org.grupomontevideo.escala.posgrado.service.MaestriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

/**
 * @author Daniel Baharian
 * @since 1 ago 2018
 */
@Component
public class MaestriaServiceImpl implements MaestriaService {

   private final MaestriaDao dao;

   @Autowired
   public MaestriaServiceImpl(MaestriaDao dao) {
      this.dao = dao;
   }

   @Override
   public Maestria get(Integer integer) {
      Optional<Maestria> optional = dao.findById(integer);
      return optional.orElse(null);
   }

   @Override
   public List<Maestria> obtenerTodas() {
      return Lists.newArrayList(dao.findAll());
   }

}
