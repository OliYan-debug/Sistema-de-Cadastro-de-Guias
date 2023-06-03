package com.sistemadecadastro.cadastrodeguias.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Size(min = 3)
    @Column(name="nome", nullable = false)
    private String nome;
    @Size(min = 15, max = 15)
    @Column(name="sus", nullable = false, length = 15)
    private String sus;
    @Size(min = 2)
    @Column(name = "procedimento", nullable = false)
    private String procedimento;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_recebimento", nullable = false)
    private LocalDate dataRecebimento;

    public Guia(String nome, String sus, String procedimento, LocalDate dataNascimento, LocalDate dataRecebimento) {
        this.nome = nome;
        this.sus = sus;
        this.procedimento = procedimento;
        this.dataNascimento = dataNascimento;
        this.dataRecebimento = dataRecebimento;
    }
    public Guia() {

    }
}

