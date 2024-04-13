package view.guestUi;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import model.Room;
import model.RoomType;
import view.ImageNotFoundException;
import view.components.*;
import view.components.HomeComponents.Card;
import view.components.HomeComponents.HomePage;
import view.components.roomComponents.RoomOnList;
import view.components.roomComponents.RoomsPanelGuest;
import view.components.sideBarComponents.SideBar;
import view.components.sideBarComponents.SideButton;

public class GuestUi extends JFrame {
    private SideBar sideBar;
    private SideButton roomsTab;
    private SideButton reservationsTab;
    private SideButton profileTab;
    private JTabbedPane tabbedContent;
    private LinkedHashMap<String, ActionListener> map = new LinkedHashMap<>();
    Color fancyColor = new Color(0x0377FF);
    Color lightColor = new Color(0x4FB5FF);
    public GuestUi(HashMap<String,RoomOnList> roomsList) {
        try {
            ImageIcon icon = new ImageIcon("hotelproject/src/main/java/view/icons/programIcon.jpg");
            if (icon == null)
                throw new ImageNotFoundException("Program icon is not found");
            setIconImage(icon.getImage());
        }catch (ImageNotFoundException e){
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

//        RoomOnList room1 = new RoomOnList(RoomType.Standard,"hotelproject/src/main/java/view/icons/StandardRoom.jpg","-Single Room with a single bed.",9000, true);
//        RoomOnList room2 = new RoomOnList(RoomType.Double,"hotelproject/src/main/java/view/icons/DoubleRoom.jpg","-Double Room with a double bed.",12000, true);
//        RoomOnList room3 = new RoomOnList(RoomType.Suite,"hotelproject/src/main/java/view/icons/SuiteRoom.jpg","-Suite Room with a double bed and a living room.",18000, true);
//        RoomOnList room4 = new RoomOnList(RoomType.Family,"hotelproject/src/main/java/view/icons/FamilyRoom.jpg","-Family Room with a double bed and two single beds.",25000, false);

        RoomsPanelGuest roomsPanel = new RoomsPanelGuest(roomsList);
        JPanel emptyTMPPanel=new JPanel();
        emptyTMPPanel.setBackground(Color.WHITE);
        ProfileUi profilePanel=new ProfileUi();
        profilePanel.setBackground(Color.WHITE);
        profilePanel.addFirstName("John");
        profilePanel.addLastName("Doe");
        profilePanel.addEmail("mdlc@gmail.com");
        profilePanel.addPassword("password123");

        // JUST AN EXAMPLE OF HOW TO ADD CARDS TO THE HOMEPAGE
        HomePage homePage = new HomePage();
        Card card1 = new Card("Available Rooms", "Click here to see the available rooms");
        homePage.addCard(card1);
        Card card2 = new Card("Reservations", "Click here to see your reservations");
        card2.setBgColor(new Color(0x995555), new Color(0x995599));
        homePage.addCard(card2);

        tabbedContent.addTab("homePage", homePage);
        tabbedContent.addTab("roomsPanel", roomsPanel);
        tabbedContent.addTab("emptyPanel",emptyTMPPanel);//Temporary
        tabbedContent.addTab("profilePanel",profilePanel);//Temporary
        ////////////////////////////////////////////////////////////////////

        /////////////////// SideBar ////////////////////////////////////////
        map.put("Home", e -> tabbedContent.setSelectedIndex(0));
        map.put("Rooms", e -> tabbedContent.setSelectedIndex(1));
        map.put("Requests", e -> tabbedContent.setSelectedIndex(2));
        map.put("ProfileUi", e -> tabbedContent.setSelectedIndex(3));
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
