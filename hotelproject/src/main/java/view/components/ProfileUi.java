package view.components;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import model.Role;
public class ProfileUi extends JPanel {
    private JPanel Container;
    private JLabel Email;
    private JLabel FirstName;
    private JLabel LastName;
    private JLabel Password;
    DynamicButton editButton = new DynamicButton("Edit");
    public ProfileUi() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        Container = new JPanel();
        LastName = new JLabel();
        FirstName = new JLabel();
        Password = new JLabel();
        Email = new JLabel();

        editButton.setButtonBgColor(new Color(0xADADAD));
        editButton.setForeground(Color.BLACK);
        editButton.setIconToButton(new ImageIcon("hotelproject/src/main/java/view/icons/edit.png"),15,4);

        setLayout(new MigLayout("wrap 1,center", "[]", "push[]push"));

        Container.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2, true));
        Container.setAutoscrolls(true);
        Container.setPreferredSize(new Dimension(500, 400));
        Container.setLayout(new MigLayout("center", "20[]20[grow,fill]20", "20[grow]20"));

        FirstName.setFont(new Font("Arial", Font.BOLD, 12)); // NOI18N
        FirstName.setHorizontalAlignment(SwingConstants.LEFT);
        FirstName.setText("First name:");
        Container.add(FirstName,"cell 0 0");


        LastName.setFont(new Font("Arial", Font.BOLD, 12)); // NOI18N
        LastName.setHorizontalAlignment(SwingConstants.LEFT);
        LastName.setText("Last name:");
        Container.add(LastName,"cell 0 1");

        Email.setFont(new Font("Arial", Font.BOLD, 12)); // NOI18N
        Email.setHorizontalAlignment(SwingConstants.LEFT);
        Email.setText("Email:");
        Container.add(Email,"cell 0 2");


        Password.setFont(new Font("Arial", Font.BOLD, 12));
        Password.setHorizontalAlignment(SwingConstants.LEFT);
        Password.setText("Password:");
        Container.add(Password,"cell 0 3");

        Container.add(editButton,"cell 0 5,alignx center,spanx 2");

//        GroupLayout layout = new GroupLayout(this);
//        this.setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(100, 100, 100)
//                .addComponent(Container, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
//                .addGap(100, 100, 100))
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(100, 100, 100)
//                .addComponent(Container, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//                .addContainerGap(100, Short.MAX_VALUE))
//        );

        add(Container);
    }

    public void addFirstName(String firstName){
        JLabel firstNameLabel = new JLabel();
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        firstNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        firstNameLabel.setText(firstName);
        Container.add(firstNameLabel,"cell 1 0");
    }
    public void addLastName(String lastName){
        JLabel lastNameLabel = new JLabel();
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        lastNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lastNameLabel.setText(lastName);
        Container.add(lastNameLabel,"cell 1 1");
    }
    public void addPassword(String password){
        JLabel passwordLabel = new JLabel();
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        passwordLabel.setText(password);
        Container.add(passwordLabel,"cell 1 3");
    }
    public void addEmail(String email){
        JLabel emailLabel = new JLabel();
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
        emailLabel.setText(email);
        Container.add(emailLabel,"cell 1 2");
    }
    public void updateFirstName(String firstName){
        ((JLabel)Container.getComponent(1)).setText(firstName);
    }
    public void updateLastName(String lastName){
        ((JLabel)Container.getComponent(3)).setText(lastName);
    }
    public void updatePassword(String password){
        ((JLabel)Container.getComponent(7)).setText(password);
    }

    /**
     * This method is used when the user is a manager or a receptionist (a worker), to add a role row to the profile
     * */
    public void addRollRow(Role role){
        JLabel roleIndecator = new JLabel();
        roleIndecator.setFont(new Font("Arial", Font.BOLD, 12));
        roleIndecator.setHorizontalAlignment(SwingConstants.LEFT);
        roleIndecator.setText("Role:");
        Container.add(roleIndecator,"cell 0 4");
        JLabel roleLabel = new JLabel();
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        roleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        roleLabel.setText(role.toString());
        Container.add(roleLabel,"cell 1 4");
    }
}
