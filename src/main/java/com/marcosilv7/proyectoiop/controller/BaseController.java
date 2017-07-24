package com.marcosilv7.proyectoiop.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by marcosilveriocastro on 24/07/17.
 */
public class BaseController {

    public void flashMessage(RedirectAttributes redirectAttrs, String titulo, String mensaje, String level) {
        if(redirectAttrs == null) {
            return;
        }
        redirectAttrs.addFlashAttribute("titulo", titulo);
        redirectAttrs.addFlashAttribute("mensaje", mensaje);
        redirectAttrs.addFlashAttribute("level", level);
        redirectAttrs.addFlashAttribute("flashMessage", "flashMessage");
    }

    public void flashMessageOverlay(RedirectAttributes redirectAttrs, String titulo, String mensaje, String level) {
        if(redirectAttrs == null) {
            return;
        }
        redirectAttrs.addFlashAttribute("titulo", titulo);
        redirectAttrs.addFlashAttribute("mensaje", mensaje);
        redirectAttrs.addFlashAttribute("level", level);
        redirectAttrs.addFlashAttribute("flashMessageOverlay", "flashMessageOverlay");
    }

}
