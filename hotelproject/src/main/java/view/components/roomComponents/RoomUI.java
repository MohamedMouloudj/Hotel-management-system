package view.components.roomComponents;

import model.hotel.RoomType;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;
import view.components.OurButton;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;


public class RoomUI extends JPanel {
    private final double imageWidth = 45;

    RoomUI(RoomType roomType, String roomPicture, String roomDescription, double price){

        setLayout(new MigLayout("wrap 2, center, insets 0 20 20 40,gap 5% 5%","[][]","[grow,fill]"));
        setBackground(new Color(242, 242, 242));

        //// room photo ////
        ImageIcon icon =new ImageIcon(roomPicture);
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(icon);
        imageLabel.setPreferredSize(new Dimension(250,200));
        add(imageLabel , "cell 0 0");


        JPanel roomDetailedInfo = new JPanel(new MigLayout("wrap 2,inset 20 15 5 20"));

        roomDetailedInfo.setBorder(BorderFactory.createLineBorder(new Color(0xC1A200),2));
        roomDetailedInfo.setBackground(new Color(242, 242, 242));
        add(roomDetailedInfo,"center,cell 1 0");


        /////////// RoomOnList Info //////////
        JLabel roomTypeLabel = new JLabel(roomType.toString());
        roomTypeLabel.setFont(new Font("Lucida Handwriting",Font.PLAIN,30));

        roomDetailedInfo.add(roomTypeLabel , "gap 0 0 0 10" );

        JLabel isAvailableLabel = new JLabel("Available");
        isAvailableLabel.setFont(new Font("",Font.PLAIN,14));
        isAvailableLabel.setForeground(new Color(0x00A000));
        roomDetailedInfo.add(isAvailableLabel , "gap 10 0 20 10" );


        JLabel roomDescriptionLabel = new JLabel(roomDescription);
        roomDescriptionLabel.setFont(new Font("Inter",Font.ITALIC,13));
        roomDetailedInfo.add(roomDescriptionLabel , "span 2 , wrap " );

        JPanel infoCollect = new JPanel(new MigLayout("wrap 2,inset 10 15 5 20"));
        infoCollect.setBackground(new Color(242, 242, 242));
        //Counter panel for counting adults and children
        CounterPanel AdultsCounter = new CounterPanel("adults");
        CounterPanel ChildrenCounter = new CounterPanel("Children");
        infoCollect.add(AdultsCounter );
        infoCollect.add(ChildrenCounter );


        //label for checkin and checkout date
        JLabel checkInLabel = new JLabel("checkin");
        checkInLabel.setFont(new Font("Inter",Font.BOLD,13));

        JLabel checkOutLabel = new JLabel("checkOut date");
        checkOutLabel.setFont(new Font("Inter",Font.BOLD,13));

        infoCollect.add(checkInLabel);
        infoCollect.add(checkOutLabel);

        //date picker
        ///////check in date

        JXDatePicker checkIn = new JXDatePicker();
        checkIn.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        checkIn.setBorder(null); // Remove border
        checkIn.setPreferredSize(new Dimension(200, 30)); // Set preferred
        infoCollect.add(checkIn);

        System.out.println("selected date " + checkIn.getDate());


        /////check out date
        JXDatePicker checkOut = new JXDatePicker();

        checkOut.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        checkOut.setBorder(null);
        checkOut.setPreferredSize(new Dimension(200, 30));
        infoCollect.add(checkOut);
        System.out.println("selected date " + checkOut.getDate());



// Create JLabel and JTextField for phone number
        JLabel phoneNumberLabel = new JLabel("Phone Number");
        JTextField phoneNumberField = new JTextField(20); // 20 columns wide
        phoneNumberField.setBorder(null); // Remove border
        phoneNumberField.setPreferredSize(new Dimension(phoneNumberField.getPreferredSize().width, 30)); // Set height
        phoneNumberField.setFont(new Font("Arial", Font.BOLD, 15)); // Set font and size
        phoneNumberField.setForeground(Color.DARK_GRAY); // Set text color
        phoneNumberField.setHorizontalAlignment(JTextField.CENTER); // Align text to center
        infoCollect.add(phoneNumberLabel , "wrap ");
        infoCollect.add(phoneNumberField, "wrap"); // "wrap" starts a new row after this component

        // Create JLabel and JTextField for credit card
        JLabel creditCardLabel = new JLabel("Credit Card");
        JTextField creditCardField = new JTextField(20); // 20 columns wide
        creditCardField.setBorder(null); // Remove border
        creditCardField.setPreferredSize(new Dimension(creditCardField.getPreferredSize().width, 30)); // Set height
        creditCardField.setFont(new Font("Arial", Font.BOLD, 15)); // Set font and size
        creditCardField.setForeground(Color.DARK_GRAY); // Set text color
        creditCardField.setHorizontalAlignment(JTextField.CENTER); // Align text to center
        infoCollect.add(creditCardLabel , "wrap");
        infoCollect.add(creditCardField, "wrap");
        //adding th info collct to the check in
        roomDetailedInfo.add(infoCollect , "span 2 , wrap");


        //price label
        JLabel priceLabel = new JLabel("Price: "+price+"DZD/Night");
        priceLabel.setFont(new Font("Inter",Font.BOLD,12));
        roomDetailedInfo.add(priceLabel , "span 2 ,  wrap , gap 15 0 0 0");

        //the main book button
        OurButton bookButton = new OurButton("Book now");
        bookButton.setButtonBgColor(new Color(0x0377FF));
        bookButton.setButtonSize(new Dimension(140,40));
        roomDetailedInfo.add(bookButton , "gap 15 0 0 0");


        //back buttonor cancel button
        OurButton backButton = new OurButton("cancel");
        backButton.setButtonSize(new Dimension(140,40));
        backButton.setBackground(Color.white);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(0x0377FF), 2));
        backButton.setForeground(new Color(0x0377FF));


        backButton.setOpaque(false);
        backButton.addActionListener(e -> {
            // Hide this RoomUI panel
            this.removeAll();
            // Show the RoomsPanel again
            RoomsPanelGuest roomsPanel = (RoomsPanelGuest) this.getParent().getParent().getParent().getParent();
            JPanel rooms = (JPanel) this.getParent();
//            roomsPanel.add(roomsPanel.filter , "center");
            for (Component component : roomsPanel.rooms.values()) {
                if (component instanceof RoomOnList) {
                    rooms.add(component , "center");
                }
            }
            // Revalidate and repaint the RoomsPanel for layout updates
            roomsPanel.revalidate();
            roomsPanel.repaint();
        });

         roomDetailedInfo.add(backButton ,"wrap");

        bookButton.addActionListener(e -> {
            //this calculated price will have a different value depending on the date children and adults
            double CalculatedPrice = price+ 0.2*AdultsCounter.getCount() + 0.15*ChildrenCounter.getCount();//this one to revise
            int response = JOptionPane.showConfirmDialog(null,
                    "The price is " + CalculatedPrice + " DZD/Night. Do you want to confirm the booking?",
                    "Confirm Booking", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                // Handle the booking confirmation here
                System.out.println("Booking confirmed");
            } else {
                System.out.println("Booking cancelled");
            }
        });

    }

}
