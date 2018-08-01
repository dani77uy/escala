package org.grupomontevideo.escala.posgrado.controller.postulacion;

import java.util.Collection;

import org.grupomontevideo.escala.posgrado.controller.AbstractController;
import org.grupomontevideo.escala.posgrado.model.entity.AreaDisciplinar;
import org.grupomontevideo.escala.posgrado.persistence.AreaDisciplinarDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Daniel B. on 18/07/2016.
 */
@RestController
public class AreaDisciplinarController extends AbstractController {

   @RequestMapping(value = "/obtener_areas", method = RequestMethod.GET)
   public
   @ResponseBody
   Collection<AreaDisciplinar> getAllByOrigenODestino(@RequestParam String tipo) {
      AreaDisciplinarDao dao = new AreaDisciplinarDao(this.getSession());
      return dao.getByOrigenYDestino(tipo);
   }
}
