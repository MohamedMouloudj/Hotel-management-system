package view.components;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

import model.Model_Card;

public class WelcomePage extends javax.swing.JPanel {
        private view.components.Card card1;
        private view.components.Card card2;
        private view.components.Card card3;
        private javax.swing.JLayeredPane panel;

        public WelcomePage() {
                initComponents();
                card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/view/icons/stock.png")),
                                "Stock Total", "$200000", "Increased by 60%"));
                card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/view/icons/profit.png")),
                                "Total Profit", "$15000", "Increased by 25%"));
                card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/view/icons/flag.png")),
                                "Unique Visitors", "$300000", "Increased by 70%"));
        }

        private void initComponents() {
                panel = new JLayeredPane();
                card1 = new Card();
                card2 = new Card();
                card3 = new Card();

                setBackground(new Color(242, 242, 242));

                panel.setLayout(new GridLayout(1, 0, 10, 0));

                card1.setColor1(new Color(142, 142, 250));
                card1.setColor2(new Color(123, 123, 245));
                panel.add(card1);

                card2.setColor1(new Color(186, 123, 247));
                card2.setColor2(new Color(167, 94, 236));
                panel.add(card2);

                card3.setColor1(new Color(241, 208, 62));
                card3.setColor2(new Color(211, 184, 61));
                panel.add(card3);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addComponent(panel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                875, Short.MAX_VALUE)
                                                                .addGap(20, 20, 20)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addComponent(panel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(20, 20, 20)));
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables

        // End of variables declaration//GEN-END:variables
}
