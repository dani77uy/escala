package org.grupomontevideo.escala.model;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * @author Daniel Baharian
 * @since 9 ago 2018
 */
@Entity
public abstract class UsuarioGenerico implements Serializable {

   private Long identificador;
   private String nombre;
   private String apellido;
   private String nombreDeUsuario;
   private String contrasena;
   private String email;
   private Pais pais;

}
