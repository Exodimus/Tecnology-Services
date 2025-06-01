package com.tecnologyservices.inventory.view.Dialogs;

import com.tecnologyservices.inventory.util.CodeGenerator;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ProductFormDialog extends JDialog {
    // Campos del formulario
    private JTextField idField;
    private JTextField codeField;
    private JTextField nameField;
    private JTextField brandField;
    private JTextField typeField;
    private JTextField colorField;
    private JTextField capacityField;
    private JTextField sizeField;
    private JTextField purchasePriceField;
    private JTextField gainPercentageField;
    private JTextField salePriceField;
    private JTextField stockField;
    private JButton saveButton;
    private JComboBox<String> categoryComboBox;

    private static final double IVA_PERCENTAGE = 13.0;

    // Categorías predefinidas (ejemplo)
    private static final String[] PREDEFINED_CATEGORIES = {
            "Electrónicos",
            "Ropa",
            "Alimentos",
            "Hogar",
            "Juguetes",
            "Oficina",
            "Deportes"
    };

    public ProductFormDialog(JFrame parent) {
        super(parent, "Formulario de Producto", true);
        setSize(500, 600);
        setLocationRelativeTo(parent);
        setResizable(false);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(14, 2, 10, 5)); // Reducido a 14 filas
        mainPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Inicializar componentes
        initializeFields();
        addFormFields(mainPanel);
        setupSaveButton();
        setupEventListeners();

        // Añadir componentes
        mainPanel.add(new JLabel());
        mainPanel.add(createButtonPanel());
        add(mainPanel);
    }

    private void initializeFields() {
        idField = createTextField(false);
        codeField = createTextField(false);
        codeField.setBackground(new Color(240, 240, 240));
        nameField = createTextField(true);
        brandField = createTextField(true);
        typeField = createTextField(true);
        colorField = createTextField(true);
        capacityField = createTextField(true);
        sizeField = createTextField(true);
        purchasePriceField = createTextField(true);
        gainPercentageField = createTextField(true);
        salePriceField = createTextField(false);
        salePriceField.setBackground(new Color(240, 240, 240));
        stockField = createTextField(true);

        // Configurar ComboBox de categorías
        categoryComboBox = new JComboBox<>(PREDEFINED_CATEGORIES);
        categoryComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        categoryComboBox.setBackground(Color.WHITE);
    }

    private void addFormFields(JPanel panel) {
        panel.add(createLabel("ID:")); panel.add(idField);
        panel.add(createLabel("Categoría*:")); panel.add(categoryComboBox);
        panel.add(createLabel("Código:")); panel.add(codeField);
        panel.add(createLabel("Nombre*:")); panel.add(nameField);
        panel.add(createLabel("Marca*:")); panel.add(brandField);
        panel.add(createLabel("Tipo*:")); panel.add(typeField);
        panel.add(createLabel("Tamaño:")); panel.add(sizeField);
        panel.add(createLabel("Color:")); panel.add(colorField);
        panel.add(createLabel("Capacidad:")); panel.add(capacityField);
        panel.add(createLabel("Precio Compra*:")); panel.add(purchasePriceField);
        panel.add(createLabel("% Ganancia*:")); panel.add(gainPercentageField);
        panel.add(createLabel("Precio Venta (con IVA 13%):")); panel.add(salePriceField);
        panel.add(createLabel("Stock*:")); panel.add(stockField);
    }

    private JTextField createTextField(boolean editable) {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        field.setEditable(editable);
        return field;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        return label;
    }

    private void setupSaveButton() {
        saveButton = new JButton("Guardar Producto");
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveButton.setBackground(new Color(0, 115, 200));  // Azul corporativo
        saveButton.setForeground(Color.WHITE);
        saveButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 80, 180), 1),  // Borde azul más oscuro
                BorderFactory.createEmptyBorder(8, 25, 8, 25)  // Padding interno aumentado
        ));
        saveButton.setFocusPainted(false);
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  // Cursor de mano al pasar
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(saveButton);
        return buttonPanel;
    }

    private void setupEventListeners() {
        saveButton.addActionListener(e -> saveProduct());

        // Listener para generación automática de código
        DocumentListener codeListener = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { updateCodeField(); }
            public void removeUpdate(DocumentEvent e) { updateCodeField(); }
            public void changedUpdate(DocumentEvent e) { updateCodeField(); }
        };

        brandField.getDocument().addDocumentListener(codeListener);
        typeField.getDocument().addDocumentListener(codeListener);
        sizeField.getDocument().addDocumentListener(codeListener);
        capacityField.getDocument().addDocumentListener(codeListener);
        colorField.getDocument().addDocumentListener(codeListener);

        // Listener para cambio de categoría
        categoryComboBox.addActionListener(e -> updateCodeField());

        // Listener para cálculo de precio
        DocumentListener priceListener = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { calculateSalePrice(); }
            public void removeUpdate(DocumentEvent e) { calculateSalePrice(); }
            public void changedUpdate(DocumentEvent e) { calculateSalePrice(); }
        };

        purchasePriceField.getDocument().addDocumentListener(priceListener);
        gainPercentageField.getDocument().addDocumentListener(priceListener);
    }

    private void updateCodeField() {
        if (!brandField.getText().isEmpty() && !typeField.getText().isEmpty()) {
            codeField.setBackground(new Color(255, 255, 200));
            generateProductCode();
        }
    }

    private void generateProductCode() {
        String category = (String) categoryComboBox.getSelectedItem();
        String brand = brandField.getText();
        String type = typeField.getText();
        String size = sizeField.getText();
        String capacity = capacityField.getText();
        String color = colorField.getText();
        int id = idField.getText().isEmpty() ? 0 : Integer.parseInt(idField.getText());

        String generatedCode = CodeGenerator.generate(
                category,
                brand,
                type,
                size,
                capacity,
                color,
                id
        );

        codeField.setText(generatedCode);
    }

    private void calculateSalePrice() {
        try {
            double purchasePrice = Double.parseDouble(purchasePriceField.getText());
            double gainPercentage = Double.parseDouble(gainPercentageField.getText());

            double priceBeforeTax = purchasePrice * (1 + (gainPercentage / 100));
            double finalPrice = priceBeforeTax * (1 + (IVA_PERCENTAGE / 100));

            salePriceField.setText(String.format("%,.2f", finalPrice));
        } catch (NumberFormatException ex) {
            salePriceField.setText("");
        }
    }

    private void saveProduct() {
        // Validar campos obligatorios
        if (nameField.getText().isEmpty() || brandField.getText().isEmpty() ||
                typeField.getText().isEmpty() || purchasePriceField.getText().isEmpty() ||
                stockField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor complete todos los campos obligatorios (*)",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Aquí iría la lógica para guardar el producto
        System.out.println("Producto guardado - Código: " + codeField.getText());
        dispose();
    }
}