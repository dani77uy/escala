package org.grupomontevideo.escala.posgrado.model.enums;

/**
 * Created by Daniel B. on 04/08/2016.
 */
public enum EnumEstadoPostulacion {

   PENDIENTE(1, "Pendiente"),
   IMPRESO(2, "Impresa"),
   FIRMADO_POR_TUTOR(3, "Firmada por tutor"),
   APROBADO_POR_COORDINADOR_ORIGEN(4, "Aprobada por administradores institucional de origen"),
   APROBADO_POR_COORDINADOR_DESTINO(5, "Aprobada por administradores institucional de destino"),
   APROBADO_POR_SECRETARIA(6, "Aprobada por secretaria ejecutiva"),
   RECHAZADA_ORIGEN(51, "Rechazada por universidad de origen"),
   RECHAZADA_DESTINO(52, "Rechazada por universidad de destino"),
   RECHAZADA_SECRETARIA(53, "Rechazada por secretaría ejecutiva");

   private final int id;

   private final String nombre;

   EnumEstadoPostulacion(int id, String nombre) {
      this.id = id;
      this.nombre = nombre;
   }

   public int getId() {
      return id;
   }

   public String getNombre() {
      return nombre;
   }
}
