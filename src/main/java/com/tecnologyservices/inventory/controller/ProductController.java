package com.tecnologyservices.inventory.controller;

import com.tecnologyservices.inventory.service.ProductService;
import com.tecnologyservices.inventory.view.DashboardView;

import javax.swing.table.DefaultTableModel;

public class ProductController {

    private final DashboardView view;
    private final ProductService productService;

    public ProductController(DashboardView view) {
        this.view = view;
        this.productService = new ProductService();
        loadProducts();
    }

    private void loadProducts() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{
                        "ID", "Código", "Nombre", "Marca", "Tipo", "Color", "Capacidad",
                        "ID Categoría", "Precio Compra", "Ganancia %", "Precio Venta", "Stock"
                }, 0
        );

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
                    row.stock
            });
        }

        view.getProductTable().setModel(model);
    }
}