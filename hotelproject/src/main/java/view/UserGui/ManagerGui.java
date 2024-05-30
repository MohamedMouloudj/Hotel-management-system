package view.UserGui;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.swing.*;
import controllers.UserType;
import model.supervisors.Manager;
import view.UserMangementGui.GuestManagement;
import view.UserMangementGui.ReceptionistsManagement;
import view.components.WelcomePage;
import view.components.pagePanel;
import view.components.roomComponents.RoomsPanelManager;


public class ManagerGui extends UserGui<Manager> {
    private int roomsNumber;
    private int workersNumber;
    private int guestsNumber;
    private WelcomePage welcomePage;
    public ManagerGui(Manager manager,int roomsNumber,int workersNumber,int guestsNumber) {
        super(manager);
        this.roomsNumber=roomsNumber;
        this.workersNumber=workersNumber;
        this.guestsNumber=guestsNumber;
        super.init();
    }

    @Override
    public LinkedHashMap<String, String> getMenuItems() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("Home", "home");
        map.put("Receptionists", "hammer");
        map.put("Guests", "user");
        map.put("Rooms", "bed");

        return map;
    }

    @Override
    public HashMap<Integer, JPanel> getPanels() {

        HashMap<Integer, JPanel> pagesMap = new HashMap<>();

        welcomePage = new WelcomePage(UserType.MANAGER);
        welcomePage.addCard("hotelproject/src/main/java/view/icons/bed.png", "Rooms",  roomsNumber+" rooms", new Color(0x00BFFF), new Color(0, 112, 255));
        welcomePage.addCard("hotelproject/src/main/java/view/icons/hammer.png", "Workers", workersNumber+" workers", new Color(200, 0, 255),new Color(0x7D3CFF) );
        welcomePage.addCard("hotelproject/src/main/java/view/icons/user.png", "Guests", guestsNumber+" guests", new Color(0xA2FF00), new Color(76, 211, 0));
        pagesMap.put(1, welcomePage);
        pagesMap.put(2, new ReceptionistsManagement(user));
        pagesMap.put(3, new GuestManagement(user));
        pagesMap.put(4, new RoomsPanelManager());

        return pagesMap;
    }

    public WelcomePage getWelcomePage() {
        return welcomePage;
    }
}
