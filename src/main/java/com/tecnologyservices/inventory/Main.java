package com.tecnologyservices.inventory;

import com.tecnologyservices.inventory.controller.AuthController;
import com.tecnologyservices.inventory.util.DatabaseInitializer;
import com.tecnologyservices.inventory.view.LoginView;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("Inicializando DB...");
        DatabaseInitializer.initializeDatabase();
        SwingUtilities.invokeLater(() -> {
            LoginView view = new LoginView();
            new AuthController(view);
            view.setVisible(true);
        });
    }
}
