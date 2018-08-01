package org.grupomontevideo.escala.posgrado.controller.administradores;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.grupomontevideo.escala.posgrado.controller.AbstractController;
import org.grupomontevideo.escala.posgrado.model.entity.EstadoPostulacion;
import org.grupomontevideo.escala.posgrado.model.entity.Postulacion;
import org.grupomontevideo.escala.posgrado.model.entity.Universidad;
import org.grupomontevideo.escala.posgrado.model.entity.WordpressUser;
import org.grupomontevideo.escala.posgrado.model.enums.EnumEstadoPostulacion;
import org.grupomontevideo.escala.posgrado.persistence.CoordinadorDao;
import org.grupomontevideo.escala.posgrado.persistence.PostulacionDao;
import org.grupomontevideo.escala.posgrado.persistence.WordpressUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Created by Daniel B. on 03/08/2016.
 */
@RestController
@RequestMapping("/coordinador")
public class CoordinadorController extends AbstractController {

   private final Logger LOG = LoggerFactory.getLogger(this.getClass());

   private static final DateFormat DF = new SimpleDateFormat("dd/MM/yyyy");

   @RequestMapping(value = "/obtener_universidad", method = RequestMethod.POST)
   public
   @ResponseBody
   Universidad getUniversidadDeCoordinador(@RequestParam(value = "userId") String userId) {
      WordpressUser usuario = getUsuario(userId);
      if (usuario != null) {
         return new CoordinadorDao(getSession()).getUniversidadDeCoordinador(usuario);
      }
      return null;
   }

   @RequestMapping(value = "/postulaciones_por_universidad_origen", method = RequestMethod.POST)
   public
   @ResponseBody
   Map<String, Object> getPostulacionesUniversidadOrigen(@RequestParam(value = "userId") String userId) {
      final Map<String, Object> resultado = Maps.newHashMap();
      if (StringUtils.isNumeric(userId)) {
         final WordpressUser coordinador = new WordpressUserDao(this.getSession()).get(Long.valueOf(userId));
         final Universidad univOrig = new CoordinadorDao(getSession()).getUniversidadDeCoordinador(coordinador);
         final Collection<Postulacion> postulaciones = new PostulacionDao(getSession()).getPorUniversidad(univOrig, true,
               EnumEstadoPostulacion.FIRMADO_POR_TUTOR);
         if (Objects.isNull(postulaciones)) {
            resultado.put("codigo", "-1");
            resultado.put("mensaje", "No se han podido obtener postulaciones para el coordinador seleccionado en Universidad de origen");
         } else {
            resultado.put("codigo", "0");
            resultado.put("mensaje", "Postulaciones para el coordinador seleccionado en Universidad de origen");
            resultado.put("postulaciones", postulaciones);
         }
      }
      return resultado;
   }

   @RequestMapping(value = "/postulaciones_por_universidad_destino", method = RequestMethod.POST)
   public
   @ResponseBody
   Map<String, Object> getPostulacionesUniversidadDestino(@RequestParam(value = "userId") String userId) {
      final Map<String, Object> resultado = Maps.newHashMap();
      final WordpressUser user = getUsuario(userId);
      if (user != null) {
         final Universidad univDest = new CoordinadorDao(getSession()).getUniversidadDeCoordinador(user);
         final Collection<Postulacion> postulaciones = new PostulacionDao(getSession()).getPorUniversidad(univDest, false,
               EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_ORIGEN);
         if (Objects.isNull(postulaciones)) {
            resultado.put("codigo", "-1");
            resultado.put("mensaje", "No se han podido obtener postulaciones para el coordinador seleccionado en Universidad de destino");
         } else {
            resultado.put("codigo", "0");
            resultado.put("mensaje", "Postulaciones para el coordinador seleccionado en Universidad de destino");
            resultado.put("postulaciones", postulaciones);
         }
      } else {
         resultado.put("codigo", "-2");
         resultado.put("mensaje", "Coordinador no encontrado.");
      }
      return resultado;
   }

