package view.UserGui;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JPanel;

import controllers.Main;
import model.Guest;
import view.components.Form_Home;
import view.components.ProfileUi;
import view.components.roomComponents.RoomsPanelGuest;

public class GuestUi extends UserGui<Guest> {

    public GuestUi(Guest guest) {
        super(guest);
    }

    @Override
    public LinkedHashMap<String, String> getMenuItems() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("Profile", "8");
        map.put("Rooms", "3");
        map.put("Reservations", "3");
        map.put("My Data", "6");
        map.put("Notifications", "6");
        map.put("Analytics/Reports", "7");
        map.put("Sample Page", "8");
        map.put("About", "8");
        map.put("Sample Page", "8");
        map.put("Contact", "8");
        map.put("Help and Support", "8");
        map.put("Language", "9");
        return map;
    }

    @Override
    public HashMap<Integer, JPanel> getForms() {

        HashMap<Integer, JPanel> formMap = new HashMap<>();
        RoomsPanelGuest roomsPanel = new RoomsPanelGuest(Main.roomsToRoomPanelGuest());

        ProfileUi p = new ProfileUi();
        p.addFirstName(user.getFirstName());
        p.addLastName(user.getLastName());
        p.addEmail(user.getEmail());

        formMap.put(1, p);
        formMap.put(2, roomsPanel);
        formMap.put(3, new Form_Home("Reservations"));
        formMap.put(4, new Form_Home("My Data"));
        formMap.put(5, new Form_Home("Notifications"));
        formMap.put(6, new Form_Home("Analytics/Reports"));
        formMap.put(7, new Form_Home("Sample Page"));
        formMap.put(8, new Form_Home("About"));
        formMap.put(9, new Form_Home("Contact"));
        formMap.put(10, new Form_Home("Help and Support"));
        formMap.put(11, new Form_Home("Language"));

        return formMap;
    }

    public static void main(String args[]) {
        // Creating a valid Guest object with actual values
        Guest validGuest = new Guest("John", "Doe", "john.doe@example.com", "password");

        // Passing the valid Guest object to the GuestUi constructor
        UserGui.run(new GuestUi(validGuest));
    }

}
