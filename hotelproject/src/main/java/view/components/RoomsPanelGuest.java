package view.components;

import model.RoomType;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class RoomsPanelGuest extends JPanel {
    private JScrollPane scrollPane;

    //
    HashMap<RoomType, Room> rooms = new HashMap<RoomType , Room>();
    Set<RoomType> keys = rooms.keySet(); // get the keys (roomType)
    //used a set to avoid duplicates
    public JPanel filter;

    /*
        using the hashmap to index all the rooms by their type
        and then using the combobox to select the room type
    */

    public RoomsPanelGuest(){


        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("wrap 1,center ","[grow]","push[]10[]push"));

        Room room1 = new Room(RoomType.Standard,"hotelproject/src/main/java/view/icons/singleRoom.jpg","-Single Room with a single bed.");
        Room room2 = new Room(RoomType.Double,"hotelproject/src/main/java/view/icons/doubleRoom.jpg","-Double Room with a double bed.");
        Room room3 = new Room(RoomType.Suite,"hotelproject/src/main/java/view/icons/suitRoom.jpg","-Suite Room with a double bed and a living room.");
        Room room4 = new Room(RoomType.Family,"hotelproject/src/main/java/view/icons/familyRoom.jpg","-Family Room with a double bed and two single beds.");

       //add rooms to the hash map
        rooms.put(room1.getRoomType(),room1);
        rooms.put(room2.getRoomType(),room2);
        rooms.put(room3.getRoomType(),room3);
        rooms.put(room4.getRoomType(),room4);

        //filter for filtering the rooms by type
        filter = new JPanel();
        filter.setLayout(new FlowLayout());

        JComboBox<RoomType> comboBox = new JComboBox<>();

        //addign the  options to the combo box
        for (RoomType roomType : keys ) {
            comboBox.addItem(roomType);
        }
        comboBox.setPreferredSize(new Dimension(150, 30));

        //setting the default value shown to select a roomType
        DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value == null) {
                    setText("Select a RoomType");
                } else {
                    setText(value.toString());
                }
                return this;
            }
        };

        // Set the custom renderer to the JComboBox
        comboBox.setRenderer(renderer);
        // Set the selected item to null to display the initial text
        comboBox.setSelectedItem(null);
        filter.add(comboBox);

        DynamicButton FilterButton = new DynamicButton("filter");
        FilterButton.setButtonBgColor(new Color(0x0377FF));
        FilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomType selectedRoomType = (RoomType) comboBox.getSelectedItem();
                ArrayList<Room> roomsToAdd = new ArrayList<>();

                for (Room room : rooms.values()) {
                        if (selectedRoomType != null && room.getRoomType() == selectedRoomType) {
                            roomsToAdd.add(room);
                        }
                }
                //remove all the rooms and add those who have the same type as the selected one
                panel.removeAll();
                panel.add(filter , "center");
                if (!roomsToAdd.isEmpty()) {
                    for (Room room : roomsToAdd) {
                        panel.add(room , "center");
                    }
                }else {
                   // add a panel for no rooms found
                    JLabel noRoomsFound = new JLabel("No rooms found with this type");
                    panel.add(noRoomsFound , "center , wrap");
                    DynamicButton JoinWaitlist = new DynamicButton("Join Waitlist");
                    JoinWaitlist.setButtonBgColor(new Color(0x0377FF));
                    JoinWaitlist.setButtonSize(new Dimension(110,40));
                    panel.add(JoinWaitlist , "center");


                }
                panel.revalidate();
                panel.repaint();
            }
        });
        filter.add(FilterButton);

       //another button to show all the rooms again
        DynamicButton resetButton = new DynamicButton("show all");
        resetButton.setForeground(new Color(0x0377FF));
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.add(filter ,"center");
                for (Room room : rooms.values()) { // assuming allRooms is a list that contains all the Room objects
                    panel.add(room , "center");
                }
                panel.revalidate();
                panel.repaint();
            }
        });
        filter.add(resetButton);

        panel.add(filter, "center");

        for (Room room : rooms.values()) {
            panel.add(room , "center");
        }

    //this will allow us to know which room was clicked
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




        scrollPane = new JScrollPane(panel);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove scroll pane border
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Increase scroll speed
        scrollPane.setPreferredSize(new Dimension(600, 500)); // Set preferred size

        setLayout(new MigLayout("insets 10, fill"));
        add(scrollPane, "grow, growy, push");

    }
}

class Room extends JPanel implements ActionListener {

    public RoomUI roomDetail;
    private RoomType roomType;
    private String roomPicture;
    private String roomDescription;

    Room(RoomType roomType, String roomPicture, String roomDescription){

        this.roomType = roomType;
        this.roomPicture = roomPicture;
        this.roomDescription = roomDescription;

        Border border= BorderFactory.createLineBorder(new Color(0xC1A200),2);
        setBorder(border);

        setLayout(new MigLayout("wrap 2, center, insets 0 5 0 5,gap 5% 5%","[][]","[grow,fill]"));

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

        bookButton.addActionListener(this);
        /////////////////////////////////

    }

    RoomType getRoomType() {
        return this.roomType;
    };
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
    public String getRoomPicture() {
        return this.roomPicture;
    }
    public void setRoomPicture(String roomPicture) {
        this.roomPicture = roomPicture;
    }
    public String getRoomDescription() {
        return this.roomDescription;
    }
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
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

    ///this action is to display the room detail panel and hide(remove) the rooms ui
    @Override
    public void actionPerformed(ActionEvent e) {

        roomDetail = new RoomUI(this.roomType,this.roomPicture , this.roomDescription);
        //get the parent that is the roomsPanel
        JPanel rooms = (JPanel) getComponent(0).getParent().getParent(); // Assuming RoomsPanel is the parent of Roomr

        // remove all other Room panels
        rooms.removeAll();

        roomDetail.setVisible(true);
        rooms.add(roomDetail);

        // Revalidate and repaint the RoomsPanel for layout updates
        rooms.revalidate();
        rooms.repaint();

        System.out.println("clicked action performed");
    }

    public void addActionListener(ActionListener actionListener) {
    }
}



