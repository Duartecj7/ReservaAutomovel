package com.Duarte.ReservaAutomovel.reserva;

import com.Duarte.ReservaAutomovel.carro.Carro;
import com.Duarte.ReservaAutomovel.carro.CarroRepository;
import com.Duarte.ReservaAutomovel.cliente.Cliente;
import com.Duarte.ReservaAutomovel.cliente.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservaServiceTest {
    @InjectMocks
    private ReservaService reservaService;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private CarroRepository carroRepository;

    public ReservaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddReservaComDadosValidos() {
        // Mock data
        UUID clienteId = UUID.randomUUID();
        UUID carroId = UUID.randomUUID();

        Cliente cliente = new Cliente();
        cliente.setId(clienteId);

        Carro carro = new Carro();
        carro.setId(carroId);
        carro.setDisponivel(true);

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setCarro(carro);
        reserva.setDataInicio(LocalDate.of(2024, 12, 10));
        reserva.setDatafim(LocalDate.of(2024, 12, 20));

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));
        when(carroRepository.findById(carroId)).thenReturn(Optional.of(carro));
        when(reservaRepository.save(reserva)).thenReturn(reserva);

        // Execute
        Reserva resultado = reservaService.addReserva(reserva);

        // Assert
        assertNotNull(resultado);
        assertEquals(clienteId, resultado.getCliente().getId());
        assertEquals(carroId, resultado.getCarro().getId());
        verify(reservaRepository, times(1)).save(reserva);
    }

    @Test
    void testAddReservaClienteNaoExistente() {
        // Mock data
        Reserva reserva = new Reserva();
        Cliente cliente = new Cliente();
        cliente.setId(UUID.randomUUID());
        reserva.setCliente(cliente);

        when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.empty());

        // Assert & Execute
        RuntimeException exception = assertThrows(RuntimeException.class, () -> reservaService.addReserva(reserva));
        assertEquals("[ERRO] Cliente com ID " + cliente.getId() + " não encontrado", exception.getMessage());
    }

    @Test
    void testAddReservaCarroNaoDisponivel() {
        UUID clienteId = UUID.randomUUID();
        UUID carroId = UUID.randomUUID();

        Cliente cliente = new Cliente();
        cliente.setId(clienteId);

        Carro carro = new Carro();
        carro.setId(carroId);
        carro.setDisponivel(false);

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setCarro(carro);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));
        when(carroRepository.findById(carroId)).thenReturn(Optional.of(carro));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> reservaService.addReserva(reserva));
        assertEquals("[ERRO] O carro com ID " + carroId + " não está disponível", exception.getMessage());
    }

    @Test
    void testAddReservaDataInvalida() {
        UUID clienteId = UUID.randomUUID();
        UUID carroId = UUID.randomUUID();

        Cliente cliente = new Cliente();
        cliente.setId(clienteId);

        Carro carro = new Carro();
        carro.setId(carroId);
        carro.setDisponivel(true);

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setCarro(carro);
        reserva.setDataInicio(LocalDate.of(2024, 12, 20));
        reserva.setDatafim(LocalDate.of(2024, 12, 10));

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));
        when(carroRepository.findById(carroId)).thenReturn(Optional.of(carro));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> reservaService.addReserva(reserva));
        assertEquals("[ERRO] A data de início (2024-12-20) deve ser anterior à data de fim (2024-12-10)", exception.getMessage());
    }
}
