package model.supervisors;

import controllers.PasswordHashing;
import model.User;

public abstract class Worker extends User {
    protected Role role;
    private String oasisMail;
    public Worker(String firstName, String lastName, String email,String password ) {
        super(firstName, lastName, email, password);
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

    @Override
    public String toString() {
        return "Worker{" +
                "role=" + role +
                ", oasisMail='" + oasisMail + '\'' +
                super.toString() +
                '}';
    }
}