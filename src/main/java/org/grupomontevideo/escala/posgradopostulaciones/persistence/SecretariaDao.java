package org.grupomontevideo.escala.posgradopostulaciones.persistence;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Postulacion;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Universidad;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.WordpressUser;
import org.grupomontevideo.escala.posgradopostulaciones.model.enums.EnumEstadoPostulacion;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * Created by Daniel B on 10/08/2016.
 */
public class SecretariaDao extends AbstractDao {

   public static final int FILTER_GENERAL = 1;
   public static final int FILTER_UNIVERSIDAD = 2;
   public static final int FILTER_CONVOCATORIA = 3;
   public static final int FILTER_RANGO_FECHA = 4;
   public static final int FILTER_PAIS_ORIGEN = 5;
   public static final int FILTER_PAIS_DESTINO = 6;

   public static final int FILTER_POR_DOCUMENTO = 1;
   public static final int FILTER_POR_NOMBRE = 2;
   public static final int FILTER_POR_APELLIDO = 3;

   public SecretariaDao(Session session) {
      super(session);
   }

   public Collection<Postulacion> getPostulacionesParaSecretaria(Universidad universidad, java.sql.Date fechaInicialPost, java.sql.Date fechaFinalPost,
         String paisOrigen, String paisDestino, int filtro, EnumEstadoPostulacion estado) {
      Query<Postulacion> query = null;
      switch (filtro) {
         case FILTER_GENERAL:
            query = getSession().createQuery("select p from Postulacion p where p.estado.codEstadoPost = :estado");
            break;
         case FILTER_UNIVERSIDAD:
            query = getSession().createQuery("select p from Postulacion p where p.estado.codEstadoPost = :estado and p.universidadDestino.id = :univDest");
            query.setParameter("univDest", universidad.getId());
            break;
         case FILTER_CONVOCATORIA:
            query = getSession().createQuery("SELECT P FROM Convocatoria C, Postulacion P WHERE C.id = :id AND P.fechaPostulacion BETWEEN C.fechaInicio AND C.fechaFin AND P.estado.codEstadoPost = :estado");
            query.setParameter("id", 1);
            break;
         case FILTER_RANGO_FECHA:
            query = getSession().createQuery("select P FROM Postulacion P WHERE P.fechaPostulacion between :fini AND :ffin AND P.estado.codEstadoPost = :estado");
            query.setParameter("fini", fechaInicialPost);
            query.setParameter("ffin", fechaFinalPost);
            break;
         case FILTER_PAIS_ORIGEN:
            query = getSession().createQuery("select P FROM Postulacion P WHERE P.universidadOrigen.idPais = :porig AND P.estado.codEstadoPost = :estado");
            query.setParameter("porig", paisOrigen);
         case FILTER_PAIS_DESTINO:
            query = getSession().createQuery("select P FROM Postulacion P WHERE P.universidadDestino.idPais = :pdest AND P.estado.codEstadoPost = :estado");
            query.setParameter("pdest", paisDestino);
            break;
      }
      if (query != null) {
         query.setParameter("estado", estado.getId());
         return query.getResultList();
      }
      return null;
   }

   public Collection<Postulacion> getPostulacionesPorUsuario(String documento, String nombre, String apellido, EnumEstadoPostulacion estado, int filtro) {
      Query<Postulacion> query = null;
      String hql = "";
      switch (filtro) {
         case FILTER_POR_DOCUMENTO:
            if (estado == null) {
               hql = "SELECT P FROM Postulacion P WHERE P.documentoNac = :doc";
            } else {
               hql = "SELECT P FROM Postulacion P WHERE P.documentoNac = :doc AND P.estado.codEstadoPost = :estado";
            }
            query = getSession().createQuery(hql);
            query.setParameter("doc", documento);
            break;
         case FILTER_POR_NOMBRE:
            if (estado == null) {
               hql = "SELECT P FROM Postulacion P WHERE P.nombres LIKE :nom";
            } else {
               hql = "SELECT P FROM Postulacion P WHERE P.nombres LIKE :nom AND P.estado.codEstadoPost = :estado";
            }
            query = getSession().createQuery(hql);
            query.setParameter("nom", "%" + nombre + "%");
            break;
         case FILTER_POR_APELLIDO:
            if (estado == null) {
               hql = "SELECT P FROM Postulacion P WHERE P.nombres LIKE :ape";
            } else {
               hql = "SELECT P FROM Postulacion P WHERE P.nombres LIKE :ape AND P.estado.codEstadoPost = :estado";
            }
            query = getSession().createQuery(hql);
            query.setParameter("ape", "%" + apellido + "%");
            break;
      }
      if (estado != null) {
         query.setParameter("estado", estado.getId());
      }
      return query.getResultList();
   }

   public Collection<WordpressUser> getCoordinadoresPorUniversidad(Universidad universidad) {
      final Query<WordpressUser> query = getSession().createQuery("SELECT U FROM org.grupomontevideo.escala.posgradopostulaciones.model.entity.WordpressUser U, "
            + " org.grupomontevideo.escala.posgradopostulaciones.model.entity.WordpressCoordinadorMetaData W WHERE W.fieldId = :universidadesId"
            + " AND W.userValue = :univ AND W.userId = U.id");
      query.setParameter("universidadesId", Long.valueOf(1));
      query.setParameter("univ", String.valueOf(universidad.getId()));
      return query.getResultList();
   }

   public Collection<Universidad> getUniversidadesPorPais(String paisId) {
      return getSession().createQuery("SELECT U FROM Universidad U where U.idPais = :idpais").setParameter("idpais", paisId).getResultList();
   }

   public boolean verificarSiEsSecretario(WordpressUser user) {
      if (user == null) {
         return false;
      }
      final Query<WordpressUser> usersQuery = getSession().createQuery("SELECT U FROM WordpressUser U, WordpressUserRol UR, WorpressRol R "
            + " WHERE UR.userId = U.id AND R.role = :role AND R.id = UR.roleId AND U.id = :uid");
      usersQuery.setParameter("role", "administrator");
      usersQuery.setParameter("uid", user.getId());
      List<WordpressUser> users = usersQuery.getResultList();
      return users != null && users.size() > 0;
   }

   @Override
   protected boolean remove(Serializable serializable) {
      return false;
   }
}
