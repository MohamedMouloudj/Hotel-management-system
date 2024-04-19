package view.components.sideBarComponents;

import net.miginfocom.swing.MigLayout;
import view.components.DynamicButton;
import view.login.loginMain.LogInForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class SideBar extends JPanel {
    private JLayeredPane sideTabs;
    private JLabel logo;

    private int width = 240;

    Color startColor = new Color(0x0377FF); // Dark blue
    Color endColor = new Color(0x4FB5FF); // Light blue

    /**
     * @param map           HashMap<String, ActionListener>.
     *                      The key is the text of the tab, and the value is the
     *                      action listener of the tab.
     * @param tabbedContent JTabbedPane.
     *                      The container of all the panel to be shown. It must be
     *                      instantiated in UI class (like GuestUi, ManagerUi, etc)
     */
    public SideBar(Map<String, ActionListener> map, JTabbedPane tabbedContent) {
        setLayout(new MigLayout("fillx,wrap 1 , inset 0", "[right," + width + "!]", "20[]70[]push[]30"));

        ///////////// logo ///////////////////////
        logo = new JLabel();
        ImageIcon icon = new ImageIcon("hotelproject/src/main/java/view/icons/OasisLogo.png");
        logo.setIcon(icon);

        /////////////// sideTabs //////////////////
        sideTabs = new JLayeredPane();
        sideTabs.setLayout(new MigLayout("fillx, wrap 1 , inset 0 ", "0[left," + (width - 30) + "!]0", "[]"));
        sideTabs.setBackground(null);

        for (String text : map.keySet()) {
            SideButton btn = new SideButton(text);
            btn.addActionListener(map.get(text));
            btn.setFont(new Font("sans-serif", 2, 17));
            sideTabs.add(btn, "right,growx, pushx, h 40!, w 100% ,gapbottom 10");
        }
        sideTabs.setBackground(null);

        /////////////// quitButton //////////////////
        DynamicButton quitButton = new DynamicButton("Log out");
        quitButton.setIconToButton(new ImageIcon("hotelproject/src/main/java/view/icons/quit.png"), 5, 4);
        quitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Warning",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    setVisible(false);
                    new LogInForm().setVisible(true);
                    SwingUtilities.getWindowAncestor(SideBar.this).dispose(); // Close the current window
                }
            }
        });

        add(logo, "center");
        add(sideTabs, "wrap, growy, pushy");
        add(quitButton, "center");

        setVisible(true);

    }

    // For gradient background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, width, height, endColor);
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, width, height);
    }
}