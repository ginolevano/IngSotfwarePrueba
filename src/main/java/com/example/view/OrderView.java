package com.example.view;

import javax.swing.*;
import com.example.model.Order;
import com.example.model.ExchangeRateService;
import java.awt.*;
import java.net.URL;

public class OrderView extends JFrame {
    private JTextField searchField = new JTextField(10);
    private JButton searchButton = new JButton("Search");
    private JTextArea resultArea = new JTextArea(10, 40);
    private ExchangeRateService exchangeRateService;

    public OrderView() {
        setTitle("Order Management");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        // Inicializar el servicio de tipo de cambio
        this.exchangeRateService = new ExchangeRateService();
        
        // Cargar y establecer el icono de la aplicación
        try {
            URL iconURL = getClass().getResource("/images/app.png");
            if (iconURL != null) {
                ImageIcon icon = new ImageIcon(iconURL);
                setIconImage(icon.getImage());
            }
        } catch (Exception e) {
            System.err.println("Error al cargar el icono: " + e.getMessage());
        }

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
        //Display order details in resultArea with EUR and USD conversion
        if (order != null) {
            double totalBruto = order.getGrossTotal();
            double totalDescuento = order.getDiscountedTotal();
            
            StringBuilder sb = new StringBuilder();
            sb.append("╔══════════════════════════════════════╗\n");
            sb.append("║      ORDER DETAILS - ").append(order.getIdPedido()).append("\n");
            sb.append("╚══════════════════════════════════════╝\n\n");
            
            // Mostrar cada artículo
            sb.append("ITEMS:\n");
            sb.append("────────────────────────────────────\n");
            for (int i = 0; i < order.getListaArticulos().size(); i++) {
                var art = order.getListaArticulos().get(i);
                sb.append(art.getNombre())
                  .append(" x").append(art.getCantidad())
                  .append(" => ").append(String.format("%.2f", art.getPrecio())).append("€ ")
                  .append("(").append(art.getDescuento()).append("% off) = ")
                  .append(String.format("%.2f", art.getDiscountedAmount())).append("€\n");
            }
            
            // Obtener tipo de cambio EUR/USD
            double exchangeRate = rate;
            try {
                exchangeRate = exchangeRateService.getEURtoUSDRate();
                System.out.println("Tipo de cambio obtenido: 1 EUR = " + exchangeRate + " USD");
            } catch (Exception e) {
                System.err.println("⚠ No se pudo obtener el tipo de cambio. Usando valor por defecto: " + rate);
                exchangeRate = rate;
            }
            
            // Mostrar totales en EUR y USD
            sb.append("\n────────────────────────────────────\n");
            sb.append("TOTALS:\n");
            sb.append("────────────────────────────────────\n");
            sb.append(String.format("Gross Total =>       %.2f€  |  %.2f$\n", 
                totalBruto, totalBruto * exchangeRate));
            sb.append(String.format("Discounted Total =>  %.2f€  |  %.2f$\n", 
                totalDescuento, totalDescuento * exchangeRate));
            sb.append("────────────────────────────────────\n");
            sb.append(String.format("Exchange Rate: 1€ = %.4f$\n", exchangeRate));
            
            resultArea.setText(sb.toString());
        } else {
            resultArea.setText("Order not found, Try again!!");
        }
    }
}
