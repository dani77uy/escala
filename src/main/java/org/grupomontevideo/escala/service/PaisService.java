package org.grupomontevideo.escala.service;

import java.util.List;

import org.grupomontevideo.escala.model.Pais;
import org.springframework.stereotype.Service;

/**
 * @author Daniel Baharian
 * @since 25 ago 2018
 */
@Service
public interface PaisService {

   Pais agregar(Pais pais);

   List<Pais> agregar(Iterable<Pais> paises);

   void agregarEnOtroIdioma(Iterable<Pais> paises, String idioma);
}
