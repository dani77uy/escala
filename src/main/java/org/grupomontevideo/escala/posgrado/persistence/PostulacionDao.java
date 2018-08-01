package org.grupomontevideo.escala.posgrado.persistence;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.grupomontevideo.escala.posgrado.model.entity.EstadoPostulacion;
import org.grupomontevideo.escala.posgrado.model.entity.Postulacion;
import org.grupomontevideo.escala.posgrado.model.entity.Universidad;
import org.grupomontevideo.escala.posgrado.model.entity.WordpressUser;
import org.grupomontevideo.escala.posgrado.model.enums.EnumEstadoPostulacion;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.google.common.collect.Lists;

/**
 * Created by Daniel B. on 18/07/2016.
 */
public class PostulacionDao extends AbstractDao<Postulacion, Integer> {

   public PostulacionDao(Session session) {
      super(session);
   }

   @Override
   public Postulacion add(Postulacion postulacion) {
      String uniqueID = UUID.randomUUID().toString();
      postulacion.setUniqueId(uniqueID);
      return super.add(postulacion);
   }

   @Override
   public Postulacion edit(Postulacion postulacion) {
      return super.edit(postulacion);
   }

   @Override
   protected boolean remove(Postulacion postulacion) {
      return false;
   }

   public Postulacion get(Integer pk) {
      return super.get(pk, Postulacion.class);
   }

   public Collection<Postulacion> getPorUsuario(WordpressUser userId) {
      Query<Postulacion> query = getSession().createQuery("from Postulacion where usuario = :usuario");
      query.setParameter("usuario", userId);
      return query.getResultList();
   }

   public Postulacion getPorIdEImpresa(Integer id) {
      Query<Postulacion> query = getSession().createQuery("from Postulacion where id = :id and impreso = :impreso");
      query.setParameter("id", id);
      query.setParameter("impreso", Boolean.TRUE);
      return query.getSingleResult();
   }

   public Collection<Postulacion> getPorUniversidad(Universidad universidad, Boolean esOrigen, EnumEstadoPostulacion...  estados) {
      final Query<Postulacion> query;
      if (esOrigen == null) {
         if (estados == null || estados.length == 0) {
            query = getSession().createQuery("from Postulacion p where p.universidadOrigen.id = :univId or p.universidadDestino.id = :univId");
         } else {
            query = getSession().createQuery("from Postulacion p where (p.universidadOrigen.id = :univId or p.universidadDestino.id = :univId)"
                  + " and p.estado.codEstadoPost in (?1)");
         }
      } else {
         if (esOrigen) {
            if (estados == null || estados.length == 0) {
               query = getSession().createQuery("from Postulacion p where p.universidadOrigen.id = :univId");
            } else {
               query = getSession().createQuery("from Postulacion p where p.universidadOrigen.id = :univId and p.estado.codEstadoPost in (?1)");
            }
         } else {
            if (estados == null || estados.length == 0) {
               query = getSession().createQuery("from Postulacion p where p.universidadDestino.id = :univId");
            } else {
               query = getSession().createQuery("from Postulacion p where p.universidadDestino.id = :univId and p.estado.codEstadoPost in (?1)");
            }
         }
      }
      if (estados != null && estados.length > 0) {
         List<Integer> ids = Lists.newArrayList();
         for(EnumEstadoPostulacion enumEstadoPostulacion : estados) {
            ids.add(enumEstadoPostulacion.getId());
         }
         query.setParameter(1, ids);
      }
      query.setParameter("univId", universidad.getId());

      return query.getResultList();
   }

   public Collection<Postulacion> getPorFechas(Timestamp fechaInicial, Timestamp fechaFinal) {
      final Query<Postulacion> query = getSession().createQuery("from Postulacion p where p.fechaPostulacion between :fini and :ffin");
      query.setParameter("fini", fechaInicial);
      query.setParameter("ffin", fechaFinal);
      return query.getResultList();
   }

