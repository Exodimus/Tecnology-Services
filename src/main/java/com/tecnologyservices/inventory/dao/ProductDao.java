package com.tecnologyservices.inventory.dao;
import com.tecnologyservices.inventory.model.DatabaseManager;
import com.tecnologyservices.inventory.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setCode(rs.getString("code"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setType(rs.getString("type"));
                product.setColor(rs.getString("color"));
                product.setCapacity(rs.getString("capacity"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setPurchasePrice(rs.getDouble("purchase_price"));
                product.setTaxPercentage(rs.getDouble("tax_percentage"));
                product.setGainPercentage(rs.getDouble("gain_percentage"));
                product.setStock(rs.getInt("stock"));

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo básico de errores (puedes usar logs si prefieres)
        }

        return products;
    }
    public boolean saveProduct(Product product) {
        String sql = "INSERT INTO products (code, name, brand, type, color, capacity, category_id, " +
                "purchase_price, tax_percentage, gain_percentage, stock) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getCode());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getBrand());
            stmt.setString(4, product.getType());
            stmt.setString(5, product.getColor());
            stmt.setString(6, product.getCapacity());
            stmt.setInt(7, product.getCategoryId());
            stmt.setDouble(8, product.getPurchasePrice());
            stmt.setDouble(9, product.getTaxPercentage());
            stmt.setDouble(10, product.getGainPercentage());
            stmt.setInt(11, product.getStock());

            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Product> searchProducts(String criterio) {
        List<Product> productos = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE code LIKE ? OR name LIKE ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String filtro = "%" + criterio + "%";
            stmt.setString(1, filtro);
            stmt.setString(2, filtro);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setCode(rs.getString("code"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setType(rs.getString("type"));
                product.setColor(rs.getString("color"));
                product.setCapacity(rs.getString("capacity"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setPurchasePrice(rs.getDouble("purchase_price"));
                product.setTaxPercentage(rs.getDouble("tax_percentage"));
                product.setGainPercentage(rs.getDouble("gain_percentage"));
                product.setStock(rs.getInt("stock"));
                productos.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public boolean updataProduct(Product product) {
        String sql = "UPDATE products SET code = ?, name = ?, brand = ?, type = ?, color = ?, " +
                "capacity = ?, category_id = ?, purchase_price = ?, tax_percentage = ?, " +
                "gain_percentage = ?, stock = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getCode());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getBrand());
            stmt.setString(4, product.getType());
            stmt.setString(5, product.getColor());
            stmt.setString(6, product.getCapacity());
            stmt.setInt(7, product.getCategoryId());
            stmt.setDouble(8, product.getPurchasePrice());
            stmt.setDouble(9, product.getTaxPercentage());
            stmt.setDouble(10, product.getGainPercentage());
            stmt.setInt(11, product.getStock());
            stmt.setInt(12, product.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
