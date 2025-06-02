package com.tecnologyservices.inventory.util;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JPanel implements TableCellRenderer {
    private final JButton editButton;
    private final JButton deleteButton;

    public ButtonRenderer() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        setOpaque(true);

        // Configurar botón Editar
        editButton = new JButton("Editar");
        editButton.setPreferredSize(new Dimension(80, 25));
        editButton.setBackground(new Color(70, 130, 180));
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);

        // Configurar botón Eliminar
        deleteButton = new JButton("Eliminar");
        deleteButton.setPreferredSize(new Dimension(80, 25));
        deleteButton.setBackground(new Color(220, 53, 69));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);

        add(editButton);
        add(deleteButton);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            editButton.setBackground(new Color(50, 110, 160));
            deleteButton.setBackground(new Color(200, 43, 59));
        } else {
            setBackground(table.getBackground());
            editButton.setBackground(new Color(70, 130, 180));
            deleteButton.setBackground(new Color(220, 53, 69));
        }
        return this;
    }
}