package com.Duarte.ReservaAutomovel.reserva;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReservaService reservaService;

    @Test
    void testGetReservaById() throws Exception {
        UUID reservaId = UUID.randomUUID();
        Reserva reserva = new Reserva();
        reserva.setId(reservaId);

        when(reservaService.getReservaById(reservaId)).thenReturn(reserva);

        mockMvc.perform(get("/reservas/" + reservaId))
                .andExpect(status().isOk());
    }
    @Test
    void testGetReservaByIdReservaNaoExistente() throws Exception {
        UUID reservaId = UUID.randomUUID();

        when(reservaService.getReservaById(reservaId)).thenThrow(new RuntimeException("não encontrada"));

        mockMvc.perform(get("/reservas/" + reservaId))
                .andExpect(status().isNotFound()); // status é 404
    }
    @Test
    void testListReservas() throws Exception {
        Reserva reserva1 = new Reserva();
        reserva1.setId(UUID.randomUUID());
        Reserva reserva2 = new Reserva();
        reserva2.setId(UUID.randomUUID());

        List<Reserva> reservas = List.of(reserva1, reserva2);

        when(reservaService.getAllReservas()).thenReturn(reservas);

        mockMvc.perform(get("/reservas"))
                .andExpect(status().isOk()); // status é 200

    }
}
