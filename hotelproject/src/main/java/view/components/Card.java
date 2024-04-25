package view.components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.*;

public class Card extends javax.swing.JPanel {

    public void setColor1(Color color1) {
        this.color1 = color1;
        repaint();
        revalidate();
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
        repaint();
        revalidate();
    }

    private Color color1;
    private Color color2;

    public Card(ImageIcon icon, String title, String values) {
        initComponents(icon, title, values);
        setOpaque(false);
        color1 = Color.BLACK;
        color2 = Color.WHITE;
    }

    public void setData(Model_Card data) {
        lbIcon.setIcon(data.getIcon());
        lbTitle.setText(data.getTitle());
        lbValues.setText(data.getValues());
    }

    private void initComponents(ImageIcon icon, String title, String values) {

        lbIcon = new javax.swing.JLabel();
        lbTitle = new javax.swing.JLabel(title);
        lbValues = new javax.swing.JLabel(values);

        lbIcon.setIcon(icon);

        lbTitle.setFont(new java.awt.Font("sansserif", 1, 16));
        lbTitle.setForeground(new java.awt.Color(255, 255, 255));


        lbValues.setFont(new java.awt.Font("sansserif", 1, 14));
        lbValues.setForeground(new java.awt.Color(255, 255, 255));


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbValues)
                                        .addComponent(lbTitle)
                                        .addComponent(lbIcon))
                                .addContainerGap(283, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(lbIcon)
                                .addGap(18, 18, 18)
                                .addComponent(lbTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbValues)
                                .addGap(18, 18, 18)
                                .addContainerGap(25, Short.MAX_VALUE)));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(new Color(255, 255, 255, 50));
        g2.fillOval(getWidth() - (getHeight() / 2), 10, getHeight(), getHeight());
        g2.fillOval(getWidth() - (getHeight() / 2) - 20, getHeight() / 2 + 20, getHeight(), getHeight());
        super.paintComponent(grphcs);
    }

    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbValues;
}