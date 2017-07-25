package com.marcosilv7.proyectoiop.controller;

import com.marcosilv7.proyectoiop.service.interfaces.OptimizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by marcosilveriocastro on 24/07/17.
 */
@Controller
@RequestMapping("/app/reportes")
public class ReportesController extends BaseController {

    private OptimizacionService optimizacionService;

    @Autowired
    public ReportesController(OptimizacionService optimizacionService){
        this.optimizacionService=optimizacionService;
    }

    @RequestMapping("/generar")
    public String generarReporte(Model model){
        optimizacionService.limpiarReporte();
        optimizacionService.generarReporte();
        model.addAttribute("reporteCompra",optimizacionService.obtenerResultadoCompra());
        model.addAttribute("reporteInventario",optimizacionService.obtenerResultadoInventario());
        model.addAttribute("periodos",optimizacionService.obtenerPeriodos());
        return "app/reportes/index";
    }
}
