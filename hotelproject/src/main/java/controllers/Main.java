package controllers;

import model.*;
import view.components.roomComponents.RoomOnList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.login.loginMain.LogInForm;
import model.RoomType;

public class Main {
        public static Hotel hotel;

        public static void main(String[] args) {
                Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
                mongoLogger.setLevel(Level.SEVERE);
                // LogInForm.runForm();
                // TODO: After login result, decide which UI to show and which what type of

                // TODO: rename the class and the pacakge in the package view.userUi and remove
                // them
                // TODO: do what ever u want
                // TODO: and dont modify them use touch any thing only the name of the classes
                // and pakages..
                // TODO: the modify the name of the login package
                // TODO: the implementtion of forgot password
                // TODO: if u want to use UiConstatnts interface complete it
                // TODO:make the code as simple as u can and CLEAAAAAAN
                // TODO: add delete update room try to use methodes that are fimmiliar to the
                // methodes that are in User class(in mdel)
                // TODO: in the class User their is the mothodesof adding deleting from the data
                // base if u want to remove this methodes to another class u r free but dont
                // modify them ,modify only the localhost thing (Mouloudj u know )
                // TODO: try to find a solution the the problem of colors and scroll bare while
                // sign in (Mouloudj u know)
                // TODO: try to fing a solution the problem while refrech the all user ,i mean
                // when we add a guest or receptionist the header of the table will be corrupted
                // (Mouloudj u know)
                // TODO: the profileUi we will not use it only if we had time
                // TODO: the methode roomsToRoomPanelGuest remove it from the Main
                // TODO: rename the packag of login in View

                // TODO : delete the things (methodes classes attributes imports icons ...) that
                // we dont use (no yellow lines please)

                // TODO: be carfuuuuuuuuuuu not using date
                // TODO: RAHIM AND MOULOUDJ ->{ROOMS AND RESERVATIONS AND REQUESTS } in short
                // the rooms job

                // connected users to be passed to Hotel instance
                hotel = new Hotel(1);// example
                Manager.addRoom(RoomType.Standard, true, 9000);
                Manager.addRoom(RoomType.Double, true, 12000);
                Manager.addRoom(RoomType.Suite, false, 18000);
                Manager.addRoom(RoomType.Standard, true, 25000);
                // new GuestUi(null);
                LogInForm.runForm();

        }

        /**
         * This method is used to convert the rooms in the hotel to a format that can be
         * used in the UI in the GuestUi class
         * 
         * @return HashMap<String,RoomOnList> roomsUiList
         */
        public static HashMap<String, RoomOnList> roomsToRoomPanelGuest() {
                HashMap<String, Room> allRooms = hotel.getRooms();
                HashMap<String, RoomOnList> roomsUiList = new HashMap<>();

                String description = "";
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
                                                "hotelproject/src/main/java/view/icons/" + roomType.toString()
                                                                + "Room.jpg",
                                                description,
                                                price,
                                                roomPriceCount.get(price) > 0);
                                roomsUiList.put(roomType.toString() + price, roomOnList);
                        }
                }
                return roomsUiList;
        }
}