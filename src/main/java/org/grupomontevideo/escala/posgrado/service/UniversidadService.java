package org.grupomontevideo.escala.posgrado.service;

import java.util.List;

import org.grupomontevideo.escala.posgrado.model.entity.Universidad;
import org.springframework.stereotype.Service;

/**
 * @author Daniel Baharian
 * @since 1 ago 2018
 */
@Service
public interface UniversidadService {

   List<Universidad> obtenerUniversidadesPorPais(String paisId);

   List<Universidad> obtenerTodasOrdenadasPorPrioridadDePais();
}
