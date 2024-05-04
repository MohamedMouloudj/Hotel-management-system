package view.UserMangementGui;

import controllers.Controller;
import view.components.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestManagement extends UserManagement {

    public GuestManagement() {
        super("Guest");
//        add();
        Controller.initiateTable("Guests",getColumnNames(),table);
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
                   Controller.addGuest(nameField.getText(), lastNameField.getText(), emailField.getText(),table);
                   nameField.setText("");
                   lastNameField.setText("");
                   emailField.setText("");
                   msg.displayMessage(Message.MessageType.SUCCESS, "Guest added successfully", (JPanel) userInputPanel.getParent().getParent().getParent(), layout);
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
                // TODO Auto-generated method stub
                System.out.println(12);
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
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
