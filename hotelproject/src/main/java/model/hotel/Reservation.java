package model.hotel;
import controllers.OurDate;
import model.Guest;
import org.bson.Document;

import java.util.Iterator;

public class Reservation {
    private final double CUTOFF_PERCENTAGE = 0.2;
    private double cutOffDays;
    private String roomNumber;
    private final String guestId;
    private OurDate checkInDate;
    private OurDate checkOutDate;
    private boolean isPaid;
    private boolean isConfirmed;

    public Reservation(String roomNumber, String guestId, OurDate checkInDate, OurDate checkOutDate) {
        this.roomNumber = roomNumber;
        this.guestId = guestId;
        this.checkInDate = checkInDate;
        calculateCutoffDays();
        this.checkOutDate = checkOutDate;
        this.isPaid = false;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getGuestId() {
        return guestId;
    }

    public OurDate getCheckInDate() {
        return checkInDate;
    }

    public OurDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setCheckInDate(OurDate checkInDate) {
        this.checkInDate = checkInDate;
        calculateCutoffDays();

    }
    public void setCheckOutDate(OurDate checkOutDate) {
        this.checkOutDate = checkOutDate;
        calculateCutoffDays();
    }

    private void calculateCutoffDays(){
        OurDate today = new OurDate();
        if (today.getMonth() == checkInDate.getMonth() && today.getYear() == checkInDate.getYear()) {
            this.cutOffDays = Math.floor((double) (checkInDate.getDay() - today.getDay()) * CUTOFF_PERCENTAGE);
        } else {
            // TODO : use designed methode to calculate days number between two dates
        }
    }

    /**
     * @return the number of days (20%) before the check in date that the guest can
     * cancel the reservation or must confirm the reservation
     */
    public double getCutOffDays() {
        return cutOffDays;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    /**
     * This method is used to calculate the total cost of the reservations of a
     * guest, if a room number is provided it will return the cost of that room
     * only, otherwise it will return the total cost of all non paid reservations
     *
     * @param roomNumber String
     */
    public double calculateTotalCost(Guest guest, String roomNumber) {
        if (roomNumber != null) {
            return Hotel.getRooms().get(roomNumber).getRoomPrice();
        }
        double totalCost = 0;
        Iterator<String> keys = guest.getReservations().keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            if (!guest.getReservations().get(key).isPaid()) {
                totalCost +=
                        Hotel.getRooms().get(key).getRoomPrice();
            }
        }
        return totalCost;
    }

    /**
     * Coverts a Document object to a Reservation object
     * @param document Document
     *                 The document to be converted
     * @return The Reservation object or null if the conversion fails
     * */
    public static Reservation fromDocument(Document document) {
        try{
            String roomNumber = document.getString("roomNumber");
            String guestId = document.getString("guestId");
            OurDate checkInDate = OurDate.parse(document.getString("checkInDate")); // Assuming OurDate has a parse method
            OurDate checkOutDate = OurDate.parse(document.getString("checkOutDate")); // Assuming OurDate has a parse method
            boolean isPaid = document.getBoolean("isPaid");
            boolean isConfirmed = document.getBoolean("isConfirmed");
            Reservation reservation = new Reservation(roomNumber, guestId, checkInDate, checkOutDate);
            reservation.setPaid(isPaid);
            reservation.setConfirmed(isConfirmed);
            return reservation;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
