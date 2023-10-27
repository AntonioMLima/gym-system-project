package com.myGym.gymsystemproject.repository;

import com.myGym.gymsystemproject.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, String> {
    boolean existsByCPF(String CPF);

}
