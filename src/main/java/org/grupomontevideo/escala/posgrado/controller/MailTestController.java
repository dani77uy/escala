package org.grupomontevideo.escala.posgrado.controller;

import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.velocity.app.VelocityEngine;
import org.grupomontevideo.escala.posgrado.controller.mail.EnvioDeCorreoService;
import org.grupomontevideo.escala.posgrado.model.entity.Postulacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daniel Baharian
 * @since 22 jul 2018
 */
@RestController
@RequestMapping("/mail_test")
public class MailTestController extends AbstractController {

   private final EnvioDeCorreoService envioDeCorreoService;

   private final VelocityEngine velocityEngine;

   @Autowired
   public MailTestController(VelocityEngine velocityEngine) {
      this.velocityEngine = velocityEngine;
      this.envioDeCorreoService = new EnvioDeCorreoService(velocityEngine);
   }

   @RequestMapping(value = "/send_from_gmail", method = RequestMethod.GET)
   public Callable<String> test(HttpServletResponse response) {
      return () -> {
         Thread.sleep(8000);
         try {
            final boolean enviar = BooleanUtils.toBoolean(getProperty("mail.enviar"));
            final String from = getProperty("mail.from");
            final String host = getProperty("mail.host");
            final String username = getProperty("mail.username");
            final String password = getProperty("mail.password");
            final String auth = getProperty("mail.smtp.auth");
            final String port = getProperty("mail.smtp.socketFactory.port");
            final String enable = getProperty("mail.smtp.ssl.enable");
            Postulacion postulacion = new Postulacion();
            postulacion.setId(999999);
            postulacion.setEmail("dinguel@hotmail.com");
            if (enviar) {
               envioDeCorreoService.sendFromGMail(from, username, password, "PRUEBA TEST ESCALA POSGRADO", port, auth, enable, host,
                     postulacion, "andresaugm@gmail.com");
               response.setStatus(HttpServletResponse.SC_OK);
               return "OK";
            } else {
               response.setStatus(HttpServletResponse.SC_FORBIDDEN);
               return "NO ENVIAR";
            }

         } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
         }
         return "ERROR";
      };
   }



}
