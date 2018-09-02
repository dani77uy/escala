package org.grupomontevideo.escala.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniel Baharian
 * @since 25 ago 2018
 */
@Entity(name = "TIPO_ESCALA")
@Getter
@Setter
public class Escala implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Setter(AccessLevel.NONE)
   private Integer id;

   @Column(name = "FECHA_DESDE")
   private Date fechaDesde;

   @Column(name = "FECHA_HASTA")
   private Date fechaHasta;

   @Enumerated
   @Column(name = "TIPO_ESCALA")
   private TipoEscala nombre;

   public enum TipoEscala {
      DOCENTE,
      POSGRADO;
   }
}
