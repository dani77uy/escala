package org.grupomontevideo.escala.model;

import java.io.Serializable;

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
public class Pais implements Serializable {

   private Integer identificador;
   private String nombre;
}
