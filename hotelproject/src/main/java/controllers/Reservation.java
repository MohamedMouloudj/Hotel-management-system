package controllers;

public class Reservation {
    private String roomNumber;
    private final String guestId;
    private OurDate checkInDate;
    private OurDate checkOutDate;
    private double cutOffDays;
    private boolean isPaid;

    public Reservation(String roomNumber, String guestId, OurDate checkInDate, OurDate checkOutDate) {
        this.roomNumber = roomNumber;
        this.guestId = guestId;
        this.checkInDate = checkInDate;
        OurDate today = new OurDate();
        if(today.getMonth()==checkInDate.getMonth() && today.getYear()==checkInDate.getYear()){
            this.cutOffDays= Math.floor((double)(checkInDate.getDay()-today.getDay())*0.2);
        }
        else{
            //TODO : use designed methode to calculate days number between two dates
        }
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
        OurDate today = new OurDate();
        if(today.getMonth()==checkInDate.getMonth() && today.getYear()==checkInDate.getYear()){
            this.cutOffDays= Math.floor((double)(checkInDate.getDay()-today.getDay())*0.2);
        }
        else{
            //TODO : use designed methode to calculate days number between two dates
        }
    }

    public void setCheckOutDate(OurDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    /*
   *
   * @return the number of days (20%) before the check in date that the guest can cancel the reservation or must confirm the reservation
   * */
    public double getCutOffDays() {
        return cutOffDays;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
