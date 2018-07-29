package org.grupomontevideo.escala.posgradopostulaciones.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Daniel B. on 18/07/2016.
 */
@Entity
@Table(name = "wp_users", schema = "ESCALAPOSGRADO")
public class WordpressUser implements Serializable {

   private Long id;

   private String userLogin;

   private String userPass;

   private String userNicename;

   private String userEmail;

   private String userUrl;

   private Date userRegistered;

   private String userActivationKey;

   private Integer userStatus;

   private String displayName;

   @Id
   @Column(name = "ID", nullable = false)
   public Long getId() {
      return id;
   }

   protected void setId(Long id) {
      this.id = id;
   }

   @Basic
   @Column(name = "user_login", nullable = false, length = 60)
   public String getUserLogin() {
      return userLogin;
   }

   protected void setUserLogin(String userLogin) {
      this.userLogin = userLogin;
   }

   @Basic
   @JsonIgnore
   @Column(name = "user_pass", nullable = false, length = 255, updatable = false)
   public String getUserPass() {
      return userPass;
   }

   protected void setUserPass(String userPass) {
      this.userPass = userPass;
   }

   @Basic
   @Column(name = "user_nicename", nullable = false, length = 50)
   public String getUserNicename() {
      return userNicename;
   }

   protected void setUserNicename(String userNicename) {
      this.userNicename = userNicename;
   }

   @Basic
   @Column(name = "user_email", nullable = false, length = 100)
   public String getUserEmail() {
      return userEmail;
   }

   protected void setUserEmail(String userEmail) {
      this.userEmail = userEmail;
   }

   @Basic
   @Column(name = "user_url", nullable = false, length = 100)
   public String getUserUrl() {
      return userUrl;
   }

   protected void setUserUrl(String userUrl) {
      this.userUrl = userUrl;
   }

   @Basic
   @Column(name = "user_registered", nullable = false)
   public Date getUserRegistered() {
      return userRegistered;
   }

   protected void setUserRegistered(Date userRegistered) {
      this.userRegistered = userRegistered;
   }

   @Basic
   @Column(name = "user_activation_key", nullable = false, length = 255)
   public String getUserActivationKey() {
      return userActivationKey;
   }

   protected void setUserActivationKey(String userActivationKey) {
      this.userActivationKey = userActivationKey;
   }

   @Basic
   @Column(name = "user_status", nullable = false)
   public Integer getUserStatus() {
      return userStatus;
   }

   protected void setUserStatus(Integer userStatus) {
      this.userStatus = userStatus;
   }

   @Basic
   @Column(name = "display_name", nullable = false, length = 250)
   public String getDisplayName() {
      return displayName;
   }

   protected void setDisplayName(String displayName) {
      this.displayName = displayName;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }

     if (o instanceof WordpressUser) {
        WordpressUser that = (WordpressUser) o;
        return this.getId() == this.getId();
     }
     return false;
   }

   @Override
   public int hashCode() {
      int result = id != null ? id.hashCode() : 0;
      result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
      result = 31 * result + (userPass != null ? userPass.hashCode() : 0);
      result = 31 * result + (userNicename != null ? userNicename.hashCode() : 0);
      result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
      result = 31 * result + (userUrl != null ? userUrl.hashCode() : 0);
      result = 31 * result + (userRegistered != null ? userRegistered.hashCode() : 0);
      result = 31 * result + (userActivationKey != null ? userActivationKey.hashCode() : 0);
      result = 31 * result + (userStatus != null ? userStatus.hashCode() : 0);
      result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
      return result;
   }
}
