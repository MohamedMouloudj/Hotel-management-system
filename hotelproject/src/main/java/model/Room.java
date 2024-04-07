package model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Room {

    protected static int idCounter = 0;
    private String roomNumber;
    private RoomType roomType;
    private double price;
    private boolean isAvailable;

    public Room(RoomType roomType, boolean isAvailable, double price) {
        idCounter++;
        this.roomNumber = roomType.toString() + idCounter;
        this.roomType = roomType;
        this.price=price;
        this.isAvailable = isAvailable;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public double getRoomPrice() {
        return price;
    }

    public boolean availability() {
        return isAvailable;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    /**
     * Called when retrieving from DB
     * */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
        idCounter--;
    }


    public void insertToDB() {
        try {
            // Retrieving the Client collection
            MongoCollection<Document> roomCollection = Hotel.hotelDatabase.getCollection("Rooms");

            // Creating a document for the new room
            Document guestDocument = new Document("roomNumber", this.roomNumber)
                    .append("roomType", this.roomType.toString())
                    .append("price", this.price)
                    .append("isAvailable", this.isAvailable);

            // Inserting the guest document into the collection
            roomCollection.insertOne(guestDocument);
            System.out.println("Room inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
