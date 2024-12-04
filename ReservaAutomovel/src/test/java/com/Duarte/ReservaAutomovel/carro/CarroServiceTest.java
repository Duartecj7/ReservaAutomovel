package com.Duarte.ReservaAutomovel.carro;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarroServiceTest {

    @InjectMocks
    private CarroService carroService;

    @Mock
    private CarroRepository carroRepository;

    public CarroServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCarros() {
        Carro carro1 = new Carro();
        carro1.setId(UUID.randomUUID());
        carro1.setModelo("Modelo A");

        Carro carro2 = new Carro();
        carro2.setId(UUID.randomUUID());
        carro2.setModelo("Modelo B");

        when(carroRepository.findAll()).thenReturn(List.of(carro1, carro2));

        List<Carro> carros = carroService.getAllCarros();

        assertEquals(2, carros.size());
        assertEquals("Modelo A", carros.get(0).getModelo());
        assertEquals("Modelo B", carros.get(1).getModelo());
    }

    @Test
    void testSaveCarro() {
        Carro carro = new Carro();
        carro.setId(UUID.randomUUID());
        carro.setModelo("Modelo A");

        when(carroRepository.save(any(Carro.class))).thenReturn(carro);

        Carro savedCarro = carroService.saveCarro(carro);

        assertNotNull(savedCarro.getId());
        assertEquals("Modelo A", savedCarro.getModelo());
        verify(carroRepository, times(1)).save(carro);
    }
}
