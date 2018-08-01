package org.grupomontevideo.escala.posgrado.controller.mail;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.CharEncoding;
import org.apache.velocity.app.VelocityEngine;
import org.grupomontevideo.escala.posgrado.controller.AbstractController;
import org.grupomontevideo.escala.posgrado.model.entity.Postulacion;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * Created by Daniel B. on 28/08/2016.
 */
public class EnvioDeCorreoService extends AbstractController {

   private JavaMailSender mailSender;

   private VelocityEngine velocityEngine;

   public void setMailSender(JavaMailSender mailSender) {
      this.mailSender = mailSender;
   }

   public void setVelocityEngine(VelocityEngine velocityEngine) {
      this.velocityEngine = velocityEngine;
   }

   public EnvioDeCorreoService(JavaMailSender mailSender, VelocityEngine velocityEngine) {
      this.mailSender = mailSender;
      this.velocityEngine = velocityEngine;
   }

   public EnvioDeCorreoService(VelocityEngine velocityEngine) {
      this.velocityEngine = velocityEngine;
   }

   public void aprobar(Postulacion postulacion, String correoDeCoordinadorOrigen) {
      sendConfirmationEmail(postulacion, correoDeCoordinadorOrigen);
   }

   private void sendConfirmationEmail(final Postulacion postulacion, String correoDeCoordinadorOrigen) {
      velocityEngine.setProperty("resource.loader", "class");
      velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
      velocityEngine.init();
      MimeMessagePreparator preparator = mimeMessage -> {
         MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
         message.setTo(correoDeCoordinadorOrigen);
         message.setFrom(getProperty("mail.usuario"));
         Map model = new HashMap();
         model.put("postulacion", postulacion);
         String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/mail.vm", CharEncoding.UTF_8, model);
         message.setText(text, true);
      };
      this.mailSender.send(preparator);
   }

   private String getBody(Postulacion postulacion) {
      Map model = new HashMap();
      model.put("postulacion", postulacion);
      return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/mail.vm", CharEncoding.UTF_8, model);
   }

   public void sendFromGMail(String from, String username, String pass, String subject, String port, String auth, String ssl, String host,
         Postulacion postulacion, String correoCoordinadorOrigen) throws MessagingException {
      Properties props = System.getProperties();
      props.put("mail.smtp.auth", auth);
      props.put("mail.smtp.starttls.enable", ssl);
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", port);

      final PasswordAuthentication authentication = new PasswordAuthentication(username, pass);
      Session mailSession = Session.getInstance(props, new Authenticator() {

         @Override
         protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
         }
      });

      Message message = new MimeMessage(mailSession);
      message.setFrom(new InternetAddress(from));
      message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(correoCoordinadorOrigen));
      message.setSubject(subject);
      message.setText(getBody(postulacion));
      Transport.send(message);
   }

}
