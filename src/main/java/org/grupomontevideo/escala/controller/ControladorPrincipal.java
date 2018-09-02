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

   private static final String ACTIVO = "ACTIVO";

   private static void setModeloGenericoMenu(Model model) {
      model.asMap().clear();
      model.addAttribute("inicio", "Inicio");
      model.addAttribute("perfil", "Perfil");
      model.addAttribute("admin", "Administrador");
      model.addAttribute("coord", "Coordinador");
      model.addAttribute("postulacion", "Postulación");
      model.addAttribute("logout", "Salir");
   }

   @RequestMapping(value = { "/", "/welcome", "/home", "index" }, method = RequestMethod.GET)
   public String welcomePage(Model model) {
      setModeloGenericoMenu(model);
      model.addAttribute(ACTIVO, "1");
      model.addAttribute("titulo", "Por favor, ingrese");
      model.addAttribute("email", "Correo electrónico");
      model.addAttribute("password", "Contraseña");
      model.addAttribute("recordar", "Recordar");
      model.addAttribute("login", "Ingresar");
      model.addAttribute("olvido", "¿Olvidó su contraseña?");
      return "homePage";
   }

   @RequestMapping(value = "/perfil", method = RequestMethod.GET)
   public String profile(Model model) {
      setModeloGenericoMenu(model);
      model.addAttribute(ACTIVO, "5");
      return "profile";
   }

   @RequestMapping(value = "/administrador", method = RequestMethod.GET)
   public String adminPage(Model model, Principal principal) {
      setModeloGenericoMenu(model);
      model.addAttribute(ACTIVO, "2");
      return "adminPage";
   }

   @RequestMapping(value = "/coordinador", method = RequestMethod.GET)
   public String coordPage(Model model, Principal principal) {
      setModeloGenericoMenu(model);
      model.addAttribute(ACTIVO, "3");
      return "coordPage";
   }

   @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public String logoutSuccessfulPage(Model model) {
      setModeloGenericoMenu(model);
      model.addAttribute(ACTIVO, "0");
      return "logoutPage";
   }

   @RequestMapping(value = "/postulacion", method = RequestMethod.GET)
   public String postulacionPage(Model model, Principal principal) {
      setModeloGenericoMenu(model);
      model.addAttribute(ACTIVO, "4");
      List<Pais> paisList = new ArrayList();
      paisList.add(new Pais(1, "Uruguay"));
      paisList.add(new Pais(2, "Perú"));
      paisList.add(new Pais(3, "Venezuela"));
      model.addAttribute("paises", paisList);
      return "postulacionPage";
   }

}
