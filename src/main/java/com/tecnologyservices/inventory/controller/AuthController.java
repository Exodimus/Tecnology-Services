package com.tecnologyservices.inventory.controller;
import com.tecnologyservices.inventory.service.UserService;
import com.tecnologyservices.inventory.view.LoginView;
import javax.swing.*;
import javax.swing.*;
import java.sql.*;
public class AuthController extends JFrame {
    private LoginView view;
    private UserService authService;

    public AuthController(LoginView view) {
        this.view = view;
        this.authService = new UserService();

        initController();
    }

    private void initController() {
        view.getBtnLogin().addActionListener(e -> procesarLogin());
    }

    private void procesarLogin() {
        String username = view.getUserText();
        String password = view.getPasswordText();

        if (authService.autenticar(username, password)) {
            JOptionPane.showMessageDialog(view, "Login exitoso");
            // new MainView().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(view, "Credenciales incorrectas");
        }
    }

}
