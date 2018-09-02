package org.grupomontevideo.escala.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniel Baharian
 * @since 9 ago 2018
 */
@Entity(name = "ROL")
@Getter
@Setter
public class Rol implements Serializable {

   @Id
   @Column(name = "ID")
   @Setter(AccessLevel.NONE)
   private Short identificador;

   @Column(name = "NOMBRE")
   @Enumerated
   private Tipo nombre;

   public enum Tipo {
      ADMINISTRADOR,
      COORDINADOR,
      POSTULANTE_POSGRADO,
      POSTULANTE_DOCENTE;
   }
}
