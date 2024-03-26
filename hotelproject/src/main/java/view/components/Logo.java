package view.components;

import javax.swing.*;
import java.awt.*;

public class Logo extends JPanel {
    public Logo() {
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon("hotelproject/src/main/java/view/icons/OasisLogo.png");
        label.setIcon(icon);
        setBackground(null);
        setPreferredSize(new Dimension(200, 70));
        add(label);
    }
}
