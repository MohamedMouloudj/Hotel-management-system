package view.basicComponents;

import model.RoomType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CounterPanel extends JPanel{
    int counter = 0;
    CounterPanel(){
        //some styling
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(110, 30));

        setLayout(new FlowLayout());

        JLabel CounterType = new JLabel("Adults");
        CounterType.setFont(new Font("Inter",Font.PLAIN,10));
        add(CounterType);

        JLabel counterLabel = new JLabel(String.valueOf(counter));
        counterLabel.setBackground(Color.darkGray);
        counterLabel.setSize(new Dimension(30,30));

        add(counterLabel );


        ImageIcon incrementIcon = new ImageIcon("./view/icons/add.png");
        JButton incrementButton = new JButton();
        incrementButton.setBackground(new Color(0, 0, 0, 0)); // Set background color to transparent
        incrementButton.setOpaque(false); // Allow transparency
        incrementButton.setBorderPainted(false); //
        incrementButton.setIcon(incrementIcon);

        ImageIcon decrementIcon = new ImageIcon("./view/icons/close.png");
        JButton decrementButton = new JButton("-");
        decrementButton.setIcon(decrementIcon);


        incrementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                counterLabel.setText(String.valueOf(counter));
                if (!decrementButton.isEnabled()) {
                    decrementButton.setEnabled(true);
                }
            }
        });
        add(incrementButton );

        decrementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (counter > 0) {
                    counter--;
                    counterLabel.setText(String.valueOf(counter));
                    if (counter == 0) {
                        decrementButton.setEnabled(false);
                    }
                }
            }
        });
        add(decrementButton );


    }
}


public class RoomDetail extends JPanel {
    private final double imageWidth = 45;
    private int counter = 0;
    RoomDetail(RoomType roomType, String roomPicture, String roomDescription){

        /*JPanel roomInfo = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        add(roomInfo,BorderLayout.EAST);*/



        //// room photo ////
        ImageIcon icon =new ImageIcon(roomPicture);
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(icon);
        imageLabel.setPreferredSize(new Dimension(250,150));
        add(imageLabel);


        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        /////////// Room Info ///////////
        JLabel roomTypeLabel = new JLabel(roomType.toString());
        roomTypeLabel.setFont(new Font("Lucida Handwriting",Font.PLAIN,15));
        constraints.gridx = 1;
        add(roomTypeLabel,constraints);

        JLabel isAvailableLabel = new JLabel("Available");
        isAvailableLabel.setFont(new Font("Inter",Font.PLAIN,15));
        isAvailableLabel.setForeground(new Color(0x00A000));
        constraints.gridx = 2;
        add(isAvailableLabel,constraints);

        JLabel roomDescriptionLabel = new JLabel(roomDescription);
        roomDescriptionLabel.setFont(new Font("Inter",Font.PLAIN,8));
        constraints.gridx = 1;
        add(roomDescriptionLabel,constraints);

        //setLayout(new FlowLayout());

        //adults logic (increment and decrement)

       CounterPanel Counter = new CounterPanel();
       add(Counter);


        JLabel priceLabel = new JLabel("Price: "+roomType.getPrice()+"DZD/Night");
        priceLabel.setFont(new Font("Inter",Font.PLAIN,10));
        add(priceLabel,constraints);

        OurButton bookButton = new OurButton("Book now");
        bookButton.setWidth(110);
        add(bookButton,constraints);

    }

}


