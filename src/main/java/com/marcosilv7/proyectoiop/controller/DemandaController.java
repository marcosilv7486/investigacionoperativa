package com.marcosilv7.proyectoiop.controller;

import com.marcosilv7.proyectoiop.dao.domain.CategoriaProducto;
import com.marcosilv7.proyectoiop.dao.domain.Demanda;
import com.marcosilv7.proyectoiop.service.interfaces.OptimizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by marcosilveriocastro on 24/07/17.
 */
@Controller
@RequestMapping("/app/demanda")
public class DemandaController extends BaseController {

    private OptimizacionService optimizacionService;

    @Autowired
    public DemandaController(OptimizacionService optimizacionService){
        this.optimizacionService=optimizacionService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("demandas", optimizacionService.obtenerDemanda());
        return "app/demanda/index";
    }

    @GetMapping("/crear")
    public String crear(Model model){
        model.addAttribute("periodos", optimizacionService.obtenerPeriodos());
        model.addAttribute("productos", optimizacionService.obtenerProductos());
        model.addAttribute("demanda",new Demanda());
        return "app/demanda/mantener";
    }

    @GetMapping("/editar/{id}")
    public String editar(Model model,@PathVariable(name = "id")Integer id) {
        model.addAttribute("demanda",  optimizacionService.obtenerDemanda(id));
        model.addAttribute("periodos", optimizacionService.obtenerPeriodos());
        model.addAttribute("productos", optimizacionService.obtenerProductos());
        return "app/demanda/mantener";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute @Valid Demanda demanda , BindingResult result ,
                          RedirectAttributes redirectAttributes , Model model) {
        if(result.hasErrors()) {
            model.addAttribute("periodos", optimizacionService.obtenerPeriodos());
            model.addAttribute("productos", optimizacionService.obtenerProductos());
            return "app/demanda/mantener";
        }
        if (demanda.getId()== null) {
            try {
                optimizacionService.registrarDemanda(demanda);
                flashMessage(redirectAttributes , "Exito","Demanda  registrada correctamente","success");
                return  "redirect:/app/demanda/";
            }catch (Exception e){
                model.addAttribute("periodos", optimizacionService.obtenerPeriodos());
                model.addAttribute("productos", optimizacionService.obtenerProductos());
                result.addError(new ObjectError("Demanda",e.getMessage()));
                return "app/demanda/mantener";
            }
        }
        else {
            try {
                optimizacionService.modificarDemanda(demanda);
                flashMessage(redirectAttributes , "Exito","Demanda  modificada correctamente","success");
                return  "redirect:/app/demanda/";
            }catch (Exception e){
                model.addAttribute("periodos", optimizacionService.obtenerPeriodos());
                model.addAttribute("productos", optimizacionService.obtenerProductos());
                result.addError(new ObjectError("Demanda",e.getMessage()));
                return "app/demanda/mantener";
            }
        }

    }



}
