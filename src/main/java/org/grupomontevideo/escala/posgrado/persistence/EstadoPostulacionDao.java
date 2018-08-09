package org.grupomontevideo.escala.posgrado.persistence;

import org.grupomontevideo.escala.posgrado.model.entity.EstadoPostulacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Daniel B. on 19/07/2016.
 */
@Repository
public interface EstadoPostulacionDao extends CrudRepository<EstadoPostulacion, Integer> {

}
