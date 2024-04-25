package view.components;

import javax.swing.*;

import controllers.UserType;
import net.miginfocom.swing.MigLayout;
import java.awt.*;

public class WelcomePage extends javax.swing.JPanel {

    public WelcomePage(UserType userType) {

        if (userType == UserType.GUEST) {
            initComponents();
            JLabel jLabel1 =new JLabel("<html>welcome to Oasis hotel <br>Check our rooms and make a reservation!<html>");
            jLabel1.setFont(new Font("SansSerif", Font.PLAIN, 36));
            jLabel1.setForeground(new Color(0, 112, 157));
            add(jLabel1, "span 3, center");
        }
        if (userType == UserType.MANAGER) {
            initComponents();
            JLabel jLabel1 =new JLabel("Hotel stats");
            jLabel1.setFont(new Font("SansSerif", Font.PLAIN, 36));
            jLabel1.setForeground(new Color(0, 112, 157));
            add(jLabel1, "span 3, center");
        }

    }

        private void initComponents() {
                setBackground(new Color(242, 242, 242));
                setLayout(new MigLayout("fillx, center, wrap 3, inset 10, gap 15", "push[grow]push", "push[]10[]push"));
        }
        public void addCard(Card card){
                add(card, "growx, pushx, h 100%, w 33%");
        }
}
