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
@Table(name = "ESCALA_PRIORIODADES_PAISES", schema = "ESCALAPOSGRADO")
public class PrioriodadesDePais implements Serializable {

   private String idPais;

   private Integer prioridad;

   @Id
   @Column(name = "ID_PAIS", nullable = false, length = 2)
   public String getIdPais() {
      return idPais;
   }

   public void setIdPais(String idPais) {
      this.idPais = idPais;
   }

   @Basic
   @Column(name = "PRIORIDAD", nullable = false)
   public Integer getPrioridad() {
      return prioridad;
   }

   public void setPrioridad(Integer prioridad) {
      this.prioridad = prioridad;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      PrioriodadesDePais that = (PrioriodadesDePais) o;

      if (idPais != null ? !idPais.equals(that.idPais) : that.idPais != null) {
         return false;
      }
      if (prioridad != null ? !prioridad.equals(that.prioridad) : that.prioridad != null) {
         return false;
      }

      return true;
   }

   @Override
   public int hashCode() {
      int result = idPais != null ? idPais.hashCode() : 0;
      result = 31 * result + (prioridad != null ? prioridad.hashCode() : 0);
      return result;
   }
}
