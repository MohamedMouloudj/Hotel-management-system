package view.components;

import model.RoomType;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RoomsPanelGuest extends JPanel {
    private JScrollPane scrollPane;
    public RoomsPanelGuest(){
        JPanel panel = new JPanel();

        panel.setLayout(new MigLayout("wrap 1,center, insets 10","[grow]","[]10[]"));

        Room room1 = new Room(RoomType.Standard,"hotelproject/src/main/java/view/icons/singleRoom.jpg","-Single Room with a single bed.");
        Room room2 = new Room(RoomType.Double,"hotelproject/src/main/java/view/icons/doubleRoom.jpg","-Double Room with a double bed.");
        Room room3 = new Room(RoomType.Suite,"hotelproject/src/main/java/view/icons/suitRoom.jpg","-Suite Room with a double bed and a living room.");
        Room room4 = new Room(RoomType.Family,"hotelproject/src/main/java/view/icons/familyRoom.jpg","-Family Room with a double bed and two single beds.");

        panel.add(room1,"center");
        panel.add(room2,"center");
        panel.add(room4,"center");
        panel.add(room3,"center");

        scrollPane = new JScrollPane(panel);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove scroll pane border
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Increase scroll speed
        scrollPane.setPreferredSize(new Dimension(600, 500)); // Set preferred size

        setLayout(new MigLayout("insets 0, fill"));
        add(scrollPane, "grow, growy, push");

    }
}

class Room extends JPanel {
    Room(RoomType roomType, String roomPicture, String roomDescription){

        Border border= BorderFactory.createLineBorder(new Color(0xC1A200),2);
        setBorder(border);

        setLayout(new MigLayout("wrap 2, center, insets 0 5 0 5","[][]","[grow,fill]"));

        ImageIcon icon =new ImageIcon(roomPicture);
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(icon);
        add(imageLabel,"cell 0 0");

        JPanel roomInfo = new JPanel(new MigLayout("wrap 2,inset 5 15 5 20"));
//        GridBagConstraints constraints = new GridBagConstraints();
        add(roomInfo,"center,cell 1 0");

        /////////// Room Info ///////////
        JLabel roomTypeLabel = new JLabel(roomType.toString());
        roomTypeLabel.setFont(new Font("Lucida Handwriting",Font.PLAIN,22));
        roomInfo.add(roomTypeLabel,"span 2,left,wrap");

        JLabel isAvailableLabel = new JLabel("Available");
        isAvailableLabel.setFont(new Font("Inter",Font.PLAIN,12));
        isAvailableLabel.setForeground(new Color(0x00A000));
        roomInfo.add(isAvailableLabel,"span 2,left,wrap");

        JLabel roomDescriptionLabel = new JLabel("<html>" +roomDescription+"<html>");
        roomDescriptionLabel.setFont(new Font("Inter",Font.PLAIN,10));
        roomInfo.add(roomDescriptionLabel,"span 2,left,growy, pushy, w 60% ,wrap");

        JPanel pricePanel = new JPanel(new MigLayout("wrap 1,filly,inset 0","[grow]","[]2[]"));
        roomInfo.add(pricePanel,"left");
        JLabel priceLabel = new JLabel(" Price: "+roomType.getPrice()+"DZD/Night");
        priceLabel.setFont(new Font("Inter",Font.PLAIN,13));
        pricePanel.add(priceLabel,"left,wrap");

        DynamicButton bookButton = new DynamicButton("Book now");
        bookButton.setButtonBgColor(new Color(0x0377FF));
        pricePanel.add(bookButton,"span 2,left,wrap,growx,pushx");
        /////////////////////////////////

    }

    //TODO: To be checked later
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
