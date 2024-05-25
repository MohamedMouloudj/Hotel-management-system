package view.UserMangementGui;

import controllers.Controller;
import model.User;
import model.supervisors.Manager;
import view.components.Message;
import view.components.items.MyTextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ReceptionistsManagement extends UserManagement {
    private MyTextField txtOasisMail;
    private final Random random = new Random();

    public ReceptionistsManagement() {
        super("Receptionist");
        addOasisEmail(); // Add Oasis email field to user input panel
        Controller.initiateTable("Workers", getColumnNames(), table);
        setupTableSelectionListener(); // Setup table selection listener
    }

    @Override
    public String[] getColumnNames() {
        // Define column names for the table
        return new String[] { "firstName", "lastName", "email", "OasisMail" };
    }

    @Override
    public ActionListener addActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Add a receptionist to the database
                    Controller.addReceptionist(nameField.getText(), lastNameField.getText(), emailField.getText(),
                            txtOasisMail.getText(), table);
                    nameField.setText("");
                    lastNameField.setText("");
                    emailField.setText("");
                    msg.displayMessage(Message.MessageType.SUCCESS, "Receptionist added successfully",
                            (JPanel) userInputPanel.getParent().getParent().getParent(), layout);
                } catch (Exception ex) {
                    msg.displayMessage(Message.MessageType.ERROR, ex.getMessage(),
                            (JPanel) userInputPanel.getParent().getParent().getParent(), layout);
                }
            }
        };
    }

    @Override
    public ActionListener deleteActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    // Get the email from the selected row
                    String OasisEmail = (String) table.getValueAt(selectedRow, 3); // Assuming email is at index 2

                    try {
                        // Delete the guest/worker from the database
                        // Database.deleteFromDataBase("Guests", "email", email);
                        if (!emailField.getText().trim().isEmpty()) {

                            Manager.removeWorkerFromDataBase(OasisEmail);

                            // Remove the row from the table
                            DefaultTableModel model = (DefaultTableModel) table.getModel();
                            model.removeRow(selectedRow);

                            // Clear the input fields
                            nameField.setText("");
                            lastNameField.setText("");
                            emailField.setText("");

                            // Display a success message
                            msg.displayMessage(Message.MessageType.SUCCESS, "Guest deleted successfully",
                                    (JPanel) userInputPanel.getParent().getParent().getParent(), layout);
                        } else {
                            msg.displayMessage(Message.MessageType.SUCCESS, "All fields are required",
                                    (JPanel) userInputPanel.getParent().getParent().getParent(), layout);
                        }
                    } catch (Exception ex) {
                        // Display an error message
                        msg.displayMessage(Message.MessageType.ERROR, ex.getMessage(),
                                (JPanel) userInputPanel.getParent().getParent().getParent(), layout);
                    }
                }
            }
        };
    }

    @Override
    public ActionListener clearActionListener() {
        // ActionListener for clearing input fields
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear text fields
                nameField.setText("");
                lastNameField.setText("");
                emailField.setText("");
            }
        };
    }

    // Method to add Oasis email field to the user input panel
    private void addOasisEmail() {
        JLabel lblOasisMail = new JLabel("Oasis Mail:");
        lblOasisMail.setFont(new Font("SansSerif", Font.BOLD, 14));
        super.userInputPanel.add(lblOasisMail);
        super.userInputPanel.add(createOasisMailField(super.nameField, super.lastNameField), "growx, width 60%");
    }

    // Method to create Oasis email field
    private MyTextField createOasisMailField(MyTextField txtName, MyTextField txtLastName) {
        txtOasisMail = new MyTextField("OasisMail", "hotelproject/src/main/java/view/icons/mail.png");

        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateOasisMail();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateOasisMail();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateOasisMail();
            }

            // Method to update Oasis email based on name and last name
            private void updateOasisMail() {
                String name = txtName.getText().trim();
                String lastName = txtLastName.getText().trim();
                if (name.isEmpty() && lastName.isEmpty()) {
                    txtOasisMail.setText("");
                    return;
                }
                String oasisMail = name + lastName;
                User rs;
                rs = Controller.getUserFromModel("Workers", "OasisMail", (oasisMail + Controller.DOMAIN_RECEPTIONIST));
                if (rs == null) {
                    txtOasisMail.setText((oasisMail + Controller.DOMAIN_RECEPTIONIST));
                } else {
                    txtOasisMail.setText((oasisMail + random.nextInt(100) + Controller.DOMAIN_RECEPTIONIST));
                }
            }
        };

        // Add document listener to update Oasis email dynamically
        txtName.getDocument().addDocumentListener(listener);
        txtLastName.getDocument().addDocumentListener(listener);
        txtOasisMail.setEditable(false);
        return txtOasisMail;
    }

    // Setup table selection listener
    private void setupTableSelectionListener() {
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow >= 0) {
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        nameField.setText(model.getValueAt(selectedRow, 0).toString());
                        lastNameField.setText(model.getValueAt(selectedRow, 1).toString());
                        emailField.setText(model.getValueAt(selectedRow, 2).toString());
                        txtOasisMail.setText(model.getValueAt(selectedRow, 3).toString());
                    }
                }
            }
        });
    }

}
