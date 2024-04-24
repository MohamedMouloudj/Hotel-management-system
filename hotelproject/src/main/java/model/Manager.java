package model;

import controllers.Main;

public class Manager extends User {

    public static final String firstName = "ww";
    public static final String lastName = "ww";
    public static final String email = "ww";
    public static final String password = "ww";

    public Manager() {
        super(firstName, lastName, email, password);
    }

    public static void addRoom(RoomType roomType, boolean isAvailable, double price) {
        Room room = new Room(roomType, isAvailable, price);
        Main.hotel.getRooms().put(room.getRoomNumber(), room);
        room.insertToDB();
    }
}