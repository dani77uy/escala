package org.grupomontevideo.escala.posgradopostulaciones.model.entity.pk;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.grupomontevideo.escala.posgradopostulaciones.model.entity.Postulacion;
import org.grupomontevideo.escala.posgradopostulaciones.model.entity.WordpressUser;

/**
 * Created by Daniel B. on 02/08/2016.
 */
@Embeddable
public class LogPostulacionesPK implements Serializable {

   @ManyToOne
   @JoinColumn(name = "USER_ID")
   private WordpressUser user;

   @ManyToOne
   @JoinColumn(name = "POST_ID")
   private Postulacion postulacion;

   @Column(name = "FECHA_CREACION", nullable = false)
   private Timestamp fechaCreacion;

   @Column(name = "FECHA_MODIFICACION", nullable = false)
   private Timestamp fechaModificacion;

   public WordpressUser getUser() {
      return user;
   }

   public void setUser(WordpressUser user) {
      this.user = user;
   }

   public Postulacion getPostulacion() {
      return postulacion;
   }

   public void setPostulacion(Postulacion postulacion) {
      this.postulacion = postulacion;
   }

   public Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   public Timestamp getFechaModificacion() {
      return fechaModificacion;
   }

   public void setFechaModificacion(Timestamp fechaModificacion) {
      this.fechaModificacion = fechaModificacion;
   }

}
