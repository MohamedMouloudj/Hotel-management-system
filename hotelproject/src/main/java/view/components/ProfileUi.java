package view.components;

import controllers.Controller;
import controllers.UserType;
import model.supervisors.Receptionist;
import model.supervisors.Role;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileUi extends JPanel {
    private JPanel container;
    private JLabel emailLabel;
    private JLabel emailLabelData;
    private String email;
    private JLabel firstNameLabel;
    private JLabel firstNameLabelData;
    private String firstName;
    private JLabel lastNameLabel;
    private String lastName;
    private JLabel lastNameLabelData;
    protected UserType userType;
    private OurButton editButton = new OurButton("Edit");
    private ProfileEdit editProfile = new ProfileEdit();

    public ProfileUi(UserType userType) {
        this.userType=userType;
        initComponents();
    }

    private void initComponents() {
        container = new JPanel();
        lastNameLabel = new JLabel();
        firstNameLabel = new JLabel();
        emailLabel = new JLabel();

        editButton.setButtonBgColor(new Color(0xC0C0C0));
        editButton.setForeground(Color.BLACK);
        editButton.setIconToButton(new ImageIcon("hotelproject/src/main/java/view/icons/edit.png"), 15, 4);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(container);
                add(editProfile);
                revalidate();
                repaint();
            }
        });

        setLayout(new MigLayout("wrap 1,center", "[]", "push[]push"));
        setBackground(new Color(242, 242, 242));

        container.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2,
                true));
        container.setAutoscrolls(true);
        container.setPreferredSize(new Dimension(500, 400));
        container.setLayout(new MigLayout("center", "20[]20[grow,fill]20",
                "20[grow]20"));

        firstNameLabel.setFont(new Font("Arial", Font.BOLD, 12)); // NOI18N
        firstNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        firstNameLabel.setText("First name:");
        container.add(firstNameLabel, "cell 0 0");

        lastNameLabel.setFont(new Font("Arial", Font.BOLD, 12)); // NOI18N
        lastNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lastNameLabel.setText("Last name:");
        container.add(lastNameLabel, "cell 0 1");

//        if (userType!=UserType.GUEST){
            emailLabel.setFont(new Font("Arial", Font.BOLD, 12)); // NOI18N
            emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
            emailLabel.setText("Email:");
            container.add(emailLabel, "cell 0 2");
