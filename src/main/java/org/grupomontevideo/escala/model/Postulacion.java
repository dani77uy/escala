package org.grupomontevideo.escala.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniel Baharian
 * @since 9 ago 2018
 */
@Entity
@Getter
@Setter
public abstract class Postulacion implements Serializable {

   @Id
   private Long identificador;
   @ManyToOne
   private UsuarioPostulante usuarioPostulante;
   @ManyToOne
   private Universidad universidadOrigen;
   @ManyToOne
   private Universidad universidadDestino;
   private Date fechaPostulacion;
   private Date fechaComienzoPostulacion;
   private Short cantidadDiasDeLaPostulacion;
   private String descripcion;
}
