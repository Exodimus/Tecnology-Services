package com.tecnologyservices.inventory.view;

import com.formdev.flatlaf.FlatLightLaf;
import com.tecnologyservices.inventory.controller.ProductController;
import com.tecnologyservices.inventory.view.Dialogs.ProductFormDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DashboardView {
    private JPanel mainPanel;
    private JTextField searchProductTextField;
    private JButton addProductButton;
    private JTable productTable;
    private JScrollPane productScrollPane;
    private JPanel headerPanel;
    private JLabel tecnologyServicesLabel;
    private JLabel moduleLabel;
    private JToolBar toolBar;

    public DashboardView() {
        //Hacer la tabla no editable
        productTable.setDefaultEditor(Object.class, null);

        //addProductButton listener
        addProductButton.addActionListener((ActionEvent e) -> {
            ProductFormDialog dialog = new ProductFormDialog(
                    (JFrame) SwingUtilities.getWindowAncestor(mainPanel),
                    productTable,
                    -1,
                    false
            );
            dialog.setVisible(true);
        });


    }

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

    public static void main(String[] args) {
        // Establecer el look and feel moderno
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Crear ventana principal
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tecnology Services - Inventario");
            DashboardView dashboard = new DashboardView();
            new ProductController(dashboard);


            frame.setContentPane(dashboard.mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null); // Centrado
            frame.setVisible(true);
        });
    }

    private void createUIComponents() {
        // Este método es gestionado por IntelliJ para inicializar componentes personalizados si se requiere
    }
}
