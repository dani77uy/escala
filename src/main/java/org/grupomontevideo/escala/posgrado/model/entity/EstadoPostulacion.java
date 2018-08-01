package org.grupomontevideo.escala.posgrado.model.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Daniel B. on 18/07/2016.
 */
@Entity
@Table(name = "ESCALA_ESTADOS_POSTULACIONES", schema = "ESCALAPOSGRADO")
public class EstadoPostulacion implements Serializable {

   private Integer codEstadoPost;

   private String descripcion;

   @Id
   @Column(name = "COD_ESTADO_POST", nullable = false)
   public Integer getCodEstadoPost() {
      return codEstadoPost;
   }

   public void setCodEstadoPost(Integer codEstadoPost) {
      this.codEstadoPost = codEstadoPost;
   }

   @Basic
   @Column(name = "DESCRIPCION", nullable = false, length = 100)
   public String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      EstadoPostulacion that = (EstadoPostulacion) o;

      if (codEstadoPost != null ? !codEstadoPost.equals(that.codEstadoPost) : that.codEstadoPost != null) {
         return false;
      }
      if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) {
         return false;
      }

      return true;
   }

   @Override
   public int hashCode() {
      int result = codEstadoPost != null ? codEstadoPost.hashCode() : 0;
      result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
      return result;
   }
}
