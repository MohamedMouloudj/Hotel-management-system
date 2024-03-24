package model;

// import controllers.KeyNotFoundException;
import controllers.OurDate;
import controllers.Reservation;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Guest extends User {

    // private static int idCounter = 0;
    // private final char type = 'G';

    private HashMap<String, Reservation> reservations = new HashMap<>();

    public Guest(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, password, email);
        // setId(++idCounter, type);
    }

    public void requestReservation(RoomType type, OurDate checkInDate, OurDate checkOutDate) {
        // Hotel.addReservation(this, type, checkInDate, checkOutDate);
    }

    public void confirmReservation(String roomNumber) {
        // Hotel.confirmReservation(roomNumber);
    }

    public void cancelReservation(String roomNumber) {
        // Hotel.cancelReservation(roomNumber);
    }

    public boolean isValid() {
        return !super.firstName.isEmpty() && !super.lastName.isEmpty() && !super.email.isEmpty()
                && !super.password.isEmpty();
    }

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void inser() {
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            // Accessing the User database
            MongoDatabase userDatabase = mongoClient.getDatabase("User");
            System.out.println("Database Name = " + userDatabase.getName());

            // Retrieving the Client collection
            MongoCollection<Document> clientCollection = userDatabase.getCollection("Client");
            System.out.println("Client Collection selected successfully");

            // Creating a document for the new guest
            Document guestDocument = new Document("firstName", this.firstName)
                    .append("lastName", this.lastName)
                    .append("email", this.email)
                    .append("password", this.password);

            // Inserting the guest document into the collection
            clientCollection.insertOne(guestDocument);
            System.out.println("Document inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Document researchByEmail(String email) {
        Document existingGuest = null; // Initialize to null outside try block
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            // Accessing the User database
            MongoDatabase userDatabase = mongoClient.getDatabase("User");
            System.out.println("Database Name = " + userDatabase.getName());

            // Retrieving the Client collection
            MongoCollection<Document> clientCollection = userDatabase.getCollection("Client");
            System.out.println("Client Collection selected successfully");

            // Check if the email already exists in the collection
            existingGuest = clientCollection.find(new Document("email", email)).first();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existingGuest; // Return outside finally block
    }

    // public static

    /**
     *
     * This method is used to modify a reservation of a guest by changing the room
     * type, it will cancel the current reservation and create a new one with the
     * new room type
     * 
     * @param roomNumber String
     *
     */
    // public void modifyReservation(String roomNumber, RoomType type) throws
    // KeyNotFoundException {
    // Reservation toBeModified = reservations.get(roomNumber);
    // if (toBeModified == null) {
    // throw new KeyNotFoundException("Room number: " + roomNumber + " not found");
    // }

    // ArrayList<Room> rooms = Hotel.getRoomByType(type);
    // Room room = null;
    // for (Room r : rooms) {
    // if (r.isAvailable()) {
    // room = r;
    // break;
    // }
    // }
    // if (room == null) {
    // System.out.println("No available rooms");
    // return;
    // }
    // OurDate checkInDate = toBeModified.getCheckInDate();
    // OurDate checkOutDate = toBeModified.getCheckOutDate();
    // reservations.remove(roomNumber);
    // Hotel.cancelReservation(roomNumber);
    // room.setAvailable(false);
    // Reservation reservation = new Reservation(room.getRoomNumber(), this.getId(),
    // checkInDate, checkOutDate);
    // reservations.put(room.getRoomNumber(), reservation);
    // Hotel.addReservation(this);

    public void modifyCheckIn(String roomNumber, OurDate checkIn) {
        reservations.get(roomNumber).setCheckInDate(checkIn);
    }

    public void modifyCheckOut(String roomNumber, OurDate checkOut) {
        reservations.get(roomNumber).setCheckOutDate(checkOut);
    }

    public HashMap<String, Reservation> getReservations() {
        return reservations;
    }

    public void payReservation(String roomNumber) {
        reservations.get(roomNumber).setPaid(true);
    }

    public void addReservation(Reservation reservation) {
        reservations.put(reservation.getRoomNumber(), reservation);
    }

    // public void removeReservation(String roomNumber) throws KeyNotFoundException
    // {
    // if (!reservations.get(roomNumber).isPaid()) {
    // System.out.println("You can't remove a paid reservation");
    // return;
    // }
    // if (reservations.get(roomNumber) == null) {
    // throw new KeyNotFoundException("Room number: " + roomNumber + " not found");
    // }
    // reservations.remove(roomNumber);
    // }

    /**
     * This method is used to calculate the total cost of the reservations of a
     * guest, if a room number is provided it will return the cost of that room
     * only, otherwise it will return the total cost of all non paid reservations
     * 
     * @param roomNumber String
     */
    public double calculateTotalCost(String roomNumber) {
        // if (roomNumber != null) {
        // return
        // Hotel.getRoom(reservations.get(roomNumber).getRoomNumber()).getRoomPrice();
        // }
        // double totalCost = 0;
        // Iterator<String> keys = reservations.keySet().iterator();
        // while (keys.hasNext()) {
        // String key = keys.next();
        // if (!reservations.get(key).isPaid()) {
        // totalCost +=
        // Hotel.getRoom(reservations.get(key).getRoomNumber()).getRoomPrice();
        // }
        // }
        // return totalCost;
        return 19d;
    }
}
