package com.example.service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderService {
    
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private ObjectMapper mapper = new ObjectMapper();
    private static final String JSON_FILE_NAME = "orders.json";
    
    public OrderService() {
    }
    
    /**
     * Obtiene la ruta del archivo orders.json en la raíz del proyecto
     */
    private Path getOrdersFilePath() {
        return Paths.get(JSON_FILE_NAME).toAbsolutePath();
    }
    
    /**
     * Carga la lista de pedidos desde el archivo JSON en la raíz
     * @return Lista de pedidos o lista vacía si hay error
     */
    public List<Order> loadOrders() {
        try {
            Path ordersFile = getOrdersFilePath();
            if (Files.exists(ordersFile)) {
                String content = new String(Files.readAllBytes(ordersFile));
                List<Order> orders = mapper.readValue(content, new TypeReference<List<Order>>() {});
                log.info("✓ Se cargaron {} pedidos desde: {}", orders.size(), ordersFile);
                return orders;
            }
            
            log.warn("❌ No se encontró orders.json en la raíz");
            return new ArrayList<>();
        } catch (Exception e) {
            log.error("❌ Error al leer el JSON: {}", e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    /**
     * Guarda la lista de pedidos en el archivo JSON (raíz del proyecto)
     * @param orders Lista de pedidos a guardar
     * @return true si se guardó correctamente, false en caso contrario
     */
    public boolean saveOrders(List<Order> orders) {
        try {
            Path ordersFile = getOrdersFilePath();
            String jsonContent = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orders);
            Files.write(ordersFile, jsonContent.getBytes(StandardCharsets.UTF_8));
            
            log.info("✓ Se guardaron {} pedidos en: {}", orders.size(), ordersFile);
            return true;
        } catch (Exception e) {
            log.error("✗ Error al guardar los pedidos: {}", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Valida que el ID del pedido sea único
     * @param orders Lista actual de pedidos
     * @param idPedido ID a validar
     * @return true si el ID es único, false si ya existe
     */
    public boolean isUniqueOrderId(List<Order> orders, String idPedido) {
        return orders.stream().noneMatch(o -> o.getIdPedido().equals(idPedido));
    }
}
