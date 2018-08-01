package org.grupomontevideo.escala.posgrado.service;

import java.util.List;

import org.grupomontevideo.escala.posgrado.model.entity.Convocatoria;
import org.springframework.stereotype.Service;

/**
 * @author Daniel Baharian
 * @since 1 ago 2018
 */
@Service
public interface ConvocatoriaService {

   List<Convocatoria> obtenerTodas();
}
