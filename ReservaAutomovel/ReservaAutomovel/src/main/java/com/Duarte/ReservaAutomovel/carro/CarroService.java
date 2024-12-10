package com.Duarte.ReservaAutomovel.carro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    //Retorna todos os carros
    public List<Carro> getAllCarros(){
        return carroRepository.findAll();
    }
    //Adiciona um carro
    public Carro saveCarro(Carro carro){
        return carroRepository.save(carro);
    }

}
