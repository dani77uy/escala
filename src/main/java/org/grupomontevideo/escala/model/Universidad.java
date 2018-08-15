package org.grupomontevideo.escala.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Universidad implements Serializable {

   @Id
   private Integer identificador;
   @ManyToOne(targetEntity = Pais.class, fetch = FetchType.EAGER)
   private Pais pais;
   private String nombre;

}
