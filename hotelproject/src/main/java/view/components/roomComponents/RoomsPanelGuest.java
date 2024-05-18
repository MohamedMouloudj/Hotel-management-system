package view.components.roomComponents;

import model.hotel.RoomType;
import net.miginfocom.swing.MigLayout;
import view.components.OurButton;
import view.components.sacrollBar.ModernScrollBarUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class RoomsPanelGuest extends JPanel {
    private JScrollPane scrollPane;
    protected final JPanel panel;

    protected HashMap<String, RoomOnList> rooms = new HashMap<String , RoomOnList>();
    protected Set<String> keys = rooms.keySet(); // get the keys (roomType)
    //used a set to avoid duplicates
    protected JPanel filter;
    /**
        using the hashmap to index all the rooms by their type
        and then using the combobox to select the room type
    */
    public RoomsPanelGuest(HashMap<String,RoomOnList> roomsList){
        setLayout(new MigLayout("wrap 1,insets 10, fill,center"));

        setBackground(new Color(242, 242, 242));
        panel = new JPanel();
        panel.setLayout(new MigLayout("wrap 2,center, gap 15 ","[grow]","push[]10[]push"));
        panel.setBackground(new Color(242, 242, 242));

        if (!roomsList.isEmpty()) {
            //add rooms to the hash map
            for (RoomOnList roomOnList :roomsList.values()){
                rooms.put(roomOnList.getRoomType().toString()+roomOnList.getRoomPrice(), roomOnList);
            }

            //filter for filtering the rooms by type
            filter = new JPanel();
            filter.setLayout(new FlowLayout());
            filter.setBackground(new Color(242, 242, 242));

            JComboBox<RoomType> comboBox = new JComboBox<>();

            //adding the  options to the combo box
            ArrayList<String> keysViewed = new ArrayList<>();
            for (String roomKey : keys ) {
                String roomType = roomKey.replaceAll("\\d+\\.\\d+", "");
                if(keysViewed!=null &&  keysViewed.contains(roomType))
                    continue;
                keysViewed.add(roomType);
                switch (roomType){
                    case "Standard":
                        comboBox.addItem(RoomType.Standard);
                        break;
                    case "Double":
                        comboBox.addItem(RoomType.Double);
                        break;
                    case "Suite":
                        comboBox.addItem(RoomType.Suite);
                        break;
                    case "Family":
                        comboBox.addItem(RoomType.Family);
                        break;
                }
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

            OurButton FilterButton = new OurButton("filter");
            FilterButton.setButtonBgColor(new Color(0x0377FF));
            FilterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RoomType selectedRoomType = (RoomType) comboBox.getSelectedItem();
                    ArrayList<RoomOnList> roomsToAdd = new ArrayList<>();

                    for (RoomOnList roomOnList : rooms.values()) {
                        if (selectedRoomType != null && roomOnList.getRoomType() == selectedRoomType) {
                            roomsToAdd.add(roomOnList);
                        }
                    }
                    //remove all the rooms and add those who have the same type as the selected one
                    panel.removeAll();
                    add(filter , "center");
                    if (!roomsToAdd.isEmpty()) {
                        for (RoomOnList roomOnList : roomsToAdd) {
                            panel.add(roomOnList, "center");
                        }
                    }else {
                        // add a panel for no rooms found
                        JLabel noRoomsFound = new JLabel("No rooms found with this type");
                        panel.add(noRoomsFound , "center , wrap");
                    }
                    panel.revalidate();
                    panel.repaint();
                }
            });
            filter.add(FilterButton);

            //another button to show all the rooms again
            OurButton resetButton = new OurButton("show all");
            resetButton.setForeground(new Color(0x0377FF));
            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.removeAll();
                    for (RoomOnList roomOnList : rooms.values()) { // assuming allRooms is a list that contains all the RoomOnList objects
                        panel.add(roomOnList, "center");
                    }
                    panel.revalidate();
                    panel.repaint();
                }
            });
            filter.add(resetButton);

            add(filter, "center");

            for (RoomOnList roomOnList : rooms.values()) {
                panel.add(roomOnList, "center");
            }

            //this will allow us to know which room was clicked
            for (int i = 0; i < panel.getComponentCount(); i++) {
                RoomOnList roomOnList = (RoomOnList) panel.getComponent(i);
                roomOnList.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Find the clicked roomOnList's corresponding RoomUI panel
                        RoomOnList clickedRoomOnList = (RoomOnList) e.getSource();
                        RoomUI clickedRoomUI = clickedRoomOnList.roomDetail;
                        clickedRoomUI.setVisible(true); // Make the clicked roomOnList's detail panel visible
                    }
                });
            }


        }else {
            JLabel noRoomsFound = new JLabel("No rooms available at the moment.");
            noRoomsFound.setFont(new Font("Arial", Font.BOLD, 36));
            panel.add(noRoomsFound , "center");
        }
        scrollPane = new JScrollPane(panel);
        scrollPane.setBackground(new Color(242, 242, 242));
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove scroll pane border
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Increase scroll speed
        scrollPane.setPreferredSize(new Dimension(600, 500)); // Set preferred size
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new ModernScrollBarUI());
        scrollPane.getVerticalScrollBar().setBackground(Color.WHITE);

        add(scrollPane, "grow, growy, push");

    }
}