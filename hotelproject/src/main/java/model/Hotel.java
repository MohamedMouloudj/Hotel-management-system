package model;

import java.util.ArrayList;
import java.util.HashMap;

import model.Guest;
import model.Room;
import model.Receptionist;

public class Hotel {

    private final String name = "L'oasis";
    private final String location = "L'Oasis Hotel Rue du Paradis 56 Besakara";
    private static HashMap<String,Guest> guests;
    private static HashMap<String,Room> rooms;
    private static HashMap<String,Receptionist> employees;

    public Hotel() {
        this.guests = new HashMap<>();
        this.rooms = new HashMap<>();
        this.employees = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public HashMap<String,Guest> getGuests() {
        return guests;
    }

    public void setGuests(HashMap<String,Guest> guests) {
        Hotel.guests = guests;
    }

    public HashMap<String,Room> getRooms() {
        return rooms;
    }

    public void setRooms(HashMap<String,Room> rooms) {
        Hotel.rooms = rooms;
    }

    public HashMap<String,Receptionist> getEmployees() {
        return employees;
    }

    public static void setEmployees(HashMap<String,Receptionist> employees) {
        Hotel.employees = employees;
    }

    public static String generateId(int idCounter, char type) {
        return type + "" + idCounter;
    }

}
