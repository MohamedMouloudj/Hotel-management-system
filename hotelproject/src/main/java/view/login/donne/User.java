package view.login.donne;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Getters and setters for each attribute
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return !firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !password.isEmpty();
    }

    public void inser() {
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            // Accessing the order database
            MongoDatabase orderDatabase = mongoClient.getDatabase("User");
            System.out.println("Database Name = " + orderDatabase.getName());

            // Retrieving a product collection
            MongoCollection<Document> productCollection = orderDatabase.getCollection("Client");
            System.out.println("product Collection selected successfully");

            // Creating a iphone document
            Document iphoneDocument = new Document("firstName", this.firstName)
                    .append("lastName", this.lastName)
                    .append("email", this.email)
                    .append("password", this.password);

            // Inserting iphone document into the collection
            productCollection.insertOne(iphoneDocument);
            System.out.println("Document inserted successfully");
        } catch (Exception exe) {
            exe.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
