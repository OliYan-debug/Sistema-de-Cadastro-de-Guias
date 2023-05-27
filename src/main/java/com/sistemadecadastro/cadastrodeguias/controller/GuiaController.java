package com.sistemadecadastro.cadastrodeguias.controller;

import com.sistemadecadastro.cadastrodeguias.model.Guia;
import com.sistemadecadastro.cadastrodeguias.service.GuiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GuiaController {
    private final GuiaService guiaService;
    @Autowired
    public GuiaController(GuiaService guiaService){
        this.guiaService = guiaService;
    }

    @PostMapping("/")
    public Guia addGuia(@RequestBody Guia guia){
        System.out.println(guia);
        return guiaService.saveGuia(guia);
    }

    @GetMapping("/")
    public List<Guia> listGuias(){
        return guiaService.listGuias();
    }
}
