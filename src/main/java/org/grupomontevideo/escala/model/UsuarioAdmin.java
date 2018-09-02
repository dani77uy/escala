package org.grupomontevideo.escala.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;

/**
 * @author Daniel Baharian
 * @since 9 ago 2018
 */
@Entity(name = "USUARIO_ADMIN")
@Getter
public class UsuarioAdmin extends UsuarioGenerico {

   @OneToMany
         //(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "adminAprobador")
   @JoinColumn(name = "ID_ADMIN_APROBADOR", referencedColumnName = "ID", nullable = true)
   private List<Postulacion> postulacionesAprobadas;

   @OneToMany
         //(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "adminRechazador")
   @JoinColumn(name = "ID_ADMIN_RECHAZADOR", referencedColumnName = "ID", nullable = true)
   private List<Postulacion> postulacionesRechazadas;
}
