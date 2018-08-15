package org.grupomontevideo.escala.model;

import java.io.Serializable;

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
public class Pais implements Serializable {

   @Id
   private Integer identificador;
   private String nombre;

   public Pais() {
   }

   public Pais(Integer identificador, String nombre) {
      this.identificador = identificador;
      this.nombre = nombre;
   }
}
