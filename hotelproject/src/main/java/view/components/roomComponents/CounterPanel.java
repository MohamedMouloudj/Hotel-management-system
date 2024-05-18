package view.components.roomComponents;

import view.components.OurButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterPanel extends JPanel {
    int counter = 0;

    public CounterPanel(String text) {
        // some styling
        setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));

        setLayout(new FlowLayout());
        setBackground(Color.white);

        ImageIcon incrementIcon = new ImageIcon("hotelproject/src/main/java/view/icons/add.png");
        OurButton incrementButton = new OurButton("");
        incrementButton.setButtonSize(new Dimension(20, 20));
        incrementButton.setBackground(Color.BLUE); // Set background color to transparent
        incrementButton.setIconToButton(incrementIcon, 20, SwingConstants.CENTER);
        incrementButton.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        add(incrementButton);

        // placeholder
        JLabel CounterType = new JLabel(text);
        CounterType.setFont(new Font("Inter", Font.BOLD, 10));
        CounterType.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        add(CounterType);

        // the actual
        JLabel counterLabel = new JLabel(String.valueOf(counter));
        counterLabel.setFont(new Font("Inter", Font.BOLD, 13));
        counterLabel.setForeground(new Color(0x0377FF));
        counterLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        // counterLabel.setSize(new Dimension(30,30));
        add(counterLabel);

        // decrement button
        ImageIcon decrementIcon = new ImageIcon("hotelproject/src/main/java/view/icons/minus.png");
        OurButton decrementButton = new OurButton("");
        decrementButton.setIconToButton(decrementIcon, 20, SwingConstants.CENTER);
        decrementButton.setButtonSize(new Dimension(20, 20));
        decrementButton.setBackground(Color.BLUE);
        decrementButton.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        add(decrementButton);

        // action listeners for buttons
        incrementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(counter <=10) {
                    counter++;
                    counterLabel.setText(String.valueOf(counter));
                    if (counter == 10) {
                        incrementButton.setEnabled(false);
                    }
                }
                if (!decrementButton.isEnabled()) {
                    decrementButton.setEnabled(true);
                }
            }
        });

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
    }

    public int getCount() {
        return this.counter;
    }
}