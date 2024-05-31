package model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import controllers.OurDate;
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
            for (Document roomDocument : roomCollection.find()) {
                Room room = new Room(RoomType.valueOf(roomDocument.getString("roomType")),
                        roomDocument.getBoolean("isAvailable", false),
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
                Document reservationsDocument = (Document) guestDocument.get("Reservations");
                HashMap<String, Reservation> reservations = new HashMap<>();
                for (Map.Entry<String, Object> entry : reservationsDocument.entrySet()) {
                    Document reservationDocument = (Document) entry.getValue();
                    reservations.put(entry.getKey().replaceAll("-","."), Reservation.fromDocument(reservationDocument));
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
                    receptionist.setPassword(workerDocument.getString("password"));
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
                if (key.equals("Reservations")){
                    document.append(key, Document.parse(value));
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

    public static void dropCollection(String collectionName) {
        try {
            Hotel.hotelDatabase.getCollection(collectionName).drop();
            System.out.println("Collection dropped successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addReservationToUser(String email, Reservation reservation) {
        try {
            // Get the user collection
            MongoCollection<Document> userCollection = Hotel.hotelDatabase.getCollection("Guests");
            // Build the query to find the user
            Document query = new Document("email", email);

            // Convert the Reservation object to a Document
            Document reservationDocument = new Document();
            reservationDocument.append("roomNumber", reservation.getRoomNumber());
            reservationDocument.append("guestEmail", reservation.getGuestEmail());
            reservationDocument.append("phoneNumber", reservation.getPhoneNumber());
            reservationDocument.append("checkInDate", reservation.getCheckInDate().getDay()+"/"+reservation.getCheckInDate().getMonth()+"/"+reservation.getCheckInDate().getYear());
            reservationDocument.append("checkOutDate", reservation.getCheckOutDate().getDay()+"/"+reservation.getCheckOutDate().getMonth()+"/"+reservation.getCheckOutDate().getYear());
            reservationDocument.append("adults", reservation.getAdults());
            reservationDocument.append("children", reservation.getChildren());
            reservationDocument.append("totalCost", reservation.getTotalCost());
            reservationDocument.append("isPaid", reservation.isPaid());
            reservationDocument.append("isConfirmed", reservation.isConfirmed());
            reservationDocument.append("isReservation", reservation.isReservation());

            String modifiedString = reservation.getGuestEmail().replace(".", "-");
            String reservationKey = reservation.getRoomNumber() + modifiedString + reservation.getCheckInDate().toString();
            Document update = new Document("$set", new Document("Reservations." + reservationKey, reservationDocument));

            // Update the user document
            userCollection.updateOne(query, update);
            System.out.println("Reservation added successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void removeReservationFromGuest(String email, String reservationKey) {
        try {
            // Get the user collection
            MongoCollection<Document> userCollection = Hotel.hotelDatabase.getCollection("Guests");
            String newKey=reservationKey.replace(".","-");
            // Build the query to find the user
            Document query = new Document("email", email);
            Document update = new Document("$unset", new Document("Reservations." + newKey, ""));

            // Update the user document
            userCollection.updateOne(query, update);
            System.out.println("Reservation removed successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class DBException extends Exception {
        DBException(String msg){
            super(msg);
        }
    }
}