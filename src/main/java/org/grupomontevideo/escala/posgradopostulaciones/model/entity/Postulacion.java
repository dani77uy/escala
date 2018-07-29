package org.grupomontevideo.escala.posgradopostulaciones.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Daniel B. on 18/07/2016.
 */
@Entity
@Table(name = "ESCALA_POSTULACIONES", schema = "ESCALAPOSGRADO")
public class Postulacion implements Serializable {

   private Integer id;

   private String nombres;

   private String calle;

   private String numeroPuerta;

   private String apartamento;

   private String codigoPostal;

   private String telefono;

   private String ciudad;

   private String idPaisDondeVive;

   private String email;

   private Date fechaNacimiento;

   private String sexo;

   private String documentoNac;

   private String idPaisDoc;

   private String pasaporte;

   private String idPaisPasaporte;

   private String denominacionProgOrig;

   private String cursosAprobados;

   private String nombreYCargoTutor;

   private String denominacionProgDest;

   private String carreraDeGrado;

   private String universidadGrado;

   private String idPaisUnivGrado;

   private String observaciones;

   private String cursosActividades;

   private Integer duracion;

   private Date fechaComienzo;

   private Timestamp fechaPostulacion;

   private Timestamp fechaModificacion;

   private WordpressUser aprobadorOrigen;

   private WordpressUser aprobadorDestino;

   private WordpressUser aprobadorSecretaria;

   private Boolean impreso;

   private WordpressUser usuario;

   private Maestria maestriaOrigen;

   private Maestria maestriaDestino;

   private AreaDisciplinar areaDisciplinarOrigen;

   private AreaDisciplinar areaDisciplinarDestino;

   private Universidad universidadOrigen;

   private Universidad universidadDestino;

   private EstadoPostulacion estado;

   private String uniqueId;

   private Date fechaAprobacionOrigen;

   private Date fechaAprobacionDestino;

   private Date fechaRechazoOrigen;

   private Date fechaRechazoDestino;

   private Date fechaAprobacionSecretaria;

   private Date fechaRechazoSecretaria;

   private String motivoRechazo;

   @Id
   @Basic(optional = false)
   @Column(name = "ID", nullable = false)
   @GeneratedValue(strategy = GenerationType.AUTO)
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   @Basic
   @Column(name = "NOMBRES", nullable = false, length = 255)
   public String getNombres() {
      return nombres;
   }

   public void setNombres(String nombres) {
      this.nombres = nombres;
   }

   @Basic
   @Column(name = "CALLE", nullable = false, length = 255)
   public String getCalle() {
      return calle;
   }

   public void setCalle(String calle) {
      this.calle = calle;
   }

   @Basic
   @Column(name = "NUMERO_PUERTA", nullable = false, length = 10)
   public String getNumeroPuerta() {
      return numeroPuerta;
   }

   public void setNumeroPuerta(String numeroPuerta) {
      this.numeroPuerta = numeroPuerta;
   }

   @Basic
   @Column(name = "APARTAMENTO", nullable = true, length = 5)
   public String getApartamento() {
      return apartamento;
   }

   public void setApartamento(String apartamento) {
      this.apartamento = apartamento;
   }

   @Basic
   @Column(name = "CODIGO_POSTAL", nullable = true, length = 10)
   public String getCodigoPostal() {
      return codigoPostal;
   }

   public void setCodigoPostal(String codigoPostal) {
      this.codigoPostal = codigoPostal;
   }

   @Basic
   @Column(name = "TELEFONO", nullable = true, length = 20)
   public String getTelefono() {
      return telefono;
   }

   public void setTelefono(String telefono) {
      this.telefono = telefono;
   }

   @Basic
   @Column(name = "CIUDAD", nullable = false, length = 100)
   public String getCiudad() {
      return ciudad;
   }

   public void setCiudad(String ciudad) {
      this.ciudad = ciudad;
   }

   @Basic
   @Column(name = "ID_PAIS_DONDE_VIVE", nullable = false, length = 2)
   public String getIdPaisDondeVive() {
      return idPaisDondeVive;
   }

   public void setIdPaisDondeVive(String idPaisDondeVive) {
      this.idPaisDondeVive = idPaisDondeVive;
   }

   @Basic
   @Column(name = "EMAIL", nullable = false, length = 100)
   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Basic
   @Column(name = "FECHA_NACIMIENTO", nullable = false)
   public Date getFechaNacimiento() {
      return fechaNacimiento;
   }

   public void setFechaNacimiento(Date fechaNacimiento) {
      this.fechaNacimiento = fechaNacimiento;
   }

