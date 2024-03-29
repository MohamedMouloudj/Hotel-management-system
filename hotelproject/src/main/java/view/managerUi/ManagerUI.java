package view.managerUi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;
import view.components.PanelTabs;

public class ManagerUI extends JFrame {

    private MigLayout layout;
    private ManagerShow panelShow;
    private PanelTabs panelTabs;
    private final double panelShowSize = 75;
    private final double loginSize = 25;
    private JLayeredPane bg;
    private ActionListener actAllrecip;
    private ActionListener actAddRecip;
    int a = 0;
    int b = 0;

    public ManagerUI() {
        initComponents();
        act();
        init();
    }

    private void init() {
        layout = new MigLayout("fill, insets 0");
        panelShow = new ManagerShow();
        HashMap<String, ActionListener> map = new HashMap<>();
        map.put("Add Reciptionist", actAddRecip);
        map.put("Delate Reciptionist", actAddRecip);
        map.put("All Reciptionists", actAllrecip);
        map.put("Add Room", null);
        map.put("Delate Room", null);
        map.put("All Rooms", null);

        panelTabs = new PanelTabs(map, "[]30[]20[]20[]20[]20[]20[]push");
        panelTabs.addAncestorListener(null);

        bg.setLayout(layout);
        bg.add(panelShow, "width " + panelShowSize + "%, pos " + "1al" + " 0 n 100%");
        bg.add(panelTabs, "width " + loginSize + "%, pos " + "0al" + " 0 n 100%");
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

    private void showAllRecipt() {
        if (a % 2 == 0) {
            panelShow.addAllreceptionist();

        } else {
            panelShow.removeAllComponents();
        }
        a++;
    }

    public static void main(String[] args) {
        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerUI().setVisible(true);
            }
        });
    }
}