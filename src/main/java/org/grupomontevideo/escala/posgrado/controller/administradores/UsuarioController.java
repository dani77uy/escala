package org.grupomontevideo.escala.posgrado.controller.administradores;

import org.grupomontevideo.escala.posgrado.controller.AbstractController;
import org.grupomontevideo.escala.posgrado.model.entity.WordpressUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Daniel B. on 27/08/2016.
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController extends AbstractController {

   @RequestMapping(value = "/obtener", method = RequestMethod.GET)
   public String get(@RequestParam("userId") String userId) {
      WordpressUser user = getUsuario(userId);
      if (user != null) {
         return user.getDisplayName();
      }
      return null;
   }
}
