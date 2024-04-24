package view.components.roomComponents;

import model.RoomType;
import net.miginfocom.swing.MigLayout;
import view.components.items.DynamicButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class RoomsPanelGuest extends JPanel {
    private JScrollPane scrollPane;
    JPanel panel;
    //
    protected HashMap<String, RoomOnList> rooms = new HashMap<String, RoomOnList>();
    protected Set<String> keys = rooms.keySet(); // get the keys (roomType)
    // used a set to avoid duplicates
    protected JPanel filter;

    /*
     * using the hashmap to index all the rooms by their type
     * and then using the combobox to select the room type
     */

    public RoomsPanelGuest(HashMap<String, RoomOnList> roomsList) {

        panel = new JPanel();
        panel.setLayout(new MigLayout("wrap 1,center ", "[grow]", "push[]10[]push"));

        // add rooms to the hash map
        for (RoomOnList roomOnList : roomsList.values()) {
            rooms.put(roomOnList.getRoomType().toString() + roomOnList.getRoomPrice(), roomOnList);
        }

        // filter for filtering the rooms by type
        filter = new JPanel();
        filter.setLayout(new FlowLayout());

        JComboBox<RoomType> comboBox = new JComboBox<>();

        // addign the options to the combo box
        for (String roomKey : keys) {
            String roomType = roomKey.replaceAll("\\d+\\.\\d+", "");
            switch (roomType) {
                case "Standard":
                    comboBox.addItem(RoomType.Standard);
                    break;
                case "Double":
                    comboBox.addItem(RoomType.Double);
                    break;
                case "Suit":
                    comboBox.addItem(RoomType.Suite);
                    break;
                case "Family":
                    comboBox.addItem(RoomType.Family);
                    break;
            }
        }
        comboBox.setPreferredSize(new Dimension(150, 30));

        // setting the default value shown to select a roomType
        DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
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
                ArrayList<RoomOnList> roomsToAdd = new ArrayList<>();

                for (RoomOnList roomOnList : rooms.values()) {
                    if (selectedRoomType != null && roomOnList.getRoomType() == selectedRoomType) {
                        roomsToAdd.add(roomOnList);
                    }
                }
                // remove all the rooms and add those who have the same type as the selected one
                panel.removeAll();
                panel.add(filter, "center");
                if (!roomsToAdd.isEmpty()) {
                    for (RoomOnList roomOnList : roomsToAdd) {
                        panel.add(roomOnList, "center");
                    }
                } else {
                    // add a panel for no rooms found
                    JLabel noRoomsFound = new JLabel("No rooms found with this type");
                    panel.add(noRoomsFound, "center , wrap");
                    DynamicButton JoinWaitlist = new DynamicButton("Join Waitlist");
                    JoinWaitlist.setButtonBgColor(new Color(0x0377FF));
                    JoinWaitlist.setButtonSize(new Dimension(110, 40));
                    panel.add(JoinWaitlist, "center");
                }
                panel.revalidate();
                panel.repaint();
            }
        });
        filter.add(FilterButton);

        // another button to show all the rooms again
        DynamicButton resetButton = new DynamicButton("show all");
        resetButton.setForeground(new Color(0x0377FF));
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.add(filter, "center");
                for (RoomOnList roomOnList : rooms.values()) { // assuming allRooms is a list that contains all the
                                                               // RoomOnList objects
                    panel.add(roomOnList, "center");
                }
                panel.revalidate();
                panel.repaint();
            }
        });
        filter.add(resetButton);

        panel.add(filter, "center");

        for (RoomOnList roomOnList : rooms.values()) {
            panel.add(roomOnList, "center");
        }

        // this will allow us to know which room was clicked
        for (int i = 0; i < getComponentCount(); i++) {
            RoomOnList roomOnList = (RoomOnList) getComponent(i);
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

        scrollPane = new JScrollPane(panel);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove scroll pane border
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Increase scroll speed
        scrollPane.setPreferredSize(new Dimension(600, 500)); // Set preferred size

        setLayout(new MigLayout("insets 10, fill"));
        add(scrollPane, "grow, growy, push");

    }
}