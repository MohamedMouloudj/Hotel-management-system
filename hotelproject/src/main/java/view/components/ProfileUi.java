package view.components;

import net.miginfocom.swing.MigLayout;
import view.components.items.DynamicButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Role;

public class ProfileUi extends JPanel {
    private JPanel container;
    private JLabel Email;
    private JLabel firstNameLabel;
    private String firstName;
    private JLabel lastNameLabel;
    private String lastName;
    private JLabel passwordLabel;
    private String password;
    private JLabel firstNameLabelData;
    private JLabel lastNameLabelData;
    private JLabel passwordLabelData;
    private DynamicButton editButton = new DynamicButton("Edit");
    private ProfileEdit editProfile = new ProfileEdit();

    public ProfileUi() {
        initComponents();
    }

    private void initComponents() {

        container = new JPanel();
        lastNameLabel = new JLabel();
        firstNameLabel = new JLabel();
        passwordLabel = new JLabel();
        Email = new JLabel();

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

        Email.setFont(new Font("Arial", Font.BOLD, 12)); // NOI18N
        Email.setHorizontalAlignment(SwingConstants.LEFT);
        Email.setText("Email:");
        container.add(Email, "cell 0 2");

        passwordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        passwordLabel.setText("Password:");
        container.add(passwordLabel, "cell 0 3");

        container.add(editButton, "cell 0 5,alignx center,spanx 2,gapy 10");

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

    public void addPassword(String password) {
        this.password = password;
        passwordLabelData = new JLabel();
        passwordLabelData.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordLabelData.setHorizontalAlignment(SwingConstants.LEFT);
        passwordLabelData.setText(this.password);
        container.add(passwordLabelData, "cell 1 3");
    }

    public void addEmail(String email) {
        JLabel emailLabel = new JLabel();
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
        emailLabel.setText(email);
        container.add(emailLabel, "cell 1 2");
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

    public void updatePassword(String password) {
        this.password = password;
        passwordLabelData.setText(password);
        revalidate();
        repaint();
    }

    /**
     * This method is used when the user is a manager or a receptionist (a
     * worker),
     * to add a role row to the profile
     */
    public void addRollRow(Role role) {
        JLabel roleIndecator = new JLabel();
        roleIndecator.setFont(new Font("Arial", Font.BOLD, 12));
        roleIndecator.setHorizontalAlignment(SwingConstants.LEFT);
        roleIndecator.setText("Role:");
        container.add(roleIndecator, "cell 0 4");
        JLabel roleLabel = new JLabel();
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        roleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        roleLabel.setText(role.toString());
        container.add(roleLabel, "cell 1 4");
    }

    protected JPanel getContainer() {
        return container;
    }
}

class ProfileEdit extends JPanel {
    private JLabel FirstName;
    private JLabel LastName;
    private JLabel Password;
    private DynamicButton confirm = new DynamicButton("Confirm");
    private DynamicButton cancel = new DynamicButton("Cancel");

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField passwordField;

    ProfileEdit() {
        initComponents();
    }

    private void initComponents() {

        LastName = new JLabel();
        FirstName = new JLabel();
        Password = new JLabel();

        confirm.setButtonBgColor(new Color(0xC0C0C0));
        confirm.setButtonTxtColor(Color.BLACK);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isUpdated = false;
                Message msg = new Message();
                ProfileUi profilePanel = (ProfileUi) getParent();
                try {
                    if (firstNameField.getText().isEmpty() && lastNameField.getText().isEmpty()
                            && passwordField.getText().isEmpty()) {
                        throw new InputException("Please fill at least one field");
                    }
                    if (!passwordField.getText().isEmpty() && passwordField.getText().length() < 8) {
                        throw new InputException("Password must be at least 8 characters");
                    }
                    if (!firstNameField.getText().isEmpty()) {
                        profilePanel.updateFirstName(firstNameField.getText());
                        firstNameField.setText("");
                        isUpdated = true;
                    }
                    if (!lastNameField.getText().isEmpty()) {
                        profilePanel.updateLastName(lastNameField.getText());
                        lastNameField.setText("");
                        isUpdated = true;
                    }
                    if (!passwordField.getText().isEmpty()) {
                        profilePanel.updatePassword(passwordField.getText());
                        passwordField.setText("");
                        isUpdated = true;
                    }
                } catch (InputException ex) {
                    msg.displayMessage(Message.MessageType.ERROR, ex.getMessage(), profilePanel, null);
                }
                if (isUpdated) {
                    msg.displayMessage(Message.MessageType.SUCCESS, "Profile updated successfully",
                            profilePanel, null);
                }
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
                passwordField.setText("");
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

        Password.setFont(new Font("Arial", Font.BOLD, 12));
        Password.setHorizontalAlignment(SwingConstants.LEFT);
        Password.setText("Password:");
        add(Password, "cell 0 2");
        passwordField = new JTextField(15);
        passwordField.setPreferredSize(new Dimension(100, 30));
        add(passwordField, "cell 1 2");

        JPanel buttonsContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,
                5));
        buttonsContainer.add(confirm);
        buttonsContainer.add(cancel);
        add(buttonsContainer, "cell 0 3,alignx center,spanx 2");
    }

    /**
     * Show a message on the screen
     *
     * @param messageType : The type of the message (Error, Success)
     * @param message     : The message to be displayed
     * @param bg          : The background panel to add the message to
     */
    // private void displayMessage(Message.MessageType messageType, String message,
    // JPanel bg) {
    // Message ms = new Message();
    // ms.displayMessage(messageType, message);
    // TimingTarget target = new TimingTargetAdapter() {
    // @Override
    // public void begin() {
    // if (!ms.isShow()) {
    // bg.add(ms, "pos 0.5al -30", 0); // Insert to bg fist index 0
    // ms.setVisible(true);
    // bg.repaint();
    // }
    // }

    // @Override
    // public void timingEvent(float fraction) {
    // float f;
    // if (ms.isShow()) {
    // f = 40 * (1f - fraction);
    // } else {
    // f = 40 * fraction;
    // }
    // bg.add(ms, "pos 0.5al " + (int) (f - 30));
    // bg.repaint();
    // bg.revalidate();
    // }

    // @Override
    // public void end() {
    // if (ms.isShow()) {
    // bg.remove(ms);
    // bg.repaint();
    // bg.revalidate();
    // } else {
    // ms.setShow(true);
    // }
    // }
    // };
    // Animator animator = new Animator(300, target);
    // animator.setResolution(0);
    // animator.setAcceleration(0.5f);
    // animator.setDeceleration(0.5f);
    // animator.start();
    // new Thread(new Runnable() {
    // @Override
    // public void run() {
    // try {
    // Thread.sleep(2000);
    // animator.start();
    // } catch (InterruptedException e) {
    // System.err.println(e);
    // }
    // }
    // }).start();
    // }
    // }
}

class InputException extends Exception {
    public InputException(String message) {
        super(message);
    }
}