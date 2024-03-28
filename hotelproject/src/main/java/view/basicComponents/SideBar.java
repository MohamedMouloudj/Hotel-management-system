package view.basicComponents;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SideBar extends JPanel{
    private JPanel sideTabs;
    /*Color fancyColor = new Color(0x0377FF);
    Color lightColor = new Color(0x4FB5FF);*/
    public SideBar(){

        setSize(1000, 500);
        setLayout(new BorderLayout());

        Logo logo=new Logo();

        sideTabs=new JPanel();
        sideTabs.setPreferredSize(new Dimension(180,200));
        sideTabs.setBackground(null);
//      sideTabs.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        sideTabs.setLayout(new MigLayout("fillx,wrap 1 , inset 0 ","0[]0","[]"));

        OurButton quitButton=new OurButton("Log out");
        quitButton.setIconToButton("hotelproject/src/main/java/view/icons/quit.png",5,4);
        quitButton.setWidth(200);

        JPanel container=new JPanel();
        container.setLayout(new MigLayout("fillx, inset 0,wrap 1","[right]","[]30"));
        container.setBackground(new Color(0x1E90FF));
        container.setPreferredSize(new Dimension(200,0));

        container.add(logo,"wrap, gapbottom 50");
        container.add(sideTabs,"wrap, growy, pushy");
        container.add(quitButton);
        add(container,BorderLayout.WEST);
        setVisible(true);

    }

    /**
     * This method adds a tab to the sidebar, its purpose is to use different sidebars to accommodate different windows
     * @param tab the tab to be added
     *
     * @autor Mouloudj
     * */
    public void addTab(SideLabel tab){
        sideTabs.add(tab,"right");
    }
}