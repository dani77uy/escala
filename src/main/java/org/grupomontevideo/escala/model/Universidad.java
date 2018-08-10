package org.grupomontevideo.escala.model;

import java.io.Serializable;
import java.util.List;

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
public class Universidad implements Serializable {

   private Integer identificador;
   private Pais pais;
   private String nombre;
   private List<UsuarioCoordinador> coordinadores;
}
