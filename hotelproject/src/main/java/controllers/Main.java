package controllers;

import view.login.main.LogInForm;

import java.util.*;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import model.Guest;

import static view.login.main.LogInForm.*;

public class Main {
    public static void main(String[] args) {
        // Receptionist receptionist = new Receptionist("karin", "Doe", "password123",
        // "john@example.com", 222);
        // Manager.addEmployee(receptionist);
        // Manager.removeEmployee("chakib", "Doe");
        LogInForm.runForm();
        // System.out.println(Guest.isValidEmail("uu"));

    }
}