package view.components;

import javax.swing.*;

public class Image extends JPanel {

    Image(){
        ImageIcon icon = new ImageIcon("./view/icons/room.png");
        JLabel imageLabel = new JLabel(icon);
        add(imageLabel);
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("RoomOnList Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Image());
        frame.pack();
        frame.setVisible(true);
    }
}
