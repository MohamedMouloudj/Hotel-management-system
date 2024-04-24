package view.guestUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;

import model.Guest;
import view.UserGui;
import view.components.*;
import view.components.HomeComponents.Card;
import view.components.HomeComponents.HomePage;
import view.components.roomComponents.RoomOnList;
import view.components.roomComponents.RoomsPanelGuest;
import view.components.sideBarComponents.SideBar;
import view.components.sideBarComponents.SideButton;

public class GuestUi extends UserGui {
    private SideBar sideBar;
    private final HomePage homePage;
    private final RoomsPanelGuest roomsPanel;
    JPanel emptyTMPPanel;
    private final ProfileUi profilePanel;
    private SideButton roomsTab;
    private SideButton reservationsTab;
    private SideButton profileTab;
    private JTabbedPane tabbedContent;
    private LinkedHashMap<String, ActionListener> map;
    Color fancyColor = new Color(0x0377FF);
    Color lightColor = new Color(0x4FB5FF);

    public GuestUi(HashMap<String, RoomOnList> roomsList, Guest guest) {

        /////////////////// Right Content /////////////////////////////////

        roomsPanel = new RoomsPanelGuest(roomsList);
        emptyTMPPanel = new JPanel();
        emptyTMPPanel.setBackground(Color.WHITE);
        profilePanel = new ProfileUi();
        profilePanel.setBackground(Color.WHITE);
        profilePanel.addFirstName(guest.getFirstName());
        profilePanel.addLastName(guest.getLastName());
        profilePanel.addEmail(guest.getEmail());

        // JUST AN EXAMPLE OF HOW TO ADD CARDS TO THE HOMEPAGE
        homePage = new HomePage();
        Card card1 = new Card("Available Rooms", "Click here to see the available rooms");
        homePage.addCard(card1);
        Card card2 = new Card("Reservations", "Click here to see your reservations");
        card2.setBgColor(new Color(0x995555), new Color(0x995599));
        homePage.addCard(card2);
        ////////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////////////////////////
        add(sideBar, BorderLayout.WEST);
        add(tabbedContent, BorderLayout.CENTER);
    }

    @Override
    public void initializeTabbedContent() {
        tabbedContent = new JTabbedPane();
        tabbedContent.setUI(super.hideTabs); //Hide tab titles
        tabbedContent.addTab("homePage", homePage);
        tabbedContent.addTab("roomsPanel", roomsPanel);
        tabbedContent.addTab("emptyPanel", emptyTMPPanel);//Temporary
        tabbedContent.addTab("profilePanel", profilePanel);//Temporary
    }

    @Override
    public void initializeSideBar() {
        map = new LinkedHashMap<>();
        map.put("Home", e -> tabbedContent.setSelectedIndex(0));
        map.put("Rooms", e -> tabbedContent.setSelectedIndex(1));
        map.put("Requests", e -> tabbedContent.setSelectedIndex(2));
        map.put("ProfileUi", e -> tabbedContent.setSelectedIndex(3));
        sideBar = new SideBar(map, tabbedContent);
    }
}