package org.grupomontevideo.escala.posgradopostulaciones.persistence;

import org.grupomontevideo.escala.posgradopostulaciones.model.entity.WordpressUser;
import org.hibernate.Session;

/**
 * Created by Daniel B. on 18/07/2016.
 */
public class WordpressUserDao extends AbstractDao<WordpressUser, Long>{

   public WordpressUserDao(Session session) {
      super(session);
   }

   @Override
   protected WordpressUser edit(WordpressUser wordpressUser) {
      return null;
   }

   @Override
   protected boolean remove(WordpressUser wordpressUser) {
      return false;
   }

   public WordpressUser get(Long pk) {
      return super.get(pk, WordpressUser.class);
   }

}
