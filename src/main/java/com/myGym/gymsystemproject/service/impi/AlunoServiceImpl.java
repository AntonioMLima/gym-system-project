package com.myGym.gymsystemproject.service.impi;

import com.myGym.gymsystemproject.model.Aluno;
import com.myGym.gymsystemproject.model.Endereco;
import com.myGym.gymsystemproject.repository.AlunoRepository;
import com.myGym.gymsystemproject.repository.EnderecoRepository;
import com.myGym.gymsystemproject.service.AlunoService;
import com.myGym.gymsystemproject.service.ValidadorDocumento;
import com.myGym.gymsystemproject.service.ViaCepConsultatt;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AlunoServiceImpl implements AlunoService {

    private AlunoRepository alunoRepository;
    private ValidadorDocumento validarCpf;

    private ViaCepConsultatt cepConsulta;
    private EnderecoRepository enderecoRepository;



    public AlunoServiceImpl(AlunoRepository alunoRepository,
                            ValidadorDocumento validarCPF,
                            ViaCepConsultatt cepConsulta,
                            EnderecoRepository enderecoRepository){
        this.alunoRepository = alunoRepository;
        this.validarCpf = validarCPF;
        this.cepConsulta = cepConsulta;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public Aluno create(Aluno alunoParaCriar) {

        if (validarCpf.validarCpf(alunoParaCriar.getCPF())){
            if (alunoRepository.existsByCPF(alunoParaCriar.getCPF())){
                throw new IllegalArgumentException("O aluno já está regitrado no sistema");
            } else {

                salvarAlunoComEndereco(alunoParaCriar);
            }
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }


        return alunoParaCriar;
    }

    @Override
    public Aluno findByCPF(String cpf) {
        return alunoRepository.findById(cpf).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    @Override
    public Aluno updateAlunoByCpf(String cpf, Aluno alunoAtualizado) {
        Aluno alunoAntigo = alunoRepository.findById(cpf).orElseThrow(NoSuchElementException::new);
        if (!alunoAntigo.getCPF().equals(alunoAtualizado.getCPF())){
            throw new IllegalArgumentException("O CPF do aluno antigo e do novo aluno deve ser o mesmo.");
        }

        alunoAntigo.setCPF(alunoAtualizado.getCPF());
        alunoAntigo.setEndereco(alunoAntigo.getEndereco());
        alunoAntigo.setIdade(alunoAntigo.getIdade());
        alunoAntigo.setRegistroGeral(alunoAtualizado.getRegistroGeral());
        alunoAntigo.setDataNascimento(alunoAtualizado.getDataNascimento());

        return alunoRepository.save(alunoAntigo);
    }

    @Override
    public void deleteAlunoByCpf(String cpf) {
        alunoRepository.deleteById(cpf);
    }
    
    private void salvarAlunoComEndereco(Aluno aluno) {
        String cep = aluno.getEndereco().getCep();
        Endereco endereco = cepConsulta.obterEndereco(cep);
        enderecoRepository.save(endereco);
        aluno.setEndereco(endereco);
        alunoRepository.save(aluno);
    }
}
