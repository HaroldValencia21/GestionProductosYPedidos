package com.productosypedidos.gestion_productos_pedidos.service;

import com.productosypedidos.gestion_productos_pedidos.model.Producto;
import com.productosypedidos.gestion_productos_pedidos.utils.JsonUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class ProductoService {
    private static final String FILE_PATH = "src/main/resources/data/productos.json";
    private List<Producto> productos;
    private JsonUtil jsonUtil;

    // Constructor para uso normal con Spring
    public ProductoService() {
        this(new JsonUtil());
    }

    // Constructor para inyección en pruebas
    public ProductoService(JsonUtil jsonUtil) {
        this.jsonUtil = jsonUtil;
        this.productos = this.jsonUtil.cargarProductosDesdeJson(FILE_PATH);
        if (this.productos == null) {
            this.productos = new ArrayList<>();
        }
    }

    public List<Producto> obtenerProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        this.jsonUtil.guardarProductosEnJson(FILE_PATH, productos);
    }
}