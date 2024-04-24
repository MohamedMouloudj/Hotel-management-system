package view.UserMangementGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestManagement extends UserManagement {

    public GuestManagement() {
        super("Guest");
        add();
    }

    @Override
    public String[] getCollumnNames() {
        return new String[] { "firstName", "lastName", "email" };
    }

    @Override
    public ActionListener addActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(12);

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

    @Override
    public ActionListener updateActionListener() {
        // TODO Auto-generated method stub
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }

        };
    }

    public void add() {
        table.addRow(new Object[] { "Mike", "Bhand", "mikebhand@gmail.com", });
        table.addRow(new Object[] { "John", "Doe", "johndoe@example.com", });
        table.addRow(new Object[] { "Emily", "Smith", "emilysmith@example.com", });
        table.addRow(new Object[] { "David", "Johnson", "davidjohnson@example.com", });
        table.addRow(new Object[] { "Sarah", "Brown", "sarahbrown@example.com", });
        table.addRow(new Object[] { "Michael", "Williams", "michaelwilliams@example.com",
        });
        table.addRow(new Object[] { "Jessica", "Taylor", "jessicataylor@example.com",
        });
        table.addRow(new Object[] { "James", "Anderson", "jamesanderson@example.com",
        });
        table.addRow(new Object[] { "Emma", "Thomas", "emmathomas@example.com", });
        table.addRow(new Object[] { "William", "Martinez", "williammartinez@example.com",
        });
        table.addRow(new Object[] { "Olivia", "Garcia", "oliviagarcia@example.com", });

    }
}
