package org.grupomontevideo.escala.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniel Baharian
 * @since 9 ago 2018
 */
@Getter
@Setter
@MappedSuperclass
public abstract class UsuarioGenerico implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID", updatable = false, nullable = false)
   @Setter(AccessLevel.NONE)
   private Long identificador;

   @Column(name = "NOMBRE")
   private String nombre;

   @Column(name = "APELLIDO")
   private String apellido;

   @Column(name = "NOMBRE_USUARIO", unique = true)
   private String nombreDeUsuario;

   @Column(name = "CONTRASENA")
   private String contrasena;

   @Column(name = "EMAIL", unique = true)
   private String email;

   @ManyToOne(targetEntity = Pais.class)
   private Pais pais;

}
