package org.grupomontevideo.escala.posgradopostulaciones.controller.postulacion;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.grupomontevideo.escala.posgradopostulaciones.controller.AbstractController;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.AreaDisciplinar;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Convocatoria;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.EstadoPostulacion;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Maestria;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Postulacion;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Universidad;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.WordpressUser;
import org.grupomontevideo.escala.posgradopostulaciones.model.enums.EnumEstadoPostulacion;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.AreaDisciplinarDao;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.ConvocatoriaDao;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.EstadoPostulacionDao;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.MaestriaDao;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.PostulacionDao;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.UniversidadDao;
import org.grupomontevideo.escala.posgradopostulaciones.persistence.WordpressUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;

/**
 * Created by Daniel B. on 18/07/2016.
 */
@RestController
public class PostulacionController extends AbstractController {

   private final Logger LOG = LoggerFactory.getLogger(this.getClass());

   @RequestMapping(value = "/agregar_postulacion", method = RequestMethod.POST)
   public
   @ResponseBody
   Map<String, Object> agregarPostulacion(@RequestParam(value = "txtUserId") String id, @RequestParam(value = "txtNombres") String nombre,
         @RequestParam(value = "txtCalle") String calle, @RequestParam(value = "txtNumeroPuerta") String numero,
         @RequestParam(value = "txtApartamento", required = false) String apto, @RequestParam(value = "txtZip", required = false) String zip,
         @RequestParam(value = "txtTelefono", required = false) String telefono, @RequestParam(value = "txtCiudadOrigen") String ciudad,
         @RequestParam(value = "selPaisOrigen") String paisDondeVive, @RequestParam(value = "txtEmail") String email,
         @RequestParam(value = "txtFechaNac") String fechaNac, @RequestParam(value = "txtDocumento") String documento,
         @RequestParam(value = "selPaisDoc") String paisDoc, @RequestParam(value = "txtPasaporte", required = false) String pasaporte,
         @RequestParam(value = "selPaisPasaporte", required = false) String paisPasaporte,
         @RequestParam(value = "selUnivOrigen") String universidadOrigen, @RequestParam(value = "optProgramaOrig") String programaOrigen,
         @RequestParam(value = "txtDenominacionProgramaOrig") String denominacionProgramaOrgien,
         @RequestParam(value = "selAreaDisciplinariaOrig") String areaDisciplinariaOrigen,
         @RequestParam(value = "txtCursosAprobados") String cursosAprobados, @RequestParam(value = "txtTutor") String tutor,
         @RequestParam(value = "selUnivDestino") String univDestino, @RequestParam(value = "optProgramaDest") String progDestino,
         @RequestParam(value = "txtDenominacionProgramaDest") String denominacionProgDestino,
         @RequestParam(value = "selAreaDisciplinariaDest") String areaDisciplinariaDestino,
         @RequestParam(value = "txtCarreraDeGrado") String carreraGrado, @RequestParam(value = "txtDeLaUniversidad") String deLaUniversidad,
         @RequestParam(value = "selPaisUniversidad") String paisUniversidad,
         @RequestParam(value = "txtObservacionesSolicitante") String observaciones,
         @RequestParam(value = "txtCursosActividades") String cursosActividades, @RequestParam(value = "txtDuracion") String duracion,
         @RequestParam(value = "txtFechaComienzo") String fechaComienzo, HttpServletRequest request) {

      final String methodName = new Object() {

      }.getClass().getEnclosingMethod().getName();
      final Map<String, Object> resultado = new HashMap<>();
      final Postulacion postulacion = new Postulacion();
      try {

         final WordpressUser wu = new WordpressUserDao(this.getSession()).get(Long.valueOf(id));

         final AreaDisciplinarDao add = new AreaDisciplinarDao(this.getSession());
         final AreaDisciplinar adOrig = add.get(Integer.parseInt(areaDisciplinariaOrigen));
         final AreaDisciplinar adDest = add.get(Integer.parseInt(areaDisciplinariaDestino));

         final UniversidadDao ud = new UniversidadDao(this.getSession());
         final Universidad unOrig = ud.get(Integer.valueOf(universidadOrigen));
         final Universidad unDest = ud.get(Integer.valueOf(univDestino));

         final MaestriaDao md = new MaestriaDao(this.getSession());
         final Maestria mOrig = md.get(Integer.valueOf(programaOrigen));
         final Maestria mDest = md.get(Integer.valueOf(progDestino));

         postulacion.setApartamento(apto);
         postulacion.setAreaDisciplinarDestino(adDest);
         postulacion.setAreaDisciplinarOrigen(adOrig);
         postulacion.setCalle(calle);
         postulacion.setCarreraDeGrado(carreraGrado);
         postulacion.setCiudad(ciudad);
         postulacion.setCodigoPostal(zip);
         postulacion.setCursosActividades(cursosActividades);
         postulacion.setCursosAprobados(cursosAprobados);
         postulacion.setDenominacionProgDest(denominacionProgDestino);
         postulacion.setDenominacionProgOrig(denominacionProgramaOrgien);
         postulacion.setDocumentoNac(documento);
         postulacion.setUsuario(wu);
         postulacion.setUniversidadOrigen(unOrig);
         postulacion.setUniversidadGrado(deLaUniversidad);
         postulacion.setUniversidadDestino(unDest);
         postulacion.setTelefono(telefono);
         postulacion.setPasaporte(pasaporte);
         postulacion.setObservaciones(observaciones);
         postulacion.setNumeroPuerta(numero);
         postulacion.setNombreYCargoTutor(tutor);
         postulacion.setNombres(nombre);
         postulacion.setMaestriaOrigen(mOrig);
         postulacion.setMaestriaDestino(mDest);
         postulacion.setImpreso(Boolean.FALSE);
         postulacion.setIdPaisUnivGrado(paisUniversidad);
         postulacion.setIdPaisPasaporte(paisPasaporte);
         postulacion.setIdPaisDoc(paisDoc);
         postulacion.setIdPaisDondeVive(paisDondeVive);
         postulacion.setFechaPostulacion(Timestamp.from(Instant.now()));
         postulacion.setFechaModificacion(Timestamp.from(Instant.now()));
         postulacion.setFechaComienzo(convert(fechaComienzo));
         postulacion.setFechaNacimiento(convert(fechaNac));
         postulacion.setDuracion(Integer.valueOf(duracion));
         postulacion.setEmail(email);

         EstadoPostulacion estadoPostulacion = new EstadoPostulacionDao(this.getSession()).get(EnumEstadoPostulacion.PENDIENTE.getId());

         postulacion.setEstado(estadoPostulacion);

         if (esValida(postulacion, resultado, false)) {

            PostulacionDao dao = new PostulacionDao(this.getSession());
            dao.add(postulacion);

            resultado.put("redirect", "index.php/formulario-de-postulacion/");
            resultado.put("id", String.valueOf(postulacion.getId()));
            resultado.put("mensaje", "Postulación creada con éxito");
            resultado.put("codigo", "0");

            agregarAlLog(postulacion, request.getRemoteAddr(), request.getHeader("user-agent"), methodName);
         }
      } catch (Exception ex) {
         resultado.put("codigo", "-1");
         resultado.put("mensaje", ex.getLocalizedMessage());
         ex.printStackTrace();
      }

      return resultado;
   }

