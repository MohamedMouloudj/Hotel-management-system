package view.windows;

import javax.swing.*;
import java.awt.*;
import view.basicComponents.*;
public class GuestUi extends JFrame {
    public GuestUi() {
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SideBar sideBar = new SideBar();
        add(sideBar, BorderLayout.WEST);

        sideBar.addTab("Home");
        sideBar.addTab("Rooms");

        setVisible(true);
    }
}
