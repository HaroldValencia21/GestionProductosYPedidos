package com.productosypedidos.gestion_productos_pedidos.utils;

import com.productosypedidos.gestion_productos_pedidos.model.Producto;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {

    private ObjectMapper objectMapper;

    public JsonUtil() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Producto> cargarProductosDesdeJson(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Producto>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void guardarProductosEnJson(String filePath, List<Producto> productos) {
        try {
            File file = new File(filePath);
            objectMapper.writeValue(file, productos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}