package com.Duarte.ReservaAutomovel.reserva;

import com.Duarte.ReservaAutomovel.carro.Carro;
import com.Duarte.ReservaAutomovel.carro.CarroRepository;
import com.Duarte.ReservaAutomovel.cliente.Cliente;
import com.Duarte.ReservaAutomovel.cliente.ClienteRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {
    private static final Logger log = LoggerFactory.getLogger(ReservaService.class);
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CarroRepository carroRepository;

    /** Retorna todas as reservas
    * @return lista de reservas */
    public List<Reserva> getAllReservas(){
        return reservaRepository.findAll();
    }

    /** Procurar reserva por id
     *
     * @param id -> id da reserva
     * @return reserva com o id correspondente
     * @throws RuntimeException se a reserva não for encontrada*/
    public Reserva getReservaById(UUID id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Reserva com ID %s não encontrada", id)));
    }

    /**Adiciona uma reserva caso os dados sejam válidos
     * Valida se : cliente/carro existe no sistema
     *             carro está disponivel para reserva
     *             data de inicio da reserva é antes da data de fim da reserva
     * @param reserva -> objeto reserva a ser adicionado
     * @return reserva adicionada
     * @throws RuntimeException se dados inválidos ou mal formatados*/
    public Reserva addReserva(Reserva reserva){
        // 1ª validação : CLIENTE EXISTE NO SISTEMA
        Cliente cliente = clienteRepository.findById(reserva.getCliente()
                                                        .getId())
                                                        .orElseThrow(() -> {
                                                            String errorMessage = String.format("[ERRO] Cliente com ID %s não encontrado"
                                                                    , reserva.getCliente().getId());
                                                            log.error(errorMessage);
                                                            throw new RuntimeException(errorMessage);
                                                        });


        // 2ª validação : CARRO EXISTE NO SISTEMA
        Carro carro = carroRepository.findById(reserva.getCarro()
                                                    .getId())
                                                    .orElseThrow(() -> {
                                                        String errorMessage = String.format("[ERRO] Carro com ID %s não encontrado",
                                                                reserva.getCarro().getId());
                                                        log.error(errorMessage);
                                                        throw new RuntimeException(errorMessage);
                                                    });
        // --> validação : CARRO DISPONIVEL
        if (!carro.getDisponivel()){
            String errorMessage = String.format("[ERRO] O carro com ID %s não está disponível", carro.getId());
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        // 3ª validação : DATAINICIO<DATAFIM
        if (reserva.getDataInicio().isAfter(reserva.getDatafim())) {
            String errorMessage = String.format("[ERRO] A data de início (%s) deve ser anterior à data de fim (%s)",
                    reserva.getDataInicio(), reserva.getDatafim());
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        //Associar cliente/carro à reserva caso excepções não sejam lançadas
        reserva.setCliente(cliente);
        reserva.setCarro(carro);
        //Adicionar a reserva
        return reservaRepository.save(reserva);
    }
}
