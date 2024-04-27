package controllers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import model.Database;
import model.Guest;
import model.User;
import model.hotel.Hotel;
import model.hotel.Room;
import model.hotel.RoomType;
import model.supervisors.Receptionist;
import model.supervisors.Role;
import model.supervisors.Worker;
import org.bson.Document;
import view.UserGui.GuestUi;
import view.components.roomComponents.RoomOnList;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Its purpose is to keep the UI in sync with the data, and fire the appropriate events when an interaction happens.
 * */
public class Controller {
    protected static User user ;

    /**
     * This method is used to set the user data in the Model and open the appropriate UI
     * @param user The user to be set
     * */
    public static void  setHotelUserAndOpenUI(User user){
        Hotel.setUser(user);
        if (user instanceof Guest){
            Hotel.initHotelModel(UserType.GUEST);
            GuestUi.run(new GuestUi((Guest) user));
        }
        if (user instanceof Worker){
            if (((Worker) user).getRole().equals(Role.RECEPTIONIST)){
                Hotel.initHotelModel(UserType.RECEPTIONIST);
                //new ReceptionistUi();
            }
            if (((Worker) user).getRole().equals(Role.HOTEL_MANAGER)){
                Hotel.initHotelModel(UserType.MANAGER);
                //new ManagerUi();
            }
        }
    }
    public static void addGuestFromInputs(String firstName, String lastName, String email, String password) {
        Guest guest = new Guest(firstName, lastName, email, password);
        Receptionist.addGuestToDataBase(guest);
        setUser(guest);
    }

    //TODO: to be checked
    /**
     * Allows searching for a user by various keys (FOR MANAGER AND RECEPTIONIST)
     * @param collectionName
     * @param researchBy
     * @param matchingField
     * @return
     */
    public static User getUserFromModel(String collectionName, String researchBy, String matchingField) {

        switch (collectionName) {
            case "Guests" -> {System.out.println("email");
                if (researchBy.equals("email"))
                    if (!Hotel.getGuests().isEmpty() && Hotel.getGuests().containsKey(matchingField)) {
                        return Hotel.getGuests().get(matchingField);
                    }else if (Hotel.getGuests().isEmpty()) {
                       Document guestDoc=Database.findInDataBase(collectionName, researchBy, matchingField);
                        System.out.println("guest doc: "+guestDoc);
                          if (guestDoc!=null){
                              System.out.println("Entered");
                              return new Guest(guestDoc.getString("firstName"),
                                      guestDoc.getString("lastName"),
                                      guestDoc.getString("email"),
                                      guestDoc.getString("password"));
                          }
                    }
            }
            case "Receptionists" -> {
                if(researchBy.equals("email"))
                    if (Hotel.getWorkers().containsKey(matchingField)) {
                        return Hotel.getWorkers().get(matchingField);
                    }
                if (researchBy.equals("OasisMail"))
                        for (Worker receptionist : Hotel.getWorkers().values()) {
                            if (receptionist.getOasisMail().equals(matchingField)) {
                                return receptionist;
                            }
                        }
            }
        }
        return null;
    }

    /**
     * Get all documents from the database without the password field, and return them to views as a 2D array
     * @param collectionName The name of the collection
     * @param columnNames The names of documents to retrieve
     * */
    public static String[][] databaseToViewWithoutPassword(String collectionName, String[] columnNames) {
        String[][] data = null;
        try{

            MongoCollection<Document> collection = Hotel.hotelDatabase.getCollection(collectionName);

            // Retrieving all documents in the collection
            try (MongoCursor<Document> cursor = collection.find().iterator()) {
                List<String[]> dataList = new ArrayList<>();
                while (cursor.hasNext()) {
                    Document document = cursor.next();

                    // Remove the "password" field
                    document.remove("password");

                    String[] row = new String[columnNames.length];
                    for (int i = 0; i < columnNames.length; i++) {
                        row[i] = document.getString(columnNames[i]);
                    }
                    dataList.add(row);
                }
                data = dataList.toArray(new String[dataList.size()][]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * This method is used to convert the rooms in the hotel to a format that can be used in the UI in the GuestUi class
     * @return HashMap<String,RoomOnList> roomsUiList
     * */
    public static HashMap<String, RoomOnList> roomsToRoomPanelGuest(){
        HashMap<String, Room> allRooms = Hotel.getRooms();
        HashMap<String,RoomOnList> roomsUiList = new HashMap<>();

        String description="";
        int counter;

        for (RoomType roomType : RoomType.values()) {
            description = switch (roomType) {
                case Standard -> "-Single Room with a single bed.";
                case Double -> "-Double Room with a double bed.";
                case Suite -> "-Suite Room with a double bed and a living room.";
                case Family -> "-Family Room with a double bed and two single beds.";
            };

            HashMap<Double, Integer> roomPriceCount = new HashMap<>();
            HashSet<Double> prices = new HashSet<>();
            for (Room room : allRooms.values()) {
                if (room.getRoomType().equals(roomType) && !prices.contains(room.getRoomPrice())) {
                    prices.add(room.getRoomPrice());
                }
            }
            counter = 0;
            for (Double price : prices) {
                for (Room room : allRooms.values()) {
                    if (room.getRoomType().equals(roomType) && room.getRoomPrice() == price) {
                        counter++;
                    }
                }
                roomPriceCount.put(price, counter);
            }
            for (Double price : roomPriceCount.keySet()) {
                RoomOnList roomOnList = new RoomOnList(roomType,
                        "hotelproject/src/main/java/view/icons/"+ roomType.toString() + "Room.png",
                        description,
                        price,
                        roomPriceCount.get(price)>0);
                roomsUiList.put(roomType.toString() + price, roomOnList);
            }
        }
        return roomsUiList;
    }

    public static void setUser(User user) {
        Controller.user = user;
    }

    public static User getUser() {
        return user;
    }
}