//        }

        container.add(editButton, "cell 0 4,alignx center,spanx 2,gapy 10");

        add(container);
    }

    public void addFirstName(String firstName) {
        this.firstName = firstName;
        firstNameLabelData = new JLabel();
        firstNameLabelData.setFont(new Font("Arial", Font.PLAIN, 12));
        firstNameLabelData.setHorizontalAlignment(SwingConstants.LEFT);
        firstNameLabelData.setText(this.firstName);
        container.add(firstNameLabelData, "cell 1 0");
    }

    public void addLastName(String lastName) {
        this.lastName = lastName;
        lastNameLabelData = new JLabel();
        lastNameLabelData.setFont(new Font("Arial", Font.PLAIN, 12));
        lastNameLabelData.setHorizontalAlignment(SwingConstants.LEFT);
        lastNameLabelData.setText(this.lastName);
        container.add(lastNameLabelData, "cell 1 1");
    }

    public void addEmail(String email) {
        this.email = email;
        emailLabelData = new JLabel();
        emailLabelData.setFont(new Font("Arial", Font.PLAIN, 12));
        emailLabelData.setHorizontalAlignment(SwingConstants.LEFT);
        emailLabelData.setText(this.email);
        container.add(emailLabelData, "cell 1 2");
    }

    public void updateFirstName(String firstName) {
        this.firstName = firstName;
        firstNameLabelData.setText(firstName);
        revalidate();
        repaint();
    }

    public void updateLastName(String lastName) {
        this.lastName = lastName;
        lastNameLabelData.setText(lastName);
        revalidate();
        repaint();
    }
    public void updateEmail(String email) {
        this.email = email;
        emailLabelData.setText(email);
        revalidate();
        repaint();
    }

    /**
     * This method is used when the user is a manager or a receptionist (a
     * worker),
     * to add a role row to the profile
     */
    public void addRollRow(Role role) {
        JLabel roleIndicator = new JLabel();
        roleIndicator.setFont(new Font("Arial", Font.BOLD, 12));
        roleIndicator.setHorizontalAlignment(SwingConstants.LEFT);
        roleIndicator.setText("Role:");
        container.add(roleIndicator, "cell 0 3");
        JLabel roleLabel = new JLabel();
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        roleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        roleLabel.setText(role.toString());
        container.add(roleLabel, "cell 1 3");
    }

    protected JPanel getContainer() {
        return container;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

class ProfileEdit extends JPanel {
    private JLabel FirstName;
    private JLabel LastName;
    private JLabel Email;
    private OurButton confirm = new OurButton("Confirm");
    private OurButton cancel = new OurButton("Cancel");
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;

    ProfileEdit() {
        initComponents();
    }

    private void initComponents() {

        LastName = new JLabel();
        FirstName = new JLabel();
        Email = new JLabel();

        confirm.setButtonBgColor(new Color(0xC0C0C0));
        confirm.setButtonTxtColor(Color.BLACK);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message();
                ProfileUi profilePanel= (ProfileUi) getParent();
                String firstName=firstNameField.getText();
                String lastName=lastNameField.getText();
                String email="";
                if (profilePanel.userType!=UserType.GUEST)
                    email=emailField.getText();
                boolean isUpdated=false;
                try {
                    if (!firstName.isEmpty()) {
                        if (profilePanel.userType==UserType.GUEST ){
                            Controller.handleUpdates("Guest",null,Controller.getUser().getEmail(),"firstName",firstName,null);
                        }else{
                            Controller.handleUpdates("Worker",null,((Receptionist)Controller.getUser()).getOasisMail(),"firstName",firstName,null);
                        }
                        isUpdated=true;
                        firstNameField.setText("");
                        profilePanel.updateFirstName(firstName);
                    }
                    if (!lastName.isEmpty()) {
                        if (profilePanel.userType==UserType.GUEST){
                            Controller.handleUpdates("Guest",null,Controller.getUser().getEmail(),"lastName",lastName,null);
                        }else{
                            Controller.handleUpdates("Worker",null,((Receptionist)Controller.getUser()).getOasisMail(),"lastName",lastName,null);
                        }
                        isUpdated=true;
                        lastNameField.setText("");
                        profilePanel.updateLastName(lastName);
                    }
                    if (!email.isEmpty()) {
                        if (profilePanel.userType!=UserType.GUEST){
                            Controller.handleUpdates("Worker",null,((Receptionist)Controller.getUser()).getOasisMail(),"email",email,null);
                        }
                        isUpdated=true;
                        emailField.setText("");
                        profilePanel.updateEmail(email);
                    }
                    if (!isUpdated)
                        throw new Exception("Please fill at least one field");
                } catch (Exception ex){
                    msg.displayMessage(Message.MessageType.ERROR, ex.getMessage(), profilePanel, null);
                }
                if (isUpdated)
                    msg.displayMessage(Message.MessageType.SUCCESS, "Profile updated successfully",
                        profilePanel, null);
                profilePanel.removeAll();
                profilePanel.add(profilePanel.getContainer());
                profilePanel.revalidate();
                profilePanel.repaint();
            }
        });
        cancel.setButtonTxtColor(new Color(0x0377FF));
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfileUi profilePanel = (ProfileUi) getParent();
                profilePanel.removeAll();
                profilePanel.add(profilePanel.getContainer());
                firstNameField.setText("");
                lastNameField.setText("");
                if (profilePanel.userType!=UserType.GUEST)
                    emailField.setText("");
                profilePanel.revalidate();
                profilePanel.repaint();
            }
        });

        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2, true));
        setAutoscrolls(true);
        setPreferredSize(new Dimension(500, 400));
        setLayout(new MigLayout("center", "20[]20[grow,fill]20", "20[grow]20"));

        FirstName.setFont(new Font("Arial", Font.BOLD, 12)); // NOI18N
        FirstName.setHorizontalAlignment(SwingConstants.LEFT);
        FirstName.setText("First name:");
        add(FirstName, "cell 0 0");
        firstNameField = new JTextField(15);
        firstNameField.setPreferredSize(new Dimension(100, 30));
        add(firstNameField, "cell 1 0");

        LastName.setFont(new Font("Arial", Font.BOLD, 12)); // NOI18N
        LastName.setHorizontalAlignment(SwingConstants.LEFT);
        LastName.setText("Last name:");
        add(LastName, "cell 0 1");
        lastNameField = new JTextField(15);
        lastNameField.setPreferredSize(new Dimension(100, 30));
        add(lastNameField, "cell 1 1");

        if(Controller.getUser() instanceof Receptionist){
            Email.setFont(new Font("Arial", Font.BOLD, 12)); // NOI18N
            Email.setHorizontalAlignment(SwingConstants.LEFT);
            Email.setText("Email:");
            add(Email, "cell 0 2");
            emailField = new JTextField(15);
            emailField.setPreferredSize(new Dimension(100, 30));
            add(emailField, "cell 1 2");
        }

        JPanel buttonsContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,
                5));
        buttonsContainer.add(confirm);
        buttonsContainer.add(cancel);
        add(buttonsContainer, "cell 0 3,alignx center,spanx 2");
    }
}