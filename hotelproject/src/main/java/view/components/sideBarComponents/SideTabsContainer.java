package view.components.sideBarComponents;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

//TODO: Forget about this class right now
public class SideTabsContainer extends JLayeredPane {

    /**
     * @param map           TODO: write your documentation about the hashmap utility
     * @param tabbedContent JTabbedPane.
     *                      The container of all the panel to be shown. It must be instantiated in UI class (like GuestUi, ManagerUi, etc)
     */
    public SideTabsContainer(HashMap<String, ActionListener> map, JTabbedPane tabbedContent) {
        init(map, tabbedContent);
    }

    private void init(HashMap<String, ActionListener> map, JTabbedPane tabbedContent) {
        setLayout(new MigLayout("fillx,wrap 1 , inset 0 ", "0[]0", "[]"));
        setPreferredSize(new Dimension(180,200));
        setBackground(null);
        int panelCounter = 0;
        for (String text : map.keySet()) {
            SideButton btn = new SideButton(text);
            btn.addActionListener(map.get(text));
            int finalPanelCounter = panelCounter;// This variable must be final or effectively final, because it is used in the inner class
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    tabbedContent.setSelectedIndex(finalPanelCounter);
                }
            });
            panelCounter++;
            add(btn, "right");
        }

    }

}