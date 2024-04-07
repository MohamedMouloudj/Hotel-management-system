package model;

import org.bson.Document;

public class Receptionist extends Manager {
    // private static int idCounter = 0;
    // private final char type = 'R';
    private Role role;

    public Receptionist(String firstName, String lastName, String password, String email) {
        super(firstName, lastName, password, email);
        // setId(++idCounter, type);
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public void insertToDB() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inser'");
    }

}