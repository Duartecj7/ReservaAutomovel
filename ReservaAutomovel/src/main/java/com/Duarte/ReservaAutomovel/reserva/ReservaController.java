package com.Duarte.ReservaAutomovel.reserva;

import com.Duarte.ReservaAutomovel.carro.CarroRepository;
import com.Duarte.ReservaAutomovel.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> listReservas(){
            return reservaService.getAllReservas();
    }

    @GetMapping("/{id}")
    public Reserva getReservaById(@PathVariable UUID id){
        return  reservaService.getReservaById(id);
    }
    @PostMapping
    public Reserva criarReserva(@RequestBody Reserva reserva){
        return reservaService.addReserva(reserva);
    }
}
