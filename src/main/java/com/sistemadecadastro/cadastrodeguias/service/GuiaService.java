package com.sistemadecadastro.cadastrodeguias.service;

import com.sistemadecadastro.cadastrodeguias.model.Guia;
import com.sistemadecadastro.cadastrodeguias.repository.GuiaRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
    @Transactional
    public void updateGuia(Integer id, String nome, String sus, String procedimento, LocalDate dataNascimento, LocalDate dataRecebimento) {
        var guia = guiaRespository.findById(id).orElseThrow(() -> {
            return new IllegalStateException("Guia com o id: " + id +  " não encontrada");
        });
        if(nome != null && nome.length() > 3 && !Objects.equals(guia.getNome(), nome)){
            guia.setNome(nome);
        }
        if(sus != null && sus.length() == 15 && !Objects.equals(guia.getSus(), sus)){
            guia.setSus(sus);
        }
        if (procedimento != null && procedimento.length() > 2 && !Objects.equals(guia.getProcedimento(), procedimento)) {
            guia.setProcedimento(procedimento);
        }

        if (dataNascimento != null) {
            guia.setDataNascimento(dataNascimento);
        }

        if (dataRecebimento != null) {
            guia.setDataRecebimento(dataRecebimento);
        }
    }
    public List<Guia> listGuias(){
        return guiaRespository.findAll();
    }
}
