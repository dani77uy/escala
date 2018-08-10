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
public class Rol implements Serializable {

   private Short identificador;
   private Tipo nombre;

   public enum Tipo {
      ADMINISTRADOR,
      COORDINADOR,
      POSTULANTE_POSGRADO,
      POSTULANTE_DOCENTE;
   }
}
