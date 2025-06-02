package com.tecnologyservices.inventory.controller;

import com.tecnologyservices.inventory.service.UserService;
import com.tecnologyservices.inventory.view.LoginView;
import com.tecnologyservices.inventory.view.DashboardView;
import javax.swing.*;

public class AuthController {
    private final LoginView loginView;
    private final UserService authService;

    public AuthController(LoginView view) {
        this.loginView = view;
        this.authService = new UserService();
        initController();
    }

    private void initController() {
        loginView.getBtnLogin().addActionListener(e -> processLogin());
    }

    private void processLogin() {
        String username = loginView.getUserText();
        String password = loginView.getPasswordText();

        if (authService.authenticate(username, password)) {
            onLoginSuccess();
        } else {
            showLoginError();
        }
    }

    private void onLoginSuccess() {
        // Usamos getMainPanel() como componente padre
        JOptionPane.showMessageDialog(
                loginView.getMainPanel(),
                "Inicio de sesión exitoso",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );

        JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(loginView.getMainPanel());
        loginFrame.dispose();

        showDashboard();
    }

    private void showDashboard() {
        JFrame dashboardFrame = new JFrame("Tecnology Services - Inventario");
        DashboardView dashboard = new DashboardView(dashboardFrame);

        dashboardFrame.setContentPane(dashboard.getMainPanel());
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardFrame.setSize(1000, 600);
        dashboardFrame.setLocationRelativeTo(null);
        dashboardFrame.setVisible(true);
    }

    private void showLoginError() {
        JOptionPane.showMessageDialog(
                loginView.getMainPanel(), // Usamos getMainPanel() como componente padre
                "Credenciales incorrectas",
                "Error de autenticación",
                JOptionPane.ERROR_MESSAGE
        );
    }
}