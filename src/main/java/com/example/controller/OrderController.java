package com.example.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.Order;
import com.example.service.OrderService;
import com.example.view.OrderView;
import com.example.view.CreateOrderDialog;
import com.example.view.EditOrderDialog;

public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    //Atributo de clase OrderView
    private OrderView view;
    //Lista de orders
    private List<Order> orders;
    //Atributo de clase OrderService
    private OrderService orderService;

    public OrderController(OrderView view, List<Order> orders, OrderService orderService) {
        //Initialize attributes
        this.view = view;
        this.orders = orders;
        this.orderService = orderService;
        
        //Adding action listener to the search button, call searchOrder on click
        this.view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchOrder();
            }
        });
        
        //Adding action listener to the create button
        this.view.getCreateOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createOrder();
            }
        });
        
        //Adding action listener to the delete button
        this.view.getDeleteOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteOrder();
            }
        });
        
        //Adding action listener to the edit button
        this.view.getEditOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editOrder();
            }
        });
    }

    private void searchOrder() {
        String id = view.getSearchId();

        // display order if found, else display "Order not found."
        if (id == null || id.trim().isEmpty()) {
            log.warn("Empty ID provided");
            view.displayOrder(null);
            return;
        }
        // Buscar pedido por idPedido (String)
        Order found = null;
        for (Order o : orders) {
            if (o != null && id.equals(o.getIdPedido())) {
                found = o;
                break;
            }
        }

        if (found != null) {
            view.displayOrder(found);
            log.info("Order found: {}", found.getIdPedido());
        } else {
            view.displayOrder(null);
            log.warn("Order not found with ID: {}", id);
        }
    }
    
    private void createOrder() {
        log.info("Abriendo diálogo para crear nuevo pedido");
        CreateOrderDialog dialog = new CreateOrderDialog(view, orders, orderService);
        
        // Si el usuario confirmó la creación
        Order newOrder = dialog.getOrder();
        if (newOrder != null) {
            // Agregar a la lista en memoria
            orders.add(newOrder);
            
            // Guardar en JSON
            if (orderService.saveOrders(orders)) {
                log.info("Pedido creado exitosamente: {}", newOrder.getIdPedido());
                // Actualizar la lista en la vista
                view.refreshOrderIdsList(orders);
                view.displayOrder(newOrder);
            } else {
                log.error("Error al guardar el pedido en JSON");
                view.displayOrder(null);
                orders.remove(newOrder); // Revertir cambio
            }
        }
    }
    
    private void deleteOrder() {
        String idToDelete = view.getSelectedOrderId();
        
        if (idToDelete == null || idToDelete.trim().isEmpty()) {
            log.warn("No se seleccionó un pedido para borrar");
            view.displayOrder(null);
            return;
        }
        
        // Buscar y eliminar el pedido
        Order orderToDelete = orders.stream()
            .filter(o -> o.getIdPedido().equals(idToDelete))
            .findFirst()
            .orElse(null);
        
        if (orderToDelete != null) {
            orders.remove(orderToDelete);
            
            if (orderService.saveOrders(orders)) {
                log.info("Pedido eliminado: {}", idToDelete);
                view.refreshOrderIdsList(orders);
                view.displayOrder(null);
            } else {
                log.error("Error al guardar cambios en JSON");
                orders.add(orderToDelete); // Revertir cambio
                view.displayOrder(orderToDelete);
            }
        } else {
            log.warn("Pedido no encontrado: {}", idToDelete);
            view.displayOrder(null);
        }
    }
    
    private void editOrder() {
        String idToEdit = view.getSelectedOrderId();
        
        if (idToEdit == null || idToEdit.trim().isEmpty()) {
            log.warn("No se seleccionó un pedido para editar");
            view.displayOrder(null);
            return;
        }
        
        // Buscar el pedido
        Order orderToEdit = orders.stream()
            .filter(o -> o.getIdPedido().equals(idToEdit))
            .findFirst()
            .orElse(null);
        
        if (orderToEdit != null) {
            log.info("Abriendo diálogo para editar pedido: {}", idToEdit);
            EditOrderDialog dialog = new EditOrderDialog(view, orderToEdit);
            
            // Si el usuario confirmó la edición
            if (dialog.isConfirmed()) {
                if (orderService.saveOrders(orders)) {
                    log.info("Pedido actualizado: {}", idToEdit);
                    view.refreshOrderIdsList(orders);
                    view.displayOrder(orderToEdit);
                } else {
                    log.error("Error al guardar cambios en JSON");
                }
            }
        } else {
            log.warn("Pedido no encontrado: {}", idToEdit);
            view.displayOrder(null);
        }
    }
}
