package com.marcosilv7.proyectoiop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/home")
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "app/home/index";
    }
}
