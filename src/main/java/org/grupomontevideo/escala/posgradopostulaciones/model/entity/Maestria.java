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
@Table(name = "ESCALA_MAESTRIA", schema = "ESCALAPOSGRADO")
public class Maestria implements Serializable {

   private Integer codMaestria;

   private String nombre;

   private String descripcion;

   private Boolean habilitado;

   private String origenODestino;

   @Id
   @Column(name = "COD_MAESTRIA", nullable = false)
   public Integer getCodMaestria() {
      return codMaestria;
   }

   public void setCodMaestria(Integer codMaestria) {
      this.codMaestria = codMaestria;
   }

   @Basic
   @Column(name = "NOMBRE", nullable = false, length = 20)
   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   @Basic
   @Column(name = "DESCRIPCION", nullable = true, length = 100)
   public String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   @Basic
   @Column(name = "HABILITADO", nullable = false)
   public Boolean getHabilitado() {
      return habilitado;
   }

   public void setHabilitado(Boolean habilitado) {
      this.habilitado = habilitado;
   }

   @Basic
   @Column(name = "ORIGEN_O_DESTINO", nullable = false, length = 2)
   public String getOrigenODestino() {
      return origenODestino;
   }

   public void setOrigenODestino(String origenODestino) {
      this.origenODestino = origenODestino;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      Maestria maestria = (Maestria) o;

      if (codMaestria != null ? !codMaestria.equals(maestria.codMaestria) : maestria.codMaestria != null) {
         return false;
      }
      if (nombre != null ? !nombre.equals(maestria.nombre) : maestria.nombre != null) {
         return false;
      }
      if (descripcion != null ? !descripcion.equals(maestria.descripcion) : maestria.descripcion != null) {
         return false;
      }
      if (habilitado != null ? !habilitado.equals(maestria.habilitado) : maestria.habilitado != null) {
         return false;
      }
      if (origenODestino != null ? !origenODestino.equals(maestria.origenODestino) : maestria.origenODestino != null) {
         return false;
      }

      return true;
   }

   @Override
   public int hashCode() {
      int result = codMaestria != null ? codMaestria.hashCode() : 0;
      result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
      result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
      result = 31 * result + (habilitado != null ? habilitado.hashCode() : 0);
      result = 31 * result + (origenODestino != null ? origenODestino.hashCode() : 0);
      return result;
   }
}
