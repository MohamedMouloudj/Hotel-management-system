package view.basicComponents;

import model.RoomType;
import net.miginfocom.swing.MigLayout;
import view.login.loginComponents.MyButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RoomsPanel extends JPanel {
    public RoomsPanel(){
        Border border= BorderFactory.createLineBorder(new Color(0xC1A200));
        JPanel panel=new JPanel();
        setBorder(BorderFactory.createTitledBorder(border,"Rooms",TitledBorder.LEFT,TitledBorder.TOP,new Font("Lucida Handwriting",Font.BOLD,20),new Color(0xC1A200)));

        panel.setLayout(new GridLayout(4,2,20,30));

        Room room1 = new Room(RoomType.Standard,"hotelproject/src/main/java/view/icons/singleRoom.jpg","Single Room with a single bed");
        Room room2 = new Room(RoomType.Double,"hotelproject/src/main/java/view/icons/doubleRoom.jpg","Double Room with a double bed");
        Room room3 = new Room(RoomType.Suite,"hotelproject/src/main/java/view/icons/suitRoom.jpg","Suite Room with a double bed and a living room");
        Room room4 = new Room(RoomType.Family,"hotelproject/src/main/java/view/icons/familyRoom.jpg","Family Room with a double bed and two single beds");

        panel.add(room1);
        panel.add(room2);
        panel.add(room4);
        panel.add(room3);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(700, 600)); // Set preferred size
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);

        add(scrollPane);
    }
}

class Room extends JPanel {
    private final double imageWidth = 45;
    Room(RoomType roomType, String roomPicture, String roomDescription){

        ImageIcon icon =new ImageIcon(roomPicture);
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(icon);
        imageLabel.setPreferredSize(new Dimension(250,150));
        add(imageLabel);

        JPanel roomInfo = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        add(roomInfo,BorderLayout.EAST);

        /////////// Room Info ///////////
        JLabel roomTypeLabel = new JLabel(roomType.toString());
        roomTypeLabel.setFont(new Font("Lucida Handwriting",Font.PLAIN,15));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets=new Insets(0,10,10,10);
        roomInfo.add(roomTypeLabel,constraints);

        JLabel isAvailableLabel = new JLabel("Available");
        isAvailableLabel.setFont(new Font("Inter",Font.PLAIN,15));
        isAvailableLabel.setForeground(new Color(0x00A000));
        constraints.gridy = 0;
        constraints.gridx = 1;
        roomInfo.add(isAvailableLabel,constraints);

        JLabel roomDescriptionLabel = new JLabel(roomDescription);
        roomDescriptionLabel.setFont(new Font("Inter",Font.PLAIN,8));
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        roomInfo.add(roomDescriptionLabel,constraints);

        JLabel priceLabel = new JLabel("Price: "+roomType.getPrice()+"DZD/Night");
        priceLabel.setFont(new Font("Inter",Font.PLAIN,10));
        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.insets=new Insets(10,0,10,30);
        roomInfo.add(priceLabel,constraints);

        OurButton bookButton = new OurButton("Book now");
        bookButton.setWidth(110);
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
            ((OurButton)((JPanel)getComponent(1)).getComponent(4)).setEnabled(false);
        }
    }
}
