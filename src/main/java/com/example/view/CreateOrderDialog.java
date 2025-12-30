package com.example.view;

import javax.swing.*;
import com.example.model.Article;
import com.example.model.Order;
import com.example.service.OrderService;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderDialog extends JDialog {
    
    private JTextField orderIdField;
    private JPanel articlesPanel;
    private JButton addArticleButton;
    private JButton confirmButton;
    private JButton cancelButton;
    
    private List<ArticlePanel> articlePanels = new ArrayList<>();
    private Order createdOrder = null;
    private List<Order> orders;
    private OrderService orderService;
    private boolean confirmed = false;
    
    public CreateOrderDialog(JFrame parent, List<Order> orders, OrderService orderService) {
        super(parent, "Create New Order", true);
        this.orders = orders;
        this.orderService = orderService;
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 500);
        setLocationRelativeTo(parent);
        
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel superior: ID del pedido
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        idPanel.add(new JLabel("Order ID:"));
        orderIdField = new JTextField(10);
        idPanel.add(orderIdField);
        mainPanel.add(idPanel, BorderLayout.NORTH);
        
        // Panel central: artículos
        JPanel articlesContainerPanel = new JPanel(new BorderLayout());
        articlesContainerPanel.setBorder(BorderFactory.createTitledBorder("Articles"));
        
        articlesPanel = new JPanel();
        articlesPanel.setLayout(new BoxLayout(articlesPanel, BoxLayout.Y_AXIS));
        
        // Agregar primer artículo
        addArticlePanel();
        
        JScrollPane scrollPane = new JScrollPane(articlesPanel);
        articlesContainerPanel.add(scrollPane, BorderLayout.CENTER);
        
        addArticleButton = new JButton("Add Article");
        addArticleButton.addActionListener(e -> addArticlePanel());
        articlesContainerPanel.add(addArticleButton, BorderLayout.SOUTH);
        
        mainPanel.add(articlesContainerPanel, BorderLayout.CENTER);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Panel inferior: botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        confirmButton = new JButton("Create");
        confirmButton.addActionListener(e -> createOrder());
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private void addArticlePanel() {
        ArticlePanel articlePanel = new ArticlePanel(articlePanels.size() + 1);
        articlePanels.add(articlePanel);
        articlesPanel.add(articlePanel);
        articlesPanel.revalidate();
        articlesPanel.repaint();
    }
    
    private void createOrder() {
        String orderId = orderIdField.getText().trim();
        
        // Validar que el ID no esté vacío
        if (orderId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Order ID cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validar que el ID sea único
        if (!orderService.isUniqueOrderId(orders, orderId)) {
            JOptionPane.showMessageDialog(this, "Order ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Recopilar artículos
        ArrayList<Article> articles = new ArrayList<>();
        for (ArticlePanel panel : articlePanels) {
            Article article = panel.getArticle();
            if (article != null) {
                articles.add(article);
            }
        }
        
        // Validar que haya al menos un artículo
        if (articles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "At least one article is required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Crear el pedido
        createdOrder = new Order(orderId, articles);
        confirmed = true;
        dispose();
    }
    
    public Order getOrder() {
        return createdOrder;
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    // Clase interna para gestionar un artículo en el formulario
    private static class ArticlePanel extends JPanel {
        private JTextField nameField;
        private JTextField quantityField;
        private JTextField priceField;
        private JTextField discountField;
        
        public ArticlePanel(int index) {
            setLayout(new GridLayout(1, 4, 5, 0));
            setBorder(BorderFactory.createTitledBorder("Article " + index));
            
            nameField = new JTextField("Item " + index, 15);
            quantityField = new JTextField("1", 5);
            priceField = new JTextField("0.0", 5);
            discountField = new JTextField("0", 5);
            
            add(new JPanel() {{
                add(new JLabel("Name:"));
                add(nameField);
            }});
            add(new JPanel() {{
                add(new JLabel("Qty:"));
                add(quantityField);
            }});
            add(new JPanel() {{
                add(new JLabel("Price:"));
                add(priceField);
            }});
            add(new JPanel() {{
                add(new JLabel("Discount %:"));
                add(discountField);
            }});
        }
        
        public Article getArticle() {
            try {
                String name = nameField.getText().trim();
                int quantity = Integer.parseInt(quantityField.getText().trim());
                double price = Double.parseDouble(priceField.getText().trim());
                double discount = Double.parseDouble(discountField.getText().trim());
                
                if (name.isEmpty() || quantity <= 0 || price < 0 || discount < 0) {
                    return null;
                }
                
                return new Article(name, quantity, price, discount);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }
}
