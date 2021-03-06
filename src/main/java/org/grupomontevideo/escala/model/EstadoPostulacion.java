package org.grupomontevideo.escala.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniel Baharian
 * @since 9 ago 2018
 */
@Entity(name = "ESTADO_POSTULACION")
@Getter
@Setter
public class EstadoPostulacion implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Setter(AccessLevel.NONE)
   private short id;

   @Column(unique = true)
   private String nombre;
}
