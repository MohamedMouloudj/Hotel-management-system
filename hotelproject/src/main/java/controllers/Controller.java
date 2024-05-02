package controllers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import model.Database;
import model.Guest;
import model.User;
import model.hotel.Hotel;
import model.hotel.Room;
import model.hotel.RoomType;
import model.supervisors.*;
import org.bson.Document;
import view.UserGui.GuestUi;
import view.UserGui.ManagerGui;
import view.UserGui.ReceptionistGui;
import view.components.roomComponents.RoomOnList;
import view.components.table.Table;
import view.login.container.ForgetPassword;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Its purpose is to keep the UI in sync with the data, and fire the appropriate events when an interaction happens.
 * */
public class Controller {
    private static ManagerGui managerGui;
    private final static String MANAGER_EMAIL="admin";
    private final static String MANAGER_PASSWORD="admin12345#";
    public final static String DOMAIN_RECEPTIONIST = "@Oasis.dz";
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
                ReceptionistGui.run(new ReceptionistGui((Receptionist) user));
            }
        }
        if (user == null){
            Hotel.initHotelModel(UserType.MANAGER);
            managerGui=new ManagerGui(null,Hotel.getRooms().size(),Hotel.getWorkers().size(),Hotel.getGuests().size());
            ManagerGui.run(managerGui);
        }
    }

    public static void checkRegistration(String name, String lastName, String email, String password) throws AccessAppException {
        if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new AccessAppException("All the Fields Are Required");
        } else if (!isValidEmail(email)) {
            throw new AccessAppException("Invalid Email");
        } else if (getUserFromModel("Guests", "email", email) != null) {
            throw new AccessAppException("Email already exists");
        } else {
            addGuestFromInputs(name, lastName, email, password);
        }
    }
    public static void checkLogin(String email, String password, JPanel bg)throws AccessAppException{
        User loginUser;
        if (email.isEmpty() || password.isEmpty()) {
            throw new AccessAppException("All the Fields Are Required");
        } else if (email.equals(MANAGER_EMAIL) && password.equals(MANAGER_PASSWORD)) {
            setHotelUserAndOpenUI(null);
            SwingUtilities.getWindowAncestor(bg).dispose(); // Close the login form
            return;
        } else if (!isValidEmail(email)) {
            throw new AccessAppException("Invalid Email");
        } else {
            if (email.endsWith(DOMAIN_RECEPTIONIST)) { // Receptionist
                loginUser = getUserFromModel("Workers", "OasisMail", email);
            } else {
                loginUser = getUserFromModel("Guests", "email", email);
            }

            if (loginUser == null) {
                throw new AccessAppException("User not found");
            } else {
                String truePassword = loginUser.getPassword();
                if (!PasswordHashing.verifyPassword(password, truePassword)) {
                    throw new AccessAppException("Incorrect Password");
                } else {
                    setHotelUserAndOpenUI(loginUser);
                }
                SwingUtilities.getWindowAncestor(bg).dispose(); // Close the login form
            }
        }
    }
    public static void launchForgotPasswordUI(Component c){
        ForgetPassword forgetPasswordPanel = new ForgetPassword();
        JFrame parentContainer = (JFrame) SwingUtilities.getWindowAncestor(c);
        parentContainer.setContentPane(forgetPasswordPanel);
        parentContainer.revalidate();
        parentContainer.repaint();
    }
    private static Guest addGuestFromInputs(String firstName, String lastName, String email, String password) {
        Guest guest = new Guest(firstName, lastName, email, PasswordHashing.hashPassword(password));
        Receptionist.addGuestToDataBase(guest);
        return guest;
    }

    /**
     * Allows searching for a user by various keys (FOR Guest OR Worker)
     * @param collectionName The name of the collection
     * @param researchBy The field to search by
     * @param matchingField The value to match
     * @return User user or null
     */
    public static User getUserFromModel(String collectionName, String researchBy, String matchingField) {
        switch (collectionName) {
            case "Guests" -> {
                if (researchBy.equals("email"))
                    if (!Hotel.getGuests().isEmpty() && Hotel.getGuests().containsKey(matchingField)) {
                        return Hotel.getGuests().get(matchingField);
                    }else if (Hotel.getGuests().isEmpty()) {
                       Document guestDoc=Database.findInDataBase(collectionName, researchBy, matchingField);
                          if (guestDoc!=null){
                              return new Guest(guestDoc.getString("firstName"),
                                      guestDoc.getString("lastName"),
                                      guestDoc.getString("email"),
                                      guestDoc.getString("password"));
                          }
                    }
            }
            case "Workers" -> {
                if(researchBy.equals("OasisMail"))
                    if (!Hotel.getWorkers().isEmpty() && Hotel.getWorkers().containsKey(matchingField)) {
                        return Hotel.getWorkers().get(matchingField);
                    }else if (Hotel.getWorkers().isEmpty()) {
                        Document workerDoc=Database.findInDataBase(collectionName, researchBy, matchingField);
                        if(workerDoc!=null){
                            Worker worker;
                            if (workerDoc.getString("role").equals("RECEPTIONIST")){
                                worker=new Receptionist(workerDoc.getString("firstName"),
                                        workerDoc.getString("lastName"),
                                        workerDoc.getString("email"));
                                worker.setOasisMail(workerDoc.getString("OasisMail"));
                                worker.setPassword(workerDoc.getString("password"));
                            }else {
                                worker=new Others(workerDoc.getString("firstName"),
                                        workerDoc.getString("lastName"),
                                        workerDoc.getString("email"));
                                worker.setOasisMail(workerDoc.getString("OasisMail"));
                                worker.setPassword(workerDoc.getString("password"));
                            }
                            return worker;
                        }
                    }
                if (researchBy.equals("email")) {
                    if (!Hotel.getWorkers().isEmpty()) {
                        for (Worker receptionist : Hotel.getWorkers().values()) {
                            if (receptionist.getOasisMail().equals(matchingField)) {
                                return receptionist;
                            }
                        }
                    }else {
                        Document workerDoc=Database.findInDataBase(collectionName, researchBy, matchingField);
                        if(workerDoc!=null){
                            Worker worker;
                            if (workerDoc.getString("role").equals("RECEPTIONIST")){
                                worker=new Receptionist(workerDoc.getString("firstName"),
                                        workerDoc.getString("lastName"),
                                        workerDoc.getString("email"));
                                worker.setOasisMail(workerDoc.getString("OasisMail"));
                                worker.setPassword(workerDoc.getString("password"));
                            }else {
                                worker=new Others(workerDoc.getString("firstName"),
                                        workerDoc.getString("lastName"),
                                        workerDoc.getString("email"));
                                worker.setOasisMail(workerDoc.getString("OasisMail"));
                                worker.setPassword(workerDoc.getString("password"));
                            }
                            return worker;
                        }
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
    public static void initiateTable(String collectionName, String[] columnNames, Table table) {
        try{
            MongoCollection<Document> collection = Hotel.hotelDatabase.getCollection(collectionName);
            try (MongoCursor<Document> cursor = collection.find().iterator()) {
                while (cursor.hasNext()) {
                    Document document = cursor.next();

                    if (document.containsKey("password"))
                        document.remove("password");

                    Object[] row = new String[columnNames.length];
                    for (int i = 0; i < columnNames.length; i++) {
                        row[i] = document.getString(columnNames[i]);
                    }
                    table.addRow(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addGuest(String firstName, String lastName, String email,Table table) throws AccessAppException {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            throw new AccessAppException("All the Fields Are Required");
        } else if (!isValidEmail(email)) {
            throw new AccessAppException("Invalid Email");
        }
        Guest guest=(Guest) getUserFromModel("Guests","email",email);
        if (guest!=null) {
            throw new AccessAppException("Email already exists");
        }
        guest=addGuestFromInputs(firstName,lastName,email,firstName + lastName+ "123");
        Hotel.getGuests().put(email, guest);
        table.addRow(new Object[]{firstName,lastName,email});
        if (managerGui!=null && Hotel.getUser() instanceof Manager)
            managerGui.getWelcomePage().updateCard("Guests",Hotel.getGuests().size());
    }
    public static void addReceptionist(String firstName, String lastName, String email, String oasisMail,Table table) throws AccessAppException {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || oasisMail.isEmpty()) {
            throw new AccessAppException("All the Fields Are Required");
        } else if (!isValidEmail(email)) {
            throw new AccessAppException("Invalid Email");
        }
        //Remove all digits from the oasisMail to use it in next process and avoid mutation
        StringBuilder sb = new StringBuilder(oasisMail);
        for (int i = 0; i < sb.length(); i++) {
            if (Character.isDigit(sb.charAt(i))) {
                sb.deleteCharAt(i);
                i--;
            }
        }
        //Check if the email is already used by other user
        Worker receptionist=(Worker) getUserFromModel("Workers","OasisMail",sb.toString());
        if (receptionist!=null && (receptionist.getEmail().equals(email))){
            throw new AccessAppException("Email already exists!");
        }
        if (getUserFromModel("Guests","email",email)!=null){
            throw new AccessAppException("Email already exists!");
        }
        receptionist=new Receptionist(firstName,lastName,email);
        receptionist.setOasisMail(oasisMail);
        receptionist.setPassword(PasswordHashing.hashPassword(firstName+lastName+"123"));
        Manager.addWorkerToDataBase(receptionist);
        Hotel.getWorkers().put(oasisMail,receptionist);
        table.addRow(new Object[]{firstName,lastName,email,oasisMail});
        if (managerGui!=null && Hotel.getUser() instanceof Manager)
            managerGui.getWelcomePage().updateCard("Workers",Hotel.getWorkers().size());
    }

    public static void handleUpdates(String entity,String roomNumber, String email, String key, Object updatedValue) throws AccessAppException {

        switch (entity) {
            case "Guest" -> {
                try {
                    if(!Objects.equals(key, "Reservations")){
                        Database.updateFieldInDataBase("Guests", "email", email, key, (String) updatedValue);
                    }
                    if (Objects.equals(key, "Reservations")){
                        HashMap<String, Object> objectHashMap = new HashMap<>((HashMap<String, Object>) updatedValue);
                        Document tmpDocument = new Document(objectHashMap);
                        Database.updateFieldInDataBase("Guests", "email", email, key, tmpDocument.toJson());
                    }
                }catch (Exception e){
                    throw new AccessAppException(e.getMessage());
                }
            }
            case "Worker" -> {
                try {
                    if(Objects.equals(key, "email")){
                        if (!isValidEmail((String) updatedValue))
                            throw new AccessAppException("Invalid Email");
                        if(getUserFromModel("Workers", "OasisMail", (String) updatedValue) != null
                                || getUserFromModel("Workers", "OasisMail", (String) updatedValue) != null)
                            throw new AccessAppException("Email already exists");
                        Database.updateFieldInDataBase("Workers", "OasisMail", email, key, (String) updatedValue);
                    }
                    if(!Objects.equals(key, "Reservations")){
                        Database.updateFieldInDataBase("Workers", "OasisMail", email, key, (String) updatedValue);
                    }
                    if (Objects.equals(key, "Reservations")){
                        HashMap<String, Object> objectHashMap = new HashMap<>((HashMap<String, Object>) updatedValue);
                        Document tmpDocument = new Document(objectHashMap);
                        Database.updateFieldInDataBase("Workers", "OasisMail", email, key, tmpDocument.toJson());
                    }
                }catch (Exception e){
                    throw new AccessAppException(e.getMessage());
                }
            }
            // TODO case "Rooms" -> updateRoom(email, key, updatedValue);
            default -> throw new AccessAppException("Unexpected value: " + entity);
        }
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

        if (allRooms.isEmpty()) {
            return roomsUiList;
        }

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


    /**
     * Sets the active panel in the main content area.
     *
     * @param panel the panel to be displayed
     * @param mainContentPanel the main content panel that holds the shown panels
     */
    public static void setActivePanel(JPanel mainContentPanel,JComponent panel) {
        mainContentPanel.removeAll(); // Remove all existing components from the main content panel
        mainContentPanel.add(panel); // Add the new panel
        mainContentPanel.repaint(); // Repaint the main content panel
        mainContentPanel.revalidate(); // Revalidate the layout of the main content panel
    }

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);
    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static User getUser() {
        return Hotel.getUser();
    }
}

class AccessAppException extends Exception {
    public AccessAppException(String message) {
        super(message);
    }
}