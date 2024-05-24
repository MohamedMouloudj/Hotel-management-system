package model.supervisors;

import controllers.Controller;
import model.Database;
import model.Guest;
import model.hotel.Reservation;
import org.bson.Document;
import view.components.table.Table;

import java.util.HashMap;
import java.util.Objects;

public class Receptionist extends Worker {

//    private HashMap<String, Reservation> reservations = new HashMap<>();
    public Receptionist(String firstName, String lastName, String email) {
        super(firstName, lastName,email,null );
        this.role = Role.RECEPTIONIST;
    }

//    public HashMap<String, Reservation> getReservations() {
//        return reservations;
//    }

//    public void setReservations(HashMap<String, Reservation> reservations) {
//        this.reservations = reservations;
//    }

    public static void addGuestToDataBase(Guest guest){
        HashMap<String,String> guestDocument = new HashMap<>();
        guestDocument.put("firstName", guest.getFirstName());
        guestDocument.put("lastName", guest.getLastName());
        guestDocument.put("email", guest.getEmail());
        guestDocument.put("password", guest.getPassword());

        HashMap<String, Object> objectHashMap = new HashMap<>(guest.getReservations());
        Document tmpDocument = new Document(objectHashMap);
        System.out.println(tmpDocument.toJson());
        guestDocument.put("Reservations", tmpDocument.toJson());

        Database.addToDataBase("Guests", guestDocument);
    }
}