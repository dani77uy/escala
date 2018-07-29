package org.grupomontevideo.escala.posgradopostulaciones.persistence;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

/**
 * Created by Daniel B. on 18/07/2016.
 */
public abstract class AbstractDao<T extends Serializable, PK extends Serializable> {

   private final Session session;

   public AbstractDao(Session session) {
      this.session = session;
   }

   protected Session getSession() {
      return session;
   }

   protected T add(T t) {
      session.beginTransaction();
      session.save(t);
      session.getTransaction().commit();
      return t;
   }

   protected T edit(T t) {
      session.beginTransaction();
      session.update(t);
      session.getTransaction().commit();
      return t;
   }

   protected T get(PK pk, Class<T> clazz) {
      return session.get(clazz, pk);
   }

   protected abstract boolean remove(T t);

   protected Collection<T> getAll(Class clazz) {
      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<T> query = builder.createQuery(clazz);
      Root<T> variableRoot = query.from(clazz);
      query.select(variableRoot);
      return session.createQuery(query).getResultList();
   }

}
