package view.gustUi;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import view.ImageNotFoundedException;
import view.components.*;
import view.components.sideBarComponents.SideBar;
import view.components.sideBarComponents.SideButton;

public class GuestUi extends JFrame {
    private SideBar sideBar;
    private SideButton roomsTab;
    private SideButton reservationsTab;
    private SideButton profileTab;
    private JTabbedPane tabbedContent;
    private HashMap<String, ActionListener> map = new HashMap<>();
    Color fancyColor = new Color(0x0377FF);
    Color lightColor = new Color(0x4FB5FF);
    public GuestUi() {
        try {
            ImageIcon icon = new ImageIcon("hotelproject/src/main/java/view/icons/programIcon.jpg");
            if (icon == null)
                throw new ImageNotFoundedException("Program icon is not found");
            setIconImage(icon.getImage());
        }catch (ImageNotFoundedException e){
            e.printStackTrace();
        }

        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5,0));
        /////////////////////////////////////////////////////////////

        /////////////////// Right Content /////////////////////////////////
        tabbedContent = new JTabbedPane();
        tabbedContent.setUI(new HiddenTabTitleUI()); //Hide tab titles

        RoomsPanelGuest roomsPanel = new RoomsPanelGuest();
        JPanel emptyTMPPanel=new JPanel();
        emptyTMPPanel.setBackground(Color.WHITE);
        ProfileUi profilePanel=new ProfileUi();
        profilePanel.setBackground(Color.WHITE);
        profilePanel.addFirstName("John");
        profilePanel.addLastName("Doe");
        profilePanel.addEmail("mdlc@gmail.com");
        profilePanel.addPassword("password123");
        tabbedContent.addTab("roomsPanel", roomsPanel);
        tabbedContent.addTab("emptyPanel",emptyTMPPanel);//Temporary
        tabbedContent.addTab("profilePanel",profilePanel);//Temporary
        ////////////////////////////////////////////////////////////////////

        /////////////////// SideBar ////////////////////////////////////////
        map.put("Rooms", e -> tabbedContent.setSelectedIndex(0));
        map.put("Reservations", e -> tabbedContent.setSelectedIndex(1));//Temporary
        map.put("ProfileUi", e -> tabbedContent.setSelectedIndex(2));//Temporary
        sideBar = new SideBar(map, tabbedContent);

        ////////////////////////////////////////////////////////////////////
        add(sideBar, BorderLayout.WEST);
        add(tabbedContent, BorderLayout.CENTER);
        setVisible(true);
    }
}

/**
 * Override the default tabbed pane UI to hide the tab titles and tab area
 * */
class HiddenTabTitleUI extends BasicTabbedPaneUI {

    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        // Return 0 to hide the tab titles
        return 0;
    }

    @Override
    protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
        // Return 0 to hide the tab area (adjust for padding if needed)
        return 0;
    }
}
