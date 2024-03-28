package view.guestUi;

import java.awt.*;
// import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;
import view.basicComponents.PanelTabs;

public class GuestUi extends JFrame {

    private MigLayout layout;
    private GuestShow panelShow;
    private PanelTabs panelTabs;
    private final double panelShowSize = 75;
    private final double panelTabsSize = 25;
    private JLayeredPane bg;

    public GuestUi() {
        initComponents();
        act();
        init();
    }

    private void init() {
        layout = new MigLayout("fill, insets 0");
        panelShow = new GuestShow();
        HashMap<String, ActionListener> map = new HashMap<>();
        map.put("Rooms", null);
        map.put("Add Reservation", null);
        map.put("Cancel Reservation", null);
        map.put("Update Reservation", null);
        map.put("Profile", null);

        panelTabs = new PanelTabs(map, "[]40[]20[]20[]20[]20[]20push");
        panelTabs.addAncestorListener(null);

        bg.setLayout(layout);
        bg.add(panelShow, "width " + panelShowSize + "%, pos " + "1al" + " 0 n 100%");
        bg.add(panelTabs, "width " + panelTabsSize + "%, pos " + "0al" + " 0 n 100%");
    }

    private void act() {

    }

    private void initComponents() {
        bg = new javax.swing.JLayeredPane();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        try {
            Image backgroundImage = new ImageIcon(
                    getClass().getResource("/view/icons/OasisLogo.png")).getImage();
            setIconImage(backgroundImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
                bgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 933, Short.MAX_VALUE));
        bgLayout.setVerticalGroup(
                bgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 537, Short.MAX_VALUE));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bg, GroupLayout.Alignment.TRAILING));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bg));

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuestUi().setVisible(true);
            }
        });
    }
}