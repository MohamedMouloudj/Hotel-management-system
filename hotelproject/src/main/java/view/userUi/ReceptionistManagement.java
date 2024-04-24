package view.userUi;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controllers.Controller;
import model.supervisors.Manager;
import model.supervisors.Receptionist;
import model.User;
import view.components.Message;
import view.components.Message.MessageType;
import view.components.items.MyButton;
import view.components.items.MyTextField;

public class ReceptionistManagement extends UserManagement {
    Message msg = new Message();

    public ReceptionistManagement() {
        super("Receptionist");
    }

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

            private void updateOasisMail() {
                String name = txtName.getText().trim();
                String lastName = txtLastName.getText().trim();
                if (name.isEmpty() && lastName.isEmpty()) {
                    txtOasisMail.setText("");
                    return;
                }
                String email = name + lastName + "@Oasis.dz";
                txtOasisMail.setText(name + lastName
                        + (Controller.getUserFromModel("Receptionists", "OasisMail", email) != null ? new Random().nextInt(100) : "")
                        + "@Oasis.dz");
            }
        };

        txtName.getDocument().addDocumentListener(listener);
        txtLastName.getDocument().addDocumentListener(listener);
        txtOasisMail.setEditable(false);
        return txtOasisMail;
    }

    @Override
    public String[] getColumnNames() {
        return new String[] { "firstName", "lastName", "email", "OasisMail" };
    }

    @Override
    public String[][] getData() {
        return Controller.databaseToViewWithoutPassword("Receptionist", getColumnNames());
    }

    @Override
    public JPanel addUser() {
        JPanel panel = super.addUser();
        panel.setBackground(Color.WHITE);
        MyTextField oasisMailTextField = createOasisMailField(super.nameTextField,
                super.lastNameTextField);
        panel.add(oasisMailTextField, "w 60%");
        MyButton addButton = new MyButton("Add Receptionist");
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String name = nameTextField.getText();
                String lastName = lastNameTextField.getText();
                String email = emailTextField.getText();
                if (name.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                    msg.displayMessage(MessageType.ERROR, "All the Fields Are Required", panel, null);

                } else if (!User.isValidEmail(email)) {
                    msg.displayMessage(MessageType.ERROR, "Invalide Email", panel, null);

                } else if (Controller.getUserFromModel("Receptionist", "email", email) != null) {
                    msg.displayMessage(MessageType.ERROR, "Receptionist with email " + email + " already exists.",
                            panel,
                            null);

                } else {
                    Receptionist receptionist = new Receptionist(name, lastName, email);
                    Manager.addWorkerToDataBase(receptionist);
                    nameTextField.setText("");
                    lastNameTextField.setText("");
                    emailTextField.setText("");
                    msg.displayMessage(MessageType.SUCCESS, "Register succefully", panel, null);
                    refreshAllUsers();
                }
            }

        });
        panel.add(addButton, "w 40%, h 40");
        return panel;
    }

}
