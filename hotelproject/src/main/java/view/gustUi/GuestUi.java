package view.gustUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.components.*;
public class GuestUi extends JFrame implements MouseListener {
    SideBar sideBar;
    SideButton roomsTab;
    SideButton reservationsTab;
    SideButton profileTab;
    JTabbedPane tabbedContent;
    Color fancyColor = new Color(0x0377FF);
    Color lightColor = new Color(0x4FB5FF);
    public GuestUi() {
        setIconImage(new ImageIcon("hotelproject/src/main/java/view/icons/programIcon.jpg").getImage());
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(5,0));

        sideBar = new SideBar();
        sideBar.setWidth(800);
        add(sideBar, BorderLayout.WEST);

        roomsTab = new SideButton("Rooms");
        reservationsTab = new SideButton("Reservations");
        profileTab = new SideButton("Profile");

        sideBar.addTab(roomsTab);
        sideBar.addTab(reservationsTab);
        sideBar.addTab(profileTab);
        roomsTab.addMouseListener(this);


        RoomsPanel roomsPanel = new RoomsPanel();

        tabbedContent = new JTabbedPane();

        JPanel emptyTMPPanel=new JPanel();
        emptyTMPPanel.setBackground(Color.WHITE);

        tabbedContent.add(roomsPanel, "roomsPanel");
        tabbedContent.add(emptyTMPPanel,"emptyPanel");//Temporary
        add(tabbedContent, BorderLayout.CENTER);

        setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == roomsTab) {
            tabbedContent.setSelectedIndex(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Do nothing
    }
}
