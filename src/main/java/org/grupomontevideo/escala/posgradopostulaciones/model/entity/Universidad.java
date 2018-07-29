package org.grupomontevideo.escala.posgradopostulaciones.model.entity;

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
@Table(name = "ESCALA_UNIVERSIDAD", schema = "ESCALAPOSGRADO")
public class Universidad implements Serializable {

   private Integer id;

   private String nombre;

   private String idPais;

   @Id
   @Column(name = "ID", nullable = false)
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   @Basic
   @Column(name = "NOMBRE", nullable = false, length = 100)
   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   @Basic
   @Column(name = "ID_PAIS", nullable = false, length = 2)
   public String getIdPais() {
      return idPais;
   }

   public void setIdPais(String idPais) {
      this.idPais = idPais;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      Universidad that = (Universidad) o;

      if (id != null ? !id.equals(that.id) : that.id != null) {
         return false;
      }
      if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) {
         return false;
      }
      if (idPais != null ? !idPais.equals(that.idPais) : that.idPais != null) {
         return false;
      }

      return true;
   }

   @Override
   public int hashCode() {
      int result = id != null ? id.hashCode() : 0;
      result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
      result = 31 * result + (idPais != null ? idPais.hashCode() : 0);
      return result;
   }

   public Universidad() {}

   public Universidad(Integer id, String nombre, String idPais) {
      this.id = id;
      this.nombre = nombre;
      this.idPais = idPais;
   }

   @Override
   public String toString() {
      return this.id + " " + this.nombre + " " + this.idPais;
   }
}
