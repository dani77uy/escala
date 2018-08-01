package org.grupomontevideo.escala.posgrado.persistence;

import org.grupomontevideo.escala.posgrado.model.entity.AreaDisciplinar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Daniel B. on 18/07/2016.
 */
@Repository
public interface AreaDisciplinarDao extends CrudRepository<AreaDisciplinar, Integer> {

}
