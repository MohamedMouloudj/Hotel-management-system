package view.managerUi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

import view.components.sideBarComponents.SideBar;

public class ManagerUI extends JFrame {

    private ManagerShow panelShow;
    HashMap<String, ActionListener> map = new HashMap<>();
    private SideBar sideBar;
    private JTabbedPane TabbedContent; //TODO : Add all the panel to be shown to this tabbed content , the add it to the sideBar
    private final double panelShowSize = 75;
    private final double loginSize = 25;
    private ActionListener actAllrecip;
    private ActionListener actAddRecip;
    int a = 0;
    int b = 0;

    public ManagerUI() {
        //TODO: Check GuestUi to understand the pattern, DON'T CHANGE GuestUi

        act();
        init();
    }

    private void init() {
        setLayout(new BorderLayout(5,0));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelShow = new ManagerShow();

        map.put("Add Reciptionist", actAddRecip);
        map.put("Delate Reciptionist", actAddRecip);
        map.put("All Reciptionists", actAllrecip);
        map.put("Add RoomOnList", null);
        map.put("Delate RoomOnList", null);
        map.put("All Rooms", null);

        sideBar = new SideBar(map, TabbedContent);



        add(sideBar, BorderLayout.WEST);
        add(panelShow, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void act() {
        actAllrecip = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                showAllRecipt();
            }
        };
        actAddRecip = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (b % 2 == 0) {
                    panelShow.addReci();

                } else {
                    panelShow.removeAllComponents();
                }
                b++;

            }
        };
    }



    private void showAllRecipt() {
        if (a % 2 == 0) {
            panelShow.addAllreceptionist();

        } else {
            panelShow.removeAllComponents();
        }
        a++;
    }

}