package model;
import model.Role;
public class Employee extends User{
    Role role;
    public void setRole(Role role) {
        this.role = role;
    }
    public Role getRole() {
        return role;
    }
}