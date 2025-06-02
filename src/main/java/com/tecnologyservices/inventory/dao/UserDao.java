package com.tecnologyservices.inventory.dao;
import com.tecnologyservices.inventory.model.DatabaseManager;

import java.sql.*;
public class UserDao {
    public boolean verifierCredentials(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace(); // o loguear
            return false;
        }
    }
}
