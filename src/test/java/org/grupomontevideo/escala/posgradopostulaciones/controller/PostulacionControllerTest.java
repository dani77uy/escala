package org.grupomontevideo.escala.posgradopostulaciones.controller;

import java.sql.Date;

import org.grupomontevideo.escala.posgrado.controller.postulacion.PostulacionController;
import org.grupomontevideo.escala.posgrado.model.entity.EstadoPostulacion;
import org.grupomontevideo.escala.posgrado.model.entity.Postulacion;
import org.grupomontevideo.escala.posgrado.model.enums.EnumEstadoPostulacion;
import org.grupomontevideo.escala.posgrado.persistence.PostulacionDao;
import org.hibernate.Session;
import org.testng.annotations.Test;

import mockit.Mocked;

/**
 * Created by T_DanielB6 on 19/07/2016.
 */
public class PostulacionControllerTest {

   @Mocked
   PostulacionDao postulacionDao;

   @Mocked
   Session session;

   @Test
   public void testDate() {
      String date = "21-12-2016";
      Date d = PostulacionController.convert(date);
      System.out.println(d.toString());
   }


   private Postulacion getPostulacion() {
      Postulacion postulacion = new Postulacion();
      postulacion.setId(1);
      EstadoPostulacion estadoPostulacion = new EstadoPostulacion();
      estadoPostulacion.setCodEstadoPost(EnumEstadoPostulacion.PENDIENTE.getId());
      estadoPostulacion.setDescripcion("Pendiente");
      postulacion.setEstado(estadoPostulacion);
      postulacion.setCalle("Calle 1");
      return postulacion;
   }


}
