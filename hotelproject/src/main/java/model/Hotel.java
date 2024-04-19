package model;

import java.util.ArrayList;
import java.util.HashMap;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import controllers.Reservation;
import org.bson.Document;

public class Hotel {
    public static final MongoDatabase hotelDatabase = MongoConnection.getDatabase();
    private final int connectedUserType;
    private final String name = "L'Oasis";
    private final String location = "L'Oasis Hotel Rue du Paradis 56 Besakara";
    protected static final HashMap<String, Guest> guests = new HashMap<>();
    protected static final HashMap<String, Reservation> reservationRequests = new HashMap<>();
    protected static final HashMap<String, Guest> confirmedGuests = new HashMap<>();
    protected static final HashMap<String, Guest> waitlist = new HashMap<>();
    protected static final HashMap<String, Room> rooms = new HashMap<>();
    protected static final HashMap<String, Receptionist> employees = new HashMap<>();

    /**
     * @param connectedUserType 1 for guest, 2 for receptionist, 3 for manager
     */
    public Hotel(int connectedUserType) {
        this.connectedUserType = connectedUserType;

        // Retrieving rooms from the database and putting them in the rooms HashMap
        try {
            MongoConnection.retrieveRoomsFromDB(rooms);
        } catch (RetreiveFromDBException e) {
            System.out.println(e.getMessage());
        }

        if (connectedUserType == 1) {

        }
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public HashMap<String, Guest> getGuests() {
        return guests;
    }

    public HashMap<String, Room> getRooms() {
        return rooms;
    }

    public HashMap<String, Receptionist> getEmployees() {
        return employees;
    }

}

class RetreiveFromDBException extends Exception {
    RetreiveFromDBException(String msg) {
        super(msg);
    }
}