package view.UserMangementGui;

import controllers.Controller;
import model.User;
import view.components.Message;
import view.components.items.MyTextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
        Controller.initiateTable("Workers",getColumnNames(),table);
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
                    Controller.addReceptionist(nameField.getText(), lastNameField.getText(), emailField.getText(),txtOasisMail.getText(),table);
                    nameField.setText("");
                    lastNameField.setText("");
                    emailField.setText("");
                    msg.displayMessage(Message.MessageType.SUCCESS, "Receptionist added successfully", (JPanel) userInputPanel.getParent().getParent().getParent(), layout);
                } catch (Exception ex) {
                    msg.displayMessage(Message.MessageType.ERROR, ex.getMessage(), (JPanel) userInputPanel.getParent().getParent().getParent(), layout);
                }
            }
        };
    }

    @Override
    public ActionListener deleteActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementation for deleting a receptionist

                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
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
                String oasisMail=name + lastName ;
                User rs;
                rs=Controller.getUserFromModel("Workers","OasisMail",(oasisMail+Controller.DOMAIN_RECEPTIONIST));
                if (rs==null){
                    txtOasisMail.setText((oasisMail+Controller.DOMAIN_RECEPTIONIST));
                }else{
                    txtOasisMail.setText((oasisMail+random.nextInt(100)+Controller.DOMAIN_RECEPTIONIST));
                }
            }
        };

        // Add document listener to update Oasis email dynamically
        txtName.getDocument().addDocumentListener(listener);
        txtLastName.getDocument().addDocumentListener(listener);
        txtOasisMail.setEditable(false);
        return txtOasisMail;
    }

    // Method to add sample data to the table
    public void add() {


//        table.addRow(new Object[] { "Mike", "Bhand", "mikebhand@gmail.com", "MikeBhand@Oasis.dz" });
//        table.addRow(new Object[] { "John", "Doe", "johndoe@example.com", "JohnDoe@oasis.dz", "15 May, 2019" });
//        table.addRow(new Object[] { "Emily", "Smith", "emilysmith@example.com", "EmilySmith@oasis.dz" });
//        table.addRow(new Object[] { "David", "Johnson", "davidjohnson@example.com", "DavidJohnson@oasis.dz" });
//        table.addRow(new Object[] { "Sarah", "Brown", "sarahbrown@example.com", "SarahBrown@oasis.dz" });
//        table.addRow(new Object[] { "Michael", "Williams", "michaelwilliams@example.com",
//                "MichaelWilliams@oasis.dz" });
//        table.addRow(new Object[] { "Jessica", "Taylor", "jessicataylor@example.com",
//                "JessicaTaylor@oasis.dz" });
//        table.addRow(new Object[] { "James", "Anderson", "jamesanderson@example.com",
//                "JamesAnderson@oasis.dz" });
//        table.addRow(new Object[] { "Emma", "Thomas", "emmathomas@example.com", "EmmaThomas@oasis.dz" });
//        table.addRow(new Object[] { "William", "Martinez", "williammartinez@example.com",
//                "WilliamMartinez@oasis.dz" });
//        table.addRow(new Object[] { "Olivia", "Garcia", "oliviagarcia@example.com", "OliviaGarcia@oasis.dz" });
    }
}
