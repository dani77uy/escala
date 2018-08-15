package org.grupomontevideo.escala.model;

import javax.persistence.Entity;
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
public class UsuarioCoordinador extends UsuarioGenerico {

   @ManyToOne(targetEntity = Universidad.class)
   private Universidad universidad;
}
