package controllers;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
// import org.bson.conversions.Bson;

import model.Receptionist;

public class Manager {

    private static final MongoClient mongoClient = new MongoClient("localhost",
            27017);
    private static final MongoDatabase userDatabase = mongoClient.getDatabase("User");

    public static void addEmployee(Receptionist receptionist) {
        MongoClient mongoClient = null;
        try {

            // Accessing the User database
            System.out.println("Database Name = " + userDatabase.getName());

            // Retrieving the Receptionists collection
            MongoCollection<Document> collection = userDatabase.getCollection("Receptionists");

            // Create a document for the receptionist
            Document receptionistDocument = new Document("id", receptionist.getId())
                    .append("firstName", receptionist.getFirstName())
                    .append("lastName", receptionist.getLastName())
                    .append("email", receptionist.getEmail())
                    .append("phone-number", receptionist.getPhone())
                    .append("password", receptionist.getPassword())
                    .append("Role", receptionist.getRole());

            // Insert the receptionist document into the collection
            collection.insertOne(receptionistDocument);
            System.out.println("Receptionist inserted successfully");
        } catch (Exception e) {
            System.err.println("Error occurred while adding receptionist:");
            e.printStackTrace();
        } finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
    }

    public static void removeEmployee(String firstName, String lastName) {
        try {
            // Creating the filter to match the receptionist by first and last name
            Document filter = new Document("firstName", firstName).append("lastName",
                    lastName);
            MongoCollection<Document> collection = userDatabase.getCollection("Receptionists");

            // Deleting the receptionist document
            collection.deleteOne(filter);

            System.out.println("Receptionist removed successfully");
        } catch (Exception e) {
            System.err.println("Error occurred while removing receptionist:");
            e.printStackTrace();
        } finally {
            // Ensure to close resources
            mongoClient.close();
        }
    }
}
