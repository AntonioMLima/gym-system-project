package com.myGym.gymsystemproject.service;

import com.myGym.gymsystemproject.model.Endereco;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "viacep", url = "viacep.com.br/ws")
public interface ViaCepConsultatt {

    @GetMapping("/{cep}/json")
    Endereco obterEndereco(@PathVariable("cep") String cep);
}
