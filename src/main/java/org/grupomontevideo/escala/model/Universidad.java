package org.grupomontevideo.escala.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniel Baharian
 * @since 9 ago 2018
 */
@Entity(name = "UNIVERSIDAD")
@Getter
@Setter
public class Universidad implements Serializable {

   @Id
   @Column(name = "ID")
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Setter(AccessLevel.NONE)
   private Integer identificador;

   @ManyToOne(targetEntity = Pais.class, fetch = FetchType.EAGER)
   private Pais pais;

   @Column(name = "NOMBRE")
   private String nombre;

}
