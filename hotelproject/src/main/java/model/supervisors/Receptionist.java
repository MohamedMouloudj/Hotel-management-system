package model.supervisors;

import model.Database;
import model.Guest;
import org.bson.Document;
import java.util.HashMap;
import java.util.Objects;

public class Receptionist extends Worker {

    public Receptionist(String firstName, String lastName, String email) {
        super(firstName, lastName, email, firstName + "123");
        this.role = Role.RECEPTIONIST;
    }
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
    public static void removeGuestFromDataBase(String email){
        try {
            Database.removeFromDataBase("Guests", "email", email);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void updateGuestInDataBase(String email, String key, Object updatedValue){
        try {
            if(!Objects.equals(key, "Reservations")){
                Database.updateFieldInDataBase("Guests", "email", email, key, (String) updatedValue);
            }
            if (Objects.equals(key, "Reservations")){
                HashMap<String, Object> objectHashMap = new HashMap<>((HashMap<String, Object>) updatedValue);
                Document tmpDocument = new Document(objectHashMap);
                Database.updateFieldInDataBase("Guests", "email", email, key, tmpDocument.toJson());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}