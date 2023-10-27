package com.myGym.gymsystemproject.service;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.Integer.parseInt;

@Service
public class ValidadorDocumento {

    final private Function<String, Integer> stringToInteger = numeroString -> parseInt(numeroString);

    private List<Integer> multiplicarOrdemDecrescente(int contador, List<Integer> listaInteiros){
        List<Integer> numerosMultiplicados = new ArrayList<>();
        for (Integer numero: listaInteiros){
            numerosMultiplicados.add(numero * contador);
            contador--;
        }
        return numerosMultiplicados;
    }

    private List<Integer> cortarPartesCpf(String cpf, int quantidadeDigitos) {
         List<Integer> cpfRetornado = Arrays
                .stream(cpf.split(""))
                .map(stringToInteger)
                .limit(quantidadeDigitos).toList();
         return cpfRetornado;
    }

    private List<Integer> ultimosDigitos(String cpf, int quantidadeUltimosDigitos) {
        int indiceComeco = cpf.length() - quantidadeUltimosDigitos;
        List<Integer> digitos = Arrays.stream(cpf.substring(indiceComeco)
                .split(""))
                .map(stringToInteger).toList();
        return  digitos;
    }

    public boolean validarCpf(String cpf){;

        List<Integer> cpfParaLista = cortarPartesCpf(cpf, cpf.length() -1);

        List<Integer> numerosCpfSemUltimosDigitos = cortarPartesCpf(cpf, 9);
        
        List<Integer> ultimosDigitos = ultimosDigitos(cpf, 2);

        System.out.println("ultimosDigitos = " + ultimosDigitos);

        Integer somaTotal = multiplicarOrdemDecrescente(10, numerosCpfSemUltimosDigitos)
                .stream()
                .reduce(0, Integer::sum);



        Integer restoDivisao = somaTotal * 10 % 11;
        restoDivisao = restoDivisao == 10 ? 0 : restoDivisao;

        boolean primeiroDigitoValido = restoDivisao == ultimosDigitos.get(0);
        System.out.println("primeiroDigitoValido = " + primeiroDigitoValido);

        List<Integer> numerosCpfSemOUltimoDigito = cortarPartesCpf(cpf, 10);

        Integer somaTotal2 = multiplicarOrdemDecrescente(11, numerosCpfSemOUltimoDigito)
                .stream()
                .reduce(0, Integer::sum);

        Integer restoDivisao2 = somaTotal2 * 10 % 11;
        restoDivisao2 = restoDivisao2 == 10 ? 0 : restoDivisao2;

        boolean segundoDigitoValido = restoDivisao2 == ultimosDigitos.get(1);
        System.out.println("segundoDigitoValido = " + segundoDigitoValido);

        System.out.println("somaTotal = " + somaTotal);


        Predicate<Integer> verificarSeTodosIguais = numero -> numero == cpfParaLista.get(0);

        boolean cpfValido = ( primeiroDigitoValido && segundoDigitoValido )
                &&   !(cpfParaLista.stream().allMatch(verificarSeTodosIguais))
                && cpfParaLista.size() == 10;

        System.out.println("cpfParaLista.size() = " + cpfParaLista.size());


        return cpfValido;

    }
}


