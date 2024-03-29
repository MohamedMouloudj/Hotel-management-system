package view.gustUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import view.components.*;
import view.components.sideBarComponents.SideBar;
import view.components.sideBarComponents.SideButton;

public class GuestUi extends JFrame {
    private SideBar sideBar;
    private SideButton roomsTab;
    private SideButton reservationsTab;
    private SideButton profileTab;
    private JTabbedPane tabbedContent;
    private HashMap<String, ActionListener> map = new HashMap<>();
    Color fancyColor = new Color(0x0377FF);
    Color lightColor = new Color(0x4FB5FF);
    public GuestUi() {
        setIconImage(new ImageIcon("hotelproject/src/main/java/view/icons/programIcon.jpg").getImage());
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5,0));
        /////////////////////////////////////////////////////////////

        /////////////////// Right Content /////////////////////////////////
        tabbedContent = new JTabbedPane();
        RoomsPanel roomsPanel = new RoomsPanel();
        JPanel emptyTMPPanel=new JPanel();
        emptyTMPPanel.setBackground(Color.WHITE);
        tabbedContent.add(roomsPanel, "roomsPanel");
        tabbedContent.add(emptyTMPPanel,"emptyPanel");//Temporary
        ////////////////////////////////////////////////////////////////////

        /////////////////// SideBar ////////////////////////////////////////
        map.put("Rooms", e -> tabbedContent.setSelectedIndex(0));
        map.put("Reservations", e -> tabbedContent.setSelectedIndex(1));//Temporary
        sideBar = new SideBar(map, tabbedContent);
        sideBar.setWidth(800);

        ////////////////////////////////////////////////////////////////////
        add(sideBar, BorderLayout.WEST);
        add(tabbedContent, BorderLayout.CENTER);
        setVisible(true);
    }

}
