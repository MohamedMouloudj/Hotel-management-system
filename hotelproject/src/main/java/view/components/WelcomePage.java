package view.components;

import javax.swing.*;

import controllers.UserType;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.util.HashMap;

public class WelcomePage extends javax.swing.JPanel {
    private HashMap<String, Card> cards;
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
            JLabel jLabel1 =new JLabel("Hotel stats:");
            jLabel1.setFont(new Font("SansSerif", Font.PLAIN, 36));
            jLabel1.setForeground(new Color(0, 112, 157));
            add(jLabel1, "span 3, center");
            cards=new HashMap<>();
        }

    }

        private void initComponents() {
                setBackground(new Color(242, 242, 242));
                setLayout(new MigLayout("fillx, center, wrap 3, inset 10, gap 10", "push[fill]push", "push[]50[]push"));
        }
        public void addCard(String iconPath, String title, String description,Color color1, Color color2){
                try {
                    ImageIcon icon = new ImageIcon(iconPath);
                    icon = new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                    Card card = new Card(icon, title, description);
                    card.setColor1(color1);
                    card.setColor2(color2);
                    card.setPreferredSize(new Dimension(220, 110));
                    add(card, "");
                    try {
                        cards.put(title, card);
                    }catch (NullPointerException e){
                        cards=new HashMap<>();
                        cards.put(title, card);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
        }
        public void updateCard(String title, int newValue){
            try {
                cards.get(title).updateDescription(newValue+(title.equals("Rooms")?" rooms":title.equals("Workers")?" workers":" guests"));
                revalidate();
                repaint();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
}
