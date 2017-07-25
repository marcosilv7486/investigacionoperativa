package com.marcosilv7.proyectoiop.controller;

import com.marcosilv7.proyectoiop.dao.domain.Periodo;
import com.marcosilv7.proyectoiop.dao.domain.Producto;
import com.marcosilv7.proyectoiop.service.interfaces.OptimizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.Period;

/**
 * Created by marcosilveriocastro on 24/07/17.
 */
@Controller
@RequestMapping("/app/periodos")
public class PeriodoController extends BaseController {

    private OptimizacionService optimizacionService;

    @Autowired
    public PeriodoController(OptimizacionService optimizacionService) {
        this.optimizacionService = optimizacionService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("periodos", optimizacionService.obtenerPeriodos());
        return "app/periodos/index";
    }

    @GetMapping("/crear")
    public String create(Model model) {
        model.addAttribute("periodo",  new Periodo() );
        return "app/periodos/mantener";
    }

    @GetMapping("/editar/{id}")
    public String editar(Model model,@PathVariable(name = "id")Integer id) {
        model.addAttribute("periodo",  optimizacionService.obtenerPeriodo(id));
        return "app/periodos/mantener";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute @Valid Periodo periodo , BindingResult result ,
                          RedirectAttributes redirectAttributes , Model model) {
        if(result.hasErrors()) {
            return "app/periodos/mantener";
        }
        if (periodo.getId()== null) {
            optimizacionService.registrarPeriodo(periodo);
        }
        else {
            optimizacionService.modificarPeriodo(periodo);
        }
        return  "redirect:/app/periodos/";
    }
}