   @Basic
   @Column(name = "SEXO", nullable = true, length = 1)
   public String getSexo() {
      return sexo;
   }

   public void setSexo(String sexo) {
      this.sexo = sexo;
   }

   @Basic
   @Column(name = "DOCUMENTO_NAC", nullable = false, length = 15)
   public String getDocumentoNac() {
      return documentoNac;
   }

   public void setDocumentoNac(String documentoNac) {
      this.documentoNac = documentoNac;
   }

   @Basic
   @Column(name = "ID_PAIS_DOC", nullable = false, length = 2)
   public String getIdPaisDoc() {
      return idPaisDoc;
   }

   public void setIdPaisDoc(String idPaisDoc) {
      this.idPaisDoc = idPaisDoc;
   }

   @Basic
   @Column(name = "PASAPORTE", nullable = true, length = 20)
   public String getPasaporte() {
      return pasaporte;
   }

   public void setPasaporte(String pasaporte) {
      this.pasaporte = pasaporte;
   }

   @Basic
   @Column(name = "ID_PAIS_PASAPORTE", nullable = true, length = 2)
   public String getIdPaisPasaporte() {
      return idPaisPasaporte;
   }

   public void setIdPaisPasaporte(String idPaisPasaporte) {
      this.idPaisPasaporte = idPaisPasaporte;
   }

   @Basic
   @Column(name = "DENOMINACION_PROG_ORIG", nullable = false, length = 1500)
   public String getDenominacionProgOrig() {
      return denominacionProgOrig;
   }

   public void setDenominacionProgOrig(String denominacionProgOrig) {
      this.denominacionProgOrig = denominacionProgOrig;
   }

   @Basic
   @Column(name = "CURSOS_APROBADOS", nullable = false, length = 1500)
   public String getCursosAprobados() {
      return cursosAprobados;
   }

   public void setCursosAprobados(String cursosAprobados) {
      this.cursosAprobados = cursosAprobados;
   }

   @Basic
   @Column(name = "NOMBRE_Y_CARGO_TUTOR", nullable = false, length = 300)
   public String getNombreYCargoTutor() {
      return nombreYCargoTutor;
   }

   public void setNombreYCargoTutor(String nombreYCargoTutor) {
      this.nombreYCargoTutor = nombreYCargoTutor;
   }

   @Basic
   @Column(name = "DENOMINACION_PROG_DEST", nullable = false, length = 1500)
   public String getDenominacionProgDest() {
      return denominacionProgDest;
   }

   public void setDenominacionProgDest(String denominacionProgDest) {
      this.denominacionProgDest = denominacionProgDest;
   }

   @Basic
   @Column(name = "CARRERA_DE_GRADO", nullable = false, length = 100)
   public String getCarreraDeGrado() {
      return carreraDeGrado;
   }

   public void setCarreraDeGrado(String carreraDeGrado) {
      this.carreraDeGrado = carreraDeGrado;
   }

   @Basic
   @Column(name = "UNIVERSIDAD_GRADO", nullable = false, length = 100)
   public String getUniversidadGrado() {
      return universidadGrado;
   }

   public void setUniversidadGrado(String universidadGrado) {
      this.universidadGrado = universidadGrado;
   }

   @Basic
   @Column(name = "ID_PAIS_UNIV_GRADO", nullable = false, length = 2)
   public String getIdPaisUnivGrado() {
      return idPaisUnivGrado;
   }

   public void setIdPaisUnivGrado(String idPaisUnivGrado) {
      this.idPaisUnivGrado = idPaisUnivGrado;
   }

   @Basic
   @Column(name = "OBSERVACIONES", nullable = false, length = 1500)
   public String getObservaciones() {
      return observaciones;
   }

   public void setObservaciones(String observaciones) {
      this.observaciones = observaciones;
   }

   @Basic
   @Column(name = "CURSOS_ACTIVIDADES", nullable = false, length = 1500)
   public String getCursosActividades() {
      return cursosActividades;
   }

   public void setCursosActividades(String cursosActividades) {
      this.cursosActividades = cursosActividades;
   }

   @Basic
   @Column(name = "DURACION", nullable = false)
   public Integer getDuracion() {
      return duracion;
   }

   public void setDuracion(Integer duracion) {
      this.duracion = duracion;
   }

   @Basic
   @Column(name = "FECHA_COMIENZO", nullable = false)
   public Date getFechaComienzo() {
      return fechaComienzo;
   }

   public void setFechaComienzo(Date fechaComienzo) {
      this.fechaComienzo = fechaComienzo;
   }

