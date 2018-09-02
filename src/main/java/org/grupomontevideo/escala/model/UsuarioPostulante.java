package org.grupomontevideo.escala.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniel Baharian
 * @since 25 ago 2018
 */
@Getter
@Setter
@Entity(name = "USUARIO_POSTULANTE")
public class UsuarioPostulante extends UsuarioGenerico {

   @Column(name = "CALLE")
   private String calle;

   @Column(name = "NUMERO_PUERTA")
   private String numeroPuerta;

   @Column(name = "OTROS_DATOS_DIRECCION")
   private String otrosDatosDeDireccion;

   @Column(name = "FECHA_NACIMIENTO")
   private Date fechaNacimiento;

   @Column(name = "NOMBRE_TUTOR")
   private String nombreTutor;

   @OneToMany
   @JoinColumn(name = "ID_POSTULANTE", referencedColumnName = "ID", nullable = false)
         //(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "usuarioPostulante")
   @Setter(AccessLevel.NONE)
   private List<Postulacion> postulaciones;
}
