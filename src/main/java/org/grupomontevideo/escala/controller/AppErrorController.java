package org.grupomontevideo.escala.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Daniel Baharian
 * @since 3 nov 2017
 */
@Controller
public class AppErrorController implements ErrorController {

   /**
    * Error Attributes in the Application
    */
   private ErrorAttributes errorAttributes;

   private final static String ERROR_PATH = "/error";

   /**
    * Controller for the Error Controller
    *
    * @param errorAttributes
    */
   public AppErrorController(ErrorAttributes errorAttributes) {
      this.errorAttributes = errorAttributes;
   }

   /**
    * Supports the HTML Error View
    *
    * @param request
    * @return
    */
   @RequestMapping(value = ERROR_PATH, produces = "text/html")
   public ModelAndView errorHtml(HttpServletRequest request) {
      getLogger().warn("Hubo un error al realizar la petición {}", request.getRequestURL().toString());
      return new ModelAndView("/errors/error", getErrorAttributes(request, false));
   }

   /**
    * Supports other formats like JSON, XML
    *
    * @param request
    * @return
    */
   @RequestMapping(value = ERROR_PATH)
   @ResponseBody
   public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
      Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));
      HttpStatus status = getStatus(request);
      return new ResponseEntity<>(body, status);
   }

   /**
    * Returns the path of the error page.
    *
    * @return the error path
    */
   @Override
   public String getErrorPath() {
      return ERROR_PATH;
   }

   private boolean getTraceParameter(HttpServletRequest request) {
      String parameter = request.getParameter("trace");
      if (parameter == null) {
         return false;
      }
      return !"false".equals(parameter.toLowerCase());
   }

   private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
      WebRequest requestAttributes = (WebRequest) new ServletRequestAttributes(request);
      return this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
   }

   private HttpStatus getStatus(HttpServletRequest request) {
      Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
      if (statusCode != null) {
         try {
            return HttpStatus.valueOf(statusCode);
         } catch (Exception ex) {
            getLogger().error("Error al obtener el status.", ex);
            ex.printStackTrace();
         }
      }
      return HttpStatus.INTERNAL_SERVER_ERROR;
   }

   protected Logger getLogger() {
      return LoggerFactory.getLogger(AppErrorController.class);
   }
}
