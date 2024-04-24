package view.UserGui;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JPanel;

import model.Receptionist;
import view.UserMangementGui.GuestManagement;
import view.components.Form_Home;
import view.components.ProfileUi;

public class ReceptionistGui extends UserGui<Receptionist> {

    public ReceptionistGui(Receptionist user) {
        super(user);
    }

    @Override
    public LinkedHashMap<String, String> getMenuItems() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("profile", "8");
        map.put("Guests", "8");
        map.put("Rooms", "3");
        map.put("Requests", "3");
        map.put("My Data", "6");
        map.put("Notifications", "6");
        map.put("Analytics/Reports", "7");
        map.put("Sample Page", "8");
        map.put("About", "8");
        map.put("Help and Support", "8");
        map.put("language", "9");
        return map;
    }

    @Override
    public HashMap<Integer, JPanel> getForms() {

        HashMap<Integer, JPanel> formMap = new HashMap<>();

        ProfileUi profile = new ProfileUi();
        profile.addFirstName(user.getFirstName());
        profile.addLastName(user.getLastName());
        profile.addEmail(user.getEmail());
        profile.addPassword(user.getPassword());

        formMap.put(1, profile);
        formMap.put(2, new GuestManagement());
        formMap.put(3, new Form_Home("Rooms"));
        formMap.put(4, new Form_Home("Requests"));
        formMap.put(5, new Form_Home("My Data"));
        formMap.put(6, new Form_Home("Notifications"));
        formMap.put(7, new Form_Home("Analytics/Reports"));
        formMap.put(8, new Form_Home("Sample Page"));
        formMap.put(9, new Form_Home("About"));
        formMap.put(10, new Form_Home("Help and Support"));
        formMap.put(11, new Form_Home("language"));

        return formMap;
    }

    public static void main(String args[]) {

        UserGui.run(new ReceptionistGui(new Receptionist("null", "null", "null")));
    }
}
