package model.supervisors;


import model.Database;
import model.hotel.Hotel;
import model.hotel.Room;
import model.hotel.RoomType;
import org.bson.Document;

import java.util.HashMap;


abstract public class Manager extends Worker {

    public Manager(String firstName, String lastName,String email ,String password ) {
        super(firstName, lastName,password,email);
    }

    ////////////////////////////Workers Management////////////////////////////
    public static void addWorkerToDataBase(Worker worker) {
        HashMap<String, String> workerDocument = new HashMap<>();
        workerDocument.put("firstName", worker.getFirstName());
        workerDocument.put("lastName", worker.getLastName());
        workerDocument.put("email", worker.getEmail());
        workerDocument.put("password", worker.getPassword());
        workerDocument.put("OasisMail", worker.getOasisMail());
        workerDocument.put("role", worker.role.toString());
//        if (worker.role.equals(Role.RECEPTIONIST)) {
//            HashMap<String, Object> objectHashMap = new HashMap<>(((Receptionist) worker).getReservations());
//            Document tmpDocument = new Document(objectHashMap);
//            workerDocument.put("Reservations",tmpDocument.toJson());
//        }
        Database.addToDataBase("Workers", workerDocument);
    }
    public static void removeWorkerFromDataBase(String OasisEmail) {
        try {
            Database.removeFromDataBase("Workers", "OasisMail", OasisEmail);
            Hotel.getWorkers().remove(OasisEmail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


//    public static void updateWorkerInDataBase(String OasisEmail, String key, Object updatedValue) {
//        try {
//            if (key.equals("OasisMail")) {
//                Database.updateFieldInDataBase("Workers", "OasisMail", OasisEmail, key, (String) updatedValue);
//            }
//            if (key.equals("firstName")) {
//                Database.updateFieldInDataBase("Workers", "OasisMail", OasisEmail, key, (String) updatedValue);
//            }
//            if (key.equals("lastName")) {
//                Database.updateFieldInDataBase("Workers", "OasisMail", OasisEmail, key, (String) updatedValue);
//            }
//            if (key.equals("password")) {
//                Database.updateFieldInDataBase("Workers", "OasisMail", OasisEmail, key, (String) updatedValue);
//            }
//            if (key.equals("role")) {
//                Database.updateFieldInDataBase("Workers", "OasisMail", OasisEmail, key, (String) updatedValue);
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

    ////////////////////////////Rooms Management////////////////////////////
    public static Room addRoom(RoomType roomType, boolean isAvailable, double price){
        Room room = new Room(roomType, isAvailable, price);
        Hotel.getRooms().put(room.getRoomNumber(), room);
        addRoomToDataBase(room);
        return room;
    }
    public static void removeRoomFromDataBase(String roomNumber){
        try{
            Database.removeFromDataBase("Rooms", "roomNumber", roomNumber);
            Hotel.getRooms().remove(roomNumber);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private static void addRoomToDataBase(Room room) {
        HashMap<String,String> roomDocument = new HashMap<>();
        roomDocument.put("roomNumber", room.getRoomNumber());
        roomDocument.put("roomType", room.getRoomType().toString());
        roomDocument.put("price", String.valueOf(room.getRoomPrice()));
        roomDocument.put("isAvailable", String.valueOf(room.availability()));
        Database.addToDataBase("Rooms", roomDocument);
    }

    ///////////////////////////////////
    public static void clearData(String collection,boolean isConfirmed){
        if (!isConfirmed){
            return;
        }
        try{
            switch (collection){
                case "Guests" -> Hotel.getGuests().clear();
                case "Workers" -> Hotel.getWorkers().clear();
                case "Rooms" -> Hotel.getRooms().clear();
            }
            Database.dropCollection(collection);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}