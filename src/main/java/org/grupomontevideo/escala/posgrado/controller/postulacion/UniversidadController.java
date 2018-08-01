package org.grupomontevideo.escala.posgrado.controller.postulacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.grupomontevideo.escala.posgrado.controller.AbstractController;
import org.grupomontevideo.escala.posgrado.model.entity.Universidad;
import org.grupomontevideo.escala.posgrado.persistence.UniversidadDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Daniel B. on 18/07/2016.
 */
@RestController
public class UniversidadController extends AbstractController {

   @RequestMapping(value = "/obtener_universidades_por_prioridad", method = RequestMethod.GET)
   public @ResponseBody
   Collection getUniversidadesPorPrioridad() {
      UniversidadDao dao = new UniversidadDao(this.getSession());
      Collection res = dao.getAllOrderByCountryPriority();
      List<Universidad> lista = new ArrayList<>();
      for (Object r : res) {
         if (r instanceof Object[]) {
            Object[] o = (Object[]) r;
            if(o[0] instanceof Universidad) {
               Universidad u = (Universidad) o[0];
               lista.add(u);
            }
         } else {
         }
      }
      return lista;
   }
}
