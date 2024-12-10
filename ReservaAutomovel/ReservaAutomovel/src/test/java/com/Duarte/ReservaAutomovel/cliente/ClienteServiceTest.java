package com.Duarte.ReservaAutomovel.cliente;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setUp() {
        cliente1 = new Cliente();
        cliente1.setId(UUID.randomUUID());
        cliente1.setNome("Cliente 1");
        cliente1.setEmail("cliente1@example.com");
        cliente1.setTelefone("123456789");

        cliente2 = new Cliente();
        cliente2.setId(UUID.randomUUID());
        cliente2.setNome("Cliente 2");
        cliente2.setEmail("cliente2@example.com");
        cliente2.setTelefone("987654321");
    }

    // getClientes()
    @Test
    void testGetClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente1, cliente2));

        List<Cliente> clientes = clienteService.getClientes();

        assertEquals(2, clientes.size());
        assertEquals("Cliente 1", clientes.get(0).getNome());
        assertEquals("Cliente 2", clientes.get(1).getNome());

        // Verificar se o método foi chamado
        verify(clienteRepository, times(1)).findAll();
    }

    // addCliente()
    @Test
    void testAddCliente() {
        when(clienteRepository.save(cliente1)).thenReturn(cliente1);

        Cliente clienteCriado = clienteService.addCliente(cliente1);

        // Verificar se o cliente retornado é o mesmo que foi criado
        assertEquals(cliente1.getNome(), clienteCriado.getNome());
        assertEquals(cliente1.getEmail(), clienteCriado.getEmail());
        assertEquals(cliente1.getTelefone(), clienteCriado.getTelefone());

        // Verificar se o método foi chamado
        verify(clienteRepository, times(1)).save(cliente1);
    }
}
