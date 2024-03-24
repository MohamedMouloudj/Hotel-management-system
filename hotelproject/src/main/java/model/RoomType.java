package model;

public enum RoomType {
    Standard(9000), Double(13000), Family(18000), Suite(25000);

    private final double price;

    RoomType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

}
