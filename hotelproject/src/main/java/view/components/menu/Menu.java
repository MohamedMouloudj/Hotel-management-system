package view.components.menu;

import net.miginfocom.swing.MigLayout;
import view.components.OurButton;
import view.login.loginMain.LogInForm;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedHashMap;

import javax.swing.*;

public class Menu extends JPanel {

    private final int width=250;
    private EventMenuSelected eventMenuSelected; // Event listener for menu selection
    private int initialX; // Initial mouse X position for dragging the frame
    private int initialY; // Initial mouse Y position for dragging the frame

    // Components declaration
    private JLabel logoLabel;
    private OurButton quitButton;
    private ListMenu<String> menuList;
    private JPanel movingPanel;

    // Constructor
    public Menu(LinkedHashMap<String, String> menuMap) {
        initComponents();
        setOpaque(false);
        init(menuMap);
    }

    // Initialize components and layout
    private void initComponents() {
        movingPanel = new JPanel(); // Panel for moving the frame
        logoLabel = new JLabel(); // Label for logo display
        menuList = new ListMenu<>(); // List for displaying menu items
        quitButton=new OurButton("Log out");
        quitButton.setIconToButton(new ImageIcon("hotelproject/src/main/java/view/icons/quit.png"),5,4);
        quitButton.addActionListener(e -> {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Warning", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.NO_OPTION)
                return;
            LogInForm logInForm = new LogInForm();
            logInForm.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();    // close the current window
        });

        movingPanel.setOpaque(false); // Make moving panel transparent

        logoLabel.setFont(new Font("sansserif", Font.BOLD, 18)); // Set font for logo label
        logoLabel.setForeground(new Color(255, 255, 255)); // Set text color for logo label
        ImageIcon icon=new ImageIcon("hotelproject/src/main/java/view/icons/OasisLogo.png");
        logoLabel.setIcon(icon); // Set logo icon

        movingPanel.setLayout(new MigLayout("", "[grow]", "[]"));
        movingPanel.add(menuList, "wrap ,grow, pushy, alignx center , aligny center");

        this.setLayout(new MigLayout("wrap 1, inset 0", "[center,"+width+"!]", "10[]60[]push[]40"));
        this.add(logoLabel, "center");
        this.add(movingPanel, "wrap ,grow, pushy, alignx right , aligny center");
        this.add(quitButton, "center");
    }

    // Custom painting method for background gradient
    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(0, 112, 255), 0, getHeight(),
                new Color(0x00BFFF));
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(graphics);
    }

    // Initialize menu items based on the provided map
    private void init(LinkedHashMap<String, String> menuMap) {
        menuList.setOpaque(false);
        menuList.addItem(new Model_Menu("", " ", Model_Menu.MenuType.EMPTY)); //WE NEED TO ADD THIS AT FIRST FOR SOME REASON!

        for (String text : menuMap.keySet()) {
            menuList.addItem(new Model_Menu(menuMap.get(text), text, Model_Menu.MenuType.MENU));
        }
    }

    // Method to initialize frame dragging functionality
    public void initMoving(JFrame frame) {
        movingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialX = e.getX();
                initialY = e.getY();
            }
        });
        movingPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - initialX, e.getYOnScreen() - initialY);
            }
        });
    }

    // Method to add event listener for menu selection
    public void addEventMenuSelected(EventMenuSelected event) {
        this.eventMenuSelected = event;
        menuList.addEventMenuSelected(event);
    }
}
