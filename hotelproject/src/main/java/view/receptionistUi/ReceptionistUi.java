package view.receptionistUi;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import javax.swing.JTabbedPane;

import model.supervisors.Receptionist;
import view.components.ProfileUi;
import view.components.sideBarComponents.SideBar;
import view.userUi.GuestManagement;
import view.UserGui;

public class ReceptionistUi extends UserGui {
    private JTabbedPane tabbedContent;
    private SideBar sideBar;
    private Receptionist receptionist;

    public ReceptionistUi(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    public Receptionist getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    @Override
    public void initializeTabbedContent() {
        tabbedContent = new JTabbedPane();
        tabbedContent.setUI(super.hideTabs);

        ProfileUi profilePanel = new ProfileUi();
        // profilePanel.addFirstName(this.receptionist.getFirstName());
        // profilePanel.addLastName(this.receptionist.getLastName());
        // profilePanel.addEmail(this.receptionist.getEmail());
        // profilePanel.addPassword("password123");

        // RoomsPanelGuest roomsPanel = new RoomsPanelGuest();

        tabbedContent.addTab("", new GuestManagement().createUserTabbedPane());
        tabbedContent.addTab("", profilePanel);
        tabbedContent.addTab("", null);
        // tabbedContent.addTab("", roomsPanel);
    }

    @Override
    public void initializeSideBar() {
        LinkedHashMap<String, ActionListener> map = new LinkedHashMap<>();
        map.put("Guests", e -> tabbedContent.setSelectedIndex(0));
        map.put("Profile", e -> tabbedContent.setSelectedIndex(1)); // Temporary
        map.put("Rooms", e -> tabbedContent.setSelectedIndex(2)); // Temporary
        map.put("Reservations", e -> tabbedContent.setSelectedIndex(3)); // Temporary

        // Initialize sidebar with the map of actions
        sideBar = new SideBar(map, tabbedContent);

        // Add sidebar and tabbed content to the frame
        add(sideBar, BorderLayout.WEST);
        add(tabbedContent, BorderLayout.CENTER);
    }

    public static void runFrom(Receptionist receptionist) {
        new ReceptionistUi(receptionist);
    }

}
