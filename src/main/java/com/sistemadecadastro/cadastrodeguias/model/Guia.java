package com.sistemadecadastro.cadastrodeguias.model;


import jakarta.persistence.*;
import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

@Entity
@Table(name="guias")
@Data
public class Guia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String sus;
    private String procedimento;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data_nascimento;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data_recebimento;

    public Guia(String nome, String sus, String procedimento, LocalDate data_nascimento, LocalDate data_recebimento) {
        this.nome = nome;
        this.sus = sus;
        this.procedimento = procedimento;
        this.data_nascimento = data_nascimento;
        this.data_recebimento = data_recebimento;
    }
    public Guia() {

    }
}

