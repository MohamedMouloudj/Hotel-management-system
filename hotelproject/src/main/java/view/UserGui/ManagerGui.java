package view.UserGui;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JPanel;

import model.Manager;
import view.UserMangementGui.GuestManagement;
import view.UserMangementGui.ReceptionistsManagement;
import view.components.Form_Home;
import view.components.ProfileUi;

public class ManagerGui extends UserGui<Manager> {

    public ManagerGui(Manager manager) {
        super(manager);
    }

    @Override
    public LinkedHashMap<String, String> getMenuItems() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("Profile", "user");
        map.put("Receptionits", "8");
        map.put("Guests", "8");
        map.put("Rooms", "3");
        map.put("Security", "4");
        map.put("Billing/Accounting", "5");
        map.put("My Data", "6");
        map.put("Analytics/Reports", "7");
        map.put("Sample Page", "8");
        map.put("Camera", "8");
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

        formMap.put(1, profile);
        formMap.put(2, new ReceptionistsManagement());
        formMap.put(3, new GuestManagement());
        formMap.put(4, new Form_Home("Rooms"));
        formMap.put(5, new Form_Home("Security"));
        formMap.put(6, new Form_Home("Billing/Accounting"));
        formMap.put(7, new Form_Home("My Data"));
        formMap.put(8, new Form_Home("Analytics/Reports"));
        formMap.put(9, new Form_Home("Sample Page"));
        formMap.put(10, new Form_Home("Camera"));
        formMap.put(11, new Form_Home("language"));
        // formMap.put(13, new JOptionPane());

        return formMap;
    }

    public static void main(String args[]) {

        UserGui.run(new ManagerGui(new Manager()));
    }

}
