package org.grupomontevideo.escala.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.grupomontevideo.escala.model.Pais;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Daniel Baharian
 * @since 15 ago 2018
 */
@Controller
public class ControladorPrincipal {

   @RequestMapping(value = { "/", "/welcome", "/home", "index" }, method = RequestMethod.GET)
   public String welcomePage(Model model) {
      return "homePage";
   }

   @RequestMapping(value = "/administrador", method = RequestMethod.GET)
   public String adminPage(Model model, Principal principal) {
      return "adminPage";
   }

   @RequestMapping(value = "/coordinador", method = RequestMethod.GET)
   public String coordPage(Model model, Principal principal) {
      return "coordPage";
   }


   @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public String logoutSuccessfulPage(Model model) {
      return "logoutPage";
   }

   @RequestMapping(value = "/postulacion", method = RequestMethod.GET)
   public String  postulacionPage(Model model, Principal principal) {
      List<Pais> paisList = new ArrayList();
      paisList.add(new Pais(1, "Uruguay"));
      paisList.add(new Pais(2, "Perú"));
      paisList.add(new Pais(3, "Venezuela"));
      model.addAttribute("paises", paisList);
      return "postulacionPage";
   }


}
