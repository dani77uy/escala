package org.grupomontevideo.escala.posgrado.service;

import org.grupomontevideo.escala.posgrado.model.entity.EstadoPostulacion;
import org.grupomontevideo.escala.posgrado.model.enums.EnumEstadoPostulacion;
import org.springframework.stereotype.Service;

/**
 * @author Daniel Baharian
 * @since 1 ago 2018
 */
@Service
public interface EstadoPostulacionService {

   EstadoPostulacion obtenerEstado(EnumEstadoPostulacion estado);

}
