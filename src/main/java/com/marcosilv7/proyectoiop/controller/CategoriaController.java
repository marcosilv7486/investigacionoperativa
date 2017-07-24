package com.marcosilv7.proyectoiop.controller;

import com.marcosilv7.proyectoiop.dao.domain.CategoriaProducto;
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
@RequestMapping("/app/categoria")
public class CategoriaController extends BaseController {

    private OptimizacionService optimizacionService;

    @Autowired
    public CategoriaController(OptimizacionService optimizacionService){
        this.optimizacionService=optimizacionService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("categorias",optimizacionService.obtenerCategorias());
        return "/app/categoria/index";
    }

    @GetMapping("/crear")
    public String create(Model model) {
        model.addAttribute("categoria",  new CategoriaProducto() );
        return "/app/categoria/mantener";
    }

    @GetMapping("/editar/{id}")
    public String editar(Model model,@PathVariable(name = "id")Integer id) {
        model.addAttribute("categoria",  optimizacionService.obtenerCategoria(id));
        return "/app/categoria/mantener";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute @Valid CategoriaProducto categoria , BindingResult result ,
                          RedirectAttributes redirectAttributes , Model model) {
        if(result.hasErrors()) {
            return "/app/categoria/mantener";
        }
        if (categoria.getId()== null) {
            optimizacionService.registrarCategoria(categoria);
            flashMessage(redirectAttributes , "Exito","Categoria  registrada correctamente","success");
        }
        else {
            optimizacionService.modificarCategoria(categoria);
            flashMessage(redirectAttributes , "Exito","Categoria modificada correctamente","success");
        }
        return  "redirect:/app/categoria/";
    }

}
