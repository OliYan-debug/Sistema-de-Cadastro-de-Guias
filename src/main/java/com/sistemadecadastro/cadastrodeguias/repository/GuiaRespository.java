package com.sistemadecadastro.cadastrodeguias.repository;

import com.sistemadecadastro.cadastrodeguias.model.Guia;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface GuiaRespository extends JpaRepository<Guia, Integer> {
    /**
     * @param Sus
     * @param procedimento
     * @return True if a guia already exists in the database with the same procedimento and False if not
     */
    public boolean existsBySusAndProcedimento(String Sus, String procedimento);
    @Transactional
    public void deleteGuiaById(int id);


    public List<Guia> findGuiasByProcedimentoContainingIgnoreCase(@Size(min = 2) String procedimento);
    public List<Guia> findGuiasByDataNascimentoBefore(LocalDate dataNascimento);
    public List<Guia> findGuiasByNomeIgnoreCase(@Size(min = 3) String nome);
}
