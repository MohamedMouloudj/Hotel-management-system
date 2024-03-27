package view.managerUi;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

// import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelShow extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;

    public PanelShow() {
        // addAllreceptionist();
        // removeAllreceptionist();

    }

    private void Allreceptionist() {
        String[] columnNames = { "Number", "Name", "Email", "Last Name", "Password" };
        Object[][] data = {
                { 1, "John", "john@example.com", "Doe", "password1" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                { 2, "Jane", "jane@example.com", "Smith", "password2" },
                // Add more data as needed
        };

        table = new JTable(data, columnNames);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setForeground(Color.BLACK);
        table.setGridColor(Color.BLACK);
        table.setBackground(Color.WHITE);

        // Customize table rendering
        table.setRowHeight(30); // Set row height
        table.setIntercellSpacing(new Dimension(5, 8)); // Set spacing between cells
        table.setShowGrid(true); // Show grid lines
        table.setCellSelectionEnabled(false);
        // table.
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Customize header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14)); // Set header font
        header.setForeground(Color.WHITE); // Set header text color
        header.setBackground(Color.BLACK); // Set header background color
        header.setReorderingAllowed(false); // Disallow column reordering

        // Customize scroll pane
        scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove scroll pane border
        scrollPane.setPreferredSize(new Dimension(600, 300)); // Set preferred size

        // Add components to panel
        setLayout(new BorderLayout());

    }

    public void addAllreceptionist() {
        Allreceptionist();
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
    }

    public void removeAllComponents() {

        removeAll();
        add(new JLabel(""));
        this.revalidate();
    }

}
