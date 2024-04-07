package model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import controllers.Main;
import org.bson.Document;
// import org.bson.conversions.Bson;

import model.Receptionist;

abstract public class Manager extends User {


    public Manager(String firstName, String lastName, String password, String email) {
        super(firstName, lastName, password, email);
    }

    public static void addEmployee(Receptionist receptionist) {
        MongoClient mongoClient = null;
        try {

            // Retrieving the Receptionists collection
            MongoCollection<Document> collection = Hotel.hotelDatabase.getCollection("Receptionists");

            // Create a document for the receptionist
            Document receptionistDocument = new Document("firstName", receptionist.getFirstName())
                    .append("lastName", receptionist.getLastName())
                    .append("email", receptionist.getEmail())
                    .append("password", receptionist.getPassword())
                    .append("Role", receptionist.getRole());

            // Insert the receptionist document into the collection
            collection.insertOne(receptionistDocument);
            System.out.println("Receptionist inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeEmployee(String firstName, String lastName) {
        try {
            // Creating the filter to match the receptionist by first and last name
            Document filter = new Document("firstName", firstName).append("lastName",
                    lastName);
            MongoCollection<Document> collection = Hotel.hotelDatabase.getCollection("Receptionists");

            // Deleting the receptionist document
            collection.deleteOne(filter);
            System.out.println("Receptionist removed successfully");
        } catch (Exception e) {
            System.err.println("Error occurred while removing receptionist:");
            e.printStackTrace();
        }
    }

    public static void addRoom(RoomType roomType, boolean isAvailable, double price){
        Room room = new Room(roomType, isAvailable, price);
        Main.hotel.getRooms().put(room.getRoomNumber(), room);
        room.insertToDB();
    }
}