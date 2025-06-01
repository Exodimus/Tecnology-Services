package com.tecnologyservices.inventory.service;
import com.tecnologyservices.inventory.dao.UserDao;
public class UserService {
    private UserDao userDAO = new UserDao();

    public boolean autenticar(String username, String password) {
        return userDAO.verificarCredenciales(username, password);
    }
}
