package view.userUi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Guest;
import model.User;
import view.components.Message;
import view.components.Message.MessageType;
import view.components.items.MyButton;
import view.components.items.MyPasswordField;
import view.components.items.MyTextField;

public class GuestManagement extends UserManagement {
    private Message msg = new Message();

    public GuestManagement() {
        super("Guest");
    }

    private MyPasswordField creatPassword(MyTextField txtName) {
        MyPasswordField txtOasisMail = new MyPasswordField("password", "/view/icons/pass.png");

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
                if (name.isEmpty()) {
                    txtOasisMail.setText("");
                    return;
                }
                txtOasisMail.setText(name + 123);
            }
        };

        txtName.getDocument().addDocumentListener(listener);
        txtOasisMail.setEditable(false);
        return txtOasisMail;
    }

    @Override
    public String[] getColumnNames() {
        return new String[] { "firstName", "lastName", "email" };
    }

    @Override
    public String[][] getData() {
        return User.getAllExceptPassword("Guest", getColumnNames());
    }

    @Override
    public JPanel addUser() {
        JPanel panel = super.addUser();
        MyPasswordField passwordField = creatPassword(super.nameTextField);
        panel.add(passwordField, "w 60%");

        MyButton addButton = new MyButton("Add Guest");
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String name = nameTextField.getText();
                String lastName = lastNameTextField.getText();
                String email = emailTextField.getText();
                String password = String.valueOf(passwordField.getPassword());
                if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    msg.displayMessage(MessageType.ERROR, "All the Fields Are Required", panel, null);

                } else if (!User.isValidEmail(email)) {
                    msg.displayMessage(MessageType.ERROR, "Invalide Email", panel, null);

                } else if (User.research("Guest", "email", email) != null) {
                    msg.displayMessage(MessageType.ERROR, "Guest with email " + email + " already exists.",
                            panel,
                            null);

                } else {
                    Guest guest = new Guest(name, lastName, email, password);
                    guest.addGuestToDataBase();
                    nameTextField.setText("");
                    lastNameTextField.setText("");
                    emailTextField.setText("");
                    passwordField.setText("");
                    msg.displayMessage(MessageType.SUCCESS, "Register succefully", panel, null);
                    refreshAllUsers();
                }
            }

        });
        panel.add(addButton, "w 40%, h 40");
        return panel;
    }

}
