package com.Duarte.ReservaAutomovel.carro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
@CrossOrigin(origins = "http://localhost:3000")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public List<Carro> listCarros(){
        return carroService.getAllCarros();
    }
    @PostMapping
    public Carro addCarro(@RequestBody Carro carro){
        return carroService.saveCarro(carro);
    }


}
