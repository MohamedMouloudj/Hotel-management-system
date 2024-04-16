package model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;

/**
 * This class is responsible for connecting to the MongoDB database and
 * retrieving the rooms from it. It holds the database connection .
 * Use it in Hotel once.
 */
public class MongoConnection {

    private static MongoClient mongoClient;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            // Replace "localhost" and "27017" with your connection details if needed
            mongoClient = new MongoClient("localhost", 27017);
        }
        return mongoClient.getDatabase("Hotel");
    }

    public static void retrieveRoomsFromDB(HashMap<String, Room> rooms) throws RetreiveFromDBException {
        MongoCollection<Document> roomCollection = retreiveCollectionFromDB("Rooms");
        System.out.println("Number of documents in Rooms: " + roomCollection.countDocuments());
        if (roomCollection == null)
            return;
        try {
            MongoCursor<Document> cursor = roomCollection.find().iterator();
            int max = 0;
            while (cursor.hasNext()) {
                Document roomDocument = cursor.next();
                Room room = new Room(RoomType.valueOf(roomDocument.getString("roomType")),
                        roomDocument.getBoolean("isAvailable"),
                        roomDocument.getDouble("price"));
                room.setRoomNumber(roomDocument.getString("roomNumber"));
                // Update the idCounter to the maximum room number (replaceAll("\\D+","")
                // removes all non-digits)
                Room.idCounter = Math.max(Room.idCounter,
                        Integer.parseInt(room.getRoomNumber().replaceAll("\\D+", "")));
                rooms.put(room.getRoomNumber(), room);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RetreiveFromDBException("Error occurred while retrieving rooms from the database");
        }
    }

    private static MongoCollection<Document> retreiveCollectionFromDB(String collectionName) {
        try {
            MongoDatabase database = MongoConnection.getDatabase();
            return database.getCollection(collectionName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
