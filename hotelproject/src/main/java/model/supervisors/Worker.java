package model.supervisors;

import model.User;

public abstract class Worker extends User {

    protected Role role;
    private final String domain = "@Oasis.dz";
    private String oasisMail;
    public Worker(String firstName, String lastName, String password, String email) {
        super(firstName, lastName, password, email);
        this.oasisMail = firstName + lastName + domain;
    }

    public String getOasisMail() {
        return oasisMail;
    }
    public void setOasisMail(String oasisMail) {
        this.oasisMail = oasisMail;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Role getRole() {
        return role;
    }

}