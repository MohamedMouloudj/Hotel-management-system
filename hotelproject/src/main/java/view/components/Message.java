package view.components;

import java.awt.*;
import javax.swing.*;
import org.jdesktop.animation.timing.*;
import net.miginfocom.swing.MigLayout;

// A custom JPanel class to display messages with different types (SUCCESS or ERROR)
public class Message extends JPanel {

    // Enumeration to represent different types of messages
    public static enum MessageType {
        SUCCESS, ERROR
    }

    // JLabel to display the message text
    private JLabel lbMessage;

    // MessageType to store the current message type
    private MessageType messageType = MessageType.SUCCESS;

    // Flag to track the visibility state of the message panel
    private boolean show;

    // Constructor to initialize the message panel
    public Message() {
        initComponents(); // Initialize UI components
        setOpaque(false); // Make the panel transparent
        setVisible(false); // Initially hide the panel
    }

    // Method to display a message with the specified type and text

    public void displayMessage(MessageType messageType, String message, JPanel bg, MigLayout layout) {
        Message ms = new Message(); // Create a new instance of Message
        ms.showMessage(messageType, message); // Show the specified message
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    bg.add(ms, "pos 0.5al -30", 0); // Add the message panel to the specified
                    // parent panel
                    ms.setVisible(true); // Make the message panel visible
                    bg.repaint(); // Repaint the parent panel
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f = ms.isShow() ? 40 * (1f - fraction) : 40 * fraction;
                if (layout != null) {
                    layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                } else {
                    bg.add(ms, "pos 0.5al " + (int) (f - 30));
                }
                bg.repaint(); // Repaint the parent panel
                bg.revalidate(); // Revalidate the parent panel
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    bg.remove(ms); // Remove the message panel from the parent panel
                    bg.repaint(); // Repaint the parent panel
                    bg.revalidate(); // Revalidate the parent panel
                } else {
                    ms.setShow(true); // Set the show flag to true
                }
            }
        };

        // Create an Animator to animate the message panel
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();

        // Create a new Thread to delay the animation
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1600); // Sleep for 1600 milliseconds (1.6 seconds)
                    animator.start(); // Start the animation
                } catch (InterruptedException e) {
                    System.err.println(e); // Print any InterruptedException
                }
            }
        }).start();
    }

    // Method to initialize UI components
    private void initComponents() {
        lbMessage = new JLabel(); // Create a new JLabel for displaying the message

        lbMessage.setFont(new Font("sansserif", 0, 14)); // Set the font for the message label
        lbMessage.setForeground(new Color(248, 248, 248)); // Set the foreground color
        lbMessage.setHorizontalAlignment(SwingConstants.CENTER); // Set horizontal alignment
        lbMessage.setText("Message"); // Set default text for the message label

        GroupLayout layout = new GroupLayout(this); // Create a GroupLayout for this panel
        this.setLayout(layout); // Set the layout manager for this panel

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbMessage, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                .addContainerGap()));

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lbMessage, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE));
    }

    // Method to paint the background of the message panel
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs; // Cast Graphics object to Graphics2D
        if (messageType == MessageType.SUCCESS) {
            g2.setColor(new Color(15, 174, 37)); // Set color for success message
        } else {
            g2.setColor(new Color(240, 52, 53)); // Set color for error message
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f)); // Set alpha composite
        g2.fillRect(0, 0, getWidth(), getHeight()); // Fill the background rectangle
        g2.setComposite(AlphaComposite.SrcOver); // Restore default alpha composite
        g2.setColor(new Color(245, 245, 245)); // Set color for border
        g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1); // Draw border rectangle
        super.paintComponent(grphcs); // Call superclass method to paint other components
    }

    // Method to show the specified message with the given type
    private void showMessage(MessageType messageType, String message) {
        this.messageType = messageType; // Update the message type
        lbMessage.setText(message); // Set the message text
        // Set appropriate icon based on message type
        if (messageType == MessageType.SUCCESS) {
            lbMessage.setIcon(new ImageIcon("hotelproject/src/main/java/view/icons/success.png"));
        } else {
            lbMessage.setIcon(new ImageIcon("hotelproject/src/main/java/view/icons/error.png"));
        }
    }

    // Getter method for the visibility state of the message panel
    public boolean isShow() {
        return show;
    }

    // Setter method for the visibility state of the message panel
    public void setShow(boolean show) {
        this.show = show;
    }
}
