package view.userUi;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import controllers.Controller;
import model.User;
import net.miginfocom.swing.MigLayout;
import view.components.Message;
import view.components.Message.MessageType;
import view.components.items.MyButton;
import view.components.items.MyTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class UserManagement extends JPanel {
    private JTabbedPane userTabbedPane;
    private String collectionName;
    private JLabel titleLabel;
    protected MyTextField nameTextField;
    protected MyTextField lastNameTextField;
    protected MyTextField emailTextField;
    protected MyTextField emailToDelete;

    public UserManagement(String collectionName) {
        this.collectionName = collectionName;

    }

    public abstract String[] getColumnNames();

    public abstract String[][] getData();

    private JScrollPane allUsers() {
        String[][] data = getData();
        String[] columnNames = getColumnNames();
        JTable table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setForeground(Color.BLACK);
        table.setGridColor(Color.BLACK);
        table.setBackground(Color.WHITE);
        table.setRowHeight(40);
        table.setIntercellSpacing(new Dimension(5, 8));
        table.setShowGrid(true);
        table.setCellSelectionEnabled(false); // Disable cell selection
        table.setRowSelectionAllowed(false); // Allow row selection (if needed)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        JTableHeader header = table.getTableHeader();
        Font headerFont = new Font("Ink free", Font.BOLD, 16);
        Color headerTextColor = Color.WHITE;
        Color headerBackgroundColor = new Color(0x4FB5FF);
        header.setFont(headerFont);
        header.setForeground(headerTextColor);
        header.setBackground(headerBackgroundColor);
        int headerHeight = 40;
        Dimension headerDimension = new Dimension(header.getPreferredSize().width, headerHeight);
        header.setPreferredSize(headerDimension);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(600, 300));

        return scrollPane;
    }

    protected JPanel addUser() {
        JPanel panel = new JPanel();
        setLayout(new BorderLayout());

        panel.setLayout(new MigLayout("wrap", "push[center]push",
                "push[]25[]10[]10[]10[]10[]push"));
        panel.setBackground(Color.WHITE);
        titleLabel = new JLabel();
        titleLabel.setFont(new Font("sansserif", Font.BOLD, 40));
        titleLabel.setForeground(new Color(0x1E90FF));
        titleLabel.setText("Add " + this.collectionName);

        panel.add(titleLabel);

        nameTextField = new MyTextField("Name", "/view/icons/user.png");
        lastNameTextField = new MyTextField("Lastname", "/view/icons/user.png");
        emailTextField = new MyTextField("Email", "/view/icons/mail.png");

        panel.add(nameTextField, "w 60%");
        panel.add(lastNameTextField, "w 60%");
        panel.add(emailTextField, "w 60%");

        return panel;
    }

    private JPanel deleteUser() {
        JPanel deletePanel = new JPanel();
        setLayout(new BorderLayout());

        deletePanel.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]push"));
        deletePanel.setBackground(Color.WHITE);
        titleLabel = new JLabel();
        titleLabel.setFont(new Font("sansserif", Font.BOLD, 40));
        titleLabel.setForeground(new Color(0x1E90FF));
        titleLabel.setText("Delete " + this.collectionName);

        deletePanel.add(titleLabel);

        emailToDelete = new MyTextField("Email", "/view/icons/mail.png");
        deletePanel.add(emailToDelete, "w 60%");

        MyButton deleteButton = new MyButton("Delete " + this.collectionName);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Message msg = new Message();
                if (emailToDelete.getText().isEmpty()) {
                    msg.displayMessage(MessageType.ERROR, "Email field Empty", deletePanel, null);
                } else if (!User.isValidEmail(emailToDelete.getText())) {
                    msg.displayMessage(MessageType.ERROR, "Invalide email", deletePanel, null);
                } else if (Controller.getUserFromModel(collectionName, "email", emailToDelete.getText()) == null) {
                    msg.displayMessage(MessageType.ERROR, "No " + collectionName + " with this email", deletePanel,
                            null);
                } else {
                    User.deleteByEmail(collectionName, emailToDelete.getText());
                    emailToDelete.setText("");
                    msg.displayMessage(MessageType.SUCCESS, collectionName + " deleted succefuly", deletePanel, null);
                    refreshAllUsers();
                }
            }

        });
        deletePanel.add(deleteButton, "w 40%, h 40");

        return deletePanel;
    }

    private JPanel navigateTabs() {
        JPanel panel = new JPanel();
        setLayout(new BorderLayout());

        panel.setLayout(new MigLayout("wrap", "push[center]push", "push[]35[]35[]35[]push"));
        panel.setBackground(Color.WHITE);

        MyButton allUsersButton = new MyButton("All " + this.collectionName + "s");
        MyButton addUserButton = new MyButton("Add " + this.collectionName);
        MyButton deleteUserButton = new MyButton("Delete " + this.collectionName);

        allUsersButton.setFont(new Font("sansserif", Font.BOLD, 20));
        addUserButton.setFont(new Font("sansserif", Font.BOLD, 20));
        deleteUserButton.setFont(new Font("sansserif", Font.BOLD, 20));

        allUsersButton.addActionListener(e -> userTabbedPane.setSelectedIndex(1));
        addUserButton.addActionListener(e -> userTabbedPane.setSelectedIndex(2));
        deleteUserButton.addActionListener(e -> userTabbedPane.setSelectedIndex(3));

        panel.add(allUsersButton, "w 60%, h 50");
        panel.add(addUserButton, "w 60%, h 50");
        panel.add(deleteUserButton, "w 60%, h 50");

        return panel;
    }

    protected void refreshAllUsers() {
        int tabIndex = userTabbedPane.indexOfTab("All" + this.collectionName);
        if (tabIndex != -1) {
            Component tabComponent = userTabbedPane.getComponentAt(tabIndex);
            if (tabComponent instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) tabComponent;
                JViewport viewport = scrollPane.getViewport();
                viewport.removeAll();
                viewport.add(allUsers());
            }
        }
    }

    public JTabbedPane createUserTabbedPane() {
        userTabbedPane = new JTabbedPane();
        userTabbedPane.addTab("Navigation", this.navigateTabs());
        userTabbedPane.addTab("All " + this.collectionName, this.allUsers());
        userTabbedPane.addTab("Add " + this.collectionName, this.addUser());
        userTabbedPane.addTab("Delete " + this.collectionName, this.deleteUser());
        userTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        userTabbedPane.setBorder(null);
        return userTabbedPane;
    }
}

