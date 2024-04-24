package model;

import java.util.HashMap;

public class Receptionist extends User {
    private final String dist = "@Oasis.dz";
    private String oasisMail;

    public String getOasisMail() {
        return oasisMail;
    }

    public void setOasisMail(String oasisMail) {
        this.oasisMail = oasisMail;
    }

    public Receptionist(String firstName, String lastName, String email) {
        super(firstName, lastName, email, firstName + "123");
        this.oasisMail = firstName + lastName + dist;
    }

    public void addReceptionistToDataBase() {
        HashMap<String, String> map = new HashMap<>();
        map.put("firstName", this.firstName);
        map.put("lastName", this.lastName);
        map.put("email", this.email);
        map.put("password", this.password);
        map.put("OasisMail", this.oasisMail);
        super.addToDataBase("Receptionist", map);
    }

}