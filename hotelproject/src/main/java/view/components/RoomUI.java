package view.components;

import model.RoomType;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class RoomUI extends JPanel {
    private final double imageWidth = 45;

    RoomUI(RoomType roomType, String roomPicture, String roomDescription){

        /* JPanel roomInfo = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        add(roomInfo,BorderLayout.EAST);*/



        //// room photo ////
        ImageIcon icon =new ImageIcon(roomPicture);
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(icon);
        imageLabel.setPreferredSize(new Dimension(250,150));
        add(imageLabel);


        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        /////////// Room Info //////////
        JLabel roomTypeLabel = new JLabel(roomType.toString());
        roomTypeLabel.setFont(new Font("Lucida Handwriting",Font.PLAIN,15));
        constraints.gridx = 1;
        add(roomTypeLabel,constraints);

        JLabel isAvailableLabel = new JLabel("Available");
        isAvailableLabel.setFont(new Font("Inter",Font.PLAIN,15));
        isAvailableLabel.setForeground(new Color(0x00A000));
        constraints.gridx = 2;
        add(isAvailableLabel,constraints);

        JLabel roomDescriptionLabel = new JLabel(roomDescription);
        roomDescriptionLabel.setFont(new Font("Inter",Font.PLAIN,8));
        constraints.gridx = 1;
        add(roomDescriptionLabel,constraints);

        //setLayout(new FlowLayout());

        //adults logic (increment and decrement)

        CounterPanel Counter = new CounterPanel();
        add(Counter);

        //date picker
        JXDatePicker datePicker = new JXDatePicker();
        datePicker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        add(datePicker);

        System.out.println("selected date " + datePicker.getDate());


        JLabel priceLabel = new JLabel("Price: "+roomType.getPrice()+"DZD/Night");
        priceLabel.setFont(new Font("Inter",Font.PLAIN,10));
        add(priceLabel,constraints);

        JButton bookButton = new JButton("Book now");
        bookButton.setPreferredSize(new Dimension(110,20));
        add(bookButton);
        bookButton.addActionListener(e -> {
            //this calculated price will have a different value depending on the date children and adults
            double CalculatedPrice = roomType.getPrice();
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