// private JPanel addUser() {
// JPanel panel = new JPanel();
// setLayout(new BorderLayout());

// panel.setLayout(new MigLayout("wrap", "push[center]push",
// "push[]25[]10[]10[]10[]10[]push"));
// panel.setBackground(Color.WHITE);

// JLabel titleLabel = new JLabel("Add " + this.collectionName);
// titleLabel.setFont(new Font("sansserif", Font.BOLD, 40));
// titleLabel.setForeground(new Color(0x1E90FF));
// panel.add(titleLabel);

// MyTextField nameTextField = new MyTextField("Name", "/view/icons/user.png");
// MyTextField lastNameTextField = new MyTextField("Lastname",
// "/view/icons/user.png");
// MyTextField emailTextField = new MyTextField("Email",
// "/view/icons/mail.png");

// ArrayList<JTextComponent> arr = new ArrayList<>(
// Arrays.asList(nameTextField, lastNameTextField, emailTextField));

// panel.add(nameTextField, "w 60%");
// panel.add(lastNameTextField, "w 60%");
// panel.add(emailTextField, "w 60%");

// if (this.collectionName.equals("Receptionists")) {
// MyTextField oasisMailTextField = createOasisMailField(nameTextField,
// lastNameTextField);
// panel.add(oasisMailTextField, "w 60%");
// arr.add(oasisMailTextField);
// } else {
// passwordField = new MyPasswordField("Password", "/view/icons/pass.png");
// panel.add(passwordField, "w 60%");
// arr.add(passwordField);
// }

// MyButton addButton = new MyButton("Add " + this.collectionName);

// addButton.addActionListener(new ActionListener() {
// @Override
// public void actionPerformed(ActionEvent e) {
// String name = nameTextField.getText().trim();
// String lastName = lastNameTextField.getText().trim();
// String email = emailTextField.getText().trim();
// Message msg = new Message();

// if (name.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
// msg.displayMessage(view.components.Message.MessageType.ERROR, "All fields are
// required", panel,
// null);
// return;
// }

// if (!User.isValidEmail(email)) {
// msg.displayMessage(view.components.Message.MessageType.ERROR, "Invalid
// email", panel, null);
// return;
// }

// if (collectionName.equals("Receptionists")) {
// Receptionist receptionist = new Receptionist(name, lastName, email);
// receptionist.addReceptionistToDataBase();

// } else {
// String password = String.valueOf(passwordField.getPassword());
// Guest guest = new Guest(name, lastName, password, email);
// guest.addGuestToDataBase();
// }

// for (JTextComponent textField : arr) {
// textField.setText("");
// }
// msg.displayMessage(view.components.Message.MessageType.SUCCESS, "User added
// successfully", panel, null);

// // Refresh the "All Users" tab
// refreshAllUsers();
// }
// });
// panel.add(addButton, "w 40%, h 40");

