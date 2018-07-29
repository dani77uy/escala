package org.grupomontevideo.escala.posgradopostulaciones.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.EstadoPostulacion;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Postulacion;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Universidad;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.WordpressCoordinadorMetaData;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.WordpressUser;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.WordpressUserRol;
import org.grupomontevideo.escala.posgradopostulaciones.model.enums.EnumEstadoPostulacion;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * Created by Daniel B. on 04/08/2016.
 */
public class CoordinadorDao extends AbstractDao {

   public static final Long ROL_COORDINADOR = Long.valueOf(4);

   public CoordinadorDao(Session session) {
      super(session);
   }

   public boolean verificarSiEsCoordinador(WordpressUser user, Universidad universidad) {
      final Long id = user.getId();
      final String univId = String.valueOf(universidad.getId());
      return Objects.nonNull(getMetaDataPorUsuario(id, univId));
   }

   public boolean esCoordinador(WordpressUser user) {
      final Long id = user.getId();
      Query<WordpressUserRol> query = getSession().createQuery("SELECT R FROM WordpressUserRol R WHERE userId = :uid");
      query.setParameter("uid", id);
      WordpressUserRol userRol = query.getSingleResult();
      return ROL_COORDINADOR == userRol.getRoleId();
   }

   public Collection<Postulacion> getPostulacionesPorUniversidadOrigen(WordpressUser user, Universidad universidad) {
      Collection<Postulacion> postulaciones = null;
      if (Objects.isNull(universidad) || Objects.isNull(user)) {
         return null;
      }
      if (verificarSiEsCoordinador(user, universidad)) {
         final Query<Postulacion> query = getSession().createQuery("select P from Postulacion P where P.universidadOrigen.id = :univId AND P.estado.id in (:estado0, :estado1, :estado2)");
         query.setParameter("univId", universidad.getId());
         query.setParameter("estado0", EnumEstadoPostulacion.PENDIENTE.getId());
         query.setParameter("estado1", EnumEstadoPostulacion.IMPRESO.getId());
         query.setParameter("estado2", EnumEstadoPostulacion.FIRMADO_POR_TUTOR.getId());
         postulaciones = query.getResultList();
      }
      return postulaciones;
   }

   public Collection<Postulacion> getPostulacionesPorUniversidadDestino(WordpressUser user, Universidad universidad) {
      Collection<Postulacion> postulaciones = null;
      if (Objects.isNull(universidad) || Objects.isNull(user)) {
         return null;
      }
      if (verificarSiEsCoordinador(user, universidad)) {
         final Query<Postulacion> query = getSession().createQuery("select P from Postulacion P where P.universidadDestino.id = :univId AND P.estado.id in (:estado0, :estado1, :estado2, :estado3)");
         query.setParameter("univId", universidad.getId());
         query.setParameter("estado0", EnumEstadoPostulacion.PENDIENTE.getId());
         query.setParameter("estado1", EnumEstadoPostulacion.IMPRESO.getId());
         query.setParameter("estado2", EnumEstadoPostulacion.FIRMADO_POR_TUTOR.getId());
         query.setParameter("estado3", EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_ORIGEN.getId());
         postulaciones = query.getResultList();
      }
      return postulaciones;
   }

   public Collection<Postulacion> getPostulacionesPorUniversidadParaEvaluar(WordpressUser coordinador, boolean esOrigen, EnumEstadoPostulacion estado) {
      final Universidad universidad = getUniversidadDeCoordinador(coordinador);
      if (Objects.nonNull(universidad)) {
         final PostulacionDao pDao = new PostulacionDao(getSession());
         return pDao.getPorUniversidad(universidad, esOrigen, estado);
      }
      return null;
   }

   @Deprecated
   public Pair<Collection<Postulacion>, Collection<Postulacion>> getPostulacionesManejadasPorUsuario(WordpressUser user) {
      final Universidad universidad = getUniversidadDeCoordinador(user);
      if (Objects.isNull(universidad)) {
         return null;
      }
      final Query<Postulacion> query = getSession().createQuery(
            "SELECT p from Postulacion p where (p.universidadDestino.id = :uni and p.aprobadorDestino.id = :apr) or (p.universidadOrigen.id = :uni and p"
                  + ".aprobadorOrigen.id = :apr) ");
      query.setParameter("uni", universidad.getId());
      query.setParameter("apr", user.getId());
      final List<Postulacion> postulaciones = query.getResultList();
      final List<Postulacion> aprobadas = new ArrayList<>();
      final List<Postulacion> rechazadas = new ArrayList<>();
      postulaciones.stream().filter(
            postulacion -> postulacion.getEstado().getCodEstadoPost() == EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_DESTINO.getId()
                  || postulacion.getEstado().getCodEstadoPost() == EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_ORIGEN.getId()).forEach(
            aprobadas::add);
      postulaciones.stream().filter(postulacion -> postulacion.getEstado().getCodEstadoPost() == EnumEstadoPostulacion.RECHAZADA_DESTINO.getId()
            || postulacion.getEstado().getCodEstadoPost() == EnumEstadoPostulacion.RECHAZADA_ORIGEN.getId()).forEach(rechazadas::add);
      return Pair.of(aprobadas, rechazadas);
   }

   @Override
   protected boolean remove(Serializable serializable) {
      return false;
   }

   private WordpressCoordinadorMetaData getMetaDataPorUsuario(Long userId, String univId) {
      final Query<WordpressCoordinadorMetaData> query = getSession().createQuery(
            "SELECT W from WordpressCoordinadorMetaData W where W.userId = :uid and W.userValue = :univ AND W.fieldId = :universiadesId");
      query.setParameter("uid", userId);
      query.setParameter("univ", univId);
      query.setParameter("universiadesId", Long.valueOf("1"));
      return query.getSingleResult();
   }

   public EstadoPostulacion getEstado(EnumEstadoPostulacion estado) {
      final EstadoPostulacionDao dao = new EstadoPostulacionDao(getSession());
      return dao.get(estado.getId());
   }

   public Universidad getUniversidadDeCoordinador(WordpressUser user) {
      final Query<WordpressCoordinadorMetaData> query = getSession().createQuery("SELECT W FROM WordpressCoordinadorMetaData W WHERE W.userId = :uid AND W.fieldId = :universidadesId");
      query.setParameter("uid", user.getId());
      query.setParameter("universidadesId", Long.valueOf("1"));
      WordpressCoordinadorMetaData metaData = query.getSingleResult();
      final String univId = metaData.getUserValue();
      final UniversidadDao universidadDao = new UniversidadDao(getSession());
      if (StringUtils.isNotBlank(univId) && NumberUtils.isNumber(univId.trim())) {
         return universidadDao.get(NumberUtils.toInt(univId));
      }
      return null;
   }



}
