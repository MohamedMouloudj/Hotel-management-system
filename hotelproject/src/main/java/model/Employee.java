package model;

import model.Role;

public class Employee extends User {
    public Employee(String firstName, String lastName, String password, String email) {
        super(firstName, lastName, password, email);
    }

    private Role role;

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public void inser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inser'");
    }
}