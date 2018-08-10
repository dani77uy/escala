package org.grupomontevideo.escala.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniel Baharian
 * @since 9 ago 2018
 */
@Entity
@Getter
@Setter
public class UsuarioPostulante extends UsuarioGenerico {

   private List<Postulacion> postulaciones;
   private String calle;
   private String numeroPuerta;
   private String otrosDatosDeDireccion;
   private Date fechaNacimiento;
   private String nombreTutor;
}
