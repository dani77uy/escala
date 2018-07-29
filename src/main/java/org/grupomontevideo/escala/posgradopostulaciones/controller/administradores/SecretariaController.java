package org.grupomontevideo.escala.posgradopostulaciones.controller.administradores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.velocity.app.VelocityEngine;
import org.grupomontevideo.escala.posgradopostulaciones.controller.AbstractController;
import org.grupomontevideo.escala.posgradopostulaciones.controller.mail.EnvioDeCorreoService;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.EstadoPostulacion;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Postulacion;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Universidad;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.WordpressUser;
import org.grupomontevideo.escala.posgradopostulaciones.model.enums.EnumEstadoPostulacion;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.CoordinadorDao;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.EstadoPostulacionDao;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.PostulacionDao;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.SecretariaDao;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.UniversidadDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

/**
 * Created by Daniel B. on 13/08/2016.
 */
@RestController
@RequestMapping("/secretaria")
public class SecretariaController extends AbstractController {

   private final Logger LOG = LoggerFactory.getLogger(this.getClass());

   @RequestMapping(value = "/verificar_si_es_secretario", method = RequestMethod.GET)
   public
   @ResponseBody
   Boolean esSecretario(@RequestParam("userId") String userId) {
      final WordpressUser user = getUsuario(userId);
      return new SecretariaDao(getSession()).verificarSiEsSecretario(user);
   }

