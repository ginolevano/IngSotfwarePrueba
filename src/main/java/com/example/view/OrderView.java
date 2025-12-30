package com.example.view;

import javax.swing.*;
import com.example.model.Order;
import com.example.model.ExchangeRateService;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class OrderView extends JFrame {
    
    // Search components
    private JTextField searchField = new JTextField(15);
    private JButton searchButton = new JButton("Search");
    
    // List of order IDs
    private JList<String> orderIdsList;
    private DefaultListModel<String> orderIdsListModel;
    
    // Results area
    private JTextArea resultArea = new JTextArea(15, 50);
    
    // Action buttons
    private JButton createOrderButton = new JButton("Create Order");
    private JButton deleteOrderButton = new JButton("Delete Order");
    private JButton editOrderButton = new JButton("Edit Order");
    
    // Currently selected order ID
    private String selectedOrderId = null;
    
    // Services
    private ExchangeRateService exchangeRateService;

    public OrderView(List<Order> orders, com.example.service.OrderService orderService) {
        setTitle("Order Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
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
        
        // Panel superior: búsqueda
        JPanel searchPanel = createSearchPanel();
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        
        // Panel central: lista de IDs + detalles
        JPanel centerPanel = new JPanel(new BorderLayout());
        
        // Panel izquierdo: lista de IDs de pedidos
        JPanel leftPanel = createOrderIdListPanel(orders);
        leftPanel.setPreferredSize(new Dimension(300, 500));
        centerPanel.add(leftPanel, BorderLayout.WEST);
        
        // Panel derecho: detalles del pedido
        JPanel rightPanel = createDetailsPanel();
        centerPanel.add(rightPanel, BorderLayout.CENTER);
        
        getContentPane().add(centerPanel, BorderLayout.CENTER);
        
        // Panel inferior: botones de acciones
        JPanel actionPanel = createActionPanel();
        getContentPane().add(actionPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.add(new JLabel("Search Order ID:"));
        panel.add(searchField);
        panel.add(searchButton);
        return panel;
    }
    
    private JPanel createOrderIdListPanel(List<Order> orders) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Available Orders"));
        
        // Modelo de la lista
        orderIdsListModel = new DefaultListModel<>();
        System.out.println("✓ Inicializando lista con " + orders.size() + " órdenes");
        for (Order order : orders) {
            orderIdsListModel.addElement(order.getIdPedido());
        }
        System.out.println("✓ Lista inicializada. Tamaño: " + orderIdsListModel.getSize());
        
        // Lista
        orderIdsList = new JList<>(orderIdsListModel);
        orderIdsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderIdsList.setFont(new Font("Arial", Font.PLAIN, 12));
        orderIdsList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedOrderId = orderIdsList.getSelectedValue();
                System.out.println("✓ Seleccionado: " + selectedOrderId);
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(orderIdsList);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createDetailsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Order Details"));
        
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font("Courier", Font.PLAIN, 11));
        
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createActionPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.add(createOrderButton);
        panel.add(deleteOrderButton);
        panel.add(editOrderButton);
        return panel;
    }

    public String getSearchId() {
        return searchField.getText();
    }

    public JButton getSearchButton() {
        return searchButton;
    }
    
    public JButton getCreateOrderButton() {
        return createOrderButton;
    }
    
    public JButton getDeleteOrderButton() {
        return deleteOrderButton;
    }
    
    public JButton getEditOrderButton() {
        return editOrderButton;
    }
    
    public String getSelectedOrderId() {
        return selectedOrderId;
    }
    
    public void refreshOrderIdsList(List<Order> orders) {
        System.out.println("✓ Actualizando lista con " + orders.size() + " órdenes");
        orderIdsListModel.clear();
        for (Order order : orders) {
            orderIdsListModel.addElement(order.getIdPedido());
        }
        System.out.println("✓ Lista actualizada. Tamaño: " + orderIdsListModel.getSize());
    }

    public void displayOrder(Order order) {
        if (order != null) {
            double totalBruto = order.getGrossTotal();
            double totalDescuento = order.getDiscountedTotal();
            
            StringBuilder sb = new StringBuilder();
            sb.append("╔══════════════════════════════════════════════════╗\n");
            sb.append("║           ORDER DETAILS - ").append(order.getIdPedido()).append("\n");
            sb.append("╚══════════════════════════════════════════════════╝\n\n");
            
            // Mostrar cada artículo con índice
            sb.append("ITEMS:\n");
            sb.append("──────────────────────────────────────────────────\n");
            for (int i = 0; i < order.getListaArticulos().size(); i++) {
                var art = order.getListaArticulos().get(i);
                sb.append(String.format("[%d] %-20s x%d => %8.2f€ (-%2.0f%%) = %8.2f€\n", 
                    i, 
                    art.getNombre(), 
                    art.getCantidad(),
                    art.getPrecio(),
                    art.getDescuento(),
                    art.getDiscountedAmount()));
            }
            
            // Obtener tipo de cambio EUR/USD
            double exchangeRate = 1.0;
            try {
                exchangeRate = exchangeRateService.getEURtoUSDRate();
            } catch (Exception e) {
                exchangeRate = 1.1;
            }
            
            // Mostrar totales en EUR y USD
            sb.append("──────────────────────────────────────────────────\n");
            sb.append("TOTALS:\n");
            sb.append("──────────────────────────────────────────────────\n");
            sb.append(String.format("Gross Total:       %8.2f€  |  %10.2f$\n", 
                totalBruto, totalBruto * exchangeRate));
            sb.append(String.format("Discounted Total:  %8.2f€  |  %10.2f$\n", 
                totalDescuento, totalDescuento * exchangeRate));
            sb.append("──────────────────────────────────────────────────\n");
            sb.append(String.format("Exchange Rate: 1€ = %.4f$\n", exchangeRate));
            
            resultArea.setText(sb.toString());
        } else {
            resultArea.setText("No order selected. Please select an order from the list or search for one.");
        }
    }
}

