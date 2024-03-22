package model;
enum Role {
    Hotel_manager,RECEPTIONIST, OtherEmployee
}
public class Employee extends User{
    Role role;

    public void setRole(Role role) {
        this.role = role;
    }
    public Role getRole() {
        return role;
    }
}