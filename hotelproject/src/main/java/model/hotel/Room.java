package model.hotel;

public class Room {

    public static int idCounter = 0;
    private String roomNumber;
    private RoomType roomType;
    private double price;
    private boolean isAvailable;

    public Room(RoomType roomType, boolean isAvailable, double price) {
        idCounter++;
        this.roomNumber = roomType.toString() + idCounter;
        this.roomType = roomType;
        this.price=price;
        this.isAvailable = isAvailable;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public double getRoomPrice() {
        return price;
    }

    public boolean availability() {
        return isAvailable;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    /**
     * Called when retrieving from DB
     * */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
        idCounter--;
    }
}
