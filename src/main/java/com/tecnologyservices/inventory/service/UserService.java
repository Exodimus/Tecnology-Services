package com.tecnologyservices.inventory.service;
import com.tecnologyservices.inventory.dao.UserDao;
public class UserService {
    private UserDao userDAO = new UserDao();

    public boolean authenticate(String username, String password) {
        return userDAO.verifierCredentials(username, password);
    }
}
