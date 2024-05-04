package view.UserGui;

import controllers.Controller;
import controllers.UserType;
import model.Guest;
import view.ImageNotFoundedException;

import view.components.WelcomePage;
import view.components.menu.Menu;
import view.components.table.PanelBorder;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

import model.User;

/**
 * An abstract class that represents a graphical user interface (GUI) for
 * different types of users (Manager/Guest/Receptionist/....other workers).
 * It extends the JFrame class and provides a common layout and functionality
 * for user GUIs.
 *
 * @param <T> the type of user that will be using this GUI, extending the User
 *            class
 */

public abstract class UserGui<T extends User> extends JFrame {
        private static final String PROGRAM_ICON_PATH = "hotelproject/src/main/java/view/icons/programIcon.jpg";
        private JPanel mainContentPanel; // Main panel for displaying the content
        private Menu sideMenu; // Menu panel displayed on the side
        private PanelBorder borderPanel; // Panel with a border for the main content area

        protected T user; // The currently logged-in user

        private HashMap<Integer, JPanel> panelMap; // Map for storing different panels

        /**
         * Constructs a UserGui instance for a given user.
         * @param user the user object for which the GUI is being created
         */
        public UserGui(T user) {
                this.user = user;
        }

        /**
         * Initialize the GUI components
         * */
        public void init() {
                initializeComponents(); // Initialize the GUI components
                setupPanels(); // Set up the form panels
        }

        /**
         * Returns a map of form panels with their corresponding indices.
         *
         * @return a HashMap containing form panels and their indices
         */
        public abstract HashMap<Integer, JPanel> getPanels();

        /**
         * Returns a map of menu items with their corresponding labels.
         *
         * @return a LinkedHashMap containing menu item labels and their corresponding
         *         strings
         */
        public abstract LinkedHashMap<String, String> getMenuItems();

        /**
         * Sets up the panels and initializes the side menu with event handling.
         */
        private void setupPanels() {
                panelMap = getPanels(); // Get the map of form panels
                if (user instanceof Guest)
                        Controller.setActivePanel(mainContentPanel,new WelcomePage(UserType.GUEST)); // Set the welcome page as the default panel
                sideMenu.initMoving(UserGui.this); // Initialize the side menu movement
                sideMenu.addEventMenuSelected(index -> Controller.setActivePanel(mainContentPanel,panelMap.get(index))); // Add event listener
                                                                                                // for side menu selection
        }

        /**
         * Initializes the GUI components for this frame.
         */
        private void initializeComponents() {
                initializeMainPanels(); // Initialize the main panels
                initializeLayout(); // Initialize the layout
                setApplicationProperties(); // Set the application properties
        }

        /**
         * Initializes the main panels of the GUI.
         */
        private void initializeMainPanels() {
                borderPanel = new PanelBorder(); // Create a new border panel
                sideMenu = new Menu(getMenuItems()); // Create a new side menu with menu items
                mainContentPanel = new JPanel(); // Create a new main content panel
        }

        /**
         * Initializes the layout of the GUI components.
         */
        private void initializeLayout() {
                borderPanel.setBackground(new Color(242, 242, 242)); // Set the background color of the border panel
                mainContentPanel.setOpaque(false); // Set the main content panel to be transparent
                mainContentPanel.setLayout(new BorderLayout()); // Set the layout of the main content panel
                // Set up the layout of the border panel
                GroupLayout borderPanelLayout = new GroupLayout(borderPanel);
                borderPanel.setLayout(borderPanelLayout);
                borderPanelLayout.setHorizontalGroup(
                                borderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(borderPanelLayout.createSequentialGroup()
                                                                .addComponent(sideMenu, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(borderPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(borderPanelLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(6, 6, 6)
                                                                                                .addComponent(mainContentPanel,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addContainerGap()))));
                borderPanelLayout.setVerticalGroup(
                                borderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(sideMenu, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                                                .addGroup(borderPanelLayout.createSequentialGroup()

                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(mainContentPanel,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));

                // Set up the layout of the content pane
                GroupLayout contentPaneLayout = new GroupLayout(getContentPane());
                getContentPane().setLayout(contentPaneLayout);
                contentPaneLayout.setHorizontalGroup(
                                contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(borderPanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                contentPaneLayout.setVerticalGroup(
                                contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(borderPanel, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        }

        /**
         * Sets the properties of the application, such as the default close operation and resizable.
         */
        private void setApplicationProperties() {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the //
                setResizable(true); // Set the frame to be non-resizable
                try {
                        ImageIcon icon = new ImageIcon(PROGRAM_ICON_PATH);
                        ImageNotFoundedException.imageNotFound(icon);
                        setIconImage(icon.getImage());
                } catch (ImageNotFoundedException e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                }
                setTitle("Oasis Hotel");
                setPreferredSize(new Dimension(1200,700)); // Set the size of the frame
                pack();
                setLocationRelativeTo(null); // Center the frame on the screen
        }

        /**
         * Runs the application and displays the main frame.
         *
         * @param frame the JFrame instance to be displayed
         */
        public static void run(JFrame frame) {
                try {
                        // Set the look and feel to Nimbus
                        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                                | UnsupportedLookAndFeelException ex) {
                        // Log any exceptions related to setting the look and feel
                        java.util.logging.Logger.getLogger(GuestUi.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                }

                // Create and display the main frame of the application
                EventQueue.invokeLater(() -> frame.setVisible(true));
        }
}
