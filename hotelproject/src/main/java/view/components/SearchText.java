package view.components;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SearchText extends JTextField {
    // Hint text to display when the text field is empty
    private final String hint = "Search here ...";

    // Constructor for SearchText
    public SearchText() {
        // Set empty border to provide padding around the text field
        setBorder(new EmptyBorder(5, 5, 5, 5));
        // Set selection color for the text field
        setSelectionColor(new Color(220, 204, 182)); // Custom selection color
    }

    // Custom painting method to display hint text when the text field is empty
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Call the parent paint method to paint the text field

        // If the text field is empty, display the hint text
        if (getText().length() == 0) {
            int h = getHeight(); // Get the height of the text field
            // Enable text anti-aliasing for smoother text rendering
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets(); // Get the insets (padding) of the text field
            FontMetrics fm = g.getFontMetrics(); // Get font metrics to calculate text position
            int c0 = getBackground().getRGB(); // Get background color RGB value
            int c1 = getForeground().getRGB(); // Get foreground (text) color RGB value
            int m = 0xfefefefe; // Bit mask to extract color components
            // Blend background and foreground colors to create a translucent color
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            // Set the color for drawing the hint text
            g.setColor(new Color(c2, true));
            // Draw the hint text at the center vertically and with padding horizontally
            g.drawString(hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }
}
