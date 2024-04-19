package model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import controllers.Main;
import org.bson.Document;
// import org.bson.conversions.Bson;

abstract public class Manager extends User {

    public Manager(String firstName, String lastName, String password, String email) {
        super(firstName, lastName, password, email);
    }

    public static void addRoom(RoomType roomType, boolean isAvailable, double price) {
        Room room = new Room(roomType, isAvailable, price);
        Main.hotel.getRooms().put(room.getRoomNumber(), room);
        room.insertToDB();
    }
}