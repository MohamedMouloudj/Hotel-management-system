package view.login.container;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.PasswordHashing;
import org.bson.Document;
import model.*;
import net.miginfocom.swing.MigLayout;
import view.components.Message.MessageType;
import view.components.items.MyButton;
import view.components.items.MyPasswordField;
import view.components.items.MyTextField;
import view.UserGui.GuestUi;
import view.UserGui.ReceptionistGui;
import view.UserGui.UserGui;
import view.components.Message;

public class PanelLoginAndRegister extends JPanel {
    private Guest registerData;
    private JPanel login;
    private JPanel register;
    private Message msg = new Message();

    public PanelLoginAndRegister(JPanel bg, MigLayout layout) {
        initComponents();
        initRegister(bg, layout);
        initLogin(bg, layout);
        login.setVisible(false);
        register.setVisible(true);
    }

    public Guest getRegisterData() {
        return registerData;
    }

    public void setRegisterData(Guest registerData) {
        this.registerData = registerData;
    }

    // Method to initialize the registration panel
    private void initRegister(JPanel bg, MigLayout layout) {
        // Set layout for the register panel
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]25[]push"));

        // Create a label for the "Create Account" text
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", Font.BOLD, 40)); // Set font style and size
        label.setForeground(new Color(0x1E90FF)); // Set text color
        register.add(label); // Add label to the register panel

        // Create text fields for name, last name, email, and password input
        MyTextField txtName = new MyTextField("Name", "/view/icons/user.png");
        MyTextField txtLastName = new MyTextField("Lastname", "/view/icons/user.png");
        MyTextField txtEmail = new MyTextField("Email", "/view/icons/mail.png");
        MyPasswordField txtPass = new MyPasswordField("Password", "/view/icons/pass.png");

        // Add text fields to the register panel
        register.add(txtName, "w 70%");
        register.add(txtLastName, "w 70%");
        register.add(txtEmail, "w 70%");
        register.add(txtPass, "w 70%");

        // Create a button for registration
        MyButton btnRegister = new MyButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get input data from text fields
                String name = txtName.getText();
                String lastName = txtLastName.getText();
                String email = txtEmail.getText();
                String password = String.valueOf(txtPass.getPassword());

