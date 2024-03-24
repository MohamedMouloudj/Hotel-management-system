package view.login.container;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
// import controllers.Hotel;

import org.bson.Document;

import view.login.components.MyButton;
import view.login.components.MyPasswordField;
import view.login.components.MyTextField;
import model.*;

import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends JLayeredPane {
    private User registerData;
    private User logInData;
    private String LoginErreurMsg;
    private String registerErreurMsg;

    public PanelLoginAndRegister(ActionListener actEventRegister, ActionListener actEventLogin) {
        this.initComponents();
        this.initRegister(actEventRegister);
        this.initLogin(actEventLogin);
        login.setVisible(false);
        register.setVisible(true);
    }

    public User getRegisterData() {
        return registerData;
    }

    public void setRegisterData(User registerData) {
        this.registerData = registerData;
    }

    public User getLogInData() {
        return logInData;
    }

    public void setLogInData(User logInData) {
        this.logInData = logInData;
    }

    public String getLoginErreurMsg() {
        return LoginErreurMsg;
    }

    public void setLoginErreurMsg(String loginErreurMsg) {
        LoginErreurMsg = loginErreurMsg;
    }

    public String getRegisterErreurMsg() {
        return registerErreurMsg;
    }

    public void setRegisterErreurMsg(String registerErreurMsg) {
        this.registerErreurMsg = registerErreurMsg;
    }

    private void initRegister(ActionListener actEventRegister) {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]25[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 40));
        label.setForeground(new Color(0x1E90FF));
        register.add(label);
        MyTextField txtName = new MyTextField();
        txtName.setPrefixIcon(new ImageIcon(getClass().getResource("/view/login/icon/user.png")));
        txtName.setHint("Name");
        register.add(txtName, "w 70%");
        MyTextField txtLastName = new MyTextField();
        txtLastName.setPrefixIcon(new ImageIcon(getClass().getResource("/view/login/icon/user.png")));
        txtLastName.setHint("Lastname");
        register.add(txtLastName, "w 70%");
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/view/login/icon/mail.png")));
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 70%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/view/login/icon/pass.png")));
        txtPass.setHint("Password");
        register.add(txtPass, "w 70%");
        MyButton btnRegister = new MyButton();
        btnRegister.setBackground(new Color(0x1E90FF));
        btnRegister.setForeground(new Color(250, 250, 250));
        btnRegister.setText("Register");
        btnRegister.addActionListener(actEventRegister);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnRegister) {
                    String name = txtName.getText();
                    String lastName = txtLastName.getText();
                    String email = txtEmail.getText();
                    String password = String.valueOf(txtPass.getPassword());
                    if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                        registerErreurMsg = "All the Fields Are Required";
                    } else if (!Guest.isValidEmail(email)) {
                        registerErreurMsg = "Invalide Email";
                    } else if (Guest.researchByEmail(email) != null) {
                        registerErreurMsg = "Guest with email " + email + " already exists.";
                    } else {
                        registerData = new Guest(name, lastName, email, password);
                        txtName.setText("");
                        txtLastName.setText("");
                        txtEmail.setText("");
                        txtPass.setText("");
                    }
                }

            }
        });
        register.add(btnRegister, "w 40%, h 40");
    }

    private void initLogin(ActionListener actEventLogin) {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 40));
        label.setForeground(new Color(0x1E90FF));
        login.add(label);
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/view/login/icon/mail.png")));
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/view/login/icon/pass.png")));
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");
        JButton btnRegisterForget = new JButton("Forgot your password ?");
        btnRegisterForget.setForeground(new Color(100, 100, 100));
        btnRegisterForget.setFont(new Font("sansserif", 1, 12));
        btnRegisterForget.setContentAreaFilled(false);
        btnRegisterForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(btnRegisterForget);
        MyButton btnLogIn = new MyButton();
        btnLogIn.setBackground(new Color(0x1E90FF));
        btnLogIn.setForeground(new Color(250, 250, 250));
        btnLogIn.setText("SIGN IN");
        btnLogIn.addActionListener(actEventLogin);
        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnLogIn) {
                    String email = txtEmail.getText();
                    String password = String.valueOf(txtPass.getPassword());
                    Document guest = Guest.researchByEmail(email);

                    if (email.isEmpty() || password.isEmpty()) {
                        LoginErreurMsg = "All the Fields Are Required";
                    } else if (!Guest.isValidEmail(email)) {
                        LoginErreurMsg = "Invalide Email";
                    } else if (guest == null) {
                        LoginErreurMsg = "There's no account with this Email ";
                    } else {
                        if (!guest.get("password")
                                .equals(password)) {
                            LoginErreurMsg = "incorrect password";
                        } else {

                            logInData = new Guest(guest.getString("firstName"), guest.getString("lastName"),
                                    guest.getString("email"),
                                    guest.getString("password"));
                            txtEmail.setText("");
                            txtPass.setText("");
                        }

                    }
                    // }else if () {

                    // }

                }
            }
        });
        login.add(btnLogIn, "w 40%, h 40");
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
                loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 327, Short.MAX_VALUE));
        loginLayout.setVerticalGroup(
                loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE));

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
                registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 327, Short.MAX_VALUE));
        registerLayout.setVerticalGroup(
                registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE));

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables

}
