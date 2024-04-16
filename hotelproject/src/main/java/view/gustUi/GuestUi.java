package view.gustUi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.JTabbedPane;

import controllers.Main;
import model.Guest;
import view.components.ProfileUi;
import view.components.HomeComponents.Card;
import view.components.HomeComponents.HomePage;
import view.components.roomComponents.RoomsPanelGuest;
import view.components.sideBarComponents.SideBar;
import view.userUi.UserGui;

public class GuestUi extends UserGui {
    private JTabbedPane tabbedContent;
    private SideBar sideBar;
    private Guest guest;

    public GuestUi(Guest guest) {
        super();
        this.guest = guest;

    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Override
    public void initializeTabbedContent() {
        tabbedContent = new JTabbedPane();
        tabbedContent.setUI(super.layoTabTitleUI);

        ProfileUi profilePanel = new ProfileUi();
        // System.out.println("name: " + this.guest.getFirstName());
        // profilePanel.addFirstName(this.guest.getFirstName());
        // profilePanel.addLastName(this.guest.getLastName());
        // profilePanel.addEmail(this.guest.getEmail());
        // profilePanel.addPassword(this.guest.getPassword());

        RoomsPanelGuest roomsPanel = new RoomsPanelGuest(Main.roomsToRoomPanelGuest());
        HomePage homePage = new HomePage();
        Card card1 = new Card("Available Rooms", "Click here to see the available rooms");
        homePage.addCard(card1);
        Card card2 = new Card("Reservations", "Click here to see your reservations");
        card2.setBgColor(new Color(0x995555), new Color(0x995599));
        homePage.addCard(card2);

        tabbedContent.addTab("", homePage);
        tabbedContent.addTab("", profilePanel);
        tabbedContent.addTab("", roomsPanel);
        tabbedContent.addTab("", null);
    }

    @Override
    public void initializeSideBar() {
        TreeMap<String, ActionListener> map = new TreeMap<>();
        map.put("Home", e -> tabbedContent.setSelectedIndex(0));
        map.put("Profile", e -> tabbedContent.setSelectedIndex(1)); // Temporary
        map.put("Rooms", e -> tabbedContent.setSelectedIndex(2)); // Temporary
        map.put("Reservations", e -> tabbedContent.setSelectedIndex(3)); // Temporary

        // Initialize sidebar with the map of actions
        sideBar = new SideBar(map, tabbedContent);

        // Add sidebar and tabbed content to the frame
        add(sideBar, BorderLayout.WEST);
        add(tabbedContent, BorderLayout.CENTER);
    }

}