   private boolean esValida(Postulacion postulacion, Map<String, Object> resultado, boolean esEdicion) {
      if (postulacion.getDuracion() < 15) {
         resultado.put("codigo", "-2");
         resultado.put("mensaje", "La duración debe ser mayor a 15 días.");
         return false;
      }
      if (postulacion.getFechaComienzo().before(new Date())) {
         resultado.put("codigo", "-3");
         resultado.put("mensaje", "La fecha de comienzo de la postulación debe ser posterior a la actual.");
         return false;
      }
      if (StringUtils.equals(postulacion.getUniversidadOrigen().getIdPais(), postulacion.getUniversidadDestino().getIdPais())) {
         resultado.put("codigo", "-4");
         resultado.put("mensaje", "El país de la universidad de origen debe ser distinto a la de destino.");
         return false;
      }

      if (!esEdicion) {
         final Convocatoria activa = getConvocatoriaActiva();
         if (activa == null) {
            resultado.put("codigo", "-6");
            resultado.put("mensaje", "No hay ninguna convocatoria activa.");
            return false;
         }
         if (postulacion.getFechaPostulacion().before(activa.getFechaFin()) && postulacion.getFechaPostulacion().after(activa.getFechaInicio())) {
            Collection<Postulacion> postulaciones = this.getPostulacionesPorUsuario(String.valueOf(postulacion.getUsuario().getId()));
            if (postulaciones.size() >= activa.getCantMaxPostulacionesPorEstudiante()) {
               resultado.put("codigo", "-5");
               resultado.put("mensaje", MessageFormat.format("No se pueden tener más de {1} postulaciones para este período",
                     activa.getCantMaxPostulacionesPorEstudiante()));

               return false;
            }
         }
      }
      return true;
   }

