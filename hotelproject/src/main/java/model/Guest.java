package model;

import controllers.KeyNotFoundException;
import controllers.OurDate;
import controllers.Reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Guest extends User{
    private static int idCounter = 0;
    private HashMap<String, Reservation> reservations = new HashMap<>();
    Guest(String firstName, String lastName, String password, String email, int phone) {
        super(firstName, lastName, password, email, phone);
        idCounter++;
        setId(idCounter, 'G','G');
    }
    public void requestReservation(RoomType type, OurDate checkInDate, OurDate checkOutDate){
        Hotel.addReservation(this,type,checkInDate,checkOutDate);
    }
    public void confirmReservation(String roomNumber){
        Hotel.confirmReservation(roomNumber);
    }
    public void cancelReservation(String roomNumber){
        Hotel.cancelReservation(roomNumber);
    }
    /**
     *
     * This method is used to modify a reservation of a guest by changing the room type, it will cancel the current reservation and create a new one with the new room type
     * @param roomNumber String
     *
     * */
    public void modifyReservation(String roomNumber,RoomType type) throws KeyNotFoundException {
        Reservation toBeModified = reservations.get(roomNumber);
        if (toBeModified==null){
            throw new KeyNotFoundException("Room number: "+roomNumber+" not found");
        }

        ArrayList<Room> rooms = Hotel.getRoomByType(type);
        Room room=null;
        for (Room r : rooms) {
            if(r.isAvailable()){
                room = r;
                break;
            }
        }
        if(room==null){
            System.out.println("No available rooms");
            return;
        }
        OurDate checkInDate = toBeModified.getCheckInDate();
        OurDate checkOutDate = toBeModified.getCheckOutDate();
        reservations.remove(roomNumber);
        Hotel.cancelReservation(roomNumber);
        room.setAvailable(false);
        Reservation reservation = new Reservation(room.getRoomNumber(),this.getId(),checkInDate,checkOutDate);
        reservations.put(room.getRoomNumber(),reservation);
        Hotel.addReservation(this);
    }
    public void modifyCheckIn(String roomNumber ,OurDate checkIn){
        reservations.get(roomNumber).setCheckInDate(checkIn);
    }
    public void modifyCheckOut(String roomNumber ,OurDate checkOut){
        reservations.get(roomNumber).setCheckOutDate(checkOut);
    }
    public HashMap<String,Reservation> getReservations(){
        return reservations;
    }
    public void payReservation(String roomNumber){
        reservations.get(roomNumber).setPaid(true);
    }
    public void addReservation(Reservation reservation){
        reservations.put(reservation.getRoomNumber(),reservation);
    }
    public void removeReservation(String roomNumber)throws KeyNotFoundException{
        if (!reservations.get(roomNumber).isPaid()){
            System.out.println("You can't remove a paid reservation");
            return;
        }
        if (reservations.get(roomNumber)==null){
            throw new KeyNotFoundException("Room number: "+roomNumber+" not found");
        }
        reservations.remove(roomNumber);
    }

    /**
     * This method is used to calculate the total cost of the reservations of a guest, if a room number is provided it will return the cost of that room only, otherwise it will return the total cost of all non paid reservations
     * @param roomNumber String
     * */
    public double calculateTotalCost(String roomNumber){
        if(roomNumber!=null){
            return Hotel.getRoom(reservations.get(roomNumber).getRoomNumber()).getRoomPrice();
        }
        double totalCost = 0;
        Iterator<String> keys = reservations.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            if(!reservations.get(key).isPaid()){
                totalCost+=Hotel.getRoom(reservations.get(key).getRoomNumber()).getRoomPrice();
            }
        }
        return totalCost;
    }
}
