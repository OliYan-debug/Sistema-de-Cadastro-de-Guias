package com.sistemadecadastro.cadastrodeguias.controller;

import com.sistemadecadastro.cadastrodeguias.model.Guia;
import com.sistemadecadastro.cadastrodeguias.service.GuiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeGuia(@PathVariable("id") Integer id){
        var deleted = guiaService.deleteGuia(id);
        if(deleted == null) return new ResponseEntity<>(String.format("Guia com o id %d não encontrada", id),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(deleted.toString(), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateGuia(@PathVariable Integer id,
                                             @RequestParam(required = false) String nome,
                                             @RequestParam(required = false) String sus,
                                             @RequestParam(required = false) String procedimento,
                                             @RequestParam(required = false) LocalDate data_nascimento,
                                             @RequestParam(required = false) LocalDate data_recebimento
                                             ){
        try {
            guiaService.updateGuia(id, nome, sus, procedimento, data_nascimento, data_recebimento);
            return new ResponseEntity<>("Guia atualizada com sucesso", HttpStatus.OK);
        } catch (IllegalStateException e){
            return new ResponseEntity<>(String.format("Guia com o id %d não encontrada", id), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/")
    public List<Guia> listGuias(){
        return guiaService.listGuias();
    }
}
