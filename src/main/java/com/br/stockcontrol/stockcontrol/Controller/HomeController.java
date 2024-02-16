package com.br.stockcontrol.stockcontrol.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/estoque")
    public String estoque() {
        return "estoque";
    }

    @GetMapping("/inventario")
    public String inventario() {
        return "inventario";
    }

    @GetMapping("/retirada")
    public String retirada() {
        return "retirada";
    }
}