   public Collection<Postulacion> getPorCoordinador(WordpressUser coordinador, EnumEstadoPostulacion estado) {
      final Query<Postulacion> postulacionQuery = getSession().createQuery(
            "select p from Postulacion p where (p.aprobadorOrigen.id = :uid and p.estado.id = :estado) or (p.aprobadorDestino.id = :uid and p.estado.id = :estado)");
      postulacionQuery.setParameter("uid", coordinador.getId());
      postulacionQuery.setParameter("estado", estado.getId());
      return postulacionQuery.getResultList();
   }

   public Collection<Postulacion> getPorEstado(EstadoPostulacion estado) {
      final CriteriaBuilder builder = getSession().getCriteriaBuilder();
      final CriteriaQuery<Postulacion> query = builder.createQuery(Postulacion.class);
      final Root<Postulacion> rootPostulacion = query.from(Postulacion.class);
      final List<Predicate> predicates = new ArrayList<>();
      if (estado != null) {
         predicates.add(builder.equal(rootPostulacion.get("estado"), estado));
      }
      query.select(rootPostulacion).where(predicates.toArray(new Predicate[]{}));

      return getSession().createQuery(query).getResultList();
   }

   public Collection<Postulacion> getPorUniversidadConfirmadas(Universidad universidad) {
      Collection<Postulacion> origenes = getPorUniversidad(universidad, true, EnumEstadoPostulacion.APROBADO_POR_SECRETARIA);
      Collection<Postulacion> destinos = getPorUniversidad(universidad, false, EnumEstadoPostulacion.APROBADO_POR_SECRETARIA);

      Set<Postulacion> set = new HashSet<>();
      set.addAll(origenes);
      set.addAll(destinos);
      return new ArrayList(set);
   }

   public Collection<Postulacion> getEvaluadasPorCoordinador(WordpressUser user, boolean aprobadas) {
      final Query<Postulacion> postulacionQuery = getSession().createQuery(
            "select p from Postulacion p where (p.aprobadorOrigen.id = :uid or p.aprobadorDestino.id = :uid) and (p.estado.id = :estado1 or p.estado.id = :estado2)");
      if (aprobadas) {
         postulacionQuery.setParameter("estado1", EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_ORIGEN.getId());
         postulacionQuery.setParameter("estado2", EnumEstadoPostulacion.APROBADO_POR_COORDINADOR_DESTINO.getId());
      } else {
         postulacionQuery.setParameter("estado1", EnumEstadoPostulacion.RECHAZADA_ORIGEN.getId());
         postulacionQuery.setParameter("estado2", EnumEstadoPostulacion.RECHAZADA_DESTINO.getId());
      }
      postulacionQuery.setParameter("uid", user.getId());
      return postulacionQuery.getResultList();
   }

   public Collection<Postulacion> getAll() {
      return getSession().createQuery("from Postulacion").getResultList();
   }

   public Collection<Postulacion> getPostulacionesParaPagAdmin(Universidad origen, Universidad destino, Timestamp fechaInicial, Timestamp fechaFinal, EstadoPostulacion estado) {
      final CriteriaBuilder builder = getSession().getCriteriaBuilder();
      final CriteriaQuery<Postulacion> query = builder.createQuery(Postulacion.class);
      final Root<Postulacion> rootPostulacion = query.from(Postulacion.class);
      final List<Predicate> predicates = new ArrayList<>();
      if (origen != null) {
         predicates.add(builder.equal(rootPostulacion.get("universidadOrigen"), origen));
      }
      if (destino != null) {
         predicates.add(builder.equal(rootPostulacion.get("universidadDestino"), destino));
      }
      if (fechaFinal != null && fechaFinal != null) {
         predicates.add(builder.between(rootPostulacion.get("fechaPostulacion"), fechaInicial, fechaFinal));
      }
      if (estado != null) {
         predicates.add(builder.equal(rootPostulacion.get("estado"), estado));
      }
      query.select(rootPostulacion).where(predicates.toArray(new Predicate[]{}));

      return getSession().createQuery(query).getResultList();
   }
}