                // Check if any field is empty
                if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    // Display error message if any field is empty
                    msg.displayMessage(MessageType.ERROR, "All the Fields Are Required", bg, layout);
                } else if (!User.isValidEmail(email)) { // Check if email is valid
                    // Display error message if email is invalid
                    msg.displayMessage(MessageType.ERROR, "Invalid Email", bg, layout);
                } else if (User.research("Guest", "email", email) != null) { // Check if email already exists
                    // Display error message if email already exists in the database
                    msg.displayMessage(MessageType.ERROR, "Guest with email " + email + " already exists.", bg, layout);
                } else {
                    // Create a new Guest object with input data
                    registerData = new Guest(name, lastName, email, password);
                    // Add the guest to the database
                    registerData.addGuestToDataBase();
                    // Clear input fields
                    txtName.setText("");
                    txtLastName.setText("");
                    txtEmail.setText("");
                    txtPass.setText("");
                    // Display success message
                    msg.displayMessage(MessageType.SUCCESS, "Register successfully", bg, layout);
                }
            }
        });

        // Add the registration button to the register panel
        register.add(btnRegister, "w 40%, h 40");
    }

    // Method to initialize the login panel
    private void initLogin(JPanel bg, MigLayout layout) {
        // Set layout for the login panel
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));

        // Create a label for the sign-in text
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", Font.BOLD, 40)); // Set font style and size
        label.setForeground(new Color(0x1E90FF)); // Set text color
        login.add(label); // Add label to the login panel

        // Create text fields for email and password input
        MyTextField txtEmail = new MyTextField("Email", "/view/icons/mail.png");
        MyPasswordField txtPass = new MyPasswordField("Password", "/view/icons/pass.png");

        // Add text fields to the login panel
        login.add(txtEmail, "w 60%");
        login.add(txtPass, "w 60%");

        // Create a button for password recovery
        JButton btnRegisterForget = new JButton("Forgot your password ?");
        btnRegisterForget.setForeground(new Color(100, 100, 100)); // Set text color
        btnRegisterForget.setFont(new Font("sansserif", Font.BOLD, 12)); // Set font style and size
        btnRegisterForget.setContentAreaFilled(false); // Make button transparent
        btnRegisterForget.setFocusPainted(false); // Remove focus border
        btnRegisterForget.setBorderPainted(false); // Remove border
        btnRegisterForget.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor
        login.add(btnRegisterForget); // Add button to the login panel

        // Create a button for signing in
        MyButton btnLogIn = new MyButton("SIGN IN");
        btnLogIn.addActionListener(e -> { // Add action listener for button click
            String email = txtEmail.getText(); // Get email input
            String password = String.valueOf(txtPass.getPassword()); // Get password input
            Document loginUser; // Document to store user data

            // Check if email or password is empty
            if (email.isEmpty() || password.isEmpty()) {
                // Display error message if any field is empty
                msg.displayMessage(MessageType.ERROR, "All the Fields Are Required", bg, layout);
            } else if (!User.isValidEmail(email)) { // Check if email is valid
                // Display error message if email is invalid
                msg.displayMessage(MessageType.ERROR, "Invalid Email", bg, layout);
            } else {
                // Check if the user is a receptionist or a guest
                if (email.endsWith("Oasis.dz")) { // Receptionist
                    loginUser = Receptionist.research("Receptionist", "OasisMail", email);
                } else { // Guest
                    loginUser = Guest.research("Guest", "email", email);
                }

                // Check if the user exists
                if (loginUser == null) {
                    // Display error message if no account is found with the provided email
                    msg.displayMessage(MessageType.ERROR, "There's no account with this Email", bg, layout);
                } else {
                    String A = loginUser.getString("password"); // Get hashed password from database
                    // Check if the provided password matches the hashed password
                    if (!PasswordHashing.verifyPassword(password, A)) {
                        // Display error message if password is incorrect
                        msg.displayMessage(MessageType.ERROR, "Incorrect password", bg, layout);
                    } else {
                        // If the user is a receptionist, open ReceptionistUi; otherwise, open GuestUi
                        if (email.endsWith("Oasis.dz")) {
                            // new ReceptionistUi(new Receptionist(
                            // loginUser.getString("firstName"),
                            // loginUser.getString("lastName"),
                            // loginUser.getString("email")));
                            UserGui.run(new ReceptionistGui(new Receptionist(loginUser.getString("firstName"),
                                    loginUser.getString("lastName"), loginUser.getString("email"))));
                        } else {
                            UserGui.run(new GuestUi(
                                    new Guest(loginUser.getString("firstName"), loginUser.getString("lastName"),
                                            loginUser.getString("email"), loginUser.getString("password"))));
                            // new com.raven.main.Main().v();
                            // UserGui.run(new GuestUi());
                        }
                        bg.getTopLevelAncestor().setVisible(false); // Hide the login form
                    }
                }
            }
        });

        // Add the sign-in button to the login panel
        login.add(btnLogIn, "w 40%, h 40");
    }

    public void showRegister(boolean show) {
        register.setVisible(show);
        login.setVisible(!show);
    }

    private void initComponents() {
        // Initialize login and register panels
        login = new JPanel();
        register = new JPanel();

        // Set layout of the main container panel using CardLayout
        setLayout(new CardLayout());

        // Set background color for the login and register panels
        login.setBackground(Color.WHITE);
        register.setBackground(Color.WHITE);

        // Add login and register panels to the main container panel
        add(login, "loginPanel");
        add(register, "registerPanel");
    }

}
// msg.displayMessage(MessageType.SUCCESS, "Welcome back " +
// receptionist.getFirstName(),
// bg,
// layout);
// txtEmail.setText("");
// txtPass.setText("");
// } else {
// String A = guest.getString("password");
// if (!PasswordHashing.verifyPassword(password, A)) {
// msg.displayMessage(MessageType.ERROR, "incorrect password", bg, layout);

// } else {

// logInData = new Guest(guest.getString("firstName"),
// guest.getString("lastName"),
// guest.getString("email"),
// guest.getString("password"));
// msg.displayMessage(MessageType.SUCCESS, "Welcome back " +
// logInData.getFirstName(), bg, layout);
// txtEmail.setText("");
// txtPass.setText("");
// }

// }
// msg.displayMessage(MessageType.SUCCESS, "Welcome back " +
// guest.getFirstName(), bg,
// layout);
// txtEmail.setText("");
// txtPass.setText("");
// private void initComponents() {

// login = new JPanel();
// register = new JPanel();

// setLayout(new CardLayout());

// login.setBackground(new Color(255, 255, 255));

// GroupLayout loginLayout = new GroupLayout(login);
// login.setLayout(loginLayout);
// loginLayout.setHorizontalGroup(
// loginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
// .addGap(0, 327, Short.MAX_VALUE));
// loginLayout.setVerticalGroup(
// loginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
// .addGap(0, 300, Short.MAX_VALUE));

// add(login, "card3");

// register.setBackground(new Color(255, 255, 255));

// GroupLayout registerLayout = new GroupLayout(register);
// register.setLayout(registerLayout);
// registerLayout.setHorizontalGroup(
// registerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
// .addGap(0, 327, Short.MAX_VALUE));
// registerLayout.setVerticalGroup(
// registerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
// .addGap(0, 300, Short.MAX_VALUE));

// add(register, "card2");
// }