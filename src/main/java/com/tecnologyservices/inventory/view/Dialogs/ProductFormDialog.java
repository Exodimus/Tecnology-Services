package com.tecnologyservices.inventory.view.Dialogs;

import com.tecnologyservices.inventory.controller.ProductController;
import com.tecnologyservices.inventory.model.Product;
import com.tecnologyservices.inventory.service.ProductService;
import com.tecnologyservices.inventory.util.CodeGenerator;
import com.tecnologyservices.inventory.view.DashboardView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;

public class ProductFormDialog extends JDialog {
    private ProductService productService;
    private ProductController productController;
    private final JTable productTable;
    private final int rowToEdit;
    private final boolean isEditMode;

    // Componentes del formulario
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
    private static final String[] PREDEFINED_CATEGORIES = {
            "Electrónicos", "Ropa", "Alimentos", "Hogar",
            "Juguetes", "Oficina", "Deportes"
    };

    public ProductFormDialog(JFrame parent, JTable productTable, int rowToEdit, boolean isEditMode) {
        super(parent, isEditMode ? "Editar Producto" : "Nuevo Producto", true);
        this.productTable = productTable;
        this.rowToEdit = rowToEdit;
        this.isEditMode = isEditMode;

        setSize(600, 650);
        setLocationRelativeTo(parent);
        setResizable(false);
        setupUI();

        if (isEditMode) {
            fillFromTableRow(rowToEdit);
        } else {
            idField.setText(String.valueOf(productTable.getRowCount() + 1));
        }
    }

    public ProductFormDialog(JFrame parent, JTable productTable, int rowToEdit, boolean isEditMode, ProductController productController) {
        super(parent, isEditMode ? "Editar Producto" : "Nuevo Producto", true);
        this.productTable = productTable;
        this.rowToEdit = rowToEdit;
        this.isEditMode = isEditMode;
        this.productController = productController;
        setSize(600, 650);
        setLocationRelativeTo(parent);
        setResizable(false);
        setupUI();

        if (isEditMode) {
            fillFromTableRow(rowToEdit);
        } else {
            idField.setText(String.valueOf(productTable.getRowCount() + 1));
        }
    }

