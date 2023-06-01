package com.sistemadecadastro.cadastrodeguias.service;

import com.sistemadecadastro.cadastrodeguias.model.Guia;
import com.sistemadecadastro.cadastrodeguias.repository.GuiaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuiaService {
    private final GuiaRespository guiaRespository;

    @Autowired
    public GuiaService(GuiaRespository guiaRespository){
        this.guiaRespository = guiaRespository;
    }

    public Guia saveGuia(Guia guia){
        if(guiaRespository.existsBySusAndProcedimento(guia.getSus(), guia.getProcedimento())){
            throw new IllegalArgumentException("Guia do paciente com o mesmo procedimento já foi cadastrada");
        }
        return guiaRespository.saveAndFlush(guia);
    }

    public Guia deleteGuia(int id){
        var guia = guiaRespository.findById(id);
        if(guia.isEmpty()) return null;
        guiaRespository.deleteGuiaById(id);
        return guia.get();
    }
    public Guia updateGuia(Integer id, Guia guia){
        var guiaDB = guiaRespository.findById(id);
        if(guiaDB.isEmpty()) return null;
        guia.setId(guiaDB.get().getId());
        return guiaRespository.save(guia);
    }
    public List<Guia> listGuias(){
        return guiaRespository.findAll();
    }
}