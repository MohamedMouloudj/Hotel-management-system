package model;

public class Room {

    private static int idCounter = 0;
    private final String roomNumber;
    private RoomType roomType;
    private boolean isAvailable;

    public Room(RoomType roomType, boolean isAvailable) {
        idCounter++;
        this.roomNumber = roomType.toString() + idCounter;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public double getRoomPrice() {
        return roomType.getPrice();
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
