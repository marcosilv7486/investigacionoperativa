package com.marcosilv7.proyectoiop.controller;

import com.marcosilv7.proyectoiop.dao.domain.Producto;
import com.marcosilv7.proyectoiop.service.interfaces.OptimizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/app/productos")
public class ProductoController {

    private OptimizacionService optimizacionService;

    @Autowired
    public ProductoController(OptimizacionService optimizacionService) {
        this.optimizacionService = optimizacionService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("productos", optimizacionService.obtenerProductos());
        return "/app/productos/index";
    }

    @GetMapping("/crear")
    public String create(Model model) {
        model.addAttribute("producto",  new Producto() );
        model.addAttribute("proveedores", optimizacionService.obtenerProveedores());
        return "/app/productos/mantener";
    }

    @GetMapping("/editar/{id}")
    public String editar(Model model,@PathVariable(name = "id")Integer id) {
        model.addAttribute("producto",  optimizacionService.obtenerProducto(id));
        model.addAttribute("proveedores", optimizacionService.obtenerProveedores());
        return "/app/productos/mantener";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute @Valid Producto producto , BindingResult result ,
                          RedirectAttributes redirectAttributes , Model model) {
        if(result.hasErrors()) {
            return "/app/productos/mantener";
        }
        if (producto.getId()== null) {
            optimizacionService.registrarProducto(producto);
        }
        else {
            optimizacionService.modificar(producto);
        }
        return  "redirect:/app/productos/";
    }

}
