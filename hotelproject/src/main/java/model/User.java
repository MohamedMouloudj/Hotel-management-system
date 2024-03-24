package model;

import org.bson.Document;

import controllers.Hotel;

public abstract class User {
    // protected String id;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String email;

    public User(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    // public void setId(int idCounter, char type) {
    // this.id = Hotel.generateId(idCounter, type);
    // }

    // public String getId() {
    // return id;
    // }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public abstract void inser();

}