   @RequestMapping(value = "/editar_postulacion", method = RequestMethod.POST)
   public
   @ResponseBody
   Map<String, Object> edit(@RequestParam(value = "txtIdPost") Integer idPost, @RequestParam(value = "txtNombres", required = false) String nombre,
         @RequestParam(value = "txtCalle", required = false) String calle, @RequestParam(value = "txtNumeroPuerta", required = false) String numero,
         @RequestParam(value = "txtApartamento", required = false) String apto, @RequestParam(value = "txtZip", required = false) String zip,
         @RequestParam(value = "txtTelefono", required = false) String telefono,
         @RequestParam(value = "txtCiudadOrigen", required = false) String ciudad,
         @RequestParam(value = "selPaisOrigen", required = false) String paisDondeVive,
         @RequestParam(value = "txtEmail", required = false) String email, @RequestParam(value = "txtFechaNac", required = false) String fechaNac,
         @RequestParam(value = "txtDocumento", required = false) String documento,
         @RequestParam(value = "selPaisDoc", required = false) String paisDoc,
         @RequestParam(value = "txtPasaporte", required = false) String pasaporte,
         @RequestParam(value = "selPaisPasaporte", required = false) String paisPasaporte,
         @RequestParam(value = "selUnivOrigen_edit", required = false) String universidadOrigen,
         @RequestParam(value = "optProgramaOrig", required = false) String programaOrigen,
         @RequestParam(value = "txtDenominacionProgramaOrig", required = false) String denominacionProgramaOrgien,
         @RequestParam(value = "selAreaDisciplinariaOrig_edit", required = false) String areaDisciplinariaOrigen,
         @RequestParam(value = "txtCursosAprobados", required = false) String cursosAprobados,
         @RequestParam(value = "txtTutor", required = false) String tutor,
         @RequestParam(value = "selUnivDestino_edit", required = false) String univDestino,
         @RequestParam(value = "optProgramaDest", required = false) String progDestino,
         @RequestParam(value = "txtDenominacionProgramaDest", required = false) String denominacionProgDestino,
         @RequestParam(value = "selAreaDisciplinariaDest_edit", required = false) String areaDisciplinariaDestino,
         @RequestParam(value = "txtCarreraDeGrado", required = false) String carreraGrado,
         @RequestParam(value = "txtDeLaUniversidad", required = false) String deLaUniversidad,
         @RequestParam(value = "selPaisUniversidad", required = false) String paisUniversidad,
         @RequestParam(value = "txtObservacionesSolicitante", required = false) String observaciones,
         @RequestParam(value = "txtCursosActividades", required = false) String cursosActividades,
         @RequestParam(value = "txtDuracion", required = false) String duracion,
         @RequestParam(value = "txtFechaComienzo", required = false) String fechaComienzo, HttpServletRequest request) {

      final String methodName = new Object() {

      }.getClass().getEnclosingMethod().getName();
      final Map<String, Object> result = new HashMap<>();
      try {
         final PostulacionDao dao = new PostulacionDao(this.getSession());
         final Postulacion postulacion = dao.get(idPost);

         if (postulacion != null) {

            if (StringUtils.isNotBlank(paisPasaporte)) {
               postulacion.setIdPaisPasaporte(paisPasaporte);
            }

            if (StringUtils.isNotBlank(paisDoc)) {
               postulacion.setIdPaisDoc(paisDoc);
            }

            if (StringUtils.isNotBlank(pasaporte)) {
               postulacion.setPasaporte(pasaporte);
            }

            if (StringUtils.isNotBlank(documento)) {
               postulacion.setDocumentoNac(documento);
            }

            if (StringUtils.isNotBlank(nombre)) {
               postulacion.setNombres(nombre);
            }

            if (StringUtils.isNotBlank(calle)) {
               postulacion.setCalle(calle);
            }

            if (StringUtils.isNotBlank(numero)) {
               postulacion.setNumeroPuerta(numero);
            }

            if (StringUtils.isNotBlank(apto)) {
               postulacion.setApartamento(apto);
            }

            if (StringUtils.isNotBlank(zip)) {
               postulacion.setCodigoPostal(zip);
            }

            if (StringUtils.isNotBlank(telefono)) {
               postulacion.setTelefono(telefono);
            }

            if (StringUtils.isNotBlank(email)) {
               postulacion.setEmail(email);
            }

            if (StringUtils.isNotBlank(paisDondeVive)) {
               postulacion.setIdPaisDondeVive(paisDondeVive);
            }

            if (StringUtils.isNotBlank(fechaNac)) {
               postulacion.setFechaNacimiento(convert(fechaNac));
            }

            if (StringUtils.isNotBlank(ciudad)) {
               postulacion.setCiudad(ciudad);
            }

            final AreaDisciplinarDao add = new AreaDisciplinarDao(this.getSession());
            final AreaDisciplinar adOrig = add.get(Integer.parseInt(areaDisciplinariaOrigen.trim()));
            final AreaDisciplinar adDest = add.get(Integer.parseInt(areaDisciplinariaDestino.trim()));

            final UniversidadDao ud = new UniversidadDao(this.getSession());
            final Universidad unOrig = ud.get(Integer.valueOf(universidadOrigen.trim()));
            final Universidad unDest = ud.get(Integer.valueOf(univDestino.trim()));

            final MaestriaDao md = new MaestriaDao(this.getSession());
            final Maestria mOrig = md.get(Integer.valueOf(programaOrigen.trim()));
            final Maestria mDest = md.get(Integer.valueOf(progDestino.trim()));

            if (adDest != null) {
               postulacion.setAreaDisciplinarDestino(adDest);
            }
            if (adOrig != null) {
               postulacion.setAreaDisciplinarOrigen(adOrig);
            }
            if (StringUtils.isNotBlank(carreraGrado)) {
               postulacion.setCarreraDeGrado(carreraGrado);
            }
            if (StringUtils.isNotBlank(cursosActividades)) {
               postulacion.setCursosActividades(cursosActividades);
            }
            if (StringUtils.isNotBlank(cursosAprobados)) {
               postulacion.setCursosAprobados(cursosAprobados);
            }
            if (StringUtils.isNotBlank(denominacionProgDestino)) {
               postulacion.setDenominacionProgDest(denominacionProgDestino);
            }
            if (StringUtils.isNotBlank(denominacionProgramaOrgien)) {
               postulacion.setDenominacionProgOrig(denominacionProgramaOrgien);
            }
            if (unOrig != null) {
               postulacion.setUniversidadOrigen(unOrig);
            }
            if (StringUtils.isNotBlank(deLaUniversidad)) {
               postulacion.setUniversidadGrado(deLaUniversidad);
            }
            if (unDest != null) {
               postulacion.setUniversidadDestino(unDest);
            }
            if (StringUtils.isNotBlank(observaciones)) {
               postulacion.setObservaciones(observaciones);
            }
            if (StringUtils.isNotBlank(tutor)) {
               postulacion.setNombreYCargoTutor(tutor);
            }
            if (mOrig != null) {
               postulacion.setMaestriaOrigen(mOrig);
            }
            if (mOrig != null) {
               postulacion.setMaestriaDestino(mDest);
            }
            if (StringUtils.isNotBlank(paisUniversidad)) {
               postulacion.setIdPaisUnivGrado(paisUniversidad);
            }
            postulacion.setFechaModificacion(Timestamp.from(Instant.now()));
            if (StringUtils.isNotBlank(fechaComienzo)) {
               postulacion.setFechaComienzo(convert(fechaComienzo));
            }
            if (StringUtils.isNotBlank(duracion)) {
               postulacion.setDuracion(Integer.valueOf(duracion));
            }

            if (esValida(postulacion, result, true)) {
               dao.edit(postulacion);
               result.put("idPost", postulacion.getId());
               result.put("mensaje", "Postulación editada");
               result.put("codResp", "0");
               agregarAlLog(postulacion, request.getRemoteAddr(), request.getHeader("user-agent"), methodName);
            }
         }
      } catch (Exception ex) {
         LOG.error("Error al hacer actualización", ex);
         result.put("mensaje", ex.getLocalizedMessage());
         result.put("codResp", "-1");
      }

      return result;
   }

