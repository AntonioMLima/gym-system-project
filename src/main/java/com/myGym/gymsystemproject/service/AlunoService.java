package com.myGym.gymsystemproject.service;

import com.myGym.gymsystemproject.model.Aluno;

import java.util.List;


public interface AlunoService {
    Aluno create(Aluno alunoParaCriar);

    Aluno findByCPF(String cpf);

    List<Aluno> findAll();

    Aluno updateAlunoByCpf(String cpf, Aluno alunoAtualizado);

    void deleteAlunoByCpf(String cpf);
}