// return panel;
// }

// package view.managerUi;

// import javax.swing.*;
// import javax.swing.event.DocumentEvent;
// import javax.swing.event.DocumentListener;
// import javax.swing.table.DefaultTableCellRenderer;
// import javax.swing.table.JTableHeader;

// import model.User;
// import net.miginfocom.swing.MigLayout;
// import view.login.loginComponents.MyButton;
// import view.login.loginComponents.MyPasswordField;
// import view.login.loginComponents.MyTextField;

// import java.awt.*;

// public class ReceptionistsManagement extends JPanel {

// private JTabbedPane receptionistTabbedPane;
// private String collectionName = "Client";

// private JScrollPane AllReceptionist() {
// // Create table data
// String[] columnNames = (this.collectionName.equals("Receptionists"))
// ? new String[] { "firstName", "lastName", "email", "OasisMail" }
// : new String[] { "firstName", "lastName", "email" };

// String[][] data = User.getAllExceptPassword("Client", columnNames);

// // Create table
// JTable table = new JTable(data, columnNames);
// table.setFont(new Font("SansSerif", Font.PLAIN, 14));
// table.setForeground(Color.BLACK);
// table.setGridColor(Color.BLACK);
// table.setBackground(Color.WHITE);

// // Customize table rendering
// table.setRowHeight(40); // Set row height
// table.setIntercellSpacing(new Dimension(5, 8)); // Set spacing between cells
// table.setShowGrid(true); // Show grid lines
// table.setCellSelectionEnabled(false);
// DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
// centerRenderer.setHorizontalAlignment(JLabel.CENTER);
// table.setDefaultRenderer(Object.class, centerRenderer);

// // Customize header
// JTableHeader header = table.getTableHeader();
// Font headerFont = new Font("Ink free", Font.BOLD, 16); // Define header font
// Color headerTextColor = Color.WHITE; // Define header text color
// Color headerBackgroundColor = new Color(0x4FB5FF); // Define header
// background color

// header.setFont(headerFont); // Set header font
// header.setForeground(headerTextColor); // Set header text color
// header.setBackground(headerBackgroundColor); // Set header background color

// // Adjust the height of the table header
// int headerHeight = 40; // Specify the desired height
// Dimension headerDimension = new Dimension(header.getPreferredSize().width,
// headerHeight);
// header.setPreferredSize(headerDimension); // Set header background color
// // header.setReorderingAllowed(false); // Disallow column reordering

// // Customize scroll pane
// JScrollPane scrollPane = new JScrollPane(table);
// scrollPane.getViewport().setBackground(Color.WHITE);
// scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove scroll
// pane border
// scrollPane.setPreferredSize(new Dimension(600, 300)); // Set preferred size

// return scrollPane;
// }

// private JPanel AddReceptionist() {
// JPanel panel = new JPanel();
// setLayout(new BorderLayout());

// panel.setLayout(new MigLayout("wrap", "push[center]push",
// "push[]25[]10[]10[]10[]10[]push"));
// panel.setBackground(Color.WHITE);

// JLabel label = new JLabel("Add Receptionist");
// label.setFont(new Font("sansserif", Font.BOLD, 40));
// label.setForeground(new Color(0x1E90FF));
// panel.add(label);

// MyTextField txtName = new MyTextField("Name", "/view/icons/user.png");
// MyTextField txtLastName = new MyTextField("Lastname",
// "/view/icons/user.png");
// MyTextField txtEmail = new MyTextField("Email", "/view/icons/mail.png");
// MyTextField txtOasisMail = new MyTextField("OasisMail",
// "/view/icons/mail.png");

// MyButton btnLogIn = new MyButton("Add Receptionist");
// panel.add(txtName, "w 60%");
// panel.add(txtLastName, "w 60%");
// panel.add(txtEmail, "w 60%");
// panel.add(txtOasisMail, "w 60%");
// panel.add(btnLogIn, "w 40%, h 40");

// // Add DocumentListener to txtName and txtLastName fields
// txtName.getDocument().addDocumentListener(new DocumentListener() {
// @Override
// public void insertUpdate(DocumentEvent e) {
// updateOasisMail();
// }

// @Override
// public void removeUpdate(DocumentEvent e) {
// updateOasisMail();
// }

// @Override
// public void changedUpdate(DocumentEvent e) {
// updateOasisMail();
// }

// private void updateOasisMail() {
// String name = txtName.getText().trim();
// String lastName = txtLastName.getText().trim();
// if (name.isEmpty() && lastName.isEmpty()) {
// txtOasisMail.setText("");
// return;
// }
// txtOasisMail.setText(name + lastName + "@Oasis.dz");
// }
// });
// txtOasisMail.setEditable(false);

