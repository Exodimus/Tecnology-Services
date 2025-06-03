package com.tecnologyservices.inventory.controller;

import com.tecnologyservices.inventory.model.Product;
import com.tecnologyservices.inventory.service.ProductService;
import com.tecnologyservices.inventory.util.ButtonEditor;
import com.tecnologyservices.inventory.util.ButtonRenderer;
import com.tecnologyservices.inventory.view.DashboardView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    private final DashboardView view;
    private final ProductService productService;
    private List<Product> productList;
    public ProductController(DashboardView view) {
        this.productList = new ArrayList<>();
        this.view = view;
        this.productService = new ProductService();
        productList = productService.getAll();
        loadProducts(productList);

        searchProduct();
    }

    public void searchProduct(){
        JButton btnSearchProd = view.getBtnSearch();
        btnSearchProd.addActionListener(e -> {
            String criterio = view.getSearchProductTextField().getText();
            productList = productService.searchProducts(criterio);
            loadProducts(productList);
        });
    }

    public void loadProducts(List<Product> productList2) {
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
        for (Product product : productList2) {
            model.addRow(new Object[]{
                    product.getId(),
                    product.getCode(),
                    product.getName(),
                    product.getBrand(),
                    product.getType(),
                    product.getCode(),
                    product.getCapacity(),
                    product.getCategoryId(),
                    product.getPurchasePrice(),
                    product.getGainPercentage(),
                    "12",
                    product.getStock(),
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
        setupActionButtons();
    }

    public void setupActionButtons() {
        JTable table = view.getProductTable();
        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(view.getMainPanel());

        table.getColumn("Acciones").setCellRenderer(new ButtonRenderer());
        table.getColumn("Acciones").setCellEditor(new ButtonEditor(table, parent));
    }
}