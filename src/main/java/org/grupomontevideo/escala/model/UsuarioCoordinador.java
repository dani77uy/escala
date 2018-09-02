package org.grupomontevideo.escala.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniel Baharian
 * @since 9 ago 2018
 */
@Entity(name = "USUARIO_COORDINADOR")
@Getter
@Setter
public class UsuarioCoordinador extends UsuarioGenerico {

   @ManyToOne(targetEntity = Universidad.class)
   private Universidad universidad;

   @OneToMany
         //(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "coordinadorAprobadorOrigen")
   @JoinColumn(name = "ID_COORDINADOR_APROBADOR_ORIGEN", referencedColumnName = "ID", nullable = true)
   @Setter(AccessLevel.NONE)
   private List<Postulacion> postulacionesAprobadasComoOrigen;

   @OneToMany
         //(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "coordinadorRechazadorOrigen")
   @JoinColumn(name = "ID_COORDINADOR_RECHAZADOR_ORIGEN", referencedColumnName = "ID", nullable = true)
   @Setter(AccessLevel.NONE)
   private List<Postulacion> postulacionesRechazadasComoOrigen;

   @OneToMany
         //(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "coordinadorAprobadorDestino")
   @JoinColumn(name = "ID_COORDINADOR_APROBADOR_DESTINO", referencedColumnName = "ID", nullable = true)
   @Setter(AccessLevel.NONE)
   private List<Postulacion> postulacionesAprobadasComoDestino;

   @OneToMany
        //(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "coordinadorRechazadorDestino")
   @JoinColumn(name = "ID_COORDINADOR_RECHAZADOR_DESTINO", referencedColumnName = "ID", nullable = true)
   @Setter(AccessLevel.NONE)
   private List<Postulacion> postulacionesRechazadasComoDestino;
}
