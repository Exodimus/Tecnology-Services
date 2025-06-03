package com.tecnologyservices.inventory.service;

import com.tecnologyservices.inventory.dao.ProductDao;
import com.tecnologyservices.inventory.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private ProductDao productDao = new ProductDao();

    public List<Product> searchProducts(String criterio) {
        return productDao.searchProducts(criterio);
    }
    public List<Product> getAll(){
        return productDao.getAllProducts();
    }
    public boolean registrarProducto(Product product) {
        return productDao.saveProduct(product);
    }
    public boolean updateProduct(Product product) {
        return productDao.updataProduct(product);
    }
    public static class ProductRow {
        public int id;
        public String code;
        public String name;
        public String brand;
        public String type;
        public String color;
        public String capacity;
        public int category_id;
        public double purchase_price;
        public double gain_percentage;
        public double sale_price;
        public int stock;

        public ProductRow(int id, String code, String name, String brand, String type,
                          String color, String capacity, int category_id,
                          double purchase_price,
                          double gain_percentage, double sale_price, int stock) {
            this.id = id;
            this.code = code;
            this.name = name;
            this.brand = brand;
            this.type = type;
            this.color = color;
            this.capacity = capacity;
            this.category_id = category_id;
            this.purchase_price = purchase_price;
            this.gain_percentage = gain_percentage;
            this.sale_price = sale_price;
            this.stock = stock;
        }
    }

    public List<ProductRow> listProductsDummy() {
        List<ProductRow> products = new ArrayList<>();
        products.add(new ProductRow(
                1, "LPTEL15", "Laptop Pro 15", "Dell", "Portátil",
                "Negro", "512GB SSD", 1, 1000.0, 13.0, 20.0, 50
        ));
        products.add(new ProductRow(
                2, "MSACC25", "Mouse Óptico", "Logitech", "Periférico",
                "Blanco", "N/A", 2, 20.0, 13.0, 25.0, 200
        ));
        products.add(new ProductRow(
                3, "TKACC75", "Teclado Mecánico", "Razer", "Periférico",
                "RGB", "N/A", 2, 60.0, 13.0, 25.0, 100
        ));
        products.add(new ProductRow(
                4, "MNEL30", "Monitor 27\"", "Samsung", "Monitor",
                "Negro", "27 pulgadas", 1, 250.0, 13.0, 20.0, 30
        ));
        return products;
    }
}