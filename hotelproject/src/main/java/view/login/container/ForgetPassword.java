package view.login.container;

import controllers.SendEmail;
import net.miginfocom.swing.MigLayout;
import view.components.DynamicButton;
import view.components.Message;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ForgetPassword extends JPanel {
    private final JPanel EmailInputPanel;
    private final JPanel VerificationCodeInputPanel;
    private final JPanel PasswordResetPanel;
    private Message msg = new Message();



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


        JPanel panel = new JPanel(new MigLayout("wrap 1, center , align center"));
        panel.setBackground(Color.white);
//        ImageIcon icon =new ImageIcon("hotelproject/src/main/java/view/login/icon/step1.png");
//        JLabel imageLabel = new JLabel();
//        imageLabel.setIcon(icon);
//        imageLabel.setPreferredSize(new Dimension(300,100));

        msg.displayMessage(Message.MessageType.SUCCESS, "email sent succesfully",panel, new MigLayout("wrap 1, center , align center"));


        JLabel loadinEmail = new JLabel();


        JLabel EmailFieldLabel = new JLabel("enter your email");

        JTextField EmailField = new JTextField(20); // 20 columns wide
      //  EmailField.setBorder(null); // Remove border
        EmailField.setPreferredSize(new Dimension(EmailField.getPreferredSize().width, 30)); // Set height
        EmailField.setFont(new Font("Arial", Font.BOLD, 15)); // Set font and size
        EmailField.setForeground(Color.DARK_GRAY); // Set text color
        EmailField.setHorizontalAlignment(JTextField.CENTER);

        DynamicButton nextButton = new DynamicButton("Next");
        nextButton.setButtonSize(new Dimension(100,40));
        nextButton.setButtonBgColor(new Color(0x0377FF));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(e -> {

            loadinEmail.setText("the email is being sent , please be patient");
            loadinEmail.setForeground(Color.BLUE);

            String email = EmailField.getText();
            String token = generateToken();
            tokens.put(token, System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)); // Token expires in 15 minutes
            SendEmail sendEmail = new SendEmail();
            try {
                sendEmail.setupServerProperties();
                sendEmail.draftEmail(email, "this is a private token , dont share it  , \n", "Your verification token is: " + token);
                sendEmail.sendEmail(email);
                msg.displayMessage(Message.MessageType.SUCCESS, "email sent succesfully",panel, new MigLayout("wrap 1, center , align center"));

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            remove(EmailInputPanel);
            add(VerificationCodeInputPanel);
            revalidate();
            repaint();
        });

        panel.add(EmailFieldLabel, "center, wrap"); // Added wrap
        panel.add(EmailField, "center, wrap"); // Added wrap
        panel.add(nextButton, "center, wrap");
        panel.add(loadinEmail, "center, wrap");



        return panel;
    }
    private String generateToken() {
        return new Random().ints(10, 33, 122).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    private JPanel createVerificationCodeInputPanel() {
        JPanel panel = new JPanel(new MigLayout("wrap 1, center , align center"));
        panel.setBackground(Color.white);


        JLabel tokenConfirmLabel = new JLabel("confirm the token sent ");
        JTextField confirmField  = new JTextField(20); // 20 columns wide



        DynamicButton nextButton = new DynamicButton("Next");
        nextButton.setButtonSize(new Dimension(100,40));
        nextButton.setButtonBgColor(new Color(0x0377FF));
        nextButton.setForeground(Color.white);
        nextButton.addActionListener(e -> {
            String enteredToken = confirmField.getText();
            if (isTokenValid(enteredToken)) {
                //JOptionPane.showMessageDialog(this, "Token is valid. Remaining time: " + remainingTimeSeconds + " seconds.");

                remove(VerificationCodeInputPanel);
                add(PasswordResetPanel);
                revalidate();
                repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Token is invalid or has expired.");
            }

        });

        //display the remaining time for the expiration of the token
        JLabel remainingTimeLabel = new JLabel();
        // the method responsible display the remaining time for the expiration of the token
        startCountdown(remainingTimeLabel);





        panel.add(tokenConfirmLabel, "center, wrap"); // Added wrap
        panel.add(confirmField, "center, wrap");
        panel.add(remainingTimeLabel, "center, wrap");
        panel.add(nextButton, "center, wrap"); // Added wrap

        return panel;
    }

    private JPanel createPasswordResetPanel() {

        JPanel panel = new JPanel(new MigLayout("wrap 1, center , align center"));
        panel.setBackground(Color.white);
        //for the password
        JLabel password = new JLabel("enter your password");
        JTextField passwordField = new JTextField(20); // 20 columns wide

        //confirm the password
        JLabel confirmPassword  = new JLabel("enter your new password ");
        JTextField  confimPasswordField = new JTextField(20); // 20 columns wide


        DynamicButton nextButton = new DynamicButton("Next");
        nextButton.setButtonSize(new Dimension(100,40));
        nextButton.setButtonBgColor(new Color(0x0377FF));
        nextButton.setForeground(Color.white);

        nextButton.addActionListener(e -> {
            //make the upadtes to th databse
        });


        panel.add(password, "center, wrap"); // Added wrap
        panel.add(passwordField, "center, wrap"); // Added wrap
        panel.add(confirmPassword, "center, wrap"); // Added wrap
        panel.add(confimPasswordField, "center, wrap"); // Added wrap
        panel.add(nextButton, "center, wrap"); //

        return panel;
    }

    //these methods are to verify the token sent and token witen by the user
    //and to check if the token is still valid or not
    //and to get the remaining time of the token
    private boolean isTokenValid(String token) {
        Long expirationTime = tokens.get(token);
        if (expirationTime == null) {

            return false;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime > expirationTime) {
            // Token has expired
            return false;
        }

        // Token is valid
        return true;
    }

//    private long getRemainingTime(String token) {
//        System.out.println(token);
//        Long expirationTime = tokens.get(token);
//        if (expirationTime == null) {
//            return -1;
//        }
//
//        long currentTime = System.currentTimeMillis();
//        return expirationTime - currentTime;
//    }
private void startCountdown(JLabel remainingTimeLabel) {
    int durationInMinutes = 15; // 15 minutes
    final int[] durationInSeconds = {durationInMinutes * 60};
    javax.swing.Timer timer = new javax.swing.Timer(1000, null);
    timer.addActionListener(e -> {
        if (durationInSeconds[0] > 0) {
            remainingTimeLabel.setText("Remaining time: " + durationInSeconds[0] / 60 + " minutes " + durationInSeconds[0] % 60 + " seconds");
            durationInSeconds[0]--;
        } else {
            remainingTimeLabel.setText("Countdown finished");
            timer.stop();
        }
    });
    timer.start();
}


}