    private void setupUI() {
        // Panel principal con scroll
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Panel de campos del formulario
        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2, 10, 8));
        fieldsPanel.setBackground(new Color(245, 245, 245));

        // Inicializar componentes
        initializeFields();
        addFormFields(fieldsPanel);

        // Panel de botón
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(createSaveButton());

        // Añadir componentes al panel principal
        mainPanel.add(new JScrollPane(fieldsPanel), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
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
        purchasePriceField = createFormattedTextField();
        gainPercentageField = createFormattedTextField();
        salePriceField = createTextField(false);
        salePriceField.setBackground(new Color(240, 240, 240));
        stockField = createTextField(true);

        // Configurar ComboBox de categorías
        categoryComboBox = new JComboBox<>(PREDEFINED_CATEGORIES);
        categoryComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        categoryComboBox.setBackground(Color.WHITE);
        categoryComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE,
                categoryComboBox.getPreferredSize().height));
    }

    private JTextField createTextField(boolean editable) {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        field.setEditable(editable);
        return field;
    }

    private JFormattedTextField createFormattedTextField() {
        NumberFormat format = NumberFormat.getNumberInstance();
        JFormattedTextField field = new JFormattedTextField(format);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        return field;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        return label;
    }

    private JButton createSaveButton() {
        saveButton = new JButton(isEditMode ? "Actualizar Producto" : "Guardar Producto");
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveButton.setBackground(new Color(13, 110, 253));
        saveButton.setForeground(Color.WHITE);
        saveButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(10, 88, 202), 1),
                BorderFactory.createEmptyBorder(8, 25, 8, 25)
        ));
        saveButton.setFocusPainted(false);
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(e -> saveProduct());
        return saveButton;
    }

    private void addFormFields(JPanel panel) {
        panel.add(createLabel("ID:"));
        panel.add(createWrappedComponent(idField));

        panel.add(createLabel("Categoría*:"));
        panel.add(createWrappedComponent(categoryComboBox));

        panel.add(createLabel("Código:"));
        panel.add(createWrappedComponent(codeField));

        panel.add(createLabel("Nombre*:"));
        panel.add(createWrappedComponent(nameField));

        panel.add(createLabel("Marca*:"));
        panel.add(createWrappedComponent(brandField));

        panel.add(createLabel("Tipo*:"));
        panel.add(createWrappedComponent(typeField));

        panel.add(createLabel("Tamaño:"));
        panel.add(createWrappedComponent(sizeField));

        panel.add(createLabel("Color:"));
        panel.add(createWrappedComponent(colorField));

        panel.add(createLabel("Capacidad:"));
        panel.add(createWrappedComponent(capacityField));

        panel.add(createLabel("Precio Compra*:"));
        panel.add(createWrappedComponent(purchasePriceField));

        panel.add(createLabel("% Ganancia*:"));
        panel.add(createWrappedComponent(gainPercentageField));

        panel.add(createLabel("Precio Venta (con IVA 13%):"));
        panel.add(createWrappedComponent(salePriceField));

        panel.add(createLabel("Stock*:"));
        panel.add(createWrappedComponent(stockField));

        setupFieldListeners();
    }

    private JPanel createWrappedComponent(JComponent component) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(245, 245, 245));
        wrapper.add(component, BorderLayout.CENTER);
        return wrapper;
    }

    private void setupFieldListeners() {
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
            double purchasePrice = Double.parseDouble(purchasePriceField.getText().replace(",", ""));
            double gainPercentage = Double.parseDouble(gainPercentageField.getText().replace(",", ""));

            double priceBeforeTax = purchasePrice * (1 + (gainPercentage / 100));
            double finalPrice = priceBeforeTax * (1 + (IVA_PERCENTAGE / 100));

            salePriceField.setText(String.format("%,.2f", finalPrice));
        } catch (NumberFormatException ex) {
            salePriceField.setText("");
        }
    }

    public void fillFromTableRow(int row) {
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        idField.setText(model.getValueAt(row, 0).toString());
        codeField.setText(model.getValueAt(row, 1).toString());
        nameField.setText(model.getValueAt(row, 2).toString());
        brandField.setText(model.getValueAt(row, 3).toString());
        typeField.setText(model.getValueAt(row, 4).toString());
        colorField.setText(model.getValueAt(row, 5).toString());
        capacityField.setText(model.getValueAt(row, 6).toString());

        try {
            int categoryIndex = Integer.parseInt(model.getValueAt(row, 7).toString()) - 1;
            categoryComboBox.setSelectedIndex(categoryIndex);
        } catch (Exception e) {
            categoryComboBox.setSelectedIndex(0);
        }

        purchasePriceField.setText(model.getValueAt(row, 8).toString());
        gainPercentageField.setText(model.getValueAt(row, 9).toString());
        salePriceField.setText(model.getValueAt(row, 10).toString());
        stockField.setText(model.getValueAt(row, 11).toString());
    }

    private void saveProduct() {
        this.productService = new ProductService();
        if (!validateFields()) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) productTable.getModel();

        if (isEditMode) {
            // Actualizar fila existente
            /*for (int i = 0; i < rowData.length; i++) {
                model.setValueAt(rowData[i], rowToEdit, i);
            }*/
            updateProductDB();
            productController.loadProducts(productService.getAll());
            JOptionPane.showMessageDialog(this, "Producto actualizado correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Añadir nuevo producto
            saveProductDB();
            // 🔄 Actualizar la tabla
            productController.loadProducts(productService.getAll());
            JOptionPane.showMessageDialog(this, "Producto creado correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }

        dispose();
    }

    private Object[] getProductData() {
        return new Object[]{
                idField.getText(),
                codeField.getText(),
                nameField.getText(),
                brandField.getText(),
                typeField.getText(),
                colorField.getText(),
                capacityField.getText(),
                categoryComboBox.getSelectedIndex() + 1,
                Double.parseDouble(purchasePriceField.getText().replace(",", "")),
                Double.parseDouble(gainPercentageField.getText().replace(",", "")),
                salePriceField.getText().replace(",", ""),
                Integer.parseInt(stockField.getText()),
                "" // Placeholder para acciones
        };
    }

    private Boolean updateProductDB(){
        Product product = new Product();
        product.setId(Integer.parseInt(idField.getText()));
        product.setCode(codeField.getText());
        product.setName(nameField.getText());
        product.setBrand(brandField.getText());
        product.setType(typeField.getText());
        product.setColor(colorField.getText());
        product.setCapacity(capacityField.getText());
        product.setCategoryId(categoryComboBox.getSelectedIndex() + 1);
        product.setPurchasePrice(Double.parseDouble(purchasePriceField.getText()));
        product.setTaxPercentage(Double.parseDouble(salePriceField.getText()));
        product.setGainPercentage(Double.parseDouble(gainPercentageField.getText()));
        product.setStock(Integer.parseInt(stockField.getText()));

        boolean updated = productService.updateProduct(product);

        if (updated) {
            JOptionPane.showMessageDialog(this, "Producto actualizado.");
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar.");
        }
        return updated;
    }
    private Boolean saveProductDB() {
        Product newProd = new Product();
        newProd.setCode(codeField.getText());
        newProd.setName(nameField.getText());
        newProd.setBrand(brandField.getText());
        newProd.setType(typeField.getText());
        newProd.setColor(colorField.getText());
        newProd.setCapacity(capacityField.getText());
        newProd.setCategoryId(categoryComboBox.getSelectedIndex() + 1);
        newProd.setPurchasePrice(Double.parseDouble(purchasePriceField.getText().replace(",", "")));
        newProd.setTaxPercentage(Double.parseDouble(salePriceField.getText().replace(",", "")));
        newProd.setGainPercentage(Double.parseDouble(gainPercentageField.getText().replace(",", "")));
        newProd.setStock(Integer.parseInt(stockField.getText()));

        return productService.registrarProducto(newProd);
    }
    private boolean validateFields() {
        if (nameField.getText().trim().isEmpty() ||
                brandField.getText().trim().isEmpty() ||
                typeField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Por favor complete los campos obligatorios: Nombre, Marca y Tipo",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Double.parseDouble(purchasePriceField.getText().replace(",", ""));
            Double.parseDouble(gainPercentageField.getText().replace(",", ""));
            Integer.parseInt(stockField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese valores numéricos válidos en Precio, Ganancia y Stock",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}