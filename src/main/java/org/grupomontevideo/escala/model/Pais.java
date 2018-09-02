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
@Entity(name = "PAIS")
@Getter
@Setter
public class Pais implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Setter(AccessLevel.NONE)
   private Integer identificador;

   @Column(name = "NOMBRE_ESPANOL", nullable = false)
   private String nombreEspanol;

   @Column(name = "NOMBRE_PORTUGUES", nullable = true)
   private String nombrePortugues;

   @Column(name = "NOMBRE_INGLES", nullable = true)
   private String nombreIngles;

   @Column(name = "CODIGO", unique = true, nullable = false)
   private String codigoAbreviacion;

   public Pais() {
   }

   public Pais(Integer identificador, String nombreEspanol) {
      this.identificador = identificador;
      this.nombreEspanol = nombreEspanol;
   }

   public Pais(Integer identificador, String nombreEspanol, String nombrePortugues, String nombreIngles) {
      this.identificador = identificador;
      this.nombreEspanol = nombreEspanol;
      this.nombrePortugues = nombrePortugues;
      this.nombreIngles = nombreIngles;
   }

   public Pais(Integer identificador, String nombreEspanol, String nombrePortugues, String nombreIngles, String codigoAbreviacion) {
      this.identificador = identificador;
      this.nombreEspanol = nombreEspanol;
      this.nombrePortugues = nombrePortugues;
      this.nombreIngles = nombreIngles;
      this.codigoAbreviacion = codigoAbreviacion;
   }

}
