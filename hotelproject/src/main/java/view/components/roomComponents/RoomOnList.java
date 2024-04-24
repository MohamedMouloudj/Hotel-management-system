package view.components.roomComponents;

import model.RoomType;
import net.miginfocom.swing.MigLayout;
import view.components.items.DynamicButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomOnList extends JPanel implements ActionListener {

    public RoomUI roomDetail;
    private RoomType roomType;
    private double price;
    private String roomPicture;
    private String roomDescription;
    private JLabel isAvailableLabel;
    private DynamicButton bookButton = new DynamicButton("Book now");

    public RoomOnList(RoomType roomType, String roomPicture, String roomDescription, double price, boolean available) {

        this.roomType = roomType;
        this.roomPicture = roomPicture;
        this.roomDescription = roomDescription;
        this.price = price;

        Border border = BorderFactory.createLineBorder(new Color(0xC1A200), 2);
        setBorder(border);

        setLayout(new MigLayout("wrap 2, center, insets 0 5 0 5,gap 5% 5%", "[][]", "[grow,fill]"));

        ImageIcon icon = new ImageIcon(roomPicture);
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(icon);
        add(imageLabel, "cell 0 0");

        JPanel roomInfo = new JPanel(new MigLayout("wrap 2,inset 5 15 5 20"));
        // GridBagConstraints constraints = new GridBagConstraints();
        add(roomInfo, "center,cell 1 0");

        /////////// RoomOnList Info ///////////
        JLabel roomTypeLabel = new JLabel(roomType.toString());
        roomTypeLabel.setFont(new Font("Lucida Handwriting", Font.PLAIN, 22));
        roomInfo.add(roomTypeLabel, "span 2,left,wrap");

        isAvailableLabel = new JLabel();
        isAvailableLabel.setFont(new Font("Inter", Font.PLAIN, 12));
        if (available) {
            isAvailableLabel.setText("Available");
            isAvailableLabel.setForeground(new Color(0x00A000));
            bookButton.setEnabled(true);
        } else {
            isAvailableLabel.setText("Not Available");
            isAvailableLabel.setForeground(new Color(0xA00000));
            bookButton.setEnabled(false);
        }

        roomInfo.add(isAvailableLabel, "span 2,left,wrap");

        JLabel roomDescriptionLabel = new JLabel("<html>" + roomDescription + "<html>");
        roomDescriptionLabel.setFont(new Font("Inter", Font.PLAIN, 10));
        roomInfo.add(roomDescriptionLabel, "span 2,left,growy, pushy, w 60% ,wrap");

        JPanel pricePanel = new JPanel(new MigLayout("wrap 1,filly,inset 0", "[grow]", "[]2[]"));
        roomInfo.add(pricePanel, "left");
        JLabel priceLabel = new JLabel(" Price: " + price + "DZD/Night");
        priceLabel.setFont(new Font("Inter", Font.PLAIN, 13));
        pricePanel.add(priceLabel, "left,wrap");

        bookButton = new DynamicButton("Book now");
        bookButton.setButtonBgColor(new Color(0x0377FF));
        pricePanel.add(bookButton, "span 2,left,wrap,growx,pushx");

        bookButton.addActionListener(this);
        /////////////////////////////////
    }

    RoomType getRoomType() {
        return this.roomType;
    };

    public double getRoomPrice() {
        return price;
    }

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

    public void setAvailable(boolean available) {
        if (available) {
            isAvailableLabel.setText("Available");
            isAvailableLabel.setForeground(new Color(0x00A000));
            bookButton.setEnabled(true);
        } else {
            isAvailableLabel.setText("Not Available");
            isAvailableLabel.setForeground(new Color(0xA00000));
            bookButton.setEnabled(false);
        }
    }

    /// this action is to display the room detail panel and hide(remove) the rooms
    /// ui
    @Override
    public void actionPerformed(ActionEvent e) {

        roomDetail = new RoomUI(this.roomType, this.roomPicture, this.roomDescription, this.price);
        // get the parent that is the roomsPanel
        JPanel rooms = (JPanel) getComponent(0).getParent().getParent(); // Assuming RoomsPanel is the parent of Roomr

        // remove all other RoomOnList panels
        rooms.removeAll();

        roomDetail.setVisible(true);
        rooms.add(roomDetail);

        // Revalidate and repaint the RoomsPanel for layout updates
        rooms.revalidate();
        rooms.repaint();

    }

    public void addActionListener(ActionListener actionListener) {
    }
}
