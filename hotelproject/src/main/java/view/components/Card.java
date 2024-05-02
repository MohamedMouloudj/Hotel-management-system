package view.components;

import net.miginfocom.swing.MigLayout;

import java.awt.*;

import javax.swing.*;

class Card extends javax.swing.JPanel {

    void setColor1(Color color1) {
        this.color1 = color1;
        repaint();
        revalidate();
    }

    void setColor2(Color color2) {
        this.color2 = color2;
        repaint();
        revalidate();
    }

    private Color color1;
    private Color color2;

    Card(ImageIcon icon, String title, String values) {
        initComponents(icon, title, values);
        setOpaque(false);
        color1 = Color.BLACK;
        color2 = Color.WHITE;
    }

    private void initComponents(ImageIcon icon, String title, String values) {

        lbIcon = new javax.swing.JLabel();
        lbTitle = new javax.swing.JLabel(title);
        lbValues = new javax.swing.JLabel(values);

        lbIcon.setIcon(icon);

        lbTitle.setFont(new java.awt.Font("sansserif", Font.BOLD, 16));
        lbTitle.setForeground(new java.awt.Color(255, 255, 255));

        lbValues.setFont(new java.awt.Font("sansserif", Font.PLAIN, 14));
        lbValues.setForeground(new java.awt.Color(255, 255, 255));

        setLayout(new MigLayout("fillx, wrap 2, gap 10", "10[grow]10", "10[]25[]15"));
        add(lbTitle, "left");
        add(lbIcon, "right, wrap");
        add(lbValues, "span 1, left");
    }
    void updateDescription(String description){
        lbValues.setText(description);
        revalidate();
        repaint();
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