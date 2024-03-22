package model;

import controllers.KeyNotFoundException;

public abstract class Management extends User{

    public Management(String firstName, String lastName, String password, String email,int phone) {
        super(firstName, lastName, password, email, phone);
    }
    public void addGuest(String firstName, String lastName, String password, String email, int phone){
        Guest guest = new Guest(firstName,lastName,password,email,phone);
        Hotel.guests.put(guest.getEmail(),guest);
    }
    public void removeGuest(String email){
        Hotel.guests.remove(email);
    }
    public void updateGuest(String email, String firstName, String lastName, String password, int phone)throws KeyNotFoundException {
        Guest guest = Hotel.guests.get(email);
        if (guest==null){
            throw new KeyNotFoundException("Guest with email: "+email+" not found");
        }
        guest.setFirstName(firstName);
        guest.setLastName(lastName);
        guest.setPassword(password);
        guest.setPhone(phone);
    }
}