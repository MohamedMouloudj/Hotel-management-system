package view.components.menu;

import model.Model_Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedHashMap;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

    @SuppressWarnings("unused")
    private EventMenuSelected eventMenuSelected; // Event listener for menu selection
    private int initialX; // Initial mouse X position for dragging the frame
    private int initialY; // Initial mouse Y position for dragging the frame

    // Components declaration
    private JLabel logoLabel;
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

        movingPanel.setOpaque(false); // Make moving panel transparent

        logoLabel.setFont(new Font("sansserif", Font.BOLD, 18)); // Set font for logo label
        logoLabel.setForeground(new Color(255, 255, 255)); // Set text color for logo label
        logoLabel.setIcon(new ImageIcon(getClass().getResource("/view/icons/OasisLogo.png"))); // Set logo icon

        GroupLayout movingPanelLayout = new GroupLayout(movingPanel);
        movingPanel.setLayout(movingPanelLayout);
        movingPanelLayout.setHorizontalGroup(
                movingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(movingPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(logoLabel, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                                .addContainerGap()));
        movingPanelLayout.setVerticalGroup(
                movingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(movingPanelLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(logoLabel)
                                .addContainerGap()));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(movingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(menuList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(movingPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(menuList, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)));
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

        menuList.addItem(new Model_Menu("", " ", Model_Menu.MenuType.EMPTY));

        for (String text : menuMap.keySet()) {
            menuList.addItem(new Model_Menu(menuMap.get(text), text, Model_Menu.MenuType.MENU));
        }

        menuList.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
        menuList.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
        menuList.addItem(new Model_Menu("quit", "Logout", Model_Menu.MenuType.MENU));
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
