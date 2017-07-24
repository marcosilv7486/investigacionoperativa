package com.marcosilv7.proyectoiop.controller;

import com.marcosilv7.proyectoiop.service.interfaces.OptimizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/productos")
public class ProductoController {

    private OptimizacionService optimizacionService;

    @Autowired
    public ProductoController(OptimizacionService optimizacionService) {
        this.optimizacionService = optimizacionService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("notas", optimizacionService.obtenerProductos());
        return "/app/productos/index";
    }


}
