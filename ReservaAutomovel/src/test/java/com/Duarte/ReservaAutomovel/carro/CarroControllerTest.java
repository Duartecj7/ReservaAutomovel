package com.Duarte.ReservaAutomovel.carro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarroController.class)
public class CarroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarroService carroService;

    @Test
    void testListCarros() throws Exception {
        // Criar carros para o teste
        Carro carro1 = new Carro();
        carro1.setId(UUID.randomUUID());
        carro1.setModelo("Modelo A");
        carro1.setMarca("Marca A");
        carro1.setAno(2022);
        carro1.setDisponivel(true);

        Carro carro2 = new Carro();
        carro2.setId(UUID.randomUUID());
        carro2.setModelo("Modelo B");
        carro2.setMarca("Marca B");
        carro2.setAno(2023);
        carro2.setDisponivel(false);

        List<Carro> carros = List.of(carro1, carro2);
        // Simular o serviço
        when(carroService.getAllCarros()).thenReturn(carros);
        // Realizar requisição GET  e verificar os resultados
        mockMvc.perform(get("/carros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].modelo").value("Modelo A"))// Verificar o modelo dos carros
                .andExpect(jsonPath("$[1].modelo").value("Modelo B"));
    }

    @Test
    void testAddCarro() throws Exception {
        // Criar carro para o teste
        Carro carro = new Carro();
        carro.setId(UUID.randomUUID());
        carro.setModelo("Modelo A");
        carro.setMarca("Marca A");
        carro.setAno(2022);
        carro.setDisponivel(true);

        // Simular o serviço
        when(carroService.saveCarro(any(Carro.class))).thenReturn(carro);
        // Realizar requisição Post  e verificar os resultados
        mockMvc.perform(post("/carros")
                        .contentType("application/json")
                        .content("""
                    {
                        "modelo": "Modelo A",
                        "marca": "Marca A",
                        "ano": 2022,
                        "disponivel": true
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelo").value("Modelo A"))
                .andExpect(jsonPath("$.marca").value("Marca A"));
    }
}
