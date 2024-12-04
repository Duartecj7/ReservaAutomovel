package com.Duarte.ReservaAutomovel.cliente;

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

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService clienteService;

    @MockitoBean
    private ClienteRepository clienteRepository;

    @Test
    void testListClientes() throws Exception {
        // Criar clientes
        Cliente cliente1 = new Cliente();
        cliente1.setId(UUID.randomUUID());
        cliente1.setNome("Cliente A");
        cliente1.setEmail("clientea@example.com");
        cliente1.setTelefone("123456789");

        Cliente cliente2 = new Cliente();
        cliente2.setId(UUID.randomUUID());
        cliente2.setNome("Cliente B");
        cliente2.setEmail("clienteb@example.com");
        cliente2.setTelefone("987654321");

        List<Cliente> clientes = List.of(cliente1, cliente2);

        // Simular o serviço
        when(clienteRepository.findAll()).thenReturn(clientes);

        // requisição GET  /clientes
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())  // Espera status 200 OK
                .andExpect(jsonPath("$[0].nome").value("Cliente A"))
                .andExpect(jsonPath("$[1].nome").value("Cliente B"));
    }

    @Test
    void testAddCliente() throws Exception {
        // Criar cliente
        Cliente cliente = new Cliente();
        cliente.setId(UUID.randomUUID());
        cliente.setNome("Cliente A");
        cliente.setEmail("clientea@example.com");
        cliente.setTelefone("123456789");

        // Simular o serviço
        when(clienteService.addCliente(any(Cliente.class))).thenReturn(cliente);

        // Realizar requisição POST  /clientes com o JSON do cliente
        mockMvc.perform(post("/clientes")
                        .contentType("application/json")
                        .content("""
                    {
                        "nome": "Cliente A",
                        "email": "clientea@example.com",
                        "telefone": "123456789"
                    }
                """))
                .andExpect(status().isOk());  // status 200 OK

    }
}
