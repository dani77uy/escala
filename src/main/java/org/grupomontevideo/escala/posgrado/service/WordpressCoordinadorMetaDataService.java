package org.grupomontevideo.escala.posgrado.service;

import org.grupomontevideo.escala.posgrado.model.entity.WordpressCoordinadorMetaData;
import org.springframework.stereotype.Service;

/**
 * @author Daniel Baharian
 * @since 1 ago 2018
 */
@Service
public interface WordpressCoordinadorMetaDataService {

   WordpressCoordinadorMetaData obtenerMetaDataPorUsuario(Long userId, String univId);
}
