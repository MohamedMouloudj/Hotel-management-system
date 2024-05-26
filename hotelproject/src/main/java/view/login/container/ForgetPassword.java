package view.login.container;

import controllers.SendEmail;
import model.Database;
import controllers.PasswordHashing;
import net.miginfocom.swing.MigLayout;
import org.bson.Document;
import view.components.Message;
import view.components.OurButton;
import view.login.loginMain.LogInForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


public class ForgetPassword extends JPanel {
    private final JPanel EmailInputPanel;
    private final JPanel VerificationCodeInputPanel;
    private final JPanel PasswordResetPanel;
    private Message msg = new Message();
    private String userEmail ;

    private final  long expirationTime = 3;



    // Store tokens and their expiration times
    private ConcurrentHashMap<String, Long> tokens = new ConcurrentHashMap<>();


    public ForgetPassword() {
        setLayout(new MigLayout("wrap 2 , center, insets 0 20 20 40,gap 5% 5%","[][]","[grow,fill]"));
        setBackground(Color.white);

        EmailInputPanel = createEmailInputPanel();
        VerificationCodeInputPanel = createVerificationCodeInputPanel();
        PasswordResetPanel = createPasswordResetPanel();
        add(EmailInputPanel);

        // initialize and  Add the first panel to the ForgetPassword panel

    }

//    a method to create each panel
//  each one to represent a step in the password reset process
//    createEmailInputPanel
//    createVerificationCodeInputPanel
//    createPasswordResetPanel



