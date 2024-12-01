package com.example.proyecto_sgp;

import com.example.proyecto_sgp.Prestamos_service.entity.Prestamo;
import com.example.proyecto_sgp.Prestamos_service.service.PrestamoService;
import com.example.proyecto_sgp.Prestamos_service.controller.PrestamoController;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PrestamoController.class)
class PrestamoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PrestamoService prestamoService;

    @InjectMocks
    private PrestamoController prestamoController;

    @Test
    void testObtenerTodos() throws Exception {
        List<Prestamo> prestamos = new ArrayList<>();
        when(prestamoService.obtenerTodos()).thenReturn(prestamos);

        mockMvc.perform(get("/api/prestamos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}