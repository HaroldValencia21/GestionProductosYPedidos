package com.productosypedidos.gestion_productos_pedidos.service;

import com.productosypedidos.gestion_productos_pedidos.model.Producto;
import com.productosypedidos.gestion_productos_pedidos.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import com.productosypedidos.gestion_productos_pedidos.service.ProductoService;

public class ProductoServiceTest {

    private ProductoService productoService;
    private JsonUtil jsonUtilMock;

    @BeforeEach
    void setUp() {
        // Crear un mock de JsonUtil
        jsonUtilMock = mock(JsonUtil.class);

        // Simular la carga de productos desde JSON
        List<Producto> productosSimulados = new ArrayList<>();
        productosSimulados.add(new Producto(1,"Producto1", 100.0));
        productosSimulados.add(new Producto(2,"Producto2", 200.0));

        // Configurar el comportamiento del mock
        Mockito.when(jsonUtilMock.cargarProductosDesdeJson(Mockito.anyString())).thenReturn(productosSimulados);

        // Crear el servicio con el mock inyectado
        productoService = new ProductoService(jsonUtilMock);
    }

    @Test
    void testObtenerProductos() {
        // Ejecutar el método
        List<Producto> productos = productoService.obtenerProductos();

        // Verificar que los productos devueltos sean correctos
        assertEquals(2, productos.size(), "Se deben devolver 2 productos.");
        assertEquals("Producto1", productos.get(0).getNombre(), "El primer producto debe ser 'Producto1'.");
    }

    @Test
    void testAgregarProducto() {
        // Crear un producto a agregar
        Producto nuevoProducto = new Producto(3,"Producto3", 300.0);

        // Ejecutar el método
        productoService.agregarProducto(nuevoProducto);

        // Verificar que el método guardarProductosEnJson fue llamado
        verify(jsonUtilMock).guardarProductosEnJson(Mockito.anyString(), Mockito.anyList());

        // Verificar que el producto fue agregado a la lista en memoria
        List<Producto> productos = productoService.obtenerProductos();
        assertEquals(3, productos.size(), "Debe haber 3 productos después de agregar uno nuevo.");
    }
}