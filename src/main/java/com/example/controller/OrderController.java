package com.example.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.Main;
import com.example.model.Order;
import com.example.model.Searcher;
import com.example.view.OrderView;

public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    //Atributo de clase OrderView
    private OrderView view;
    //Lista de orders
    private List<Order> orders;
    //Atributo de clase Searcher
    private Searcher searcher;

    public OrderController(OrderView view, List<Order> orders) {
        //Initialize attributes
        this.view = view;
        this.orders =orders;
        this.searcher = new Searcher();
        //Adding action listener to the search button, call searchOrder on click
        //...
        this.view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchOrder();
            }
        });
    }

    private void searchOrder() {
        String id = view.getSearchId();

        //... display order if found, else display "Order not found."
        try {
            int orderId = Integer.parseInt(id);

            // Buscar pedido
            Order found = searcher.findOrderById(orders, orderId);

            if (found != null) {
// Por ejemplo, una tasa de cambio 
                double rate = 1.0;
                view.displayOrder(found, rate);
                log.info("Order found: {}", found.getIdOrder());
            } else {
                view.displayOrder(null, 0);
                log.warn("Order not found with ID: {}", orderId);
            }
        } catch (NumberFormatException ex) {
            log.error("Invalid ID format: {}", id);
            view.displayOrder(null, 0);
        }
    }
}
