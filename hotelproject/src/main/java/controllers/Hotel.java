package controllers;

import java.util.ArrayList;

import model.Guest;
import model.Room;
import model.Receptionist;

public class Hotel {

    private final String name = "L'oasis";
    private final String location = "L'Oasis Hotel Rue du Paradis 56 Besakara";
    private static ArrayList<Guest> guests;
    private static ArrayList<Room> rooms;
    private static ArrayList<Receptionist> employees;

    public Hotel() {
        this.guests = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<Guest> guests) {
        this.guests = guests;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Receptionist> getEmployees() {
        return employees;
    }

    public static void setEmployees(ArrayList<Receptionist> employees) {
        employees = employees;
    }

    public static String generateId(int idCounter, char type) {
        return type + "" + idCounter;
    }

}
