package model.supervisors;

/**
 * Just to make a good hierarchy of hotel employees
 * */
public class Others extends Worker{
    public Others(String firstName, String lastName, String email) {
        super(firstName, lastName, email, firstName + "123");
        this.role = Role.OtherEmployee;
    }
}
