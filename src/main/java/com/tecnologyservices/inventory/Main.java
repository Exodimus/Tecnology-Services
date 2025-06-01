package com.tecnologyservices.inventory;
import com.tecnologyservices.inventory.util.DatabaseInitializer;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initializeDatabase();
        System.out.println("Inicializando DB");
    }
}