   @Basic
   @Column(name = "FECHA_POSTULACION", nullable = false)
   public Timestamp getFechaPostulacion() {
      return fechaPostulacion;
   }

   public void setFechaPostulacion(Timestamp fechaPostulacion) {
      this.fechaPostulacion = fechaPostulacion;
   }

   @Basic
   @Column(name = "FECHA_MODIFICACION", nullable = false)
   public Timestamp getFechaModificacion() {
      return fechaModificacion;
   }

   public void setFechaModificacion(Timestamp fechaModificacion) {
      this.fechaModificacion = fechaModificacion;
   }

   @Basic
   @Column(name = "IMPRESO", nullable = false)
   public Boolean getImpreso() {
      return impreso;
   }

   public void setImpreso(Boolean impreso) {
      this.impreso = impreso;
   }

   public void setUniqueId(String uniqueId) {
      this.uniqueId = uniqueId;
   }

   @Basic
   @Column(name = "FILE_UUID", nullable = false)
   public String getUniqueId() {
      return this.uniqueId;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      Postulacion that = (Postulacion) o;

      if (id != null ? !id.equals(that.id) : that.id != null) {
         return false;
      }
      if (nombres != null ? !nombres.equals(that.nombres) : that.nombres != null) {
         return false;
      }
      if (calle != null ? !calle.equals(that.calle) : that.calle != null) {
         return false;
      }
      if (numeroPuerta != null ? !numeroPuerta.equals(that.numeroPuerta) : that.numeroPuerta != null) {
         return false;
      }
      if (apartamento != null ? !apartamento.equals(that.apartamento) : that.apartamento != null) {
         return false;
      }
      if (codigoPostal != null ? !codigoPostal.equals(that.codigoPostal) : that.codigoPostal != null) {
         return false;
      }
      if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) {
         return false;
      }
      if (ciudad != null ? !ciudad.equals(that.ciudad) : that.ciudad != null) {
         return false;
      }
      if (idPaisDondeVive != null ? !idPaisDondeVive.equals(that.idPaisDondeVive) : that.idPaisDondeVive != null) {
         return false;
      }
      if (email != null ? !email.equals(that.email) : that.email != null) {
         return false;
      }
      if (fechaNacimiento != null ? !fechaNacimiento.equals(that.fechaNacimiento) : that.fechaNacimiento != null) {
         return false;
      }
      if (sexo != null ? !sexo.equals(that.sexo) : that.sexo != null) {
         return false;
      }
      if (documentoNac != null ? !documentoNac.equals(that.documentoNac) : that.documentoNac != null) {
         return false;
      }
      if (idPaisDoc != null ? !idPaisDoc.equals(that.idPaisDoc) : that.idPaisDoc != null) {
         return false;
      }
      if (pasaporte != null ? !pasaporte.equals(that.pasaporte) : that.pasaporte != null) {
         return false;
      }
      if (idPaisPasaporte != null ? !idPaisPasaporte.equals(that.idPaisPasaporte) : that.idPaisPasaporte != null) {
         return false;
      }
      if (denominacionProgOrig != null ? !denominacionProgOrig.equals(that.denominacionProgOrig) : that.denominacionProgOrig != null) {
         return false;
      }
      if (cursosAprobados != null ? !cursosAprobados.equals(that.cursosAprobados) : that.cursosAprobados != null) {
         return false;
      }
      if (nombreYCargoTutor != null ? !nombreYCargoTutor.equals(that.nombreYCargoTutor) : that.nombreYCargoTutor != null) {
         return false;
      }
      if (denominacionProgDest != null ? !denominacionProgDest.equals(that.denominacionProgDest) : that.denominacionProgDest != null) {
         return false;
      }
      if (carreraDeGrado != null ? !carreraDeGrado.equals(that.carreraDeGrado) : that.carreraDeGrado != null) {
         return false;
      }
      if (universidadGrado != null ? !universidadGrado.equals(that.universidadGrado) : that.universidadGrado != null) {
         return false;
      }
      if (idPaisUnivGrado != null ? !idPaisUnivGrado.equals(that.idPaisUnivGrado) : that.idPaisUnivGrado != null) {
         return false;
      }
      if (observaciones != null ? !observaciones.equals(that.observaciones) : that.observaciones != null) {
         return false;
      }
      if (cursosActividades != null ? !cursosActividades.equals(that.cursosActividades) : that.cursosActividades != null) {
         return false;
      }
      if (duracion != null ? !duracion.equals(that.duracion) : that.duracion != null) {
         return false;
      }
      if (fechaComienzo != null ? !fechaComienzo.equals(that.fechaComienzo) : that.fechaComienzo != null) {
         return false;
      }
      if (fechaPostulacion != null ? !fechaPostulacion.equals(that.fechaPostulacion) : that.fechaPostulacion != null) {
         return false;
      }
      if (fechaModificacion != null ? !fechaModificacion.equals(that.fechaModificacion) : that.fechaModificacion != null) {
         return false;
      }

