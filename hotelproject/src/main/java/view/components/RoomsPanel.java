package view.components;

import model.RoomType;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RoomsPanel extends JPanel {
    public RoomsPanel(){

        setLayout(new GridLayout(0,1,10,10));

        //TODO : remove the price from RoomType, will affect this code
        Room room1 = new Room(RoomType.Standard,"hotelproject/src/main/java/view/icons/singleRoom.jpg","Single Room with a single bed");
        Room room2 = new Room(RoomType.Double,"hotelproject/src/main/java/view/icons/doubleRoom.jpg","Double Room with a double bed");
        Room room3 = new Room(RoomType.Suite,"hotelproject/src/main/java/view/icons/suitRoom.jpg","Suite Room with a double bed and a living room");
        Room room4 = new Room(RoomType.Family,"hotelproject/src/main/java/view/icons/familyRoom.jpg","Family Room with a double bed and two single beds");

        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10,0));

        add(room1);
        add(room2);
        add(room4);
        add(room3);
    }
}

class Room extends JPanel {
    private final double imageWidth = 45;
    Room(RoomType roomType, String roomPicture, String roomDescription){

        Border border= BorderFactory.createLineBorder(new Color(0xC1A200));
        setBorder(border);

        setLayout(new MigLayout("fillx,wrap 1, debug","[fill]10[fill]","[fill]"));

        ImageIcon icon =new ImageIcon(roomPicture);
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(icon);
        add(imageLabel,"center,cell 0 0, growx, pushx ,growy, pushy");

        JPanel roomInfo = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        add(roomInfo,"center,cell 1 0");

        /////////// Room Info ///////////
        JLabel roomTypeLabel = new JLabel(roomType.toString());
        roomTypeLabel.setFont(new Font("Lucida Handwriting",Font.PLAIN,20));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets=new Insets(0,10,10,10);
        roomInfo.add(roomTypeLabel,constraints);

        JLabel isAvailableLabel = new JLabel("Available");
        isAvailableLabel.setFont(new Font("Inter",Font.PLAIN,16));
        isAvailableLabel.setForeground(new Color(0x00A000));
        constraints.gridy = 0;
        constraints.gridx = 1;
        roomInfo.add(isAvailableLabel,constraints);

        JLabel roomDescriptionLabel = new JLabel(roomDescription);
        roomDescriptionLabel.setFont(new Font("Inter",Font.PLAIN,11));
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.insets=new Insets(0,10,10,10);
        roomInfo.add(roomDescriptionLabel,constraints);

        JLabel priceLabel = new JLabel(" Price: "+roomType.getPrice()+"DZD/Night");
        priceLabel.setFont(new Font("Inter",Font.PLAIN,18));
        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        roomInfo.add(priceLabel,constraints);

        DynamicButton bookButton = new DynamicButton("Book now");
        bookButton.setButtonSize(new Dimension(120,40));
        bookButton.setButtonBgColor(new Color(0x0377FF));
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;
        roomInfo.add(bookButton,constraints);
        /////////////////////////////////

    }
    public void setAvailable(boolean available){
        if(available){
            ((JLabel)((JPanel)getComponent(1)).getComponent(1)).setText("Available");
            ((JLabel)((JPanel)getComponent(1)).getComponent(1)).setForeground(new Color(0x00A000));
        }else{
            ((JLabel)((JPanel)getComponent(1)).getComponent(1)).setText("Not Available");
            ((JLabel)((JPanel)getComponent(1)).getComponent(1)).setForeground(new Color(0xA00000));
            ((DynamicButton)((JPanel)getComponent(1)).getComponent(4)).setEnabled(false);
        }
    }
}