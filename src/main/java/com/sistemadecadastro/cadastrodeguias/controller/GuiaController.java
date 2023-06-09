package com.sistemadecadastro.cadastrodeguias.controller;

import com.sistemadecadastro.cadastrodeguias.model.Guia;
import com.sistemadecadastro.cadastrodeguias.service.GuiaService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public ResponseEntity<String> addGuia(@RequestBody @NotNull Guia guia){
        try {
            var guiaSaved = guiaService.saveGuia(guia);
            return new ResponseEntity<>(guiaSaved.toString(), HttpStatus.OK);
        }catch(ConstraintViolationException | IllegalArgumentException e){
            return new ResponseEntity<>("Verifique os campos e certifique que todos correspondem a valores válidos", HttpStatus.BAD_REQUEST);
        }
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

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<Guia>> searchGuiasByName(
            @PathVariable(name = "name") String nome
    ){
        try{
            var guias = guiaService.searchGuiasByName(nome);
            return ResponseEntity.ok(guias);
        }catch(EmptyResultDataAccessException em){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search/procedimento/{proc}")
    public ResponseEntity<List<Guia>> searchGuiasByProcedimento(
            @PathVariable(name = "proc") String procedimento
    ){
        try{
            var guias = guiaService.searchGuiasByProcedimento(procedimento);
            return ResponseEntity.ok(guias);
        }catch(EmptyResultDataAccessException em){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search/priority")
    public ResponseEntity<List<Guia>> searchGuiasByAge(){
        try{
            var guias = guiaService.searchGuiasPriority();
            return ResponseEntity.ok(guias);
        }catch(EmptyResultDataAccessException em){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search/date")
    public ResponseEntity<List<Guia>> searchGuiasBetweenDates(
            @RequestParam() LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate
    ){
        try{
            var guias = guiaService.searchGuiasBetweenDates(startDate, endDate);
            return ResponseEntity.ok(guias);
        }catch(EmptyResultDataAccessException em){
            return ResponseEntity.notFound().build();
        }
    }
}
