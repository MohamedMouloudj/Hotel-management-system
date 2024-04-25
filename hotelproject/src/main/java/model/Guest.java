package model;

import controllers.OurDate;
import model.hotel.Reservation;
import model.hotel.RoomType;

import java.util.HashMap;


public class Guest extends User {
    // key: roomNumber, value: reservation
    private HashMap<String, Reservation> reservations = new HashMap<>();

    public Guest(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
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

    public void modifyCheckIn(String roomNumber, OurDate checkIn) {
        reservations.get(roomNumber).setCheckInDate(checkIn);
    }

    public void modifyCheckOut(String roomNumber, OurDate checkOut) {
        reservations.get(roomNumber).setCheckOutDate(checkOut);
    }

    public HashMap<String, Reservation> getReservations() {
        return reservations;
    }
    public void setReservations(HashMap<String, Reservation> reservations) {
        this.reservations = reservations;
    }

    public void payReservation(String roomNumber) {
        reservations.get(roomNumber).setPaid(true);
    }

    public void addReservation(Reservation reservation) {
        reservations.put(reservation.getRoomNumber(), reservation);
    }
}
