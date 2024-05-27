package view.UserMangementGui;

import controllers.Controller;
import model.Database;
import model.Guest;
import model.hotel.Hotel;
import model.supervisors.Manager;
import view.components.Message;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestManagement extends UserManagement {

    public GuestManagement() {
        super("Guest");
        // Initiate table with guest data
        Controller.initiateTable("Guests", getColumnNames(), table);
        // Set up table selection listener
        setupTableSelectionListener();
    }

    @Override
    public String[] getColumnNames() {
        return new String[] { "firstName", "lastName", "email" };
    }

    @Override
    public ActionListener addActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Controller.addGuest(nameField.getText(), lastNameField.getText(), emailField.getText(), table);
                    nameField.setText("");
                    lastNameField.setText("");
                    emailField.setText("");
                    msg.displayMessage(Message.MessageType.SUCCESS, "Guest added successfully",
                            (JPanel) userInputPanel.getParent().getParent().getParent(), layout);
                } catch (Exception ex) {
                    msg.displayMessage(Message.MessageType.ERROR, ex.getMessage(),
                            (JPanel) userInputPanel.getParent().getParent().getParent(), layout);
                }
            }
        };
    }

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
                    }
                }
            }
        });
    }

    @Override
    public ActionListener deleteActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    // Get the email from the selected row
                    String email = (String) table.getValueAt(selectedRow, 2); // Assuming email is at index 2

                    try {
                        // Delete the guest/worker from the database
                        // Database.deleteFromDataBase("Guests", "email", email);
                        if (!emailField.getText().trim().isEmpty()) {

                            Manager.removeGuestFromDataBase(email);

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
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                lastNameField.setText("");
                emailField.setText("");
            }
        };
    }
}
