package org.grupomontevideo.escala.posgrado.controller.pdf;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.grupomontevideo.escala.posgrado.controller.AbstractController;
import org.grupomontevideo.escala.posgrado.model.entity.Postulacion;
import org.grupomontevideo.escala.posgrado.model.enums.EnumEstadoPostulacion;
import org.grupomontevideo.escala.posgrado.persistence.EstadoPostulacionDao;
import org.grupomontevideo.escala.posgrado.persistence.PostulacionDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

/**
 * Created by Daniel B on 21/07/2016.
 */
@Controller
public class PdfController extends AbstractController {

   @RequestMapping(value = "/crear_archivo_impresion", method = RequestMethod.POST)
   public
   @ResponseBody
   Map<String, Object> createPdf(@RequestParam(value = "id", required = true) String postId, @RequestParam(value = "paises") String paises, HttpServletRequest request) {
      final Map<String, Object> result = new HashMap<>();
      final String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
      try {
         if (StringUtils.isNumeric(postId)) {
            final PostulacionDao pDao = new PostulacionDao(this.getSession());
            final Postulacion postulacion = pDao.get(Integer.valueOf(postId));
            final String content = getContent(
                  postulacion.getId(),
                  postulacion.getNombres(), postulacion.getEmail(),
                  postulacion.getCalle(),
                  postulacion.getNumeroPuerta(),
                  postulacion.getApartamento(),
                  postulacion.getTelefono(),
                  postulacion.getCodigoPostal(),
                  postulacion.getCiudad(),
                  getPais(paises, postulacion.getIdPaisDondeVive()),
                  postulacion.getDocumentoNac(),
                  getPais(paises, postulacion.getIdPaisDoc()),
                  postulacion.getPasaporte(),
                  getPais(paises, postulacion.getIdPaisPasaporte()),
                  dateToString(postulacion.getFechaNacimiento()),
                  postulacion.getUniversidadOrigen().getNombre(),
                  postulacion.getDenominacionProgOrig(),
                  postulacion.getAreaDisciplinarOrigen().getNombre(),
                  postulacion.getCursosAprobados(),
                  postulacion.getNombreYCargoTutor(),
                  postulacion.getUniversidadDestino().getNombre(),
                  postulacion.getDenominacionProgDest(),
                  postulacion.getAreaDisciplinarDestino().getNombre(),
                  postulacion.getCarreraDeGrado(),
                  postulacion.getUniversidadGrado(),
                  getPais(paises, postulacion.getIdPaisUnivGrado()),
                  postulacion.getObservaciones(),
                  postulacion.getCursosActividades(),
                  postulacion.getDuracion(),
                  dateToString(postulacion.getFechaComienzo()));

            final String folder = getProperty("path.print");
            final String userFolderPath = String.valueOf(postulacion.getUsuario().getId());
            final String fileName = postulacion.getId() + ".pdf";

            File mainFolder = new File(folder);

            if (!mainFolder.exists()) {
               mainFolder.mkdir();
            }

            File subFolder = new File(folder + File.separatorChar + userFolderPath);

            if (!subFolder.exists()) {
               subFolder.mkdir();
            }

            File f = new File(folder + File.separatorChar + userFolderPath + File.separatorChar + fileName);

            if (f.exists()) {
               result.put("cod", "1");
               result.put("msg", "Ya existe un archivo");
            } else {
               f.createNewFile();

               final String CSS1 = ".uppercase { text-transform: uppercase; }";
               final String CSS2 = ".container { width: 800px; margin: 0 auto; }";

               // step 1
               Document document = new Document();
               // step 2
               PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(f));
               // step 3
               document.open();
               // step 4

               CSSResolver cssResolver = new StyleAttrCSSResolver();
               CssFile cssFile1 = XMLWorkerHelper.getCSS(new ByteArrayInputStream(CSS1.getBytes()));
               cssResolver.addCss(cssFile1);

               // HTML
               HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
               htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

               // Pipelines
               PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
               HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
               CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

               // XML Worker
               XMLWorker worker = new XMLWorker(css, true);
               XMLParser p = new XMLParser(worker);
               p.parse(new ByteArrayInputStream(content.getBytes()));

               // step 5
               document.close();

               postulacion.setImpreso(Boolean.TRUE);
               postulacion.setEstado(new EstadoPostulacionDao(getSession()).get(EnumEstadoPostulacion.IMPRESO.getId()));
               pDao.edit(postulacion);

               result.put("msg", "Archivo creado correctamente en la ruta: " + f.getName());
               result.put("cod", "0");

               agregarAlLog(postulacion, request.getRemoteAddr() ,request.getHeader("user-agent"), methodName);
            }

         }
      } catch (Exception ex) {
         ex.printStackTrace();
         result.put("msg", ex.getMessage());
         result.put("cod", "-1");
      }
      return result;
   }

   private static String getPais(String paises, String id) throws IOException {
      final ObjectMapper mapper = new ObjectMapper();
      final Map<String, String> lista = mapper.readValue(paises, Map.class);
      return lista.get(id);
   }

   private static String dateToString(Date date) {
      final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      final String text = df.format(date);
      return text;
   }

   private String getContent(Integer id, String nombre, String emailPostulante, String calle, String numero, String apto, String telefono, String zip, String ciudad,
         String pais, String documento, String paisDoc, String pasaporte, String paisPasaporte, String fechaNac, String univOrig, String progOrigen,
         String areaDiscOrig, String cursosAprobados, String tutor, String univDest, String progDest, String areaDiscDest, String carreraDeGrado,
         String deLaUniv, String paisCarreraGrado, String observaciones, String cursosActividades, Integer duracion, String fechaComienzo) throws IOException {
      final String path_logo = getProperty("path.logochico");
      StringBuilder sb = new StringBuilder();
      sb.append("<div class=\"container\">");
      sb.append("<form>");
      sb.append("<p><img src=\"" + path_logo + "\"/></p>");
      sb.append("<br/>");
      sb.append("<b><h2>POSTULACIÓN NÚMERO: <span>" + id + "</span></h2></b><br/>");
      sb.append("<b><h1>DATOS DEL ESTUDIANTE</h1></b>");
      sb.append("<p><b><h2>NOMBRES:</h2></b><br/><span>" + nombre.toUpperCase() + "</span></p>");
      sb.append("<p><b><h2>CORREO ELECTRÓNICO:</h2></b><br/><span>" + emailPostulante.toLowerCase() + "</span></p>");
      sb.append("<br/>");
      sb.append("<p><b><h2>DIRECCIÓN:</h2></b><br/>");
      sb.append("<b><span title=\"Calle\">Calle:</span></b><span>&nbsp;</span><span>" + calle.toUpperCase() + " " + numero.toUpperCase() + "<span>&nbsp;</span></span>");
      if (StringUtils.isNotBlank(apto)) sb.append("<b><span title=\"Apartamento\">Ap:</span></b><span>&nbsp;</span><span>" + apto + ",</span><span>&nbsp;</span>");
      if (StringUtils.isNotBlank(telefono)) sb.append("<b><span title=\"Teléfono\">T:</span></b><span>&nbsp;</span><span>" + telefono + ",</span><span>&nbsp;</span>");
      if (StringUtils.isNotBlank(zip)) sb.append("<b><span title=\"Código Postal\">CP:</span></b><span>&nbsp;</span><span>" + zip + "</span>");
      sb.append("</p>");
      sb.append("<br/>");
      sb.append("<p><b><h2>UBICACIÓN:</h2></b><br/><span>" + ciudad.toUpperCase() + ", " + pais + "</span></p>");
      sb.append("<br/>");
      sb.append("<p><b><h2>DOCUMENTOS:</h2></b><br/>");
      sb.append("<b><span title=\"Cédula\">C.I.:</span></b><span>&nbsp;</span><span>" + documento + "</span><span>&nbsp;</span>");
      sb.append("<b><span title=\"País de la Cédula\">País.:</span></b><span><span>&nbsp;</span>" + paisDoc.toUpperCase() + "<span>&nbsp;</span></span>");
      if (StringUtils.isNotBlank(pasaporte)) {
         sb.append("<b><span title=\"Pasaporte\">Pas.:</span></b><span>&nbsp;</span><span>" + pasaporte.toUpperCase() + "<span>&nbsp;</span></span>");
         sb.append("<b><span title=\"País del Pasaporte\">País.:</span></b><span><span>&nbsp;</span>" + paisPasaporte.toUpperCase() + "<span>&nbsp;</span></span>");
      }
      sb.append("</p>");
      sb.append("<br/>");
      sb.append("<p><b><h2>FECHA NACIMIENTO:</h2></b><br/>");
      sb.append("<b><span title=\"Fecha de nacimiento\">F.N.:</span></b><span>&nbsp;</span><span>" + fechaNac + "</span><span>&nbsp;</span>");
      sb.append("<b><span title=\"País de nacimiento\">País:</span></b><span>&nbsp;</span><span>" + paisDoc.toUpperCase() + "</span>");
      sb.append("</p>");
      sb.append("<br/>");
      sb.append("<p><b><h2>UNIVERSIDAD DE ORIGEN</h2></b><br/></p>");
      sb.append("<b>" + univOrig + "</b><br/>");
      sb.append("<p></p>");
      sb.append("<b><h1>" +"Programa de maestría o doctorado en el que está matriculado".toUpperCase() +"</h1></b>");
      sb.append("<br/>");
      sb.append("<b><h2>PROGRAMA</h2></b><br/><span>Denominación del programa:</span><span>&nbsp;</span>");
      sb.append("<b>" + progOrigen + "</b>");
      sb.append("<p><b><h2>ÁREA DISCIPLINAR</h2></b><br/></p>");
      sb.append("<b>" + areaDiscOrig + "</b>");
      sb.append("<p><b><h2>CURSOS APROBADOS</h2></b><br/></p>");
      sb.append("<span>" + cursosAprobados + "</span><br/>");
      sb.append("<b><h2>NOMBRE Y CARGO DEL TUTOR</h2></b><br/>");
      sb.append("<span>" + tutor + "</span>");
      sb.append("<br/>");
      sb.append("<b><h1>"+"Universidad de destino, actividad, programa de maestría o doctorado en el que desea participar".toUpperCase()+"</h1></b>");
      sb.append("<p><b><h2>UNIVERSIDAD DE DESTINO</h2></b><br/></p>");
      sb.append("<b>" + univDest + "</b><br/>");
      sb.append("<p><b><h2>PROGRAMA</h2></b><br/><span>Denominación del programa:</span><span>&nbsp;</span>");
      sb.append("<b>" + progDest + "</b></p>");
      sb.append("<p><b><h2>ÁREA DISCIPLINAR</h2></b><br/>");
      sb.append("<b>" + areaDiscDest + "</b></p>");
      sb.append("<br/>");
      sb.append("<b><h1>"+"Formación del solicitante".toUpperCase()+"</h1></b>");
      sb.append("<p><b><h2>CARRERA DE GRADO</h2></b><br/></p>");
      sb.append("<b>" + carreraDeGrado + "</b><span>,</span>");
      sb.append("<b>" + deLaUniv + "</b><span>,</span>");
      sb.append("<b>" + paisCarreraGrado + "</b>");
      sb.append("<p><b><h2>OBSERVACIONES</h2></b><br/></p>");
      sb.append("<span>" + observaciones + "</span>");
      sb.append("<br/>");
      sb.append(
            "<p><b><h1>"+"Cursos/Actividades propuestos y agenda de trabajo en la Universidad de destino (incluir cursos, seminarios,"
                  + " estancia de investigación, prácticas de laboratorio, etc.)".toUpperCase()+"</h1></b>");
      sb.append("<br/>");
      sb.append("<b><h2>CURSOS/ACTIVIDADES</h2></b><br/>");
      sb.append("<span>" + cursosActividades + "</span>");
      sb.append("</p>");
      sb.append(
            "<p><b><h1>"+"Duración y fecha estimada de inicio de la actividad a realizar en la Universidad de destino".toUpperCase()+"</h1></b><br/>");
      sb.append("<b><span title=\"Duración\">Duración:</span></b><span>&nbsp;</span><span class=\"uppercase\">" + duracion + ", </span>");
      sb.append("<b><span title=\"Fecha de cominezo\">F.Comienzo:</span></b><span>&nbsp;</span><span>" + fechaComienzo + "</span><span>&nbsp;</span>");
      sb.append("</p>");
      sb.append("<br/>");
      sb.append("</form>");
      sb.append("<br/><br/>");
      sb.append("<b><h2>FIRMA DEL TUTOR:</h2></b><br/><br/><br/><em>_________________________________________________________</em>");
      sb.append("<br/><br/>");
      sb.append("<p><b>Contacto:</b><span>&nbsp;</span><span>Guayabos 1729 ap. 502, Montevideo 11.200 Uruguay.</span></p>");
      sb.append("<p><b>Teléfono:</b><span>&nbsp;</span><span>(598) 2400 5411 - 2400 5401 Fax: (598) 2400 5411 - 2400 5401</span></p>");
      sb.append("<b>Email:</b><span>&nbsp;</span><span>secretariaejecutiva@grupomontevideo.org</span>");
      sb.append("</div>");

      return sb.toString();
   }

}
