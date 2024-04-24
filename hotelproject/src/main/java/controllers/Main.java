package controllers;

import model.hotel.Hotel;
import model.hotel.RoomType;
import model.supervisors.Manager;
import view.guestUi.GuestUi;
import view.login.loginMain.LogInForm;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
        public static Hotel hotel;
        public static GuestUi guestUi;
        public static void main(String[] args) {
            Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
            mongoLogger.setLevel(Level.SEVERE);
            LogInForm.runForm(); //(1st step)
            // TODO: After login result, decide which UI to show and which what type of connected users to be passed to Hotel instance
            Manager.addRoom(RoomType.Standard,true, 9000);
            Manager.addRoom(RoomType.Double,true, 12000);
            Manager.addRoom(RoomType.Suite,false, 18000);
            Manager.addRoom(RoomType.Standard,true, 25000);
        }
}

