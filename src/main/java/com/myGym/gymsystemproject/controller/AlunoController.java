package com.myGym.gymsystemproject.controller;

import com.myGym.gymsystemproject.model.Aluno;
import com.myGym.gymsystemproject.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AlunoController {

    AlunoService alunoService;


    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    @PostMapping()
    public ResponseEntity<Aluno> adicionarAluno(@RequestBody Aluno aluno){
        alunoService.create(aluno);
        return ResponseEntity.ok(aluno);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Aluno>> buscarTodosAlunos() {
        return ResponseEntity.ok(alunoService.findAll());
    }

    @GetMapping( "/{cpf}")
    public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable String cpf){
        return ResponseEntity.ok(alunoService.findByCPF(cpf));
    }

    @PutMapping( "/{cpf}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable String cpf, @RequestBody Aluno alunoAtualizado ){
        alunoService.updateAlunoByCpf(cpf, alunoAtualizado);
        return ResponseEntity.ok(alunoAtualizado);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarAluno(@PathVariable String cpf){
        alunoService.deleteAlunoByCpf(cpf);
        return ResponseEntity.ok().build();
    }
}
