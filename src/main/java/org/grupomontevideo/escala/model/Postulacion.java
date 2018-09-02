package org.grupomontevideo.escala.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daniel Baharian
 * @since 9 ago 2018
 */
@Getter
@Setter
@Entity(name = "POSTULACION")
public class Postulacion implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "ID", updatable = false, nullable = false)
   @Setter(AccessLevel.NONE)
   private Long identificador;

   @Column(name = "ID_POSTULANTE", nullable = false, insertable = false, updatable = false)
   private Long idUsuarioPostulante;

   @Column(name = "ID_COORDINADOR_APROBADOR_ORIGEN", nullable = true)
   private Long idCoordinadorAprobadorOrigen;

   @Column(name = "ID_COORDINADOR_RECHAZADOR_ORIGEN", nullable = true)
   private Long idCoordinadorRechazadaroOrigen;

   @Column(name = "ID_COORDINADOR_APROBADOR_DESTINO", nullable = true)
   private Long idCoordinadorAprobadorDestino;

   @Column(name = "ID_COORDINADOR_RECHAZADOR_DESTINO", nullable = true)
   private Long idCoordinadorRechazadaroDestino;

   @Column(name = "ID_ADMIN_APROBADOR", nullable = true)
   private Long idAdminAprobador;

   @Column(name = "ID_ADMIN_RECHAZADOR", nullable = true)
   private Long idAdminRechazador;

   @ManyToOne
   @JoinColumn(name = "TIPO_ESCALA", nullable = false)
   private Escala escala;

   @ManyToOne(targetEntity = UsuarioPostulante.class)
   private UsuarioPostulante usuarioPostulante;

   @ManyToOne(targetEntity = UsuarioCoordinador.class)
   private UsuarioCoordinador coordinadorAprobadorOrigen;

   @ManyToOne(targetEntity = UsuarioCoordinador.class)
   //@JoinColumn(name = "ID_COORDINADOR_RECHAZADOR_ORIGEN", nullable = true)
   private UsuarioCoordinador coordinadorRechazadorOrigen;

   @ManyToOne(targetEntity = UsuarioCoordinador.class)
   //@JoinColumn(name = "ID_COORDINADOR_APROBADOR_DESTINO", nullable = true)
   private UsuarioCoordinador coordinadorAprobadorDestino;

   @ManyToOne(targetEntity = UsuarioCoordinador.class)
   //@JoinColumn(name = "ID_COORDINADOR_RECHAZADOR_DESTINO", nullable = true)
   private UsuarioCoordinador coordinadorRechazadorDestino;

   @ManyToOne(targetEntity = UsuarioAdmin.class)
   //@JoinColumn(name = "ID_ADMIN_APROBADOR", nullable = true)
   private UsuarioAdmin adminAprobador;

   @ManyToOne(targetEntity = UsuarioAdmin.class)
   //@JoinColumn(name = "ID_ADMIN_RECHAZADOR", nullable = true)
   private UsuarioAdmin adminRechazador;

   @ManyToOne(targetEntity = Universidad.class)
   @JoinColumn(name = "UNIVERSIDAD_ORIGEN", nullable = false)
   private Universidad universidadOrigen;

   @ManyToOne(targetEntity = Universidad.class)
   @JoinColumn(name = "UNIVERSIDAD_DESTINO", nullable = false)
   private Universidad universidadDestino;

   @Column(name = "FECHA_COMIENZO_POSTULACION", nullable = false)
   private Date fechaComienzoPostulacion;

   @Column(name = "DURACION_POSTULACION", nullable = false)
   private Short cantidadDiasDeLaPostulacion;

   @Column(name = "DESCRIPCION", nullable = false)
   private String descripcion;

   @ManyToOne
   @JoinColumn(name = "ESTADO", nullable = false)
   private EstadoPostulacion estadoPostulacion;

   @Column(name = "FECHA_CREACION", nullable = false)
   @Temporal(TemporalType.DATE)
   private Date fechaCreacion;

   @Column(name = "FECHA_MODIFICACION", nullable = true)
   private Date fechaUltimaModificacion;

   @Column(name = "FECHA_APROBADA_POR_ORIGEN", nullable = true)
   private Date fechaAprobadaPorOrigen;

   @Column(name = "FECHA_APROBADA_POR_DESTINO", nullable = true)
   private Date fechaAprobadaPorDestino;

   @Column(name = "FECHA_RECHAZADA_POR_ORIGEN", nullable = true)
   private Date fechaRechazadaPorOrigen;

   @Column(name = "FECHA_RECHAZADA_POR_DESTINO", nullable = true)
   private Date fechaRechazadaPorDestino;

}
