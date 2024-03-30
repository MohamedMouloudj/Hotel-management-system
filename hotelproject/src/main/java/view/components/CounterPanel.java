package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterPanel extends JPanel {
    int counter = 0;
    CounterPanel(){
        //some styling
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(110, 30));

        setLayout(new FlowLayout());

        JLabel CounterTypeAdults = new JLabel("Adults");
        CounterTypeAdults.setFont(new Font("Inter",Font.PLAIN,10));
        add(CounterTypeAdults);

        JLabel CounterTypeChildren = new JLabel("Children");
        CounterTypeChildren.setFont(new Font("Inter",Font.PLAIN,10));
        add(CounterTypeChildren);

        JLabel counterLabel = new JLabel(String.valueOf(counter));
        counterLabel.setBackground(Color.darkGray);
        counterLabel.setSize(new Dimension(30,30));

        add(counterLabel );


        ImageIcon incrementIcon = new ImageIcon("./view/icons/add.png");
        JButton incrementButton = new JButton();
        incrementButton.setBackground(new Color(0, 0, 0, 0)); // Set background color to transparent
        incrementButton.setOpaque(false); // Allow transparency
        incrementButton.setBorderPainted(false); //
        // incrementButton.setIcon(incrementIcon);

        ImageIcon decrementIcon = new ImageIcon("./view/icons/close.png");
        JButton decrementButton = new JButton("");
        // decrementButton.setIconToButton(decrementIcon , 20, 20);
        decrementButton.setPreferredSize(new Dimension(20,20));



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
