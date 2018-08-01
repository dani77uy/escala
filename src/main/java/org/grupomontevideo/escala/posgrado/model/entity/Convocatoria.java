package org.grupomontevideo.escala.posgrado.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Daniel B. on 18/07/2016.
 */
@Entity
@Table(name = "ESCALA_CONVOCATORIA", schema = "ESCALAPOSGRADO")
public class Convocatoria implements Serializable {

   private Integer id;

   private String nombre;

   private Date fechaInicio;

   private Date fechaFin;

   private Integer cantMaxPostulacionesPorEstudiante;

   @Id
   @Column(name = "ID", nullable = false)
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   @Basic
   @Column(name = "NOMBRE", nullable = false, length = 30)
   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   @Basic
   @Column(name = "FECHA_INICIO", nullable = false)
   public Date getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(Date fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   @Basic
   @Column(name = "FECHA_FIN", nullable = false)
   public Date getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(Date fechaFin) {
      this.fechaFin = fechaFin;
   }

   @Basic
   @Column(name = "CANT_MAX_POSTULACIONES_POR_ESTUDIANTE", nullable = false)
   public Integer getCantMaxPostulacionesPorEstudiante() {
      return cantMaxPostulacionesPorEstudiante;
   }

   public void setCantMaxPostulacionesPorEstudiante(Integer cantMaxPostulacionesPorEstudiante) {
      this.cantMaxPostulacionesPorEstudiante = cantMaxPostulacionesPorEstudiante;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      Convocatoria that = (Convocatoria) o;

      if (id != null ? !id.equals(that.id) : that.id != null) {
         return false;
      }
      if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) {
         return false;
      }
      if (fechaInicio != null ? !fechaInicio.equals(that.fechaInicio) : that.fechaInicio != null) {
         return false;
      }
      if (fechaFin != null ? !fechaFin.equals(that.fechaFin) : that.fechaFin != null) {
         return false;
      }
      if (cantMaxPostulacionesPorEstudiante != null
            ? !cantMaxPostulacionesPorEstudiante.equals(that.cantMaxPostulacionesPorEstudiante)
            : that.cantMaxPostulacionesPorEstudiante != null) {
         return false;
      }

      return true;
   }

   @Override
   public int hashCode() {
      int result = id != null ? id.hashCode() : 0;
      result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
      result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
      result = 31 * result + (fechaFin != null ? fechaFin.hashCode() : 0);
      result = 31 * result + (cantMaxPostulacionesPorEstudiante != null ? cantMaxPostulacionesPorEstudiante.hashCode() : 0);
      return result;
   }
}
