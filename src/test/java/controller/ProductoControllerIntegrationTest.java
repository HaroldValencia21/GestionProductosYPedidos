package com.productosypedidos.gestion_productos_pedidos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productosypedidos.gestion_productos_pedidos.model.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testObtenerProductos() throws Exception {
        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").exists());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testAgregarProducto() throws Exception {
        // Crear un nuevo producto
        Producto nuevoProducto = new Producto(99, "Producto de Prueba", 150.0);

        // Convertir el producto a JSON
        String productoJson = objectMapper.writeValueAsString(nuevoProducto);

        // Realizar la solicitud POST y verificar la respuesta
        // Añadir .with(csrf()) para manejar el token CSRF
        mockMvc.perform(post("/api/productos")
                        .with(csrf()) // Añade el token CSRF
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productoJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(99))
                .andExpect(jsonPath("$.nombre").value("Producto de Prueba"))
                .andExpect(jsonPath("$.precio").value(150.0));
    }
}