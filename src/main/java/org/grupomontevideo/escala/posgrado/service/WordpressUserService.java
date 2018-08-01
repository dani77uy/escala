package org.grupomontevideo.escala.posgrado.service;

import java.util.List;

import org.grupomontevideo.escala.posgrado.model.entity.Postulacion;
import org.grupomontevideo.escala.posgrado.model.entity.Universidad;
import org.grupomontevideo.escala.posgrado.model.entity.WordpressUser;
import org.springframework.stereotype.Service;

/**
 * @author Daniel Baharian
 * @since 29 jul 2018
 */
@Service
public interface WordpressUserService {

   Long ROL_COORDINADOR = Long.valueOf(4);

   WordpressUser obtener(Long id);

   boolean verificarSiEsCoordinador(WordpressUser user, Universidad universidad);

   boolean esCoordinador(WordpressUser user);

   List<Postulacion> obtenerPostulacionesPorUniversidadOrigen(WordpressUser user, Universidad universidad);

   List<Postulacion> getPostulacionesPorUniversidadDestino(WordpressUser user, Universidad universidad);

   Universidad obtenerUniversidadDeCoordinador(WordpressUser user);

   List<WordpressUser> obtenerCoordinadoresPorUniversidad(Universidad universidad);

   boolean verificarSiEsSecretario(WordpressUser user);

}
