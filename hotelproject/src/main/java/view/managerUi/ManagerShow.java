package view.managerUi;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import net.miginfocom.swing.MigLayout;
import view.login.loginComponents.MyButton;
import view.login.loginComponents.MyPasswordField;
import view.login.loginComponents.MyTextField;

// import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManagerShow extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;

    private JPanel panel;

    public ManagerShow() {
    }

    private void addt() {
        panel = new JPanel();
        setLayout(new BorderLayout());

        panel.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]push"));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Add Receptionist");
        label.setFont(new Font("sansserif", Font.BOLD, 40));
        label.setForeground(new Color(0x1E90FF));
        panel.add(label);

        MyTextField txtName = new MyTextField();
        try {
            Image prefixIcon = new ImageIcon(getClass().getResource("/view/icons/user.png")).getImage();
            txtName.setPrefixIcon(new ImageIcon(prefixIcon));
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtName.setHint("Email");
        panel.add(txtName, "w 60%");

        MyTextField txtLastName = new MyTextField();
        try {
            Image prefixIcon = new ImageIcon(getClass().getResource("/view/icons/user.png")).getImage();
            txtLastName.setPrefixIcon(new ImageIcon(prefixIcon));
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtLastName.setHint("Email");
        panel.add(txtLastName, "w 60%");

        MyTextField txtEmail = new MyTextField();
        try {
            Image prefixIcon = new ImageIcon(getClass().getResource("/view/icons/mail.png")).getImage();
            txtEmail.setPrefixIcon(new ImageIcon(prefixIcon));
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtEmail.setHint("Email");
        panel.add(txtEmail, "w 60%");

        MyPasswordField txtPass = new MyPasswordField();
        try {
            Image prefixIcon = new ImageIcon(getClass().getResource("/view/icons/pass.png")).getImage();
            txtPass.setPrefixIcon(new ImageIcon(prefixIcon));
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtPass.setHint("Password");
        panel.add(txtPass, "w 60%");

        MyButton btnLogIn = new MyButton();
        btnLogIn.setBackground(new Color(0x1E90FF));
        btnLogIn.setForeground(Color.WHITE);
        btnLogIn.setText("SIGN IN");
        panel.add(btnLogIn, "w 40%, h 40");

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
        removeAllComponents();
        Allreceptionist();
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
    }

    public void addReci() {
        removeAllComponents();
        addt();
        add(panel);
        // revalidate();
    }

    public void removeAllComponents() {

        removeAll();
        add(new JLabel(""));
        revalidate();
    }

}
