package view.login.main;

import javax.swing.*;
import java.awt.*;

import net.miginfocom.swing.MigLayout;
import view.login.loginComponents.MyButtonOutLine;

import java.awt.Graphics;
import java.awt.Graphics2D;
public class PanelRoom extends JPanel {

    private  String roomName;
    private  String description;
    private String available ;
    private double price ;
    private MyButtonOutLine bookButton;


    public PanelRoom(String roomName, String description , boolean available , double price) {
        this.roomName = roomName;
        this.description = description;
        this.price = price;
        if (available){
            this.available = "available";
        }else{
            this.available = "not available";
        }



       // this.image = image;
        MigLayout layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");

        // Set layout manager (FlowLayout)

        // Create information panel
        JPanel infoPanel = new JPanel();
        infoPanel.setMinimumSize(new Dimension(600, 164));

        //set layout
        infoPanel.setLayout(layout);

        //name label
        JLabel nameLabel = new JLabel(roomName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        //available label
        JLabel availableLabel = new JLabel(this.available);
        availableLabel.setFont(new Font("Arial", Font.BOLD, 16));
        availableLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        //description label
        JLabel decriptionLabel = new JLabel(description);
        decriptionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        decriptionLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        String PricePerNight = price + "  per night";
        JLabel priceLabel = new JLabel(PricePerNight);
        priceLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        priceLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

       //button panel
        bookButton = new MyButtonOutLine();
        bookButton.setBackground(new Color(0, 0, 255));
        bookButton.setForeground(new Color(255, 255, 255));
        bookButton.setText("Book Now");
        bookButton.setFont(new Font("sansserif", Font.BOLD, 15));

        infoPanel.add(bookButton , "w 30%, h 40");
       //JPanel buttonPanel = new JPanel();


        //JButton myButton = new JButton("Click Me");
        //Color myBlue = new Color(0, 0, 255);  // RGB values for blue
        //myButton.setBackground(myBlue);
       // myButton.setForeground(Color.white);
        //myButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        //myButton.setMinimumSize(new Dimension(123, 40));


        //adding panels to the label  
        infoPanel.add(nameLabel);
        infoPanel.add(availableLabel);
        infoPanel.add(decriptionLabel);
        infoPanel.add(priceLabel);

        //infoPanel.add(buttonPanel);

        infoPanel.setBackground(new Color(255, 255, 255));

        infoPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));

        // Add image and information panel
        //add(new JLabel(image));
        add(infoPanel);

    }


    /*public static void main(String[] args) {
        // Example usage (replace with your actual image and data)
        String roomName = "Deluxe Room";
        String description = "This spacious room offers a king-size bed, a private balcony, and stunning city views.";
        boolean availablity = true ; // Replace with your image path
        double price = 5000.32;

        JFrame frame = new JFrame("Room Card");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PanelRoom(roomName, description, availablity , price ));
        frame.pack();
        frame.setVisible(true);
    }*/
}

