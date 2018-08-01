package org.grupomontevideo.escala.posgrado.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Daniel B. on 04/08/2016.
 */
@Entity
@Table(name = "wp_prflxtrflds_user_field_data", schema = "ESCALAPOSGRADO")
public class WordpressCoordinadorMetaData implements Serializable {

   @Id
   @Column(name = "id", nullable = false)
   private Long id;

   @Column(name = "field_id", nullable = false)
   private Long fieldId;

   @Column(name = "user_id", nullable = false)
   private Long userId;

   @Column(name = "user_value", nullable = false, length = 255)
   private String userValue;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getFieldId() {
      return fieldId;
   }

   public void setFieldId(Long fieldId) {
      this.fieldId = fieldId;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public String getUserValue() {
      return userValue;
   }

   public void setUserValue(String userValue) {
      this.userValue = userValue;
   }
}
