package com.tecnologyservices.inventory.view;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class LoginView {
    private JPanel mainPanel;
    private JTextField textUser;
    private JPasswordField textPass;
    private JButton btnLogin;

    public LoginView() {
        initializeLookAndFeel();
        initializeComponents();
    }

    private void initializeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            // Configurar tamaños de fuente globales
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 12));
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Error al configurar FlatLaf");
            e.printStackTrace();
        }
    }

    private void initializeComponents() {
        // Configuración del panel principal (ahora más ancho)
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40)); // Más margen

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Más espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Componentes de la interfaz con tamaños aumentados
        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 14));

        textUser = new JTextField(20); // Campo más ancho
        textUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 14));

        textPass = new JPasswordField(20); // Campo más ancho
        textPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        btnLogin = new JButton("INGRESAR");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setPreferredSize(new Dimension(150, 35)); // Botón más grande
        btnLogin.setBackground(new Color(70, 130, 180)); // Color azul
        btnLogin.setForeground(Color.WHITE);

        // Posicionamiento de componentes
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(lblUser, gbc);

        gbc.gridx = 1;
        mainPanel.add(textUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(lblPass, gbc);

        gbc.gridx = 1;
        mainPanel.add(textPass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(btnLogin, gbc);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public String getUserText() {
        return textUser.getText().trim();
    }

    public String getPasswordText() {
        return new String(textPass.getPassword()).trim();
    }

    public void showLoginWindow() {
        JFrame frame = new JFrame("Inicio de Sesión - Tecnology Services");
        frame.setContentPane(this.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // Tamaño mínimo para asegurar buen aspecto
        frame.setMinimumSize(new Dimension(500, 350));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}