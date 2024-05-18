package view.UserGui;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JPanel;

import controllers.Controller;
import controllers.UserType;
import model.Guest;
import view.components.WelcomePage;
import view.components.pagePanel;
import view.components.ProfileUi;
import view.components.roomComponents.RoomOnList;
import view.components.roomComponents.RoomsPanelGuest;

public class GuestUi extends UserGui<Guest> {
    public GuestUi(Guest guest) {
        super(guest);
        super.init();
    }

    @Override
    public LinkedHashMap<String, String> getMenuItems() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("Home Page", "home");
        map.put("Rooms", "bed");
        map.put("Reservations", "bed");
        map.put("Profile", "gear");

        return map;
    }

    @Override
    public HashMap<Integer, JPanel> getPanels() {

        HashMap<Integer, JPanel> pagesMap = new HashMap<>();
        RoomsPanelGuest roomsPanel = new RoomsPanelGuest(Controller.roomsToRoomPanelGuest());

        ProfileUi profile = new ProfileUi(UserType.GUEST);
        profile.addFirstName(user.getFirstName());
        profile.addLastName(user.getLastName());

        WelcomePage welcomePage = new WelcomePage(UserType.GUEST);

        pagesMap.put(1, welcomePage);
        pagesMap.put(2, roomsPanel);
        pagesMap.put(3, new pagePanel("Reservations"));
        pagesMap.put(4, profile);

        return pagesMap;
    }
}