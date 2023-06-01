package com.sistemadecadastro.cadastrodeguias.controller;

import com.sistemadecadastro.cadastrodeguias.model.Guia;
import com.sistemadecadastro.cadastrodeguias.service.GuiaService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
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
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeGuia(@RequestParam Integer id){
        var deleted = guiaService.deleteGuia(id);
        if(deleted == null) return new ResponseEntity<>(String.format("Guia com o id %d não encontrada", id),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(deleted.toString(), HttpStatus.OK);
    }
    @PutMapping("/atualizar")
    public ResponseEntity<String> updateGuia(@RequestParam Integer id, @RequestBody Guia guia){
        var updated = guiaService.updateGuia(id, guia);
        if(updated == null) return new ResponseEntity<>(String.format("Guia com o id %d não encontrada", id), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updated.toString(), HttpStatus.OK);
    }
    @GetMapping("/")
    public List<Guia> listGuias(){
        return guiaService.listGuias();
    }
}
