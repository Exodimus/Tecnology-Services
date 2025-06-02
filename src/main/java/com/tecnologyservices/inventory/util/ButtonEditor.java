package com.tecnologyservices.inventory.util;

import com.tecnologyservices.inventory.view.Dialogs.ProductFormDialog;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private final JPanel panel;
    private final JButton editButton;
    private final JButton deleteButton;
    private int currentRow;
    private final JTable table;
    private final JFrame parent;

    public ButtonEditor(JTable table, JFrame parent) {
        this.table = table;
        this.parent = parent;

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel.setOpaque(true);

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

        panel.add(editButton);
        panel.add(deleteButton);

        editButton.addActionListener(this::handleEdit);
        deleteButton.addActionListener(this::handleDelete);
    }

    private void handleEdit(ActionEvent e) {
        fireEditingStopped();
        ProductFormDialog dialog = new ProductFormDialog(parent, table, currentRow, true);
        dialog.setVisible(true);
    }

    private void handleDelete(ActionEvent e) {
        fireEditingStopped();
        int confirm = JOptionPane.showConfirmDialog(parent,
                "¿Está seguro de eliminar este producto?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            ((DefaultTableModel) table.getModel()).removeRow(currentRow);
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        currentRow = row;
        if (isSelected) {
            panel.setBackground(table.getSelectionBackground());
            editButton.setBackground(new Color(50, 110, 160));
            deleteButton.setBackground(new Color(200, 43, 59));
        } else {
            panel.setBackground(table.getBackground());
            editButton.setBackground(new Color(70, 130, 180));
            deleteButton.setBackground(new Color(220, 53, 69));
        }
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}