   @RequestMapping(value = "/todas", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getTodas(@RequestParam("userId") String adminId) {
      if (esSecretario(adminId)) {
         return new PostulacionDao(getSession()).getAll();
      }
      return new ArrayList<Postulacion>();
   }

   @RequestMapping(value = "/aprobar_postulacion", method = RequestMethod.POST)
   public
   @ResponseBody
   Map<String, String> aprobarPostulacion(@RequestParam("postId") String postId, @RequestParam("userId") String userId, HttpServletRequest request) {
      final String methodName = new Object() {

      }.getClass().getEnclosingMethod().getName();

      final Map<String, String> resultado = Maps.newHashMap();
      aprobarORechazar(resultado, EnumEstadoPostulacion.APROBADO_POR_SECRETARIA, userId, postId, request, methodName, null);
      return resultado;
   }

   @RequestMapping(value = "/rechazar_postulacion", method = RequestMethod.POST)
   public
   @ResponseBody
   Map<String, String> rechazarPostulacion(@RequestParam("postId") String postId, @RequestParam("userId") String userId,
         @RequestParam("motivo") String motivoRechazo, HttpServletRequest request) {
      final String methodName = new Object() {

      }.getClass().getEnclosingMethod().getName();
      final Map<String, String> resultado = Maps.newHashMap();
      aprobarORechazar(resultado, EnumEstadoPostulacion.RECHAZADA_SECRETARIA, userId, postId, request, methodName, motivoRechazo);
      return resultado;
   }

   @RequestMapping(value = "/postulaciones_aprobadas_destino", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesAprobadasPorDestino(@RequestParam("userId") String userId) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(null, null, null, null, null, SecretariaDao.FILTER_GENERAL,
               EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_DESTINO);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_aprobadas_destino_por_universidad", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesAprobadasPorUniversidad(@RequestParam("userId") String userId, @RequestParam("univId") String univId) {
      int unid = NumberUtils.toInt(univId);
      Universidad universidad = new UniversidadDao(getSession()).get(unid);
      if (universidad == null) {
         return null;
      }
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(universidad, null, null, null, null, SecretariaDao.FILTER_UNIVERSIDAD,
               EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_DESTINO);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_aprobadas_destino_por_convocatoria", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesAprobadasPorConvocatoria(@RequestParam("userId") String userId) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(null, null, null, null, null, SecretariaDao.FILTER_CONVOCATORIA,
               EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_DESTINO);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_aprobadas_destino_por_rango_fechas", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesAprobadasPorRangoFechas(@RequestParam("userId") String userId, @RequestParam("fechaInicial") String fIni,
         @RequestParam("fechaFinal") String fFin) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         java.sql.Date fechaInicial = convert(fIni);
         java.sql.Date fechaFinal = convert(fFin);
         return sDao.getPostulacionesParaSecretaria(null, fechaInicial, fechaFinal, null, null, SecretariaDao.FILTER_RANGO_FECHA,
               EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_DESTINO);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_aprobadas_destino_por_pais_origen", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesAprobadasPorPaisOrigen(@RequestParam("userId") String userId,
         @RequestParam("paisOrigen") String paisOrigen) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(null, null, null, paisOrigen, null, SecretariaDao.FILTER_PAIS_ORIGEN,
               EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_DESTINO);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_aprobadas_destino_por_pais_destino", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesAprobadasPorPaisDestino(@RequestParam("userId") String userId,
         @RequestParam("paisDestino") String paisDestino) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(null, null, null, null, paisDestino, SecretariaDao.FILTER_PAIS_DESTINO,
               EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_DESTINO);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_rechazadas_destino", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesRechazadasPorDestino(@RequestParam("userId") String userId) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(null, null, null, null, null, SecretariaDao.FILTER_GENERAL,
               EnumEstadoPostulacion.RECHAZADA_DESTINO);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_rechazadas_destino_por_universidad", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesRechazadasPorUniversidad(@RequestParam("userId") String userId, @RequestParam("univId") String univId) {
      int unid = NumberUtils.toInt(univId);
      Universidad universidad = new UniversidadDao(getSession()).get(unid);
      if (universidad == null) {
         return null;
      }
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(universidad, null, null, null, null, SecretariaDao.FILTER_UNIVERSIDAD,
               EnumEstadoPostulacion.RECHAZADA_DESTINO);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_rechazadas_destino_por_convocatoria", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesRechazadasPorConvocatoria(@RequestParam("userId") String userId) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(null, null, null, null, null, SecretariaDao.FILTER_CONVOCATORIA,
               EnumEstadoPostulacion.RECHAZADA_DESTINO);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_rechazadas_destino_por_rango_fechas", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesRechazadasPorRangoFechas(@RequestParam("userId") String userId, @RequestParam("fechaInicial") String fIni,
         @RequestParam("fechaFinal") String fFin) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         java.sql.Date fechaInicial = convert(fIni);
         java.sql.Date fechaFinal = convert(fFin);
         return sDao.getPostulacionesParaSecretaria(null, fechaInicial, fechaFinal, null, null, SecretariaDao.FILTER_RANGO_FECHA,
               EnumEstadoPostulacion.RECHAZADA_DESTINO);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_rechazadas_destino_por_pais_origen", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesRechazadasPorPaisOrigen(@RequestParam("userId") String userId,
         @RequestParam("paisOrigen") String paisOrigen) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(null, null, null, paisOrigen, null, SecretariaDao.FILTER_PAIS_ORIGEN,
               EnumEstadoPostulacion.RECHAZADA_DESTINO);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_rechazadas_destino_por_pais_destino", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesRechazadasPorPaisDestino(@RequestParam("userId") String userId,
         @RequestParam("paisDestino") String paisDestino) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(null, null, null, null, paisDestino, SecretariaDao.FILTER_PAIS_DESTINO,
               EnumEstadoPostulacion.RECHAZADA_DESTINO);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_aprobadas_secretaria", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesAprobadasSecretaria(@RequestParam("userId") String userId) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(null, null, null, null, null, SecretariaDao.FILTER_GENERAL,
               EnumEstadoPostulacion.APROBADO_POR_SECRETARIA);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_aprobadas_secretaria_por_universidad", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesAprobadasSecretariaPorUniversidad(@RequestParam("userId") String userId,
         @RequestParam("univId") String univId) {
      int unid = NumberUtils.toInt(univId);
      Universidad universidad = new UniversidadDao(getSession()).get(unid);
      if (universidad == null) {
         return null;
      }
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(universidad, null, null, null, null, SecretariaDao.FILTER_UNIVERSIDAD,
               EnumEstadoPostulacion.APROBADO_POR_SECRETARIA);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_aprobadas_secretaria_por_convocatoria", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesAprobadasSecretariaPorConvocatoria(@RequestParam("userId") String userId) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(null, null, null, null, null, SecretariaDao.FILTER_CONVOCATORIA,
               EnumEstadoPostulacion.APROBADO_POR_SECRETARIA);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_aprobadas_secretaria_por_rango_fechas", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesAprobadasSecretariaPorRangoFechas(@RequestParam("userId") String userId,
         @RequestParam("fechaInicial") String fIni, @RequestParam("fechaFinal") String fFin) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         java.sql.Date fechaInicial = convert(fIni);
         java.sql.Date fechaFinal = convert(fFin);
         return sDao.getPostulacionesParaSecretaria(null, fechaInicial, fechaFinal, null, null, SecretariaDao.FILTER_RANGO_FECHA,
               EnumEstadoPostulacion.APROBADO_POR_SECRETARIA);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_aprobadas_secretaria_por_pais_origen", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesAprobadasSecretariaPorPaisOrigen(@RequestParam("userId") String userId,
         @RequestParam("paisOrigen") String paisOrigen) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(null, null, null, paisOrigen, null, SecretariaDao.FILTER_PAIS_ORIGEN,
               EnumEstadoPostulacion.APROBADO_POR_SECRETARIA);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_aprobadas_secretaria_por_pais_destino", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesAprobadasSecretariaPorPaisDestino(@RequestParam("userId") String userId,
         @RequestParam("paisDestino") String paisDestino) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(null, null, null, null, paisDestino, SecretariaDao.FILTER_PAIS_DESTINO,
               EnumEstadoPostulacion.APROBADO_POR_SECRETARIA);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_rechazadas_secretaria", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesRechazadasSecretaria(@RequestParam("userId") String userId) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesParaSecretaria(null, null, null, null, null, SecretariaDao.FILTER_GENERAL,
               EnumEstadoPostulacion.RECHAZADA_SECRETARIA);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_por_nombre", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesPorNombre(@RequestParam("userId") String userId, @RequestParam("dato") String nombre) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesPorUsuario(null, nombre, null, null, SecretariaDao.FILTER_POR_NOMBRE);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_por_apellido", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesPorApellido(@RequestParam("userId") String userId, @RequestParam("dato") String apellido) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesPorUsuario(null, null, apellido, null, SecretariaDao.FILTER_POR_APELLIDO);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_por_documento", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesPorDocumento(@RequestParam("userId") String userId, @RequestParam("dato") String documento) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getPostulacionesPorUsuario(documento, null, null, null, SecretariaDao.FILTER_POR_DOCUMENTO);
      }
      return null;
   }

   @RequestMapping(value = "/coordinadores_por_universidad", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<WordpressUser> getCoordinadoresPorUniversidad(@RequestParam("userId") String userId, @RequestParam String univId) {
      int unid = NumberUtils.toInt(univId);
      Universidad universidad = new UniversidadDao(getSession()).get(unid);
      if (universidad != null) {
         final WordpressUser usuario = getUsuario(userId);
         final SecretariaDao sDao = new SecretariaDao(getSession());
         if (sDao.verificarSiEsSecretario(usuario)) {
            return sDao.getCoordinadoresPorUniversidad(universidad);
         }
      }
      return null;
   }

   @RequestMapping(value = "/universidades_por_pais", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Universidad> getUniversidadesPorPais(@RequestParam("userId") String userId, @RequestParam String paisId) {
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao sDao = new SecretariaDao(getSession());
      if (sDao.verificarSiEsSecretario(usuario)) {
         return sDao.getUniversidadesPorPais(paisId);
      }
      return null;
   }

   @RequestMapping(value = "/estados", method = RequestMethod.GET)
   public
   @ResponseBody
   Collection<EstadoPostulacion> getEstados() {
      return new EstadoPostulacionDao(getSession()).getAll();
   }

   private void aprobarORechazar(Map<String, String> resultado, EnumEstadoPostulacion newEstado, String userId, String postId,
         HttpServletRequest request, String methodName, String motivoRechazo) {
      final Postulacion postulacion = getPostulacion(postId);
      final WordpressUser usuario = getUsuario(userId);
      final SecretariaDao secretariaDao = new SecretariaDao(getSession());
      if (secretariaDao.verificarSiEsSecretario(usuario)) {
         if (postulacion != null) {
            if (newEstado == EnumEstadoPostulacion.APROBADO_POR_SECRETARIA
                  && postulacion.getEstado().getCodEstadoPost() != EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_DESTINO.getId()) {
               resultado.put("codigo", "-5");
               resultado.put("mensaje", "No se puede aprobar una postulación que no fue aprobada anteriormente por Universidad de Destino.");
            } else {
               try {
                  final CoordinadorDao coordinadorDao = new CoordinadorDao(getSession());
                  final EstadoPostulacion newEstadoPostulacion = coordinadorDao.getEstado(newEstado);
                  if (aprobarORechazarPostulacion(postulacion, usuario, newEstadoPostulacion, motivoRechazo, request, methodName) != null) {
                     resultado.put("codigo", "0");
                     String mensaje = null;
                     if (newEstado == EnumEstadoPostulacion.APROBADO_POR_SECRETARIA) {
                        mensaje = "aprobada por secretaría";
                     } else if (newEstado == EnumEstadoPostulacion.RECHAZADA_SECRETARIA) {
                        mensaje = "rechazada por secretaría";
                     }
                     resultado.put("mensaje", "Postulación " + mensaje + " correctamente.");
                     resultado.put("estado", newEstado.getNombre());
                  } else {
                     LOG.warn("Error al aprovar o rechazar postulación porque no hay correo de coordinador de origen.");
                     resultado.put("codigo", "-5");
                     resultado.put("mensaje", "No se pudo aprobar la postulación por la secretaría porque el coordinador de origen no tiene correo electrónico o no fue aprobada por origen.");
                  }
               } catch (Exception ex) {
                  LOG.warn("Error al aprobar o rechazar postulación por secretaría.", ex);
                  resultado.put("codigo", "-4");
                  resultado.put("mensaje", "La postulación no pudo ser aprobada pues no se pudo enviar el mail de confirmación.");
               }
            }
         } else {
            resultado.put("codigo", "-2");
            resultado.put("mensaje", "No se encontró postulación.");
         }
      } else {
         resultado.put("codigo", "-1");
         resultado.put("mensaje", "No puede aprobarse por secretaría con usuario no administrador.");
      }
   }

   private EstadoPostulacion aprobarORechazarPostulacion(Postulacion postulacion, WordpressUser user, EstadoPostulacion estadoPostulacion,
         String motivoRechazo, HttpServletRequest request, String methodName) throws IOException, MessagingException {
      postulacion.setEstado(estadoPostulacion);
      postulacion.setAprobadorSecretaria(user);
      final java.sql.Date actual = new java.sql.Date(new Date().getTime());
      boolean esAprobable = true;
      if (estadoPostulacion.getCodEstadoPost() == EnumEstadoPostulacion.APROBADO_POR_SECRETARIA.getId()) {
         postulacion.setFechaAprobacionSecretaria(actual);
         final String correoCoordinadorOrigen = obtenerCorreoCoordinadorOrigen(postulacion);
         esAprobable = StringUtils.isNotBlank(correoCoordinadorOrigen);
         if (esAprobable) {
            enviarMail(postulacion, correoCoordinadorOrigen);
         }
      } else if (estadoPostulacion.getCodEstadoPost() == EnumEstadoPostulacion.RECHAZADA_SECRETARIA.getId()) {
         postulacion.setFechaRechazoSecretaria(actual);
         postulacion.setMotivoRechazo(motivoRechazo);
      }
      if (esAprobable) {
         final PostulacionDao pDao = new PostulacionDao(getSession());
         pDao.edit(postulacion);
         agregarAlLog(postulacion, request.getRemoteAddr(), request.getHeader("user-agent"), methodName);
         return estadoPostulacion;
      }
      return null;
   }

   private String obtenerCorreoCoordinadorOrigen(Postulacion postulacion) {
      WordpressUser aprOrig = postulacion.getAprobadorOrigen();
      if (aprOrig == null) {
         return null;
      }
      return aprOrig.getUserEmail();
   }

   @Autowired
   private VelocityEngine velocityEngine;

   private void enviarMail(Postulacion postulacion, String correoCoordinadorOrigen) throws IOException, MessagingException {
      final boolean enviar = BooleanUtils.toBoolean(getProperty("mail.enviar"));
      final String from = getProperty("mail.from");
      final String host = getProperty("mail.host");
      final String username = getProperty("mail.username");
      final String password = getProperty("mail.password");
      final String auth = getProperty("mail.smtp.auth");
      final String port = getProperty("mail.smtp.socketFactory.port");
      final String enable = getProperty("mail.smtp.ssl.enable");
      final String subject = getProperty("mail.subject");

      if (enviar) {
         new EnvioDeCorreoService(velocityEngine).sendFromGMail(from, username, password, subject, port, auth, enable, host, postulacion,
               correoCoordinadorOrigen);
      }
   }

}
