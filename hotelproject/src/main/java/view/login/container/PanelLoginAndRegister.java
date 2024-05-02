package view.login.container;

import controllers.Controller;
import controllers.PasswordHashing;
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

public class PanelLoginAndRegister extends JPanel{
    private final MigLayout layout;
    private final JPanel bg;
    private JPanel login;
    private JPanel register;
    private final Message msg = new Message();
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


    // Initialize the registration panel
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
                try {
                    Controller.checkRegistration(name, lastName, email, password);
                    msg.displayMessage(Message.MessageType.SUCCESS, "Registered successfully", bg, layout);
                } catch (Exception exception) {
                    msg.displayMessage(MessageType.ERROR, exception.getMessage(), bg, layout);
                }
                txtName.setText("");
                txtLastName.setText("");
                txtEmail.setText("");
                txtPass.setText("");
            }
        });

        // Add the registration button to the register panel
        register.add(btnRegister, "w 40%, h 40");
    }

    // Initialize the login panel
    private void initLogin() {
        // Set layout for the login panel
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));

        // Create a label for the sign-in text
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", Font.BOLD, 40));
        label.setForeground(new Color(0x1E90FF));
        login.add(label); // Add label to the login panel

        // Create text fields for email and password input
        MyTextField txtEmail = new MyTextField("Email", mailIconPath);
        MyPasswordField txtPass = new MyPasswordField("Password", passIconPath);

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
        btnRegisterForget.addActionListener(e -> Controller.launchForgotPasswordUI(PanelLoginAndRegister.this));
        login.add(btnRegisterForget); // Add button to the login panel

        btnLogIn.setBackground(new Color(0x1E90FF));
        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String password = String.valueOf(txtPass.getPassword());
                try {
                    Controller.checkLogin(email, password,bg);
                } catch (Exception exception) {
                    msg.displayMessage(MessageType.ERROR, exception.getMessage(), bg, layout); // Display error message
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
        login = new JPanel();
        register = new JPanel();

        setLayout(new CardLayout());
        login.setBackground(Color.WHITE);
        register.setBackground(Color.WHITE);

        // Add login and register panels to the main container panel
        add(login, "loginPanel");
        add(register, "registerPanel");
    }
}