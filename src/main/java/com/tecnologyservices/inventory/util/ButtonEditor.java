package com.tecnologyservices.inventory.util;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {

    private final JPanel panel = new JPanel();
    private final JButton editButton = new JButton("Editar");
    private final JButton deleteButton = new JButton("Eliminar");

    public ButtonEditor(ActionListener onEdit, ActionListener onDelete) {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel.add(editButton);
        panel.add(deleteButton);

        editButton.addActionListener(e -> {
            onEdit.actionPerformed(e);
            fireEditingStopped();
        });

        deleteButton.addActionListener(e -> {
            onDelete.actionPerformed(e);
            fireEditingStopped();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        // Para que los listeners sepan en qué fila están trabajando
        editButton.setActionCommand(String.valueOf(row));
        deleteButton.setActionCommand(String.valueOf(row));
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}
