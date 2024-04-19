package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import controllers.PasswordHashing;

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

    protected void addToDataBase(String collectionName, HashMap<String, String> fields) {
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            // Accessing the User database
            MongoDatabase userDatabase = mongoClient.getDatabase("Oasis-dataBase");
            System.out.println("Database Name = " + userDatabase.getName());

            // Retrieving the specified collection
            MongoCollection<Document> clientCollection = userDatabase.getCollection(collectionName);
            System.out.println(collectionName + " Collection selected successfully");

            // Creating a document for the new guest
            Document document = new Document();
            for (Map.Entry<String, String> entry : fields.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key.equals("password")) {
                    value = PasswordHashing.hashPassword(value);
                }
                document.append(key, value);
            }

            // Inserting the document into the collection
            clientCollection.insertOne(document);
            System.out.println("Document inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteByEmail(String collectionName, String email) {
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            // Accessing the User database
            MongoDatabase userDatabase = mongoClient.getDatabase("Oasis-dataBase");

            // Retrieving the specified collection
            MongoCollection<Document> collection = userDatabase.getCollection(collectionName);

            // Deleting the document with the given email
            collection.deleteOne(new Document("email", email));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Document research(String collectionName, String researchBy, String valueToSearch) {
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            MongoDatabase userDatabase = mongoClient.getDatabase("Oasis-dataBase");

            MongoCollection<Document> collection = userDatabase.getCollection(collectionName);

            // Build the query
            Document query = new Document(researchBy, valueToSearch);

            // Find the document matching the query
            return collection.find(query).first();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getDocumentCount(String collectionName) {
        long count = 0;
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            // Accessing the User database
            MongoDatabase userDatabase = mongoClient.getDatabase("Oasis-dataBase");

            // Retrieving the specified collection
            MongoCollection<Document> collection = userDatabase.getCollection(collectionName);

            // Getting the number of documents in the collection
            count = collection.countDocuments();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static String[][] getAllExceptPassword(String collectionName, String[] columnNames) {
        String[][] data = null;
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            // Accessing the User database
            MongoDatabase userDatabase = mongoClient.getDatabase("Oasis-dataBase");

            // Retrieving the specified collection
            MongoCollection<Document> collection = userDatabase.getCollection(collectionName);

            // Retrieving all documents in the collection
            try (MongoCursor<Document> cursor = collection.find().iterator()) {
                List<String[]> dataList = new ArrayList<>();
                while (cursor.hasNext()) {
                    Document document = cursor.next();

                    // Remove the "password" field
                    document.remove("password");

                    String[] row = new String[columnNames.length];
                    for (int i = 0; i < columnNames.length; i++) {
                        row[i] = document.getString(columnNames[i]);
                    }
                    dataList.add(row);
                }
                data = dataList.toArray(new String[dataList.size()][]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
