package view.UserGui;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.*;

import controllers.UserType;
import model.supervisors.Manager;
import view.UserMangementGui.GuestManagement;
import view.UserMangementGui.ReceptionistsManagement;
import view.components.Card;
import view.components.WelcomePage;
import view.components.pagePanel;
import view.components.ProfileUi;

public class ManagerGui extends UserGui<Manager> {

    public ManagerGui(Manager manager) {
        super(manager);
    }

    @Override
    public LinkedHashMap<String, String> getMenuItems() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("Home", "8");
        map.put("Receptionists", "6");
        map.put("Guests", "user");
        map.put("Rooms", "bed");

        return map;
    }

    @Override
    public HashMap<Integer, JPanel> getPanels() {

        HashMap<Integer, JPanel> formMap = new HashMap<>();

        WelcomePage welcomePage = new WelcomePage(UserType.MANAGER);
        Card card = new Card(new ImageIcon("hotelproject/src/main/java/view/icons/bed.png"), "Rooms", "Rooms");
        card.setColor1(new Color(0x00BFFF));
        card.setColor2(new Color(0, 112, 255));
        welcomePage.addCard(card);

        formMap.put(1, welcomePage);
        formMap.put(2, new ReceptionistsManagement());
        formMap.put(3, new GuestManagement());
        formMap.put(4, new pagePanel("Rooms"));


        return formMap;
    }

    public static void main(String args[]) {

        UserGui.run(new ManagerGui(null));
    }

}
