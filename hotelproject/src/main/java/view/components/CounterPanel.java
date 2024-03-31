package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterPanel extends JPanel {
    int counter = 0;
    CounterPanel(String text){
        //some styling
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(30, 10));

        setLayout(new FlowLayout());

        JLabel CounterTypeAdults = new JLabel(text);
        CounterTypeAdults.setFont(new Font("Inter",Font.PLAIN,10));
        add(CounterTypeAdults);

        JLabel counterLabel = new JLabel(String.valueOf(counter));
        counterLabel.setBackground(Color.lightGray);
        counterLabel.setSize(new Dimension(30,30));

        add(counterLabel);


        ImageIcon incrementIcon = new ImageIcon("./view/icons/add.png");
        DynamicButton incrementButton = new DynamicButton("+");
        incrementButton.setPreferredSize(new Dimension(5,5));
        incrementButton.setBackground(new Color(0, 0, 0, 0)); // Set background color to transparent
        incrementButton.setBorderPainted(false);
        incrementButton.setIconToButton(incrementIcon , 20, SwingConstants.CENTER);
        // incrementButton.setIcon(incrementIcon);

        ImageIcon decrementIcon = new ImageIcon("./view/icons/close.png");
        DynamicButton decrementButton = new DynamicButton("-");
        decrementButton.setIconToButton(decrementIcon , 20, SwingConstants.CENTER);
        decrementButton.setPreferredSize(new Dimension(20,20));


         //action listeners for buttons
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
    int getCount(){
        return this.counter;
    }
}
