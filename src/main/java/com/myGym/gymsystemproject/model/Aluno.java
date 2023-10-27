package com.myGym.gymsystemproject.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Aluno {
    @Id
    private String CPF;

    @OneToOne(cascade = CascadeType.ALL) //Estratégia usada para exclusão de um registro de um aluno
    private RG registroGeral;

    @ManyToOne
    private Endereco endereco;

    private Integer idade;
    private String dataNascimento;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public RG getRegistroGeral() {
        return registroGeral;
    }

    public void setRegistroGeral(RG registroGeral) {
        this.registroGeral = registroGeral;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
