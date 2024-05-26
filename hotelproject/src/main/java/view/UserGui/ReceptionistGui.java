package view.UserGui;

import controllers.Controller;
import controllers.UserType;
import model.supervisors.Receptionist;
import model.supervisors.Role;
import view.UserMangementGui.GuestManagement;
import view.components.ProfileUi;
import view.components.reservationComponents.ReservationPanelReceptionist;
import view.components.roomComponents.RoomsPanelGuest;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ReceptionistGui extends UserGui<Receptionist> {

    public ReceptionistGui(Receptionist user) {
        super(user);
        super.init();
    }

    @Override
    public LinkedHashMap<String, String> getMenuItems() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("Rooms", "bed");
        map.put("Guests", "user");
        map.put("Requests", "bed");
        map.put("Profile", "gear");

        return map;
    }

    @Override
    public HashMap<Integer, JPanel> getPanels() {

        HashMap<Integer, JPanel> panelMap = new HashMap<>();

        ProfileUi profile = new ProfileUi(UserType.RECEPTIONIST);
        profile.addFirstName(user.getFirstName());
        profile.addLastName(user.getLastName());
        profile.addEmail(user.getEmail());
        profile.addRollRow(Role.RECEPTIONIST);

        RoomsPanelGuest roomsPanel = new RoomsPanelGuest(Controller.roomsToRoomPanelGuest());

        panelMap.put(1, roomsPanel);
        panelMap.put(2, new GuestManagement());
        panelMap.put(3, new ReservationPanelReceptionist());
        panelMap.put(4, profile);

        return panelMap;
    }

    public static void main(String args[]) {
        UserGui.run(new ReceptionistGui(new Receptionist("null", "null", "null")));
    }
}
