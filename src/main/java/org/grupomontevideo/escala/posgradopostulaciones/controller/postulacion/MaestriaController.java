package org.grupomontevideo.escala.posgradopostulaciones.controller.postulacion;

import java.util.Collection;

import org.grupomontevideo.escala.posgradopostulaciones.controller.AbstractController;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Maestria;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.MaestriaDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Daniel B. on 18/07/2016.
 */
@RestController
public class MaestriaController extends AbstractController {

   @RequestMapping(value = "/obtener_programas", method = RequestMethod.GET)
   public
   @ResponseBody
   Collection<Maestria> getAllByOrigenODestino(@RequestParam String tipo) {
      MaestriaDao dao = new MaestriaDao(this.getSession());
      return dao.getByOrigenYDestino(tipo);
   }
}
