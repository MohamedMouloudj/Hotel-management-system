package view.login.container;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import net.miginfocom.swing.MigLayout;
import view.components.items.MyButtonOutLine;

// PanelCover class represents a panel used for login or registration cover
public class PanelCover extends JPanel {

    // Decimal format for formatting double values
    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private ActionListener event; // ActionListener for handling button clicks
    private MigLayout layout; // MigLayout for managing component layout
    private JLabel title; // JLabel for the title
    private JLabel description; // JLabel for the description
    private JLabel description1; // Additional JLabel for the description
    private MyButtonOutLine signInbutton; // Button for signing in or signing up
    private boolean isLogin; // Boolean indicating if it's a login or registration panel
    private Color blueColor = new Color(245, 245, 245); // Custom color for text

    // Constructor
    public PanelCover() {
        initComponents(); // Initialize components
        setOpaque(false); // Set panel to be transparent
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push"); // Create MigLayout
        setLayout(layout); // Set layout for the panel
        init(); // Initialize panel content
    }

    // Method to initialize panel content
    private void init() {
        title = new JLabel("Welcome Back!"); // Create title label
        title.setFont(new Font("sansserif", 1, 33)); // Set font for title
        title.setForeground(blueColor); // Set text color for title
        add(title); // Add title label to the panel
        description = new JLabel("To keep connected with us please"); // Create description label
        description.setForeground(blueColor); // Set text color for description
        description.setFont(new Font("sansserif", 1, 12)); // Set font for description
        add(description); // Add description label to the panel
        description1 = new JLabel("login with your personal info"); // Additional description label
        description1.setForeground(blueColor); // Set text color for additional description
        description1.setFont(new Font("sansserif", 1, 12)); // Set font for additional description
        add(description1); // Add additional description label to the panel
        signInbutton = new MyButtonOutLine(); // Create sign-in/sign-up button
        signInbutton.setBackground(new Color(255, 255, 255)); // Set background color for button
        signInbutton.setForeground(new Color(255, 255, 255)); // Set text color for button
        signInbutton.setText("SIGN IN"); // Set text for button
        signInbutton.setFont(new Font("sansserif", Font.BOLD, 15)); // Set font for button text
        signInbutton.addActionListener(new ActionListener() {
            // ActionListener for button click
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.actionPerformed(ae); // Perform action defined by the event listener
            }
        });
        add(signInbutton, "w 60%, h 40"); // Add button to the panel
    }

    // Method to initialize GroupLayout for the panel
    private void initComponents() {
        GroupLayout layout = new GroupLayout(this); // Create GroupLayout
        this.setLayout(layout); // Set GroupLayout for the panel
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 327, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE));
    }

    // Method to add event listener for button click
    public void addEvent(ActionListener event) {
        this.event = event; // Set event listener for the panel
    }

    // Method to adjust panel layout for left registration animation
    public void registerLeft(double v) {
        rightLogin(Double.valueOf(df.format(v)), false);
    }

    // Method to adjust panel layout for right registration animation
    public void registerRight(double v) {
        rightLogin(Double.valueOf(df.format(v)), false);
    }

    // Method to adjust panel layout for left login animation
    public void loginLeft(double v) {
        rightLogin(Double.valueOf(df.format(v)), true);
    }

    // Method to adjust panel layout for right login animation
    public void loginRight(double v) {
        rightLogin(Double.valueOf(df.format(v)), true);
    }

    // Method to adjust panel layout for login or registration animation
    private void rightLogin(double v, boolean isLogin) {
        login(isLogin); // Set panel to login or registration mode
        String padding = isLogin ? "pad 0 " : "pad 0 -"; // Determine padding direction
        layout.setComponentConstraints(title, padding + v + "% 0 " + v + "%"); // Adjust title constraints
        layout.setComponentConstraints(description, padding + v + "% 0 " + v + "%"); // Adjust description constraints
        layout.setComponentConstraints(description1, padding + v + "% 0 " + v + "%"); // Adjust additional description
                                                                                      // constraints
    }

    // Method to toggle between login and registration mode
    public void login(boolean login) {
        if (this.isLogin != login) {
            if (login) { // If login mode
                title.setText("Welcome to Our Hotel!"); // Update title text
                description.setText("Please provide your details"); // Update description text
                description1.setText("to start your journey with us."); // Update additional description text
                signInbutton.setText("SIGN UP"); // Update button text
            } else { // If registration mode
                title.setText("Welcome Back!"); // Update title text
                description.setText("To keep connected with us please"); // Update description text
                description1.setText("login with your personal info"); // Update additional description text
                signInbutton.setText("SIGN IN"); // Update button text
            }
            this.isLogin = login; // Set login mode flag
        }
    }

    // Override paintComponent method to paint custom background gradient
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs; // Cast Graphics object to Graphics2D
        // Create gradient paint for background
        GradientPaint gra = new GradientPaint(0, 0, new Color(0, 112, 255), 0, getHeight(), new Color(0x00BFFF));
        g2.setPaint(gra); // Set gradient paint
        g2.fillRect(0, 0, getWidth(), getHeight()); // Fill rectangle with gradient
        super.paintComponent(grphcs); // Call superclass paintComponent method
    }
}
