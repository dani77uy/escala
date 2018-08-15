package org.grupomontevideo.escala.model;

import java.io.Serializable;

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
public abstract class UsuarioGenerico implements Serializable {

   @Id
   private Long identificador;
   private String nombre;
   private String apellido;
   private String nombreDeUsuario;
   private String contrasena;
   private String email;
   @ManyToOne(targetEntity = Pais.class)
   private Pais pais;

}
