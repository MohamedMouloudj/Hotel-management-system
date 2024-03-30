package view.components.sideBarComponents;

import net.miginfocom.swing.MigLayout;
import view.components.DynamicButton;
import view.login.loginMain.LogInForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SideBar extends JPanel {
    private SideTabsContainer sideTabs;
    private JLabel logo;
    JPanel container;
    private int width = 250;
    private int height = 650;

    private Color endColor=new Color(0, 112, 255);
    private Color startColor= new Color(0x00BFFF);


    public SideBar(HashMap<String, ActionListener> map , JTabbedPane tabbedContent){

        setSize(width, height);
        setLayout(new BorderLayout());

        ///////////// logo ///////////////////////
        logo = new JLabel();
        ImageIcon icon = new ImageIcon("hotelproject/src/main/java/view/icons/OasisLogo.png");
        logo.setIcon(icon);
        logo.setBackground(null);
        //////////////////////////////////////////

        /////////////// sideTabs //////////////////
        sideTabs=new SideTabsContainer(map,tabbedContent);
//        sideTabs.setPreferredSize(new Dimension(width-20,200));
        sideTabs.setBackground(null);
//        sideTabs.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
//        sideTabs.setLayout(new MigLayout("fillx,wrap 1 , inset 0 ","0[]0","[]"));
        //////////////////////////////////////////

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

        JPanel container=new JPanel();
        container.setLayout(new MigLayout("fillx, inset 0,wrap 1","[right]","30[]push[]push[]30"));
        container.setBackground(new Color(0x1E90FF));
        container.setPreferredSize(new Dimension(200,0));

        container.add(logo,"center,wrap, gapbottom 50");
        container.add(sideTabs,"wrap, growy, pushy");
        container.add(quitButton,"center");
        add(container,BorderLayout.WEST);
        setVisible(true);

    }


//    /**
//     * This method adds a tab to the sidebar, its purpose is to use different sidebars to accommodate different windows
//     *
//     * @param tab the tab to be added
//     *
//     * @autor Mouloudj
//     */
//    public void addTab(SideButton tab) {
//        sideTabs.add(tab, "right");
//    }

    public void setWidth(int width) {
        this.width = width;
        setSize(width, height);
        repaint();
    }
}