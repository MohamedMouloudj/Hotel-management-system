package view.components.HomeComponents;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {
    private JLabel welcomeLabel;

    public HomePage() {
        setLayout(new MigLayout("wrap 2,center", "20[]push[]20", "40[]push"));

        welcomeLabel = new JLabel("Oasis Hotel");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));

        add(welcomeLabel, "left, growx, span 2, wrap");
    }

    public void addCard(Card card) {
        add(card, "center");
    }
}