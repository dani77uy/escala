package org.grupomontevideo.escala.posgradopostulaciones.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Daniel B. on 04/08/2016.
 */
@Entity
@Table(name = "wp_prflxtrflds_roles_id", schema = "ESCALAPOSGRADO")
public class WorpressRol implements Serializable {

   @Id
   @Column(name = "role_id", nullable = false)
   private Long id;

   @Column(name = "role", nullable = false, length = 255)
   private String role;

   @Column(name = "role_name", nullable = false, length = 255)
   private String name;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getRole() {
      return role;
   }

   public void setRole(String role) {
      this.role = role;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
