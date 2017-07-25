package com.marcosilv7.proyectoiop.controller;

import com.marcosilv7.proyectoiop.dao.domain.Proveedor;
import com.marcosilv7.proyectoiop.service.interfaces.OptimizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by marcosilveriocastro on 24/07/17.
 */
@Controller
@RequestMapping("/app/proveedor")
public class ProveedorController extends BaseController {

    private OptimizacionService optimizacionService;

    @Autowired
    public ProveedorController(OptimizacionService optimizacionService) {
        this.optimizacionService = optimizacionService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("proveedores",optimizacionService.obtenerProveedores());
        return "app/proveedor/index";
    }

    @GetMapping("/crear")
    public String create(Model model) {
        model.addAttribute("proveedor",  new Proveedor() );
        return "app/proveedor/mantener";
    }

    @GetMapping("/editar/{id}")
    public String editar(Model model,@PathVariable(name = "id")Integer id) {
        model.addAttribute("proveedor",  optimizacionService.obtenerProveedor(id));
        return "app/proveedor/mantener";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute @Valid Proveedor proveedor , BindingResult result ,
                          RedirectAttributes redirectAttributes , Model model) {
        if(result.hasErrors()) {
            return "app/proveedor/mantener";
        }
        if (proveedor.getId()== null) {
            optimizacionService.registrarProveedor(proveedor);
            flashMessage(redirectAttributes , "Exito","Proveedor registrado correctamente","success");
        }
        else {
            optimizacionService.modificarProveedor(proveedor);
            flashMessage(redirectAttributes , "Exito","Proveedor modificado correctamente","success");
        }
        return  "redirect:/app/proveedor/";
    }
}
