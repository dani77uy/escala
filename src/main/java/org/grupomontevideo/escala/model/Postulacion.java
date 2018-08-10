package org.grupomontevideo.escala.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

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
   private UsuarioPostulante usuarioPostulante;
   private Universidad universidadOrigen;
   private Universidad universidadDestino;
   private Date fechaPostulacion;
   private Date fechaComienzoPostulacion;
   private Short cantidadDiasDeLaPostulacion;
   private String descripcion;
}
