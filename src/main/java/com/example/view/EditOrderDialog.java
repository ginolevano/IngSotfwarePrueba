package com.example.view;

import javax.swing.*;
import com.example.model.Article;
import com.example.model.Order;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EditOrderDialog extends JDialog {
    
    private Order order;
    private List<EditArticlePanel> articlePanels = new ArrayList<>();
    private JButton confirmButton;
    private JButton cancelButton;
    private boolean confirmed = false;
    
    public EditOrderDialog(JFrame parent, Order order) {
        super(parent, "Edit Order - " + order.getIdPedido(), true);
        this.order = order;
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(700, 500);
        setLocationRelativeTo(parent);
        
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Label con info del pedido
        JLabel titleLabel = new JLabel("Editing Order: " + order.getIdPedido());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Panel central: artículos
        JPanel articlesContainerPanel = new JPanel(new BorderLayout());
        articlesContainerPanel.setBorder(BorderFactory.createTitledBorder("Edit Articles (Quantity and Discount only)"));
        
        JPanel articlesPanel = new JPanel();
        articlesPanel.setLayout(new BoxLayout(articlesPanel, BoxLayout.Y_AXIS));
        
        // Agregar paneles para cada artículo
        for (int i = 0; i < order.getListaArticulos().size(); i++) {
            Article article = order.getListaArticulos().get(i);
            EditArticlePanel editPanel = new EditArticlePanel(i, article);
            articlePanels.add(editPanel);
            articlesPanel.add(editPanel);
            articlesPanel.add(Box.createVerticalStrut(5));
        }
        
        JScrollPane scrollPane = new JScrollPane(articlesPanel);
        articlesContainerPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainPanel.add(articlesContainerPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
        
        // Panel inferior: botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        confirmButton = new JButton("Save Changes");
        confirmButton.addActionListener(e -> saveChanges());
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private void saveChanges() {
        try {
            // Actualizar artículos con los nuevos valores
            for (int i = 0; i < articlePanels.size(); i++) {
                EditArticlePanel panel = articlePanels.get(i);
                Article article = order.getListaArticulos().get(i);
                
                int newQuantity = panel.getQuantity();
                double newDiscount = panel.getDiscount();
                
                if (newQuantity <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantity must be greater than 0!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (newDiscount < 0 || newDiscount > 100) {
                    JOptionPane.showMessageDialog(this, "Discount must be between 0 and 100!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                article.setCantidad(newQuantity);
                article.setDescuento(newDiscount);
            }
            
            confirmed = true;
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input values!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
    
    // Clase interna para gestionar la edición de un artículo
    private static class EditArticlePanel extends JPanel {
        private JLabel nameLabel;
        private JLabel priceLabel;
        private JTextField quantityField;
        private JTextField discountField;
        
        public EditArticlePanel(int index, Article article) {
            setLayout(new GridBagLayout());
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            
            // Nombre del artículo (solo lectura)
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(new JLabel("Name:"), gbc);
            
            gbc.gridx = 1;
            nameLabel = new JLabel(article.getNombre());
            add(nameLabel, gbc);
            
            // Precio unitario (solo lectura)
            gbc.gridx = 2;
            add(new JLabel("Unit Price:"), gbc);
            
            gbc.gridx = 3;
            priceLabel = new JLabel(String.format("%.2f€", article.getPrecio()));
            add(priceLabel, gbc);
            
            // Cantidad (editable)
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(new JLabel("Quantity:"), gbc);
            
            gbc.gridx = 1;
            quantityField = new JTextField(String.valueOf(article.getCantidad()), 5);
            add(quantityField, gbc);
            
            // Descuento (editable)
            gbc.gridx = 2;
            add(new JLabel("Discount %:"), gbc);
            
            gbc.gridx = 3;
            discountField = new JTextField(String.format("%.0f", article.getDescuento()), 5);
            add(discountField, gbc);
        }
        
        public int getQuantity() throws NumberFormatException {
            return Integer.parseInt(quantityField.getText().trim());
        }
        
        public double getDiscount() throws NumberFormatException {
            return Double.parseDouble(discountField.getText().trim());
        }
    }
}