   @RequestMapping(value = "/verificar_si_esta_firmada", method = RequestMethod.POST)
   public
   @ResponseBody
   boolean verificarSiEstaFirmadaPorTutor(@RequestParam(value = "userId") String userId, @RequestParam(value = "postId") String postId,
         HttpServletRequest request) {
      final String methodName = new Object() {

      }.getClass().getEnclosingMethod().getName();
      Postulacion postulacion = getPostulacion(postId);
      WordpressUser coordinador = getUsuario(userId);
      CoordinadorDao cDao = new CoordinadorDao(getSession());
      Universidad universidadCoordinador = cDao.getUniversidadDeCoordinador(coordinador);
      if (universidadCoordinador.equals(postulacion.getUniversidadOrigen())) {
         EstadoPostulacion estadoPostulacion = postulacion.getEstado();
         if (estadoPostulacion != null) {
            if (estadoPostulacion.getCodEstadoPost() == EnumEstadoPostulacion.IMPRESO.getId()) {
               EstadoPostulacion newEstado = cDao.getEstado(EnumEstadoPostulacion.FIRMADO_POR_TUTOR);
               postulacion.setEstado(newEstado);
               PostulacionDao pDao = new PostulacionDao(getSession());
               pDao.edit(postulacion);
               agregarAlLog(coordinador, postulacion, request.getRemoteAddr(), request.getHeader("user-agent"), methodName);
               return true;
            }
         }
      }
      return false;
   }

   @RequestMapping(value = "/aprobar_postulacion_origen", method = RequestMethod.POST)
   public
   @ResponseBody
   Map<String, String> aprobarPostulacionOrigen(@RequestParam(value = "userId") String userId, @RequestParam(value = "postId") String postId,
         HttpServletRequest request) {
      final Map<String, String> resultado = Maps.newHashMap();
      final String methodName = new Object() {

      }.getClass().getEnclosingMethod().getName();
      aprobarORechazar(resultado, EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_ORIGEN, userId, postId, true, true, request, methodName, "");
      return resultado;
   }

   @RequestMapping(value = "/aprobar_postulacion_destino", method = RequestMethod.POST)
   public
   @ResponseBody
   Map<String, String> aprobarPostulacionDestino(@RequestParam(value = "userId") String userId, @RequestParam(value = "postId") String postId,
         HttpServletRequest request) {
      final Map<String, String> resultado = Maps.newHashMap();
      final String methodName = new Object() {

      }.getClass().getEnclosingMethod().getName();
      aprobarORechazar(resultado, EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_DESTINO, userId, postId, false, true, request, methodName, "");
      return resultado;
   }

   @RequestMapping(value = "/rechazar_postulacion_origen", method = RequestMethod.POST)
   public
   @ResponseBody
   Map<String, String> rechazarPostulacionOrigen(@RequestParam(value = "userId") String userId, @RequestParam(value = "postId") String postId,
         @RequestParam(value = "motivo") String motivo, HttpServletRequest request) {
      final Map<String, String> resultado = Maps.newHashMap();
      final String methodName = new Object() {

      }.getClass().getEnclosingMethod().getName();
      aprobarORechazar(resultado, EnumEstadoPostulacion.RECHAZADA_ORIGEN, userId, postId, true, false, request, methodName, motivo);
      return resultado;
   }

   @RequestMapping(value = "/rechazar_postulacion_destino", method = RequestMethod.POST)
   public
   @ResponseBody
   Map<String, String> rechazarPostulacionDestino(@RequestParam(value = "userId") String userId, @RequestParam(value = "postId") String postId,
         @RequestParam(value = "motivo") String motivo, HttpServletRequest request) {
      final Map<String, String> resultado = Maps.newHashMap();
      final String methodName = new Object() {

      }.getClass().getEnclosingMethod().getName();
      aprobarORechazar(resultado, EnumEstadoPostulacion.RECHAZADA_DESTINO, userId, postId, false, false, request, methodName, motivo);
      return resultado;
   }

