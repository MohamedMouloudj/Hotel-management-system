package model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import controllers.PasswordHashing;
import model.hotel.Hotel;
import model.hotel.Reservation;
import model.hotel.Room;
import model.hotel.RoomType;
import model.supervisors.Others;
import model.supervisors.Receptionist;
import model.supervisors.Role;
import model.supervisors.Worker;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for connecting to the MongoDB database and
 * retrieving the rooms from it. It holds the database connection .
 * Use it in Hotel once.
 */
public class Database {

    private static MongoClient mongoClient;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = new MongoClient("localhost", 27017);
        }
        return mongoClient.getDatabase("Hotel");
    }

    private static MongoCollection<Document> retreiveCollectionFromDB(String collectionName) {
        try {
            return Hotel.hotelDatabase.getCollection(collectionName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void retrieveRoomsFromDB(HashMap<String, Room> rooms) throws DBException {
        MongoCollection<Document> roomCollection = retreiveCollectionFromDB("Rooms");
        if (roomCollection == null)
            return;
        try {
            MongoCursor<Document> cursor = roomCollection.find().iterator();
            while (cursor.hasNext()) {
                Document roomDocument = cursor.next();
                Room room = new Room(RoomType.valueOf(roomDocument.getString("roomType")),
                        roomDocument.getBoolean("isAvailable",false),
                        roomDocument.getDouble("price"));
                room.setRoomNumber(roomDocument.getString("roomNumber"));
                // Update the idCounter to the maximum room number (replaceAll("\\D+","") removes all non-digits)
                Room.idCounter = Math.max(Room.idCounter,
                        Integer.parseInt(room.getRoomNumber().replaceAll("\\D+", "")));
                rooms.put(room.getRoomNumber(), room);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException("Error occurred while retrieving rooms from the database");
        }
    }

    public static void retrieveGuestsFromDB(HashMap<String, Guest> guests) throws DBException{
        MongoCollection<Document> guestCollection = retreiveCollectionFromDB("Guests");
        if (guestCollection == null)
            return;
        try {
            MongoCursor<Document> cursor = guestCollection.find().iterator();
            while (cursor.hasNext()) {
                Document guestDocument = cursor.next();
                // Parse the JSON string back to a Document
                Document reservationsDocument = Document.parse(guestDocument.getString("Reservations"));
                // Convert the Document back to a HashMap
                HashMap<String, Object> reservationsHashMap = new HashMap<>(reservationsDocument);
                // Convert each Object in reservationsHashMap to a Reservation
                HashMap<String, Reservation> reservations = new HashMap<>();
                for (Map.Entry<String, Object> entry : reservationsHashMap.entrySet()) {
                    Document reservationDocument = (Document) entry.getValue();
                    reservations.put(entry.getKey(), Reservation.fromDocument(reservationDocument));
                }
                Guest guest = new Guest(guestDocument.getString("firstName"),
                        guestDocument.getString("lastName"),
                        guestDocument.getString("email"),
                        guestDocument.getString("password"));
                guest.setReservations(reservations);
                // Add the guest to the guests HashMap
                guests.put(guest.getEmail(), guest);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException("Error occurred while retrieving guests from the database");
        }
    }

    public static void retrieveWorkersFromDB(HashMap<String, Worker> workers) throws DBException{
        MongoCollection<Document> receptionistCollection = retreiveCollectionFromDB("Workers");
        if (receptionistCollection == null)
            return;
        try {
            MongoCursor<Document> cursor = receptionistCollection.find().iterator();
            while (cursor.hasNext()) {
                Document workerDocument = cursor.next();
                if(workerDocument.getString("role").equals(Role.RECEPTIONIST.toString())){

//                    Document reservationsDocument = Document.parse(workerDocument.getString("Reservations"));
//
//                    HashMap<String, Object> reservationsHashMap = new HashMap<>(reservationsDocument);
//                    HashMap<String, Reservation> reservations = new HashMap<>();
//                    for (Map.Entry<String, Object> entry : reservationsHashMap.entrySet()) {
//                        Document reservationDocument = (Document) entry.getValue();
//                        reservations.put(entry.getKey(), Reservation.fromDocument(reservationDocument));
//                    }

                    Receptionist receptionist = new Receptionist(workerDocument.getString("firstName"),
                            workerDocument.getString("lastName"),
                            workerDocument.getString("email"));
                    receptionist.setOasisMail(workerDocument.getString("OasisMail"));
//                    receptionist.setReservations(reservations);
                    workers.put(receptionist.getOasisMail(), receptionist);
                }
                if (workerDocument.getString("role").equals(Role.OtherEmployee.toString())){
                    Others otherWorkerType = new Others(workerDocument.getString("firstName"),
                            workerDocument.getString("lastName"),
                            workerDocument.getString("email"));
                    otherWorkerType.setOasisMail(workerDocument.getString("OasisMail"));
                    workers.put(otherWorkerType.getEmail(), otherWorkerType);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException("Error occurred while retrieving rooms from the database");
        }
    }

    /**
     * Get a document from the database
     * @param collectionName The name of the collection
     * @param researchBy The field to search by
     * @param matchingField The value to match
     * */
    public static Document findInDataBase(String collectionName, String researchBy, String matchingField) {
        try {
            MongoCollection<Document> collection = Hotel.hotelDatabase.getCollection(collectionName);
            // Build the query
            Document query = new Document(researchBy, matchingField);
            // Find the document matching the query
            return collection.find(query).first();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Add a new document to the database
     * @param collectionName The name of the collection
     * @param fields The fields to add to the document
     * */
    public static void addToDataBase(String collectionName, HashMap<String, String> fields) {
        try {
            MongoCollection<Document> clientCollection = Hotel.hotelDatabase.getCollection(collectionName);
            // Creating a document for the new guest
            Document document = new Document();
            for (Map.Entry<String, String> entry : fields.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key.equals("isAvailable")){
                    document.append(key, Boolean.parseBoolean(value));
                    continue;
                }
                if (key.equals("price")){
                    document.append(key, Double.parseDouble(value));
                    continue;
                }
                document.append(key, value);
            }

            // Inserting the document into the collection
            clientCollection.insertOne(document);
            System.out.println("Document inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove a document from the database
     * @param collectionName The name of the collection
     * @param fieldName The name of the field to match
     * @param fieldValue The value of the field to match
     * */
    public static <T> void removeFromDataBase(String collectionName, String fieldName, T fieldValue) throws DBException {

        // Creating the filter to match the document by field name and value
        Document filter = new Document(fieldName, fieldValue);
        MongoCollection<Document> collection = Hotel.hotelDatabase.getCollection(collectionName);

        // Deleting the document
        collection.deleteOne(filter);
        System.out.println("Document removed successfully");
    }

    /**
     * Update a document in the database
     * @param collectionName The name of the collection
     * @param fieldName The name of the field to match
     * @param fieldValue The value of the field to match
     * @param updateFieldName The name of the field to update
     * @param updateFieldValue The value of the field to update
     * */
    public static <T> void updateFieldInDataBase(String collectionName, String fieldName, T fieldValue, String updateFieldName, T updateFieldValue) throws DBException {
        try{
            Document filter = new Document(fieldName, fieldValue);
            Document update = new Document("$set", new Document(updateFieldName, updateFieldValue));
            MongoCollection<Document> collection = Hotel.hotelDatabase.getCollection(collectionName);
            collection.updateOne(filter, update);
            System.out.println("Document updated successfully");
        }catch (Exception e){
            e.printStackTrace();
            throw new DBException("Error occurred while updating the document");
        }
    }

    public static class DBException extends Exception {
        DBException(String msg){
            super(msg);
        }
    }
}