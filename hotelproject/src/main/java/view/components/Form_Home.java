package view.components;

import javax.swing.*;
import java.awt.*;

public class Form_Home extends JPanel {
        public Form_Home(String txt) {
                initComponents(txt);
        }

        private void initComponents(String txt) {
                JLabel jLabel1 = new JLabel();

                setBackground(new Color(242, 242, 242));
                jLabel1.setFont(new Font("SansSerif", Font.PLAIN, 36));
                jLabel1.setForeground(new Color(106, 106, 106));
                jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
                jLabel1.setText(txt);

                setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = 0;
                constraints.gridy = 0;
                constraints.weightx = 1.0;
                constraints.weighty = 1.0;
                constraints.fill = GridBagConstraints.BOTH;
                constraints.insets = new Insets(128, 10, 125, 10);
                add(jLabel1, constraints);
        }
}