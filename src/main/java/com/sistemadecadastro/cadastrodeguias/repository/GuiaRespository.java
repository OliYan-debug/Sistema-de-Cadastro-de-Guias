package com.sistemadecadastro.cadastrodeguias.repository;

import com.sistemadecadastro.cadastrodeguias.model.Guia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuiaRespository extends JpaRepository<Guia, Integer> {
    /**
     * @param Sus
     * @param procedimento
     * @return True if a guia already exists in the database with the same procedimento and False if not
     */
    public boolean existsBySusAndProcedimento(String Sus, String procedimento);
}
