package view.components.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {

    public Table() {
        // Set table properties
        setShowHorizontalLines(true); // Show horizontal grid lines
        setGridColor(new Color(230, 230, 230)); // Set grid color
        setRowHeight(40); // Set row height
        getTableHeader().setReorderingAllowed(false); // Disable column reordering

        // Custom renderer for table headers
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                // Customize table header appearance
                return new TableHeader(value.toString());
            }
        });

        // Custom renderer for table cells
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                // Get default cell renderer
                Component cellRenderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);
                // Customize cell appearance
                cellRenderer.setBackground(Color.WHITE); // Set background color
                setBorder(noFocusBorder); // Remove cell border
                if (isSelected) {
                    cellRenderer.setForeground(new Color(15, 89, 140)); // Selected text color
                } else {
                    cellRenderer.setForeground(new Color(102, 102, 102)); // Default text color
                }
                return cellRenderer;
            }
        });
    }

    // Method to add a row to the table
    public void addRow(Object[] rowData) {
        DefaultTableModel tableModel = (DefaultTableModel) getModel();
        tableModel.addRow(rowData); // Add row to the table model
    }

    // Method to delete a row from the table
    public void deleteRow(int i) {
        DefaultTableModel tableModel=(DefaultTableModel) getModel();
        tableModel.removeRow(i);
    }
    public void clearTable() {
        DefaultTableModel tableModel = (DefaultTableModel) getModel();
        tableModel.setRowCount(0); // Clear all rows from the table model
    }
    public void addRowSelectionListener(ListSelectionListener listener) {
        getSelectionModel().addListSelectionListener(listener);
    }
}
