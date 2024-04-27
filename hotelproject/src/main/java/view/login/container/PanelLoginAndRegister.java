package view.login.container;

import controllers.Controller;
import controllers.PasswordHashing;
import model.Guest;
import model.User;
import net.miginfocom.swing.MigLayout;
import view.components.Message;
import view.components.Message.MessageType;
import view.components.items.MyButton;
import view.components.items.MyPasswordField;
import view.components.items.MyTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import view.managerUi.Manager;


public class PanelLoginAndRegister extends JPanel{
    private final MigLayout layout;
    private final JPanel bg;
    private Guest registerData;
    private JPanel login;
    private JPanel register;
    private Message msg = new Message();
    // Create a button for signing in
    private MyButton btnLogIn = new MyButton("SIGN IN");
    // Create a button for registration
    private MyButton btnRegister = new MyButton("Register");
    private String passIconPath = "hotelproject/src/main/java/view/icons/pass.png";
    private String mailIconPath = "hotelproject/src/main/java/view/icons/mail.png";

    public PanelLoginAndRegister(JPanel bg, MigLayout layout) {
        initComponents();
        this.layout = layout;
        this.bg = bg;
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
    }


    // Method to initialize the registration panel
    private void initRegister() {
        // Set layout for the register panel
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]25[]push"));

        // Create a label for the "Create Account" text
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", Font.BOLD, 40)); // Set font style and size
        label.setForeground(new Color(0x1E90FF)); // Set text color
        register.add(label); // Add label to the register panel

        //TODO to be checked

        // Create text fields for name, last name, email, and password input
        MyTextField txtName = new MyTextField("Name", "hotelproject/src/main/java/view/icons/user.png");
        MyTextField txtLastName = new MyTextField("Lastname", "hotelproject/src/main/java/view/icons/user.png");
        MyTextField txtEmail = new MyTextField("Email", mailIconPath);
        MyPasswordField txtPass = new MyPasswordField("Password", passIconPath);

        // Add text fields to the register panel
        register.add(txtName, "w 70%");
        register.add(txtLastName, "w 70%");
        register.add(txtEmail, "w 70%");
        register.add(txtPass, "w 70%");
        btnRegister.setBackground(new Color(0x1E90FF));
        btnRegister.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String name = txtName.getText();
                String lastName = txtLastName.getText();
                String email = txtEmail.getText();
                String password = String.valueOf(txtPass.getPassword());
                System.out.println(name + " " + lastName + " " + email + " " + password);
                // Check if any field is empty
                if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    // Display error message if any field is empty
                    msg.displayMessage(MessageType.ERROR, "All the Fields Are Required", bg, layout);
                } else if (!User.isValidEmail(email)) { // Check if email is valid
                    // Display error message if email is invalid
                    msg.displayMessage(MessageType.ERROR, "Invalid Email", bg, layout);
                } else if (Controller.getUserFromModel("Guests", "email", email) != null) { // Check if email already exists
                    // Display error message if email already exists in the database
                    msg.displayMessage(MessageType.ERROR, "Guest with email " + email + " already exists.", bg, layout);
                } else {
                    // Create a new Guest object with input data and add him to database
                    Controller.addGuestFromInputs(name, lastName, email, password);
                    System.out.println("User registered successfully");
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
    private void initLogin() {
        // Set layout for the login panel
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));

        // Create a label for the sign-in text
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", Font.BOLD, 40)); // Set font style and size
        label.setForeground(new Color(0x1E90FF)); // Set text color
        login.add(label); // Add label to the login panel

        // Create text fields for email and password input
        MyTextField txtEmail = new MyTextField("Email", mailIconPath);
        MyPasswordField txtPass = new MyPasswordField("Password", passIconPath);

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
        btnRegisterForget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ForgetPassword forgetPasswordPanel = new ForgetPassword();
                JFrame parentContainer = (JFrame) SwingUtilities.getWindowAncestor(PanelLoginAndRegister.this);
                parentContainer.setContentPane(forgetPasswordPanel);
                parentContainer.revalidate();
                parentContainer.repaint();
            }
        });
        login.add(btnRegisterForget); // Add button to the login panel

        btnLogIn.setBackground(new Color(0x1E90FF));
        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText(); // Get email input
                String password = String.valueOf(txtPass.getPassword()); // Get password input
                User loginUser; // Document to store user data

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
                        loginUser = Controller.getUserFromModel("Receptionists", "OasisMail", email);
                    } else { // Guest
                        loginUser = Controller.getUserFromModel("Guests", "email", email);
                    }

                    // Check if the user exists
                    if (loginUser == null) {
                        // Display error message if no account is found with the provided email
                        msg.displayMessage(MessageType.ERROR, "There's no account with this Email", bg, layout);
                    } else {
                        String truePassword = loginUser.getPassword(); // Get hashed password from database
                        // Check if the provided password matches the hashed password
                        if (!PasswordHashing.verifyPassword(password, truePassword)) {
                            // Display error message if password is incorrect
                            msg.displayMessage(MessageType.ERROR, "Incorrect password", bg, layout);
                            System.out.println("Incorrect password");
                        } else {
                            Controller.setHotelUserAndOpenUI(loginUser); // Set the user in the hotel model
                            System.out.println("User logged in successfully");
                        }
                        SwingUtilities.getWindowAncestor(bg).dispose(); // Close the login form
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