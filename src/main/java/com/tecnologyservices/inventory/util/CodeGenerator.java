package com.tecnologyservices.inventory.util;

public class CodeGenerator {

    /**
     * Genera un código de producto basado en características clave
     * @param category Categoría del producto (ej. "Utensilio Personal")
     * @param brand Marca del producto (ej. "Stanley")
     * @param type Tipo de producto (ej. "Termo")
     * @param size Tamaño personalizado (ej. "Grande")
     * @param capacity Capacidad (ej. "40oz")
     * @param color Color (ej. "Azul")
     * @param id Identificador único (opcional)
     * @return Código generado (ej. "UPSTTGr40A01")
     */
    public static String generate(String category, String brand, String type,
                                  String size, String capacity, String color,
                                  int id) {
        return abbreviate(category, 2)      // UP
                + abbreviate(brand, 2)      // ST
                + abbreviate(type, 1)        // T
                + parseSize(size)            // Gr
                + extractDigits(capacity)    // 40
                + abbreviate(color, 1)       // A
                + String.format("%02d", id);// 01
    }

    /**
     * Abrevia un texto a la longitud especificada
     */
    private static String abbreviate(String input, int length) {
        if (input == null || input.isEmpty()) return new String(new char[length]).replace('\0', 'X');
        String normalized = input.replaceAll("[^a-zA-Z]", "").toUpperCase();
        return normalized.length() >= length
                ? normalized.substring(0, length)
                : String.format("%-" + length + "s", normalized).replace(' ', 'X');
    }

    /**
     * Procesa tamaños personalizados (ej. Grande -> Gr)
     */
    private static String parseSize(String size) {
        if (size == null) return "XX";

        switch (size.toLowerCase()) {
            case "grande": return "Gr";
            case "mediano": return "Md";
            case "pequeño": return "Pq";
            default: return abbreviate(size, 2);
        }
    }

    /**
     * Extrae solo los dígitos de una cadena
     */
    private static String extractDigits(String input) {
        if (input == null) return "00";
        String digits = input.replaceAll("\\D+", "");
        return digits.isEmpty() ? "00" : digits;
    }

    // Versión simplificada (sin categoría/tamaño personalizado)
    public static String generateSimple(String brand, String type,
                                        String capacity, String color, int id) {
        return abbreviate(brand, 2)
                + abbreviate(type, 2)
                + extractDigits(capacity)
                + abbreviate(color, 2)
                + String.format("%02d", id);
    }
}