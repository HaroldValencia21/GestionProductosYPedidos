package com.productosypedidos.gestion_productos_pedidos.repository;

import com.productosypedidos.gestion_productos_pedidos.model.Producto;
import java.util.List;

public interface ProductoRepository {
    List<Producto> obtenerTodos();
    void guardarProductos(List<Producto> productos);
}
