package model.hotel;
import controllers.OurDate;
import model.Guest;
import org.bson.Document;

import java.util.Iterator;

public class Reservation {
    private final double CUTOFF_PERCENTAGE = 0.2;
    private final String reservationId;
    private double cutOffDays;
    private int phoneNumber;
    private int creditCardNumber;
    private String roomNumber;
    private final String guestEmail;
    private OurDate checkInDate;
    private OurDate checkOutDate;
    private int adults=0;
    private int children=0;
    private double totalCost;
    private boolean isPaid;
    private boolean isConfirmed;
    private boolean isReservation;

    public Reservation(String roomNumber, String guestEmail, OurDate checkInDate, OurDate checkOutDate,
                       int adults, int children, int phoneNumber, int creditCardNumber,double totalCost) {
        this.roomNumber = roomNumber;
        this.guestEmail = guestEmail;
        this.checkInDate = checkInDate;
//        calculateCutoffDays();
        this.checkOutDate = checkOutDate;
        this.adults = adults;
        this.children = children;
        this.phoneNumber = phoneNumber;
        this.creditCardNumber = creditCardNumber;
        this.reservationId = roomNumber + guestEmail + checkInDate.toString();
        this.totalCost = totalCost;
        this.isPaid = false;
        this.isConfirmed = false;
        this.isReservation = false;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getGuestEmail() {
        return guestEmail;
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
//        calculateCutoffDays();

    }
    public void setCheckOutDate(OurDate checkOutDate) {
        this.checkOutDate = checkOutDate;
//        calculateCutoffDays();
    }

    public boolean isReservation() {
        return isReservation;
    }

    public void setReservation(boolean reservation) {
        isReservation = reservation;
    }

    public String getReservationId() {
        return reservationId;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

//    private void calculateCutoffDays(){
//        OurDate today = new OurDate();
//        try {
//            cutOffDays = OurDate.getDaysBetweenDates(today, checkInDate) * CUTOFF_PERCENTAGE;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    /**
     * Will return the total cost of all non paid reservations
     *
     * @param guest Guest
     */
    public static double calculateAllTotalCosts(Guest guest) {

        double totalCost = 0;
        Iterator<String> keys = guest.getReservations().keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            if (!guest.getReservations().get(key).isPaid()) {
                totalCost +=
                        guest.getReservations().get(key).getTotalCost();
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
            String guestEmail = document.getString("guestEmail");
            OurDate checkInDate = OurDate.parse(document.getString("checkInDate"));
            OurDate checkOutDate = OurDate.parse(document.getString("checkOutDate"));
            int adults = document.getInteger("adults");
            int children = document.getInteger("children");
            int phoneNumber = document.getInteger("phoneNumber");
            int creditCardNumber = document.getInteger("creditCardNumber");
            double totalCost = document.getDouble("totalCost");
            boolean isPaid = document.getBoolean("isPaid");
            boolean isConfirmed = document.getBoolean("isConfirmed");
            boolean isReservation = document.getBoolean("isReservation");
            Reservation reservation = new Reservation(roomNumber, guestEmail, checkInDate, checkOutDate, adults, children, phoneNumber, creditCardNumber, totalCost);
            reservation.setPaid(isPaid);
            reservation.setConfirmed(isConfirmed);
            reservation.setReservation(isReservation);
            return reservation;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