      if (impreso != null ? !impreso.equals(that.impreso) : that.impreso != null) {
         return false;
      }

      if (uniqueId != null ? !uniqueId.equals(that.uniqueId) : that.uniqueId != null) {
         return false;
      }

      return true;
   }

   @Override
   public int hashCode() {
      int result = id != null ? id.hashCode() : 0;
      result = 31 * result + (nombres != null ? nombres.hashCode() : 0);
      result = 31 * result + (calle != null ? calle.hashCode() : 0);
      result = 31 * result + (numeroPuerta != null ? numeroPuerta.hashCode() : 0);
      result = 31 * result + (apartamento != null ? apartamento.hashCode() : 0);
      result = 31 * result + (codigoPostal != null ? codigoPostal.hashCode() : 0);
      result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
      result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
      result = 31 * result + (idPaisDondeVive != null ? idPaisDondeVive.hashCode() : 0);
      result = 31 * result + (email != null ? email.hashCode() : 0);
      result = 31 * result + (fechaNacimiento != null ? fechaNacimiento.hashCode() : 0);
      result = 31 * result + (sexo != null ? sexo.hashCode() : 0);
      result = 31 * result + (documentoNac != null ? documentoNac.hashCode() : 0);
      result = 31 * result + (idPaisDoc != null ? idPaisDoc.hashCode() : 0);
      result = 31 * result + (pasaporte != null ? pasaporte.hashCode() : 0);
      result = 31 * result + (idPaisPasaporte != null ? idPaisPasaporte.hashCode() : 0);
      result = 31 * result + (denominacionProgOrig != null ? denominacionProgOrig.hashCode() : 0);
      result = 31 * result + (cursosAprobados != null ? cursosAprobados.hashCode() : 0);
      result = 31 * result + (nombreYCargoTutor != null ? nombreYCargoTutor.hashCode() : 0);
      result = 31 * result + (denominacionProgDest != null ? denominacionProgDest.hashCode() : 0);
      result = 31 * result + (carreraDeGrado != null ? carreraDeGrado.hashCode() : 0);
      result = 31 * result + (universidadGrado != null ? universidadGrado.hashCode() : 0);
      result = 31 * result + (idPaisUnivGrado != null ? idPaisUnivGrado.hashCode() : 0);
      result = 31 * result + (observaciones != null ? observaciones.hashCode() : 0);
      result = 31 * result + (cursosActividades != null ? cursosActividades.hashCode() : 0);
      result = 31 * result + (duracion != null ? duracion.hashCode() : 0);
      result = 31 * result + (fechaComienzo != null ? fechaComienzo.hashCode() : 0);
      result = 31 * result + (fechaPostulacion != null ? fechaPostulacion.hashCode() : 0);
      result = 31 * result + (fechaModificacion != null ? fechaModificacion.hashCode() : 0);
      result = 31 * result + (impreso != null ? impreso.hashCode() : 0);
      result = 31 * result + (uniqueId != null ? uniqueId.hashCode() : 0);
      return result;
   }

   @ManyToOne
   @JoinColumn(name = "USER_ID")
   public WordpressUser getUsuario() {
      return usuario;
   }

   public void setUsuario(WordpressUser usuario) {
      this.usuario = usuario;
   }

   @ManyToOne
   @JoinColumn(name = "ID_APROBADOR_ORIGEN")
   public WordpressUser getAprobadorOrigen() {
      return aprobadorOrigen;
   }

   public void setAprobadorOrigen(WordpressUser aprobadorOrigen) {
      this.aprobadorOrigen = aprobadorOrigen;
   }

   @ManyToOne
   @JoinColumn(name = "ID_APROBADOR_DESTINO")
   public WordpressUser getAprobadorDestino() {
      return aprobadorDestino;
   }

   public void setAprobadorDestino(WordpressUser aprobadorDestino) {
      this.aprobadorDestino = aprobadorDestino;
   }

   @ManyToOne
   @JoinColumn(name = "ID_APROBADOR_SECRETARIA")
   public WordpressUser getAprobadorSecretaria() {
      return aprobadorSecretaria;
   }

   public void setAprobadorSecretaria(WordpressUser aprobadorSecretaria) {
      this.aprobadorSecretaria = aprobadorSecretaria;
   }

   @ManyToOne
   @JoinColumn(name = "ID_MAESTRIA_ORIG")
   public Maestria getMaestriaOrigen() {
      return maestriaOrigen;
   }

   public void setMaestriaOrigen(Maestria maestriaOrigen) {
      this.maestriaOrigen = maestriaOrigen;
   }

   @ManyToOne
   @JoinColumn(name = "ID_MAESTRIA_DEST")
   public Maestria getMaestriaDestino() {
      return maestriaDestino;
   }

   public void setMaestriaDestino(Maestria maestriaDestino) {
      this.maestriaDestino = maestriaDestino;
   }

   @ManyToOne
   @JoinColumn(name = "ID_AREA_DISCIP_ORIG")
   public AreaDisciplinar getAreaDisciplinarOrigen() {
      return areaDisciplinarOrigen;
   }

   public void setAreaDisciplinarOrigen(AreaDisciplinar areaDisciplinarOrigen) {
      this.areaDisciplinarOrigen = areaDisciplinarOrigen;
   }

   @ManyToOne
   @JoinColumn(name = "ID_AREA_DISCIP_DEST")
   public AreaDisciplinar getAreaDisciplinarDestino() {
      return areaDisciplinarDestino;
   }

   public void setAreaDisciplinarDestino(AreaDisciplinar areaDisciplinarDestino) {
      this.areaDisciplinarDestino = areaDisciplinarDestino;
   }

   @ManyToOne
   @JoinColumn(name = "ID_UNIVERSIDAD_ORIGEN")
   public Universidad getUniversidadOrigen() {
      return universidadOrigen;
   }

   public void setUniversidadOrigen(Universidad universidadOrigen) {
      this.universidadOrigen = universidadOrigen;
   }

   @ManyToOne
   @JoinColumn(name = "ID_UNIVERSIDAD_DESTINO")
   public Universidad getUniversidadDestino() {
      return universidadDestino;
   }

   public void setUniversidadDestino(Universidad universidadDestino) {
      this.universidadDestino = universidadDestino;
   }

   @ManyToOne
   @JoinColumn(name = "POSTULACION_ESTADO")
   public EstadoPostulacion getEstado() {
      return estado;
   }

   public void setEstado(EstadoPostulacion estado) {
      this.estado = estado;
   }

   @Column(name = "FECHA_APROBACION_ORIGEN", nullable = true)
   public Date getFechaAprobacionOrigen() {
      return fechaAprobacionOrigen;
   }

   public void setFechaAprobacionOrigen(Date fechaAprobacionOrigen) {
      this.fechaAprobacionOrigen = fechaAprobacionOrigen;
   }

   @Column(name = "FECHA_APROBACION_DESTINO", nullable = true)
   public Date getFechaAprobacionDestino() {
      return fechaAprobacionDestino;
   }

   public void setFechaAprobacionDestino(Date fechaAprobacionDestino) {
      this.fechaAprobacionDestino = fechaAprobacionDestino;
   }

   @Column(name = "FECHA_RECHAZADA_ORIGEN", nullable = true)
   public Date getFechaRechazoOrigen() {
      return fechaRechazoOrigen;
   }

   public void setFechaRechazoOrigen(Date fechaRechazoOrigen) {
      this.fechaRechazoOrigen = fechaRechazoOrigen;
   }

   @Column(name = "FECHA_RECHAZADA_DESTINO", nullable = true)
   public Date getFechaRechazoDestino() {
      return fechaRechazoDestino;
   }

   public void setFechaRechazoDestino(Date fechaRechazoDestino) {
      this.fechaRechazoDestino = fechaRechazoDestino;
   }

   @Column(name = "FECHA_APROBACION_SECRETARIA", nullable = true)
   public Date getFechaAprobacionSecretaria() {
      return fechaAprobacionSecretaria;
   }

   public void setFechaAprobacionSecretaria(Date fechaAprobacionSecretaria) {
      this.fechaAprobacionSecretaria = fechaAprobacionSecretaria;
   }

   @Column(name = "FECHA_RECHAZADA_SECRETARIA", nullable = true)
   public Date getFechaRechazoSecretaria() {
      return fechaRechazoSecretaria;
   }

   public void setFechaRechazoSecretaria(Date fechaRechazoSecretaria) {
      this.fechaRechazoSecretaria = fechaRechazoSecretaria;
   }

   @Column(name = "MOTIVO_RECHAZO", nullable = true, length = 500)
   public String getMotivoRechazo() {
      return motivoRechazo;
   }

   public void setMotivoRechazo(String motivoRechazo) {
      this.motivoRechazo = motivoRechazo;
   }
}
