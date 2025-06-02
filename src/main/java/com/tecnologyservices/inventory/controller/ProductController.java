package com.tecnologyservices.inventory.controller;

import com.tecnologyservices.inventory.service.ProductService;
import com.tecnologyservices.inventory.util.ButtonEditor;
import com.tecnologyservices.inventory.util.ButtonRenderer;
import com.tecnologyservices.inventory.view.DashboardView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ProductController {

    private final DashboardView view;
    private final ProductService productService;

    public ProductController(DashboardView view) {
        this.view = view;
        this.productService = new ProductService();
        loadProducts();
        setupActionButtons();
    }

    private void loadProducts() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{
                        "ID", "Código", "Nombre", "Marca", "Tipo", "Color", "Capacidad",
                        "ID Categoría", "Precio Compra", "Ganancia %", "Precio Venta", "Stock", "Acciones"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 12; // Solo la columna "Acciones" es editable
            }
        };

        for (ProductService.ProductRow row : productService.listProductsDummy()) {
            model.addRow(new Object[]{
                    row.id,
                    row.code,
                    row.name,
                    row.brand,
                    row.type,
                    row.color,
                    row.capacity,
                    row.category_id,
                    row.purchase_price,
                    row.gain_percentage,
                    row.sale_price,
                    row.stock,
                    "" // Placeholder para botones
            });
        }

        JTable table = view.getProductTable();
        table.setModel(model);

        // Ajustar el ancho de la columna de acciones
        TableColumn column = table.getColumnModel().getColumn(12);
        column.setMinWidth(180);
        column.setPreferredWidth(180);
        column.setMaxWidth(180);
    }

    private void setupActionButtons() {
        JTable table = view.getProductTable();
        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(view.getMainPanel());

        table.getColumn("Acciones").setCellRenderer(new ButtonRenderer());
        table.getColumn("Acciones").setCellEditor(new ButtonEditor(table, parent));
    }
}