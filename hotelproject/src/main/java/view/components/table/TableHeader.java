package view.components.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class TableHeader extends JLabel {

    // Constructor for TableHeader
    public TableHeader(String text) {
        super(text); // Call JLabel constructor with the provided text
        setOpaque(true); // Ensure that the label is opaque
        setBackground(Color.WHITE); // Set background color to white
        setFont(new Font("sansserif", 1, 12)); // Set font to sans-serif, bold, size 12
        setForeground(new Color(102, 102, 102)); // Set text color to a shade of gray
        setBorder(new EmptyBorder(10, 0, 10, 0)); // Set an empty border around the label for padding
    }

    // Custom painting method to draw a horizontal line at the bottom of the label
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); // Call the parent paintComponent method
        grphcs.setColor(new Color(230, 230, 230)); // Set color for the line to a light gray
        grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1); // Draw a line at the bottom of the label
    }
}
