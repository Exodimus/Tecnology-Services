package com.tecnologyservices.inventory.view;

import com.tecnologyservices.inventory.controller.ProductController;
import com.tecnologyservices.inventory.view.Dialogs.ProductFormDialog;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Vista principal del dashboard de inventario
 */
public class DashboardView {
    // Componentes UI generados por IntelliJ
    private JPanel mainPanel;
    private JTextField searchProductTextField;
    private JButton addProductButton;
    private JTable productTable;
    private JScrollPane productScrollPane;
    private JPanel headerPanel;
    private JLabel companyNameLabel;
    private JLabel moduleLabel;
    private JToolBar toolBar;

    /**
     * Constructor del dashboard
     * @param parentFrame Marco padre para diálogos modales
     */
    public DashboardView(JFrame parentFrame) {
        configureUI();
        setupControllers(parentFrame);
    }

    // Configura los elementos de la interfaz de usuario
    private void configureUI() {
        productTable.setDefaultEditor(Object.class, null); // Tabla no editable

        // Textos en español para la UI
        addProductButton.setText("Agregar Producto");
        companyNameLabel.setText("Tecnology Services");
        moduleLabel.setText("Módulo de Inventario");
        searchProductTextField.setToolTipText("Buscar productos...");
    }

    // Configura los controladores y eventos
    private void setupControllers(JFrame parentFrame) {
        // Evento para agregar producto
        addProductButton.addActionListener((ActionEvent e) -> {
            showProductFormDialog(parentFrame);
        });

        new ProductController(this); // Inicializa el controlador
    }

    // Muestra el diálogo de formulario de producto
    private void showProductFormDialog(JFrame parentFrame) {
        ProductFormDialog dialog = new ProductFormDialog(
                parentFrame,
                productTable,
                -1,   // Indica nuevo producto
                false // Modo creación
        );
        dialog.setVisible(true);
    }

    // --- Getters ---
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTable getProductTable() {
        return productTable;
    }

    public JTextField getSearchProductTextField() {
        return searchProductTextField;
    }

    public JButton getAddProductButton() {
        return addProductButton;
    }

    // Método para inicializar componentes personalizados
    private void createUIComponents() {
        // Gestionado por IntelliJ
    }
}