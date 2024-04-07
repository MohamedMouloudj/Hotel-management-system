package controllers;

import model.*;
import view.components.roomComponents.RoomOnList;
import view.guestUi.GuestUi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
        public static Hotel hotel;
        public static GuestUi guestUi;
        public static void main(String[] args) {
            Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
            mongoLogger.setLevel(Level.SEVERE);
            //LogInForm.runForm();
            // TODO: After login result, decide which UI to show and which what type of connected users to be passed to Hotel instance
            hotel=new Hotel(1);//example
            Manager.addRoom(RoomType.Standard,true, 9000);
            Manager.addRoom(RoomType.Double,true, 12000);
            Manager.addRoom(RoomType.Suite,false, 18000);
            Manager.addRoom(RoomType.Standard,true, 25000);
            guestUi=new GuestUi(roomsToRoomPanelGuest());

        }


        /**
         * This method is used to convert the rooms in the hotel to a format that can be used in the UI in the GuestUi class
         * @return HashMap<String,RoomOnList> roomsUiList
         * */
        public static HashMap<String,RoomOnList> roomsToRoomPanelGuest(){
                HashMap<String, Room> allRooms = hotel.getRooms();
                HashMap<String,RoomOnList> roomsUiList = new HashMap<>();

                String description="";
                int counter;

                for (RoomType roomType : RoomType.values()) {
                    description = switch (roomType) {
                        case RoomType.Standard -> "-Single Room with a single bed.";
                        case RoomType.Double -> "-Double Room with a double bed.";
                        case RoomType.Suite -> "-Suite Room with a double bed and a living room.";
                        case RoomType.Family -> "-Family Room with a double bed and two single beds.";
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
                                "hotelproject/src/main/java/view/icons/"+ roomType.toString() + "Room.jpg",
                                description,
                                price,
                                roomPriceCount.get(price)>0);
                        roomsUiList.put(roomType.toString() + price, roomOnList);
                    }
                }
                return roomsUiList;
        }
}
