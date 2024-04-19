package view.managerUi;

import view.components.ProfileUi;
import view.components.sideBarComponents.SideBar;
import view.userUi.GuestManagement;
import view.userUi.ReceptionistManagement;
import view.userUi.UserGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerUi extends UserGui {
    public ManagerUi() {
        super();
    }

    private SideBar sideBar;
    private JTabbedPane tabbedContent;
    private Map<String, ActionListener> map;

    @Override
    public void initializeTabbedContent() {

        tabbedContent = new JTabbedPane();
        tabbedContent.setUI(super.layoTabTitleUI);
        ProfileUi profilePanel = new ProfileUi();
        profilePanel.setBackground(Color.WHITE);
        profilePanel.addFirstName("John");
        profilePanel.addLastName("Doe");
        profilePanel.addEmail("mdlc@gmail.com");
        profilePanel.addPassword("password123");

        // RoomsPanelGuest roomsPanel = new RoomsPanelGuest();

        tabbedContent.addTab("", new ReceptionistManagement().createUserTabbedPane());
        tabbedContent.addTab("", new GuestManagement().createUserTabbedPane());
        tabbedContent.addTab("", profilePanel);
        // tabbedContent.addTab("", roomsPanel);
        tabbedContent.addTab("", null);

    }

    @Override
    public void initializeSideBar() {
        // Create map for sidebar actions
        map = new TreeMap<>();
        map.put("Receptionists", e -> tabbedContent.setSelectedIndex(0));
        map.put("Guests", e -> tabbedContent.setSelectedIndex(1));
        map.put("Profile", e -> tabbedContent.setSelectedIndex(2)); // Temporary
        map.put("Rooms", e -> tabbedContent.setSelectedIndex(3)); // Temporary

        // Initialize sidebar with the map of actions
        sideBar = new SideBar(map, tabbedContent);

        // Add sidebar and tabbed content to the frame
        add(sideBar, BorderLayout.WEST);
        add(tabbedContent, BorderLayout.CENTER);
    }

    /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        // SwingUtilities.invokeLater(Manager::new);
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.OFF);
        new ManagerUi();
    }
}