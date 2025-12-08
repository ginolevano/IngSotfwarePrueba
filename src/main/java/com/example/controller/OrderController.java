package com.example.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


        // display order if found, else display "Order not found."
               if (id == null || id.trim().isEmpty()) {
            log.warn("Empty ID provided");
            view.displayOrder(null, 0);
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
            double rate = 1.0; // ejemplo de tipo de cambio
            view.displayOrder(found, rate);
            log.info("Order found: {}", found.getIdPedido());
        } else {
            view.displayOrder(null, 0);
            log.warn("Order not found with ID: {}", id);
        }

    }
}
