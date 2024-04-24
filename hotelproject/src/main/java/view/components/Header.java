package view.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;

public class Header extends javax.swing.JPanel {
        // Variables declaration - do not modify//GEN-BEGIN:variables
        private JLabel jLabel1;
        private JLabel jLabel2;
        private SearchText searchText1;
        // End of variables declaration//GEN-END:variables

        public Header() {
                initComponents();
                setOpaque(false);

        }

        private void initComponents() {

                jLabel1 = new JLabel();
                searchText1 = new SearchText();
                jLabel2 = new JLabel();

                setBackground(new Color(255, 255, 255));

                jLabel1.setIcon(new ImageIcon(getClass().getResource("/view/icons/search.png"))); // NOI18N

                jLabel2.setIcon(new ImageIcon(getClass().getResource("/view/icons/menu.png"))); // NOI18N
                jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

                GroupLayout layout = new GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel1)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(searchText1,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                606, Short.MAX_VALUE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel2)
                                                                .addComponent(jLabel1)
                                                                .addContainerGap()));
                layout.setVerticalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(searchText1, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 45,
                                                                Short.MAX_VALUE));
        }

        @Override
        protected void paintComponent(Graphics grphcs) {
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.fillRect(0, 0, 25, getHeight());
                g2.fillRect(getWidth() - 25, getHeight() - 25, getWidth(), getHeight());
                super.paintComponent(grphcs);
        }

}
