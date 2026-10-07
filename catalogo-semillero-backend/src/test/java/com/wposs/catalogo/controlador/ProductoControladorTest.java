package com.wposs.catalogo.controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wposs.catalogo.dto.ProductoRequestDTO;
import com.wposs.catalogo.dto.ProductoResponseDTO;
import com.wposs.catalogo.excepcion.RecursoNoEncontradoException;
import com.wposs.catalogo.servicio.ProductoServicio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoControlador.class)
@SuppressWarnings("null")
public class ProductoControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoServicio productoServicio;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeDevolver400CuandoNombreEstaVacio() throws Exception {
        ProductoRequestDTO dtoInvalido = new ProductoRequestDTO("", "Desc", new BigDecimal("100"), 10, "Cat");

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoInvalido)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(org.hamcrest.Matchers.containsString("El nombre no puede estar vacío")));
    }

    @Test
    void debeDevolver400CuandoPrecioEsNegativo() throws Exception {
        ProductoRequestDTO dtoInvalido = new ProductoRequestDTO("Laptop", "Desc", new BigDecimal("-5"), 10, "Cat");

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoInvalido)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(org.hamcrest.Matchers.containsString("El precio debe ser mayor a 0")));
    }

    @Test
    void debeCrearProductoExitosamente() throws Exception {
        ProductoRequestDTO dtoValido = new ProductoRequestDTO("Laptop", "Desc", new BigDecimal("1500"), 10, "Cat");
        ProductoResponseDTO responseDTO = new ProductoResponseDTO(1L, "Laptop", "Desc", new BigDecimal("1500"), 10, "Cat");

        Mockito.when(productoServicio.crear(any(ProductoRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoValido)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Laptop"));
    }

    @Test
    void debeDevolver404CuandoProductoNoExiste() throws Exception {
        Mockito.when(productoServicio.buscarPorId(99L)).thenThrow(new RecursoNoEncontradoException("No encontrado"));

        mockMvc.perform(get("/api/productos/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("No encontrado"));
    }

    @Test
    void debeActualizarProductoExitosamente() throws Exception {
        ProductoRequestDTO dtoValido = new ProductoRequestDTO("Laptop", "Desc", new BigDecimal("1500"), 10, "Cat");
        ProductoResponseDTO responseDTO = new ProductoResponseDTO(1L, "Laptop", "Desc", new BigDecimal("1500"), 10, "Cat");

        Mockito.when(productoServicio.actualizar(eq(1L), any(ProductoRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/api/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoValido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Laptop"));
    }
}
