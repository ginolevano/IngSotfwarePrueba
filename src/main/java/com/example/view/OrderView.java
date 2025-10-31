package com.example.view;

import javax.swing.*;
import com.example.model.Order;
import java.awt.*;

public class OrderView extends JFrame {
    private JTextField searchField = new JTextField(10);
    private JButton searchButton = new JButton("Search");
    private JTextArea resultArea = new JTextArea(10, 40);

    public OrderView() {
        setTitle("Order Management");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        //add new JLabel("Order ID:")
        add(new JLabel("Order ID:"));
        //add to view the search field
        add(searchField);
        // add to view the search button
        add(searchButton);
        // add to view the result area inside a JScrollPane
        add(new JScrollPane(resultArea));

        pack();
        setVisible(true);
    }

    public String getSearchId() {
        //...
        return searchField.getText();
    }

    public JButton getSearchButton() {
        //...
        return searchButton;
    }

    public void displayOrder(Order order, double rate) {
        //Display order details in resultArea
         if (order != null) {
        double totalBruto = order.getGrossTotal();
        double totalDescuento = order.getDiscountedTotal();
        double totalConvertido = totalDescuento * rate;

        StringBuilder sb = new StringBuilder();
        sb.append("Order ").append(order.getIdPedido()).append(":\n");

        //Mostramos cada artículo usando getters
        for (int i = 0; i < order.getListaArticulos().size(); i++) {
            var art = order.getListaArticulos().get(i);
            sb.append(art.getNombre()) //  getNombre 
              .append(" x").append(art.getCantidad()) // getCantidad
              .append(" => ").append(art.getPrecio()).append("$ ") // getPrecio
              .append("(discount ").append(art.getDescuento()).append("%) = ") //getDescuento
              .append(art.getDiscountedAmount()).append("$\n");
        }

        sb.append("\nGross total => ").append(totalBruto).append("$");
        sb.append("\nDiscounted total => ").append(totalDescuento).append("$");
        sb.append("\nGross total (USD) => ").append(totalBruto * rate).append("$");
        sb.append("\nDiscounted total (USD) => ").append(totalConvertido).append("$");

        resultArea.setText(sb.toString());
    } else {
        resultArea.setText("Order not found, Try again!!");
    }
    }
}
