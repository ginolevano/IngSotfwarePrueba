package com.example.controller;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.Order;
import com.example.service.OrderService;
import com.example.view.OrderView;

public class Main {
    
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
    
        
        OrderService orderService = new OrderService();
        List<Order> orders = orderService.loadOrders();

        if(orders.isEmpty()){
            log.warn("No se cargaron pedidos o la lista está vacía");
        } else {
            log.info("Pedidos cargados:");
            for(Order o : orders) {
                log.info("  - {}", o.getIdPedido());
            }
        }

        log.info("Total pedidos cargados: {}", orders.size());
        
        // Convertir a ArrayList mutable
        List<Order> ordersList = new ArrayList<>(orders);
        
        //Inicializamos en la clase Main.java el MVC
        SwingUtilities.invokeLater(() -> {
            OrderView view = new OrderView(ordersList, orderService);
            new OrderController(view, ordersList, orderService);
        });

    }
    
}