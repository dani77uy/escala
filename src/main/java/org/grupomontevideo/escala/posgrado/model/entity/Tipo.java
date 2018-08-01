package org.grupomontevideo.escala.posgrado.model.entity;

import java.io.Serializable;

/**
 * @author Daniel Baharian
 * @since 1 ago 2018
 */
public interface Tipo extends Serializable {

   String getOrigenODestino();

   void setOrigenODestino(String origenODestino);

   void setHabilitado(Boolean habilitado);

   Boolean getHabilitado();

   void setDescripcion(String descripcion);

   String getDescripcion();

   void setNombre(String nombre);

   String getNombre();

}
