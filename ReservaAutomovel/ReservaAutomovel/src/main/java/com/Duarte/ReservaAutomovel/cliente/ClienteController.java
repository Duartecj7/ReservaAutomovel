package com.Duarte.ReservaAutomovel.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {
    
    @Autowired
    private ClienteRepository clienteRepository;
    @GetMapping
    public List<Cliente> listClientes() {
        return clienteRepository.findAll();
    }
    @PostMapping
    public Cliente addCliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }
}
