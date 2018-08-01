package org.grupomontevideo.escala.posgrado.service;

import java.sql.Timestamp;
import java.util.List;

import org.grupomontevideo.escala.posgrado.model.entity.EstadoPostulacion;
import org.grupomontevideo.escala.posgrado.model.entity.Postulacion;
import org.grupomontevideo.escala.posgrado.model.entity.Universidad;
import org.grupomontevideo.escala.posgrado.model.entity.WordpressUser;
import org.grupomontevideo.escala.posgrado.model.enums.EnumEstadoPostulacion;
import org.springframework.stereotype.Service;

/**
 * @author Daniel Baharian
 * @since 31 jul 2018
 */
@Service
public interface PostulacionService {

   Postulacion agregar(Postulacion postulacion);

   Postulacion editar(Postulacion postulacion);

   Postulacion obtener(Long id);

   List<Postulacion> obtenerPorUsuario(WordpressUser user);

   List<Postulacion> obtenerPorUniversidad(Universidad universidad, Boolean esOrigen, EnumEstadoPostulacion... estados);

   List<Postulacion> obtenerPorFechas(Timestamp fechaInicial, Timestamp fechaFinal);

   List<Postulacion> obtenerPorCoordinador(WordpressUser coordinador, EnumEstadoPostulacion estado);

   List<Postulacion> obtenerPorEstado(EstadoPostulacion estado);

   List<Postulacion> obtenerTodas();

   List<Postulacion> obtenerPostulacionesParaPagAdmin(Universidad origen, Universidad destino, Timestamp fechaInicial, Timestamp fechaFinal,
         EstadoPostulacion estado);

   List<Postulacion> obtenerPostulacionesParaSecretaria(Universidad universidad, java.sql.Date fechaInicialPost, java.sql.Date fechaFinalPost,
         String paisOrigen, String paisDestino, int filtro, EnumEstadoPostulacion estado);

   List<Postulacion> obtenerPostulacionesPorUsuario(String documento, String nombre, String apellido, EnumEstadoPostulacion estado, int filtro);




}
