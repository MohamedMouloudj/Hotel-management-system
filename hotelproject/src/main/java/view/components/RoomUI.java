package view.components;

import model.RoomType;
import org.jdesktop.swingx.JXDatePicker;
import view.ImageNotFoundedException;

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


        //setLayout(new MigLayout("wrap , debug","[fill][fill]","[fill]20[fill]20[]20[]20[]20[]"));



        ///back button  to the rooms ////

       setLayout(new GridBagLayout());


        DynamicButton backButton = new DynamicButton("Back");
        backButton.setButtonSize(new Dimension(40,40));
        backButton.setForeground(Color.black);
        try {
            ImageIcon  backIcon = new ImageIcon("view/icons/backIcon.png");
            if (backIcon == null)
                throw new ImageNotFoundedException("Program icon is not found");
            backButton.setIconToButton(backIcon , 3, SwingConstants.CENTER);
        }catch (ImageNotFoundedException e){
            e.printStackTrace();
        }

        backButton.setOpaque(false);
        backButton.addActionListener(e -> {
            // Hide this RoomUI panel
            this.removeAll();
            // Show the RoomsPanel again
            // Assuming RoomsPanel is the parent of Room and Room is the parent of RoomUI
            RoomsPanel roomsPanel = (RoomsPanel) this.getParent();

            for (Component component : roomsPanel.rooms) {
                if (component instanceof Room) {
                    roomsPanel.add(component);
                }
            }

            // Revalidate and repaint the RoomsPanel for layout updates
            roomsPanel.revalidate();
            roomsPanel.repaint();
        });

        add(backButton );


        /////////// Room Info //////////
        JLabel roomTypeLabel = new JLabel(roomType.toString());
        roomTypeLabel.setFont(new Font("Lucida Handwriting",Font.PLAIN,15));

        add(roomTypeLabel  );

        JLabel isAvailableLabel = new JLabel("Available");
        isAvailableLabel.setFont(new Font("Inter",Font.PLAIN,30));
        isAvailableLabel.setForeground(new Color(0x00A000));

        add(isAvailableLabel  );

        JLabel roomDescriptionLabel = new JLabel(roomDescription);
        roomDescriptionLabel.setFont(new Font("Inter",Font.PLAIN,8));

        add(roomDescriptionLabel );



        //Counter panel for counting adults and chuldren
        CounterPanel AdultsCounter = new CounterPanel("adults");
        CounterPanel ChildrenCounter = new CounterPanel("adults");
        add(AdultsCounter );
        add(ChildrenCounter );

        //date picker
        JXDatePicker datePicker = new JXDatePicker();
        datePicker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        add(datePicker );
        datePicker.setBorder(BorderFactory.createEmptyBorder(15,10,10,10));

        System.out.println("selected date " + datePicker.getDate());



        JLabel priceLabel = new JLabel("Price: "+roomType.getPrice()+"DZD/Night");
        priceLabel.setFont(new Font("Inter",Font.PLAIN,10));
        add(priceLabel );

        //the main book button
        DynamicButton bookButton = new DynamicButton("Book now");
        bookButton.setButtonBgColor(Color.getColor("0377FF"));
        bookButton.setButtonSize(new Dimension(110,30));
        add(bookButton );

        bookButton.addActionListener(e -> {

            //this calculated price will have a different value depending on the date children and adults
            double CalculatedPrice = roomType.getPrice() + 0.2*AdultsCounter.getCount() + 0.2*ChildrenCounter.getCount();//this one to revise
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




