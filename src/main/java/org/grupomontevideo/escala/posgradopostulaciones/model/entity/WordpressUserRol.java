package org.grupomontevideo.escala.posgradopostulaciones.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Daniel B. on 13/08/2016.
 */
@Entity
@Table(name = "wp_prflxtrflds_user_roles")
public class WordpressUserRol implements Serializable {

   @Id
   @Column(name = "user_id", nullable = false, unique = true)
   private Long userId;

   @Column(name = "role_id", nullable = false)
   private Long roleId;

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public Long getRoleId() {
      return roleId;
   }

   public void setRoleId(Long roleId) {
      this.roleId = roleId;
   }
}