    private JPanel createEmailInputPanel() {

        MigLayout layout = new MigLayout("wrap 1, center , align center");
        JPanel panel = new JPanel(layout);
        panel.setBackground(Color.white);



        //email fileds
        JLabel EmailFieldLabel = new JLabel("enter your email");
        EmailFieldLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font and size
        EmailFieldLabel.setForeground(Color.DARK_GRAY); // Set text color
        EmailFieldLabel.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 0));


        JTextField EmailField = new JTextField(20); // 20 columns wide
        EmailField.setPreferredSize(new Dimension(EmailField.getPreferredSize().width, 40)); // Set height
        EmailField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        EmailField.setFont(new Font("Arial", Font.BOLD, 15)); // Set font and size
        EmailField.setForeground(Color.DARK_GRAY); // Set text color
        EmailField.setHorizontalAlignment(JTextField.CENTER);

        OurButton nextButton = new OurButton("Next");
        nextButton.setButtonSize(new Dimension(265,40));
        nextButton.setButtonBgColor(new Color(0x0377FF));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(e -> {

            String email = EmailField.getText();
            userEmail = email;
            if (email.isEmpty()) {
                msg.displayMessage(Message.MessageType.ERROR, "Email field is required", panel, layout);
                return;
            }
            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            if (!email.matches(regex)) {
                msg.displayMessage(Message.MessageType.ERROR, "Invalid email format", panel, layout);
                return;
            }

            Document loginUser = Database.findInDataBase("Guests", "email", email);
            if (loginUser == null) {
                msg.displayMessage(Message.MessageType.ERROR, "Email doesent exist",panel , layout);
                return;
            }

            String token = generateToken();
            tokens.put(token, System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(expirationTime));

            SendEmail sendEmail = new SendEmail();

            try {
                sendEmail.setupServerProperties();
                sendEmail.draftEmail(email, "this is a private token , dont share it  , \n", "Your verification token is: " + token);
                boolean emailSent = sendEmail.sendEmail(email);
                if (emailSent) {
                    msg.displayMessage(Message.MessageType.SUCCESS, "email sent successfully ",panel , layout);
                } else {
                    msg.displayMessage(Message.MessageType.ERROR, "email has been sent , try again",panel , layout);
                    return;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            remove(EmailInputPanel);
            add(VerificationCodeInputPanel);
            revalidate();
            repaint();
        });

        //go back to login button
        OurButton backToLoginBtn = new OurButton("Back to Login");
        backToLoginBtn.setButtonSize(new Dimension(265,30));
        backToLoginBtn.setForeground(new Color(0x0377FF));

        backToLoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentContainer = (JFrame) SwingUtilities.getWindowAncestor(ForgetPassword.this);
                parentContainer.setVisible(false); // Hide the current JFrame

                // Ensure LogInForm is a JFrame
                LogInForm loginFormFrame = new LogInForm();
                loginFormFrame.setVisible(true); // Show the other JFrame
            }
        });

        panel.add(EmailFieldLabel, "center, wrap"); // Added wrap
        panel.add(EmailField, "center, wrap"); // Added wrap
        panel.add(nextButton, "center, wrap");
        panel.add(backToLoginBtn, "center, wrap");


        return panel;
    }


    private JPanel createVerificationCodeInputPanel() {

        MigLayout layout = new MigLayout("wrap 1, center , align center");
        JPanel panel = new JPanel(layout);
        panel.setBackground(Color.white);


        //token onfirmation inputs
        JLabel tokenConfirmLabel = new JLabel("enter your token we sent you");
        tokenConfirmLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font and size
        tokenConfirmLabel.setForeground(Color.DARK_GRAY); // Set text color
        tokenConfirmLabel.setBorder(BorderFactory.createEmptyBorder(170, 0, 0, 0));

        JTextField tokenConfirm = new JTextField(25); // 20 columns wide
        tokenConfirm.setPreferredSize(new Dimension(tokenConfirm.getPreferredSize().width, 40)); // Set height
        tokenConfirm.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        tokenConfirm.setForeground(Color.DARK_GRAY); // Set text color
        tokenConfirm.setHorizontalAlignment(JTextField.CENTER);




        OurButton nextButton = new OurButton("Next");
        nextButton.setButtonSize(new Dimension(265,40));
        nextButton.setButtonBgColor(new Color(0x0377FF));
        nextButton.setForeground(Color.white);
        nextButton.addActionListener(e -> {
            String enteredToken = tokenConfirm.getText();
            if (Objects.equals(enteredToken, ""))
            {
                msg.displayMessage(Message.MessageType.ERROR, "Token field is required", panel, layout);
                return;
            }

            if (isTokenValid(enteredToken)) {
                remove(VerificationCodeInputPanel);
                add(PasswordResetPanel);
                revalidate();
                repaint();
            } else {
                msg.displayMessage(Message.MessageType.ERROR, "Invalid token", panel, layout);
            }

        });

        //this button is for getting back to the login
        OurButton backToLoginBtn = new OurButton("Back to Login");
        backToLoginBtn.setButtonSize(new Dimension(200,30));
        backToLoginBtn.setForeground(new Color(0x0377FF));
        backToLoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentContainer = (JFrame) SwingUtilities.getWindowAncestor(ForgetPassword.this);
                parentContainer.setVisible(false); // Hide the current JFrame

                // Ensure LogInForm is a JFrame
                LogInForm loginFormFrame = new LogInForm();
                loginFormFrame.setVisible(true); // Show the other JFrame
            }
        });

        //display the remaining time for the expiration of the token
        JLabel remainingTimeLabel = new JLabel();
        // the method responsible display the remaining time for the expiration of the token
        //Long expirationTime = tokens.get(tokens);

        startCountdown(remainingTimeLabel );

        panel.add(tokenConfirmLabel, "center, wrap"); // Added wrap
        panel.add(tokenConfirm, "center, wrap");
        panel.add(remainingTimeLabel, "center, wrap");
        panel.add(nextButton, "center, wrap"); // Added wrap
        panel.add(backToLoginBtn, "center, wrap");

        return panel;
    }

    private JPanel createPasswordResetPanel() {

        MigLayout layout = new MigLayout("wrap 1, center , align center");
        JPanel panel = new JPanel(layout);
        panel.setBackground(Color.white);

        //for the passwords  label
        JLabel passwordLabel = new JLabel("enter your new  password ");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font and size
        passwordLabel.setForeground(Color.DARK_GRAY); // Set text color
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(180, 0, 0, 0));

        //for the password field
        JPasswordField passwordField = new JPasswordField(25); // 20 columns wide
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, 40)); // Set height
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        passwordField.setForeground(Color.DARK_GRAY); // Set text color
        passwordField.setHorizontalAlignment(JPasswordField.CENTER);

        /////////////////////////////

        //confirm the passwordField label
        JLabel confirmPasswordLabel = new JLabel("confirm your new password");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        confirmPasswordLabel.setForeground(Color.DARK_GRAY);

        // for the password field confirmation
        JPasswordField confirmPasword = new JPasswordField(25);
        confirmPasword.setPreferredSize(new Dimension(confirmPasword.getPreferredSize().width, 40)); // Set height
        confirmPasword.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        confirmPasword.setForeground(Color.DARK_GRAY); // Set text color
        confirmPasword.setHorizontalAlignment(JPasswordField.CENTER);


        OurButton nextButton = new OurButton("Confirm");
        nextButton.setButtonSize(new Dimension(270,40));
        nextButton.setButtonBgColor(new Color(0x0377FF));
        nextButton.setForeground(Color.white);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print(arePasswordsMatching(passwordField, confirmPasword, panel, layout));
                if (arePasswordsMatching(passwordField, confirmPasword, panel, layout)) {
                    System.out.print("the email after :" + userEmail);
                    String email = userEmail;
                    String newPassword = passwordField.getText();
                    try {
                        Database.updateFieldInDataBase("Guests", "email", email, "password", PasswordHashing.hashPassword(newPassword));
                        msg.displayMessage(Message.MessageType.SUCCESS, "Password has been updated", panel, layout);
                    } catch (Database.DBException dbException) {
                        dbException.printStackTrace();
                        msg.displayMessage(Message.MessageType.ERROR, "Failed to update password", panel, layout);
                    }


                    JFrame parentContainer = (JFrame) SwingUtilities.getWindowAncestor(ForgetPassword.this);
                    parentContainer.setVisible(false); // Hide the current JFrame

                    // Ensure LogInForm is a JFrame
                    LogInForm loginFormFrame = new LogInForm();
                    loginFormFrame.setVisible(true); // Show the other JFrame
                }

            }

        });

        //this button is for getting back to the login
        OurButton backToLoginBtn = new OurButton("Back to Login");
        backToLoginBtn.setButtonSize(new Dimension(200,30));
        backToLoginBtn.setForeground(new Color(0x0377FF));

        backToLoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentContainer = (JFrame) SwingUtilities.getWindowAncestor(ForgetPassword.this);
                parentContainer.setVisible(false); // Hide the current JFrame

                // Ensure LogInForm is a JFrame
                LogInForm loginFormFrame = new LogInForm();
                loginFormFrame.setVisible(true); // Show the other JFrame
            }
        });


        panel.add(passwordLabel, "center, wrap"); // Added wrap
        panel.add(passwordField, "center, wrap"); // Added wrap
        panel.add(confirmPasswordLabel, "center, wrap"); // Added wrap
        panel.add(confirmPasword, "center, wrap"); // Added wrap
        panel.add(nextButton, "center, wrap"); //
        panel.add(backToLoginBtn, "center, wrap"); //

        return panel;
    }

    //these methods are to verify the token sent and token witen by the user
    //and to check if the token is still valid or not
    //and to get the remaining time of the token


    private String generateToken() {
        return new Random().ints(10, 33, 122).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    private boolean isTokenValid(String token) {
        Long expirationTime = tokens.get(token);


//        System.out.print("token inside token " + SentToken);
        if (expirationTime == null) {
            return false;
        }

       long currentTime = System.currentTimeMillis();
        System.out.print("current time  " + currentTime + "  expiration time  " + expirationTime + "  the result");
        if (currentTime > expirationTime) {
            return false;
        }

        // Token is valid
        return true;
    }

    private boolean arePasswordsMatching(JTextField passwordField, JTextField confirmPasswordField, JPanel panel, MigLayout layout) {
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            msg.displayMessage(Message.MessageType.ERROR, "Both fields are required", panel, layout);
            return false;
        }

        if (!password.equals(confirmPassword)) {
            msg.displayMessage(Message.MessageType.ERROR, "Passwords do not match", panel, layout);
            return false;
        }

        return true;
    }


    private void startCountdown(JLabel remainingTimeLabel ) {
        int durationInMinutes = 3;
        final int[] durationInSeconds = {durationInMinutes * 60};
        javax.swing.Timer timer = new javax.swing.Timer(1000, null);
        timer.addActionListener(e -> {
            if (durationInSeconds[0] > 0) {
                remainingTimeLabel.setText("Remaining time: " + durationInSeconds[0] / 60 + " minutes " + durationInSeconds[0] % 60 + " seconds");
                durationInSeconds[0]--;
            } else {
                //token expired
                msg.displayMessage(Message.MessageType.ERROR, "Token has expired", this, new MigLayout("wrap 1, center, align center"));
                remainingTimeLabel.setText("Countdown finished");
                timer.stop();
                //go back to the email entering form
                remove(VerificationCodeInputPanel);
                add(EmailInputPanel);
                revalidate();
                repaint();
            }
        });
        timer.start();
    }


}