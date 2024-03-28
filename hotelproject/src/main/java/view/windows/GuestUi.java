package view.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import net.miginfocom.swing.MigLayout;
import view.basicComponents.*;
public class GuestUi extends JFrame implements MouseListener {
    SideBar sideBar;
    SideLabel roomsTab;
    SideLabel reservationsTab;
    SideLabel profileTab;
    JPanel content;
    CardLayout cardLayout;
    private boolean isRoomsPanelVisible = true;
    Color fancyColor = new Color(0x0377FF);
    Color lightColor = new Color(0x4FB5FF);
    public GuestUi() {
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(10,0));

        sideBar = new SideBar();
        roomsTab = new SideLabel("Rooms");
        reservationsTab = new SideLabel("Reservations");
        profileTab = new SideLabel("Profile");

        sideBar.addTab(roomsTab);
        sideBar.addTab(reservationsTab);
        sideBar.addTab(profileTab);
        roomsTab.addMouseListener(this);

        add(sideBar, BorderLayout.WEST);

        RoomsPanel roomsPanel = new RoomsPanel();

        cardLayout = new CardLayout();
        content = new JPanel();
        content.setLayout(cardLayout);

        JPanel emptyTMPPanel=new JPanel();
        emptyTMPPanel.setBackground(Color.WHITE);

        content.add(roomsPanel, "roomsPanel");
        content.add(emptyTMPPanel,"emptyPanel");//Temporary
        add(content, BorderLayout.CENTER);
        isRoomsPanelVisible = true;
        setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() != roomsTab) {
            isRoomsPanelVisible=false;
        }else {
            isRoomsPanelVisible=true;
        }
        System.out.println(isRoomsPanelVisible);
        if(isRoomsPanelVisible){
            cardLayout.show(content,"roomsPanel");
        }
        if (isRoomsPanelVisible==false){
            cardLayout.show(content,"emptyPanel");
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
