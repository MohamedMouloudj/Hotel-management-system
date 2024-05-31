package view.components.roomComponents;

import controllers.Controller;
import model.hotel.Hotel;
import model.hotel.RoomType;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;
import view.components.Message;
import view.components.OurButton;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.LinkedList;


public class RoomUI extends JPanel {
    private final double imageWidth = 45;
    private JLabel isAvailableLabel;
    private OurButton bookButton;
    private final Message msg = new Message();
    private final MigLayout layout=new MigLayout("wrap 2, center, insets 0 20 20 40,gap 5% 5%","[][]","[grow,fill]");

    RoomUI(RoomOnList roomOnList){
        setLayout(layout);
        setBackground(new Color(242, 242, 242));

        //// room photo ////
        ImageIcon icon =new ImageIcon(roomOnList.getRoomPicture());
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(icon);
        imageLabel.setPreferredSize(new Dimension(250,200));
        add(imageLabel , "cell 0 0");

        JPanel roomDetailedInfo = new JPanel(new MigLayout("wrap 2,inset 20 15 5 20"));

        roomDetailedInfo.setBorder(BorderFactory.createLineBorder(new Color(0xC1A200),2));
        roomDetailedInfo.setBackground(new Color(242, 242, 242));
        add(roomDetailedInfo,"center,cell 1 0");


        /////////// RoomOnList Info //////////
        JLabel roomTypeLabel = new JLabel(roomOnList.getRoomType().toString());
        roomTypeLabel.setFont(new Font("Lucida Handwriting",Font.PLAIN,30));

        roomDetailedInfo.add(roomTypeLabel , "gap 0 0 0 10" );

        if (!roomOnList.getRoomNumbers().isEmpty() && roomOnList.getRoomNumbers().stream().anyMatch(val->Hotel.getRooms().get(val).availability())) {
            isAvailableLabel = new JLabel("Available");
            isAvailableLabel.setForeground(new Color(0x00A000));
        }else {
            isAvailableLabel = new JLabel("Not available");
            isAvailableLabel.setForeground(new Color(0xA00000));
        }
        isAvailableLabel.setFont(new Font("",Font.PLAIN,14));
        roomDetailedInfo.add(isAvailableLabel , "gap 10 0 20 10" );


        JLabel roomDescriptionLabel = new JLabel(roomOnList.getRoomDescription());
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


        /////check out date
        JXDatePicker checkOut = new JXDatePicker();

        checkOut.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        checkOut.setBorder(null);
        checkOut.setPreferredSize(new Dimension(200, 30));
        infoCollect.add(checkOut);


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
        JLabel priceLabel = new JLabel("Price: "+roomOnList.getPrice()+"DZD/Night");
        priceLabel.setFont(new Font("Inter",Font.BOLD,12));
        roomDetailedInfo.add(priceLabel , "span 2 ,  wrap , gap 15 0 0 0");

        //the main book button
        bookButton = new OurButton("Book now");
        bookButton.setButtonBgColor(new Color(0x0377FF));
        bookButton.setButtonSize(new Dimension(140,40));
        roomDetailedInfo.add(bookButton , "gap 15 0 0 0");


        //back buttonor cancel button
        OurButton backButton = new OurButton("cancel");
        backButton.setButtonSize(new Dimension(140,40));
        backButton.setBackground(Color.white);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(0x0377FF), 2));
        backButton.setForeground(new Color(0x0377FF));

        JLabel reserved = new JLabel();
        reserved.setFont(new Font("Inter",Font.BOLD,12));
        reserved.setForeground(new Color(0x0377FF));


        backButton.setOpaque(false);
        backButton.addActionListener(e -> {
            // Hide this RoomUI panel
            this.removeAll();
            // Show the RoomsPanel again
            RoomsPanelGuest roomsPanel = (RoomsPanelGuest) this.getParent().getParent().getParent().getParent();

            this.getParent().getParent().getParent().setBackground(Color.GREEN);
            roomsPanel.panel.removeAll();

            for (Component component : roomsPanel.rooms.values()) {
                if (component instanceof RoomOnList) {
                    roomsPanel.panel.add(component , "center");
                }
            }

            roomsPanel.repaint();
            roomsPanel.revalidate();
        });

        roomDetailedInfo.add(backButton ,"wrap");
        roomDetailedInfo.add(reserved , " center , span 2 ,  wrap , gap 15 0 0 0");


        Controller.openBookingUI(bookButton,roomOnList.getRoomNumbers(),roomOnList.getUsedRoomNumbers(),
                roomOnList.getPrice(), AdultsCounter,ChildrenCounter,checkIn,checkOut,creditCardField,
                phoneNumberField,roomOnList,this,msg,this,layout);

    }

    public void setAvailable(boolean available){
        if(available){
            isAvailableLabel.setText("Available");
            isAvailableLabel.setForeground(new Color(0x00A000));
            bookButton.setEnabled(true);
            repaint();
            revalidate();
        }else{
            isAvailableLabel.setText("Not Available");
            isAvailableLabel.setForeground(new Color(0xA00000));
            bookButton.setEnabled(false);
            repaint();
            revalidate();
        }
    }

}
