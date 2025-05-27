package com.tecnologyservices.inventory.util;
import com.tecnologyservices.inventory.model.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "username TEXT UNIQUE NOT NULL, " +
                            "password TEXT NOT NULL, " +
                            "role TEXT NOT NULL CHECK(role IN ('admin', 'operator'))" +
                            ");"
            );

            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS categories (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "name TEXT UNIQUE NOT NULL" +
                            ");"
            );

            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS products (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "code TEXT UNIQUE NOT NULL, " +
                            "name TEXT NOT NULL, " +
                            "brand TEXT NOT NULL, " +
                            "type TEXT NOT NULL, " +
                            "color TEXT NOT NULL, " +
                            "capacity TEXT NOT NULL, " +
                            "category_id INTEGER, " +
                            "purchase_price REAL NOT NULL, " +
                            "tax_percentage REAL NOT NULL, " +
                            "gain_percentage REAL NOT NULL, " +
                            "stock INTEGER NOT NULL, " +
                            "FOREIGN KEY (category_id) REFERENCES categories(id)" +
                            ");"
            );

            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS stock_movements (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "product_id INTEGER NOT NULL, " +
                            "quantity INTEGER NOT NULL, " +
                            "type TEXT NOT NULL CHECK(type IN ('in', 'out')), " +
                            "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                            "FOREIGN KEY (product_id) REFERENCES products(id)" +
                            ");"
            );

            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS finances (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "type TEXT NOT NULL CHECK(type IN ('income', 'expense')), " +
                            "amount REAL NOT NULL, " +
                            "description TEXT, " +
                            "created_at DATETIME DEFAULT CURRENT_TIMESTAMP" +
                            ");"
            );

            System.out.println("Base de datos inicializada correctamente.");

        } catch (SQLException e) {
            System.err.println("Error inicializando la base de datos: " + e.getMessage());
        }
    }
}
