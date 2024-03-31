package view.components;

import model.RoomType;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class RoomsPanel extends JPanel {
    public ArrayList<Room> rooms = new ArrayList<Room>();

    public RoomsPanel(){

        setLayout(new GridLayout(4,2,10,10));

        //TODO : remove the price from RoomType, will affect this code

        Room room1 = new Room(RoomType.Standard,"hotelproject/src/main/java/view/icons/singleRoom.jpg","Single Room with a single bed yeaaa");
        Room room2 = new Room(RoomType.Double,"hotelproject/src/main/java/view/icons/doubleRoom.jpg","Double Room with a double bed" );
        Room room3 = new Room(RoomType.Suite,"hotelproject/src/main/java/view/icons/suitRoom.jpg","Suite Room with a double bed and a living room");
        Room room4 = new Room(RoomType.Family,"hotelproject/src/main/java/view/icons/familyRoom.jpg","Family Room with a double bed and two single beds");

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);

        for (Room room : rooms) {
            add(room);
        }

        //this will allow us to know which room button was clicked
        for (int i = 0; i < getComponentCount(); i++) {
            Room room = (Room) getComponent(i);
            room.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Find the clicked room's corresponding RoomUI panel
                    Room clickedRoom = (Room) e.getSource();
                    RoomUI clickedRoomUI = clickedRoom.roomDetail;
                    clickedRoomUI.setVisible(true); // Make the clicked room's detail panel visible
                }
            });
        }

        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10,0));

        //add(scrollPane);

    }
}

class Room extends JPanel implements ActionListener {
    public RoomUI roomDetail;
    private final double imageWidth = 45;
    private RoomType roomType;
    private String roomPicture;
    private String roomDescription;

    Room(RoomType roomType,String roomPicture, String roomDescription ){

        this.roomType = roomType;
        this.roomPicture = roomPicture;
        this.roomDescription = roomDescription;

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

        JButton bookButton = new JButton("Book now");
        bookButton.setPreferredSize(new Dimension(100,30));
        bookButton.setBackground(new Color(0x0377FF));
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;

        bookButton.addActionListener(this);

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



    ///this action is to display the room detail panel and hide(remove) the rooms ui
    @Override
    public void actionPerformed(ActionEvent e) {

        roomDetail = new RoomUI(this.roomType,this.roomPicture , this.roomDescription);
        //get the parent that is the roomsPanel
        RoomsPanel roomsPanel = (RoomsPanel) getComponent(0).getParent().getParent(); // Assuming RoomsPanel is the parent of Roomr

        // Hide all other Room panels
        roomsPanel.removeAll();

        roomDetail.setVisible(true);
        roomsPanel.add(roomDetail);

        // Revalidate and repaint the RoomsPanel for layout updates
        roomsPanel.revalidate();
        roomsPanel.repaint();

        System.out.println("clicked action performed");
    }

    public void addActionListener(ActionListener actionListener) {
    }
}
