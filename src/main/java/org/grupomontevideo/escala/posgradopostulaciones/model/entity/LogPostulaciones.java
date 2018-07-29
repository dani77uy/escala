package org.grupomontevideo.escala.posgradopostulaciones.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.grupomontevideo.escala.posgradopostulaciones.model.entity.pk.LogPostulacionesPK;

/**
 * Created by Daniel B. on 02/08/2016.
 */
@Entity
@Table(name = "ESCALA_LOG_POSTULACIONES", schema = "ESCALAPOSGRADO")
public class LogPostulaciones implements Serializable {

   @EmbeddedId
   private LogPostulacionesPK pk;

   public LogPostulacionesPK getPk() {
      return pk;
   }

   public void setPk(LogPostulacionesPK pk) {
      this.pk = pk;
   }

   @Column(name = "IP_ADDRESS", nullable = true, length = 20)
   private String ip;

   @Column(name = "USER_AGENT", nullable = true, length = 300)
   private String userAgent;

   @Column(name = "DETALLES_DEL_CAMBIO", nullable = true, length = 100)
   private String detalles;

   public String getIp() {
      return ip;
   }

   public void setIp(String ip) {
      this.ip = ip;
   }

   public String getUserAgent() {
      return userAgent;
   }

   public void setUserAgent(String userAgent) {
      this.userAgent = userAgent;
   }

   public String getDetalles() {
      return this.detalles;
   }

   public void setDetalles(String detalles) {
      this.detalles = detalles;
   }

}
