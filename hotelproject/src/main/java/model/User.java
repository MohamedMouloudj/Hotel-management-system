package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.hotel.Hotel;
import org.bson.Document;

import com.mongodb.client.MongoCollection;

public abstract class User {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String email;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
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

    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void deleteByEmail(String collectionName, String email) {
        try{
            // Retrieving the specified collection
            MongoCollection<Document> collection = Hotel.hotelDatabase.getCollection(collectionName);

            // Deleting the document with the given email
            collection.deleteOne(new Document("email", email));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
