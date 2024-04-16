package view.components.HomeComponents;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Card extends JPanel {
    private Color startColor = new Color(0x0377FF); // Dark blue
    private Color endColor = new Color(0x4FB5FF); // Light blue
    private JLabel cardTitle;
    private JLabel cardDescription;
    @SuppressWarnings("unused")
    private Dimension cardSize;

    /**
     * Creates a card that holds information about the Hotel
     */
    public Card(String cardTitle, String cardDescription) {
        setLayout(new MigLayout("wrap 1,center", "20[]20", "20[]20"));

        this.cardTitle = new JLabel(cardTitle);
        this.cardTitle.setFont(new Font("Arial", Font.BOLD, 20));
        this.cardTitle.setForeground(Color.WHITE);

        this.cardDescription = new JLabel(cardDescription);
        this.cardDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        this.cardDescription.setForeground(Color.WHITE);

        add(this.cardTitle, "center");
        add(this.cardDescription, "center");
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle.setText(cardTitle);
        repaint();
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription.setText(cardDescription);
        repaint();
    }

    /**
     * Change the background color of the card
     * 
     * @param startBgColor Color
     *                     The start color of the gradient background
     * @param endBgColor   Color
     *                     The end color of the gradient background
     */
    public void setBgColor(Color startBgColor, Color endBgColor) {
        this.startColor = startBgColor;
        this.endColor = endBgColor;
        repaint();
    }

    public void setCardSize(Dimension cardSize) {
        this.cardSize = cardSize;
        repaint();
    }

    /**
     * To paint the gradient background of the card
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Define gradient colors and positions
        int width = getWidth();
        int height = getHeight();
        GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, width, height, endColor);
        g2d.setPaint(gradientPaint);

        int cornerRadius = 15;
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draws the rounded panel without borders.
        g2d.fillRoundRect(0, 0, width, height, arcs.width, arcs.height); // paint background
        g2d.setColor(getForeground());

    }
}