package view.windows;

import view.components.*;
import javax.swing.*;
import java.awt.*;

public class GuestUiSidebar extends JFrame{
    public GuestUiSidebar(){
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Logo logo=new Logo();

        SideLabelContainer rooms=new SideLabelContainer("Rooms");
        SideLabelContainer reservations=new SideLabelContainer("Reservations");
        SideLabelContainer profile=new SideLabelContainer("Profile");

        JPanel sideTabs=new JPanel();
        sideTabs.setPreferredSize(new Dimension(170,180));
        sideTabs.setBackground(null);
        sideTabs.setLayout(new FlowLayout(FlowLayout.TRAILING,0,20));

        OurButton quitButton=new OurButton("Log out");
        quitButton.setIconToButton("hotelproject/src/main/java/view/icons/quit.png",5,4);
        quitButton.setWidth(200);

        JPanel container=new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.TRAILING,0,20));
        container.setBackground(new Color(0x1E90FF));
        container.setPreferredSize(new Dimension(200,0));

        sideTabs.add(rooms);
        sideTabs.add(reservations);
        sideTabs.add(profile);

        container.add(logo);
        container.add(sideTabs,BorderLayout.EAST);
        container.add(quitButton);
        add(container,BorderLayout.WEST);
        setVisible(true);
    }
}