   @RequestMapping(value = "/obtener_formularios_aprobados", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Formulario> getFormulariosAprobados(@RequestParam(value = "userId") String userId) {
      final Universidad universidad = getUniversidadDeCoordinador(userId);
      if (universidad != null) {
         PostulacionDao pDao = new PostulacionDao(getSession());
         Collection<Postulacion> postulaciones = pDao.getPorUniversidad(universidad, null, EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_DESTINO,
               EnumEstadoPostulacion.APROBADO_POR_SECRETARIA, EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_ORIGEN);
         List<Formulario> formularios = Lists.newArrayList();
         for (Postulacion p : postulaciones) {
            final Formulario fa = new Formulario();
            fa.id = p.getId();
            fa.duracion = p.getDuracion();
            fa.fechaEvaluacion = DF.format(p.getFechaAprobacionOrigen());
            fa.fechaComienzo = DF.format(p.getFechaComienzo());
            fa.idEstudiante = p.getUsuario().getId();
            final EstadoPostulacion estadoPostulacion = p.getEstado();
            if (Objects.nonNull(estadoPostulacion)) {
               fa.estado = estadoPostulacion.getDescripcion();
               switch (estadoPostulacion.getCodEstadoPost()) {
                  case 4:
                     fa.fechaEvaluacion = DF.format(p.getFechaAprobacionOrigen());
                     fa.nombreEvaluador = p.getAprobadorOrigen().getDisplayName();
                     break;
                  case 5:
                     fa.fechaEvaluacion = DF.format(p.getFechaAprobacionDestino());
                     fa.nombreEvaluador = p.getAprobadorDestino().getDisplayName();
                     break;
                  case 6:
                     fa.fechaEvaluacion = DF.format(p.getFechaAprobacionSecretaria());
                     fa.nombreEvaluador = p.getAprobadorSecretaria().getDisplayName();
                     break;
               }
            }
            formularios.add(fa);
         }
         return formularios;
      }
      return null;
   }

   @RequestMapping(value = "/obtener_formularios_rechazados", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Formulario> getFormulariosRechazados(@RequestParam(value = "userId") String userId) {
      final Universidad universidad = getUniversidadDeCoordinador(userId);
      if (universidad != null) {
         PostulacionDao pDao = new PostulacionDao(getSession());
         Collection<Postulacion> postulaciones = pDao.getPorUniversidad(universidad, null, EnumEstadoPostulacion.RECHAZADA_ORIGEN,
               EnumEstadoPostulacion.RECHAZADA_SECRETARIA, EnumEstadoPostulacion.RECHAZADA_DESTINO);
         List<Formulario> formularios = Lists.newArrayList();
         for (Postulacion p : postulaciones) {
            final Formulario fa = new Formulario();
            fa.id = p.getId();
            fa.duracion = p.getDuracion();
            fa.fechaComienzo = DF.format(p.getFechaComienzo());
            fa.idEstudiante = p.getUsuario().getId();
            fa.motivoRechazo = p.getMotivoRechazo();
            final EstadoPostulacion estadoPostulacion = p.getEstado();
            if (Objects.nonNull(estadoPostulacion)) {
               fa.estado = estadoPostulacion.getDescripcion();
               switch (estadoPostulacion.getCodEstadoPost()) {
                  case 51:
                     fa.fechaEvaluacion = DF.format(p.getFechaRechazoOrigen());
                     fa.nombreEvaluador = p.getAprobadorOrigen().getDisplayName();
                     break;
                  case 52:
                     fa.fechaEvaluacion = DF.format(p.getFechaRechazoDestino());
                     fa.nombreEvaluador = p.getAprobadorDestino().getDisplayName();
                     break;
                  case 53:
                     fa.fechaEvaluacion = DF.format(p.getFechaRechazoSecretaria());
                     fa.nombreEvaluador = p.getAprobadorSecretaria().getDisplayName();
                     break;
               }
            }
            formularios.add(fa);
         }
         return formularios;
      }
      return null;
   }

   @RequestMapping(value = "/obtener_postulaciones_confirmadas", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesConfirmadas(@RequestParam("userId") String userId) {
      final WordpressUser user = getUsuario(userId);
      final PostulacionDao pDao = new PostulacionDao(getSession());
      return pDao.getPorCoordinador(user, EnumEstadoPostulacion.APROBADO_POR_SECRETARIA);
   }

   @RequestMapping(value = "/verificar_si_es_coordinador", method = RequestMethod.GET)
   public
   @ResponseBody
   Boolean esCoordinador(@RequestParam("userId") String userId) {
      final WordpressUser user = getUsuario(userId);
      return new CoordinadorDao(getSession()).esCoordinador(user);
   }

   private void aprobarORechazar(Map<String, String> resultado, EnumEstadoPostulacion newEstado, String userId, String postId, boolean esOrigen,
         boolean esAprobacion, HttpServletRequest request, String methodName, String motivoRechazo) {
      final Pair<WordpressUser, Postulacion> pair = getUsuarioYPostulacion(userId, postId);
      final WordpressUser user = pair.getLeft();
      final Postulacion postulacion = pair.getRight();
      if (user == null) {
         resultado.put("codigo", "-1");
         resultado.put("mensaje", "Coordinador no encontrado");
         return;
      }
      if (postulacion == null) {
         resultado.put("codigo", "-2");
         resultado.put("mensaje", "Postulacion no encontrada");
         return;
      }
      CoordinadorDao cDao = new CoordinadorDao(getSession());
      final Universidad universidad;
      final java.sql.Date actual = new java.sql.Date(new java.util.Date().getTime());
      if (esOrigen) {
         universidad = postulacion.getUniversidadOrigen();
         postulacion.setAprobadorOrigen(user);
         postulacion.setFechaAprobacionOrigen(actual);
      } else {
         universidad = postulacion.getUniversidadDestino();
         postulacion.setAprobadorDestino(user);
         postulacion.setFechaAprobacionDestino(actual);
      }
      if (universidad == null) {
         resultado.put("codigo", "-6");
         resultado.put("mensaje", "La postulación no tiene universidad seleccionada");
         return;
      }
      final String texto;
      if (esAprobacion) {
         texto = "aprobada";
      } else {
         texto = "rechazada";
         postulacion.setMotivoRechazo(motivoRechazo);
         if (esOrigen) {
            postulacion.setFechaRechazoOrigen(actual);
         } else {
            postulacion.setFechaRechazoDestino(actual);
         }
      }
      final boolean esCoordinador = cDao.verificarSiEsCoordinador(user, universidad);
      if (esCoordinador) {
         if (postulacion.getEstado() != null) {
            final Integer codEstPost = postulacion.getEstado().getCodEstadoPost();
            if (esOrigen) {
               if (esAprobacion) {
                  if (codEstPost != EnumEstadoPostulacion.FIRMADO_POR_TUTOR.getId()) {
                     resultado.put("codigo", "-5");
                     resultado.put("mensaje", "No se puedo aprobar una postulación de origen no firmada por tutor.");
                     return;
                  }
               } else {
                  // nada
               }
            } else {
               if (esAprobacion) {
                  if (codEstPost != EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_ORIGEN.getId()) {
                     resultado.put("codigo", "-3");
                     resultado.put("mensaje", "No se puede aprobar una postulación de destino que no ha sido aprobada por origen.");
                     return;
                  }
               } else {
                  if (codEstPost != EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_ORIGEN.getId()) {
                     resultado.put("codigo", "-4");
                     resultado.put("mensaje", "No se puede rechazar una postulación de destino que no ha sido aprobada por origen.");
                     return;
                  }
               }
            }
         }
         EstadoPostulacion estado = cDao.getEstado(newEstado);
         postulacion.setEstado(estado);
         PostulacionDao pDao = new PostulacionDao(getSession());
         Postulacion newPostulacion = pDao.edit(postulacion);
         agregarAlLog(user, newPostulacion, request.getRemoteAddr(), request.getHeader("user-agent"), methodName);
         resultado.put("codigo", "0");
         resultado.put("mensaje", "Postulación " + texto + " correctamente");
         resultado.put("estado", estado.getDescripcion());
      }

   }

   private class Formulario implements Serializable {

      Integer id;

      long idEstudiante;

      String fechaComienzo;

      int duracion;

      String fechaEvaluacion;

      String motivoRechazo;

      String nombreEvaluador;

      String estado;

      public String getEstado() {
         return estado;
      }

      public void setEstado(String estado) {
         this.estado = estado;
      }

      public Integer getId() {
         return id;
      }

      public void setId(Integer id) {
         this.id = id;
      }

      public String getFechaComienzo() {
         return fechaComienzo;
      }

      public void setFechaComienzo(String fechaComienzo) {
         this.fechaComienzo = fechaComienzo;
      }

      public int getDuracion() {
         return duracion;
      }

      public void setDuracion(int duracion) {
         this.duracion = duracion;
      }

      public String getMotivoRechazo() {
         return motivoRechazo;
      }

      public void setMotivoRechazo(String motivoRechazo) {
         this.motivoRechazo = motivoRechazo;
      }

      public long getIdEstudiante() {
         return idEstudiante;
      }

      public void setIdEstudiante(long idEstudiante) {
         this.idEstudiante = idEstudiante;
      }

      public String getFechaEvaluacion() {
         return fechaEvaluacion;
      }

      public void setFechaEvaluacion(String fechaEvaluacion) {
         this.fechaEvaluacion = fechaEvaluacion;
      }

      public String getNombreEvaluador() {
         return nombreEvaluador;
      }

      public void setNombreEvaluador(String nombreEvaluador) {
         this.nombreEvaluador = nombreEvaluador;
      }
   }

}
