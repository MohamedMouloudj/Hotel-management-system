package view.components.sideBarComponents;

import net.miginfocom.swing.MigLayout;
import view.components.DynamicButton;
import view.login.loginMain.LogInForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SideBar extends JPanel {
    private JLayeredPane sideTabs;
    private JLabel logo;
//    private JPanel container;
    private int width = 250;
    private int height = 650;

    Color startColor = new Color(0x0377FF); // Dark blue
    Color endColor = new Color(0x4FB5FF); // Light blue

    /**
     * @param map           HashMap<String, ActionListener>.
     *                      The key is the text of the tab, and the value is the action listener of the tab.
     * @param tabbedContent JTabbedPane.
     *                      The container of all the panel to be shown. It must be instantiated in UI class (like GuestUi, ManagerUi, etc)
     */
    public SideBar(HashMap<String, ActionListener> map , JTabbedPane tabbedContent){
        setSize(width, height);
        setLayout(new MigLayout("fillx,wrap 1 , inset 0","[right]","30[]60[]push[]30"));


        ///////////// logo ///////////////////////
        logo = new JLabel();
        ImageIcon icon = new ImageIcon("hotelproject/src/main/java/view/icons/OasisLogo.png");
        logo.setIcon(icon);
        //////////////////////////////////////////


        /////////////// sideTabs //////////////////
        sideTabs=new JLayeredPane();
        sideTabs.setLayout(new MigLayout("fillx,wrap 1 , inset 0 ", "0[]0", "[]"));
        sideTabs.setBackground(null);

        for (String text : map.keySet()) {
            SideButton btn = new SideButton(text);
            btn.addActionListener(map.get(text));
            sideTabs.add(btn, "right");
        }
        sideTabs.setBackground(null);
        /////////////////////////////////////////////


        /////////////// quitButton //////////////////
        DynamicButton quitButton=new DynamicButton("Log out");
        quitButton.setIconToButton(new ImageIcon("hotelproject/src/main/java/view/icons/quit.png"),5,4);
        quitButton.addActionListener(e -> {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Warning", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.NO_OPTION)
                return;
            LogInForm logInForm = new LogInForm();
            logInForm.setVisible(true);
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();    // close the current window
        });
        //////////////////////////////////////////


        add(logo,"center");
        add(sideTabs,"wrap, growy, pushy,gapx 60!");
        add(quitButton,"center");

        setVisible(true);

    }

    //For gradient background
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
        g2d.fillRect(0, 0, width, height);
    }
}