package view.managerUi.container;

import javax.swing.*;
// import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import java.awt.*;

public class PanelShow extends JPanel {
    private JTable table;
    public JScrollPane scrollPane;

    public PanelShow() {
        initComponents();
        setBackground(Color.WHITE);
    }


    private void initComponents() {
        Allreciptioniste();
    }

    public void Allreciptioniste() {
        String[] columnNames = { "Number", "Name", "Email", "Last Name", "Password" }; // Include "Number" column
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

        };

        // Create a JTable with the custom table model
        table = new JTable(data, columnNames);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setForeground(Color.BLACK); // Set text color to black
        table.setGridColor(Color.BLACK); // Set grid color to black
        table.setBackground(Color.WHITE); // Set background color to white

        scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE); // Set background color for the scroll pane viewport
        scrollPane.setViewportBorder(null); // Remove border from the scroll pane's viewport
        scrollPane.setBorder(null); // Remove border from the scroll pane itself
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Add vertical scroll bar as
                                                                                         // needed

        table.setPreferredScrollableViewportSize(new Dimension(400, 200));


        setLayout(new MigLayout("fill")); // Use fill layout constraints to fill the available space
    }
}
