package com.Duarte.ReservaAutomovel.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    //Retorna todos os clientes
    public List<Cliente> getClientes(){
        return clienteRepository.findAll();
    }

    //Adiciona um cliente
    public Cliente addCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }
}