   @RequestMapping(value = "/lista_postulaciones_por_usuario", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesPorUsuario(@RequestParam(value = "txtUserId") String idUsuario) {
      final PostulacionDao dao = new PostulacionDao(this.getSession());
      if (StringUtils.isNumeric(idUsuario)) {
         final WordpressUser user = new WordpressUserDao(this.getSession()).get(Long.valueOf(idUsuario));
         return dao.getPorUsuario(user);
      }
      return null;
   }

   @RequestMapping(value = "/obtener_postulacion", method = RequestMethod.POST)
   public
   @ResponseBody
   Postulacion getPostulacion(@RequestParam(value = "postId") String id) {
      final PostulacionDao dao = new PostulacionDao(this.getSession());
      return dao.get(Integer.valueOf(id));
   }

   @RequestMapping(value = "/modificar_estado_postulacion", method = RequestMethod.POST)
   public
   @ResponseBody
   int cambiarEstadoAFirmada(@RequestParam(value = "pid", required = true) String postId, HttpServletRequest request) {
      try {
         final String methodName = new Object() {

         }.getClass().getEnclosingMethod().getName();
         final PostulacionDao dao = new PostulacionDao(this.getSession());
         Postulacion post = dao.get(Integer.valueOf(postId.trim()));
         post.setEstado(new EstadoPostulacionDao(getSession()).get(EnumEstadoPostulacion.FIRMADO_POR_TUTOR.getId()));
         dao.edit(post);
         agregarAlLog(post, request.getRemoteAddr(), request.getHeader("user-agent"), methodName);
         return 0;
      } catch (Exception ex) {
         LOG.error("Error al modificar estado a FIRMADA POR TUTOR", ex);
         return -1;
      }
   }

   @RequestMapping(value = "/obtener_postulaciones_con_filtros", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPostulacionesPorFiltro(@RequestParam(value = "univOrig", required = false) String univOrig,
         @RequestParam(value = "univDest", required = false) String univDest, @RequestParam(value = "fechaIni", required = false) String fechaIni,
         @RequestParam(value = "fechaFin", required = false) String fechaFin, @RequestParam(value = "estado", required = false) String estado) {
      final PostulacionDao postulacionDao = new PostulacionDao(getSession());
      if (Strings.isNullOrEmpty(univOrig) && Strings.isNullOrEmpty(univDest) && Strings.isNullOrEmpty(fechaIni) && Strings.isNullOrEmpty(fechaFin)
            && Strings.isNullOrEmpty(estado)) {
         return postulacionDao.getAll();
      }
      final Universidad origen = getUniversidad(univOrig);
      final Universidad destino = getUniversidad(univDest);
      final Timestamp fechaInicial = convertToTimestamp(fechaIni,0,0,0,0);
      final Timestamp fechaFinal = convertToTimestamp(fechaFin,23,59,59,999);
      EstadoPostulacion estadoPostulacion = null;
      if (StringUtils.isNumeric(estado)) {
         estadoPostulacion = new EstadoPostulacionDao(getSession()).get(Integer.valueOf(estado));
      }
      return postulacionDao.getPostulacionesParaPagAdmin(origen, destino, fechaInicial, fechaFinal, estadoPostulacion);
   }

   @RequestMapping(value = "/todas_por_universidad", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPorUniversidad(@RequestParam("univId") String univId, @RequestParam("esOrigen") boolean esOrigen) {
      final PostulacionDao postulacionDao = new PostulacionDao(getSession());
      final Universidad universidad = getUniversidad(univId);
      return postulacionDao.getPorUniversidad(universidad, esOrigen, null);
   }

   @RequestMapping(value = "/todas_por_fechas", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPorFechas(@RequestParam("fIni") String fechaInicial, @RequestParam("fFin") String fechaFinal) {
      Timestamp fini = convertToTimestamp(fechaInicial,0,0,0,0);
      Timestamp ffin = convertToTimestamp(fechaFinal,23,59,59,999);
      return new PostulacionDao(getSession()).getPorFechas(fini, ffin);
   }

   @RequestMapping(value = "/todas_por_estado", method = RequestMethod.POST)
   public
   @ResponseBody
   Collection<Postulacion> getPorEstado(@RequestParam(value = "estado") String estado) {
      EstadoPostulacion estadoPostulacion = null;
      if (StringUtils.isNumeric(estado)) {
         estadoPostulacion = new EstadoPostulacionDao(getSession()).get(Integer.valueOf(estado));
         return new PostulacionDao(getSession()).getPorEstado(estadoPostulacion);
      }
      return null;
   }

   private Convocatoria getConvocatoriaActiva() {
      ConvocatoriaDao dao = new ConvocatoriaDao(this.getSession());
      Date today = new Date();
      if (dao.getAll() != null) {
         for (Convocatoria c : dao.getAll()) {
            if (c.getFechaFin().after(today) && c.getFechaInicio().before(today)) {
               return c;
            }
         }
      }
      return null;
   }

}
