package view.components.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Model_Menu;

public class MenuItem extends JPanel {

    private boolean selected; // Flag indicating if the menu item is selected
    private boolean over; // Flag indicating if the mouse is hovering over the menu item
    private JLabel lbIcon; // Label for displaying the icon
    private JLabel lbName; // Label for displaying the name

    // Constructor
    public MenuItem(Model_Menu data) {
        initComponents(); // Initialize components
        setOpaque(false); // Make the panel transparent
        // Set up the appearance based on the type of menu item
        if (data.getType() == Model_Menu.MenuType.MENU) {
            lbIcon.setIcon(data.toIcon()); // Set icon
            lbName.setText(data.getName()); // Set name
            lbName.setFont(new Font("sansserif", Font.BOLD, 15)); // Set font for name
        } else if (data.getType() == Model_Menu.MenuType.TITLE) {
            lbIcon.setText(data.getName()); // Set title text
            lbIcon.setFont(new Font("sansserif", Font.BOLD, 20)); // Set font for title
            lbName.setVisible(false); // Hide name label for titles
        } else {
            lbName.setText(" "); // Empty space for other types
        }
    }

    // Set whether the menu item is selected
    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint(); // Repaint the panel to reflect the change
    }

    // Set whether the mouse is over the menu item
    public void setOver(boolean over) {
        this.over = over;
        repaint(); // Repaint the panel to reflect the change
    }

    // Initialize components and set up layout
    private void initComponents() {
        lbIcon = new JLabel(); // Initialize icon label
        lbName = new JLabel(); // Initialize name label

        lbIcon.setForeground(new Color(255, 255, 255)); // Set foreground color for icon label
        lbName.setForeground(new Color(255, 255, 255)); // Set foreground color for name label

        GroupLayout layout = new GroupLayout(this); // Create GroupLayout for panel
        this.setLayout(layout); // Set the layout for the panel
        // Set horizontal layout
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lbIcon)
                                .addGap(18, 18, 18)
                                .addComponent(lbName)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        // Set vertical layout
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lbIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbName, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE));
    }

    // Custom painting method for rendering the panel
    @Override
    protected void paintComponent(Graphics g) {
        // Draw a background shape when the menu item is selected or hovered over
        if (selected || over) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // Set the color and transparency based on selection or hover state
            if (selected) {
                g2.setColor(new Color(255, 255, 255, 80)); // Selected color with transparency
            } else {
                g2.setColor(new Color(255, 255, 255, 20)); // Hover color with transparency
            }
            // Draw a rounded rectangle as the background shape
            g2.fillRoundRect(10, 0, getWidth() - 20, getHeight(), 5, 5);
        }
        super.paintComponent(g); // Call the superclass method to paint other components
    }
}
