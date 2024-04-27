package view.UserMangementGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.components.items.MyTextField;

public class ReceptionistsManagement extends UserManagement {

    public ReceptionistsManagement() {
        super("Receptionist");
        addOasisEmail(); // Add Oasis email field to user input panel
        add(); // Add sample data to the table
    }

    @Override
    public String[] getCollumnNames() {
        // Define column names for the table
        return new String[] { "firstName", "lastName", "email", "OasisMail" };
    }

    @Override
    public ActionListener addActionListener() {
        // ActionListener for adding a new receptionist
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementation for adding a new receptionist
                System.out.println(12);
            }
        };
    }

    @Override
    public ActionListener deleteActionListener() {
        // ActionListener for deleting a receptionist
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementation for deleting a receptionist
                System.out.println(12);
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

    @Override
    public ActionListener updateActionListener() {
        // ActionListener for updating a receptionist
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementation for updating a receptionist
            }
        };
    }

    // Method to add Oasis email field to the user input panel
    private void addOasisEmail() {
        super.userInputPanel.add(new JLabel("OasisEmail: "));
        // Create and add Oasis email field to user input panel
        super.userInputPanel.add(createOasisMailField(super.nameField, super.lastNameField), "growx, width 60%");
    }

    // Method to create Oasis email field
    private MyTextField createOasisMailField(MyTextField txtName, MyTextField txtLastName) {
        MyTextField txtOasisMail = new MyTextField("OasisMail", "/view/icons/mail.png");

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
                txtOasisMail.setText(name + lastName + "@Oasis.dz");
            }
        };

        // Add document listener to update Oasis email dynamically
        txtName.getDocument().addDocumentListener(listener);
        txtLastName.getDocument().addDocumentListener(listener);
        txtOasisMail.setEditable(false); // Make Oasis email field non-editable
        return txtOasisMail;
    }

    // Method to add sample data to the table
    public void add() {
        // Add sample data rows to the table
        table.addRow(new Object[] { "Mike", "Bhand", "mikebhand@gmail.com", "MikeBhand@Oasis.dz" });
        table.addRow(new Object[] { "John", "Doe", "johndoe@example.com", "JohnDoe@oasis.dz", "15 May, 2019" });
        table.addRow(new Object[] { "Emily", "Smith", "emilysmith@example.com", "EmilySmith@oasis.dz" });
        table.addRow(new Object[] { "David", "Johnson", "davidjohnson@example.com", "DavidJohnson@oasis.dz" });
        table.addRow(new Object[] { "Sarah", "Brown", "sarahbrown@example.com", "SarahBrown@oasis.dz" });
        table.addRow(new Object[] { "Michael", "Williams", "michaelwilliams@example.com",
                "MichaelWilliams@oasis.dz" });
        table.addRow(new Object[] { "Jessica", "Taylor", "jessicataylor@example.com",
                "JessicaTaylor@oasis.dz" });
        table.addRow(new Object[] { "James", "Anderson", "jamesanderson@example.com",
                "JamesAnderson@oasis.dz" });
        table.addRow(new Object[] { "Emma", "Thomas", "emmathomas@example.com", "EmmaThomas@oasis.dz" });
        table.addRow(new Object[] { "William", "Martinez", "williammartinez@example.com",
                "WilliamMartinez@oasis.dz" });
        table.addRow(new Object[] { "Olivia", "Garcia", "oliviagarcia@example.com", "OliviaGarcia@oasis.dz" });
    }
}
