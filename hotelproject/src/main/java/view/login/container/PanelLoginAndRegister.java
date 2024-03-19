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

import view.login.component.MyButton;
import view.login.component.MyPasswordField;
import view.login.component.MyTextField;
import view.login.donne.User;

import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends JLayeredPane {
    public User user;

    public PanelLoginAndRegister(ActionListener actEventRegister, ActionListener actEventLogin) {
        initComponents();
        initRegister(actEventRegister);
        initLogin(actEventLogin);
        login.setVisible(false);
        register.setVisible(true);
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
        MyButton cmd = new MyButton();
        cmd.setBackground(new Color(0x1E90FF));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN UP");
        cmd.addActionListener(actEventRegister);

        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cmd) {
                    System.out.println("Name: " + txtName.getText());
                    System.out.println("Lastname: " + txtLastName.getText());
                    System.out.println("Email: " + txtEmail.getText());
                    System.out.println("Password: " + String.valueOf(txtPass.getPassword()));
                    user = new User(txtName.getText(), txtLastName.getText(), txtEmail.getText(),
                            String.valueOf(txtPass.getPassword()));
                }
            }
        });
        register.add(cmd, "w 40%, h 40");
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
        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        MyButton cmd = new MyButton();
        cmd.setBackground(new Color(0x1E90FF));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN IN");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cmd) {
                    System.out.println("Email: " + txtEmail.getText());
                    System.out.println("Password: " + String.valueOf(txtPass.getPassword()));

                }
            }
        });
        login.add(cmd, "w 40%, h 40");
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