// txtLastName.getDocument().addDocumentListener(new DocumentListener() {
// @Override
// public void insertUpdate(DocumentEvent e) {
// updateOasisMail();
// }

// @Override
// public void removeUpdate(DocumentEvent e) {
// updateOasisMail();
// }

// @Override
// public void changedUpdate(DocumentEvent e) {
// updateOasisMail();
// }

// private void updateOasisMail() {
// String name = txtName.getText().trim();
// String lastName = txtLastName.getText().trim();
// if (name.isEmpty() && lastName.isEmpty()) {
// txtOasisMail.setText("");
// return;
// }
// txtOasisMail.setText(name + lastName + "@Oasis.dz");
// }
// });

// return panel;
// }

// private JPanel DeleteReceptionist() {
// JPanel panel = new JPanel();
// setLayout(new BorderLayout());

// panel.setLayout(new MigLayout("wrap", "push[center]push",
// "push[]25[]10[]10[]10[]10[]push"));
// panel.setBackground(Color.WHITE);

// JLabel label = new JLabel("Delete Receptionist");
// label.setFont(new Font("sansserif", Font.BOLD, 40));
// label.setForeground(new Color(0x1E90FF));
// panel.add(label);

// MyTextField txtName = new MyTextField("Name", "/view/icons/user.png");
// MyTextField txtLastName = new MyTextField("Lastname",
// "/view/icons/user.png");
// MyTextField txtEmail = new MyTextField("Email", "/view/icons/mail.png");
// MyTextField txt = new MyTextField("Email", "/view/icons/mail.png");

// MyButton btnLogIn = new MyButton("Delete Receptionist");
// panel.add(txtName, "w 60%");
// panel.add(txtLastName, "w 60%");
// panel.add(txtEmail, "w 60%");
// panel.add(txt, "w 60%");
// panel.add(btnLogIn, "w 40%, h 40");

// return panel;
// }

// private JPanel chem() {

// JPanel panel = new JPanel();
// setLayout(new BorderLayout());

// panel.setLayout(new MigLayout("wrap", "push[center]push",
// "push[]35[]35[]35[]push"));
// panel.setBackground(Color.WHITE);

// MyButton btnLogIn1 = new MyButton("All Receeptionists");
// btnLogIn1.setFont(new Font("sansserif", Font.BOLD, 20));
// btnLogIn1.addActionListener(e -> receptionistTabbedPane.setSelectedIndex(1));
// panel.add(btnLogIn1, "w 60%, h 50");

// MyButton btnLogIn2 = new MyButton("Add Receeptionist");
// btnLogIn2.setFont(new Font("sansserif", Font.BOLD, 20));
// btnLogIn2.addActionListener(e -> receptionistTabbedPane.setSelectedIndex(2));
// panel.add(btnLogIn2, "w 60%, h 50");

// MyButton btnLogIn3 = new MyButton("Delete Receeptionist");
// btnLogIn3.setFont(new Font("sansserif", Font.BOLD, 20));
// btnLogIn3.addActionListener(e -> receptionistTabbedPane.setSelectedIndex(3));
// panel.add(btnLogIn3, "w 60%, h 50");
// return panel;
// }

// public JTabbedPane CreactreceptionistTabbedPane() {
// receptionistTabbedPane = new JTabbedPane();
// receptionistTabbedPane.addTab("Receptionists", this.chem());
// receptionistTabbedPane.addTab("AllReceptionist", this.AllReceptionist());
// receptionistTabbedPane.addTab("AddReceptionist", this.AddReceptionist());
// receptionistTabbedPane.addTab("DeleteReceptionist",
// this.DeleteReceptionist());
// // receptionistTabbedPane.setUI(new HiddenTabTitleUI());
// receptionistTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
// receptionistTabbedPane.setBorder(null);
// return receptionistTabbedPane;
// }
// }

// protected JPanel addUser() {
// JPanel panel = new JPanel();
// setLayout(new BorderLayout());
// panel.setLayout(new MigLayout("wrap", "push[center]push",
// "push[]25[]10[]10[]10[]10[]push"));
// panel.setBackground(Color.WHITE);
// titleLabel.setText("Add " + this.collectionName);

// panel.add(titleLabel);

// nameTextField = new MyTextField("Name", "/view/icons/user.png");
// lastNameTextField = new MyTextField("Lastname", "/view/icons/user.png");
// emailTextField = new MyTextField("Email", "/view/icons/mail.png");

// panel.add(nameTextField, "w 60%");
// panel.add(lastNameTextField, "w 60%");
// panel.add(emailTextField, "w 60%");

// return panel;
// }