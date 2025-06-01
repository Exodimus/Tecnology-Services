package com.tecnologyservices.inventory.view;

import javax.swing.*;

public class LoginView extends JFrame {
    private JTextField textUser;
    private JPasswordField textPass;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Login");
        setSize(300, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setBounds(30, 20, 80, 25);
        add(lblUser);

        textUser = new JTextField();
        textUser.setBounds(110, 20, 150, 25);
        add(textUser);

        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(30, 60, 80, 25);
        add(lblPass);

        textPass = new JPasswordField();
        textPass.setBounds(110, 60, 150, 25);
        add(textPass);

        btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(100, 100, 100, 30);
        add(btnLogin);
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public String getUserText() {
        return textUser.getText();
    }

    public String getPasswordText() {
        return new String(textPass.getPassword());
    }
}
