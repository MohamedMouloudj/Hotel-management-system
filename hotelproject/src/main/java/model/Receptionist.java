package model;

public class Receptionist extends User {
    private static int idCounter = 0;
    private final char type = 'R';
    private Role role;

    public Receptionist(String firstName, String lastName, String password, String email, int phone) {
        super(firstName, lastName, password, email, phone);
        setId(++idCounter, type);

    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}