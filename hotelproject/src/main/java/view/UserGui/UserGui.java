package view.UserGui;

import view.ImageNotFoundedException;
import view.components.Form;
import view.components.Header;
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
        private Header headerPanel; // Panel for displaying the header
        private JPanel mainContentPanel; // Main panel for displaying the content
        private Menu sideMenu; // Menu panel displayed on the side
        private PanelBorder borderPanel; // Panel with a border for the main content area

        protected T user; // The currently logged-in user

        private HashMap<Integer, JPanel> formPanelMap; // Map for storing different form panels

        /**
         * Constructs a UserGui instance for a given user.
         *
         * @param user the user object for which the GUI is being created
         */
        public UserGui(T user) {
                this.user = user;
                initializeComponents(); // Initialize the GUI components
                setupForms(); // Set up the form panels
        }

        /**
         * Returns a map of form panels with their corresponding indices.
         *
         * @return a HashMap containing form panels and their indices
         */
        public abstract HashMap<Integer, JPanel> getForms();

        /**
         * Returns a map of menu items with their corresponding labels.
         *
         * @return a LinkedHashMap containing menu item labels and their corresponding
         *         strings
         */
        public abstract LinkedHashMap<String, String> getMenuItems();

        /**
         * Sets up the form panels and initializes the side menu with event handling.
         */
        private void setupForms() {
                formPanelMap = getForms(); // Get the map of form panels
                setActiveForm(new Form()); // Set the initial form panel
                sideMenu.initMoving(UserGui.this); // Initialize the side menu movement
                sideMenu.addEventMenuSelected(index -> setActiveForm(formPanelMap.get(index))); // Add event listener
                                                                                                // for menu selection
        }

        /**
         * Sets the active form panel in the main content area.
         *
         * @param form the form panel to be displayed
         */
        private void setActiveForm(JComponent form) {
                mainContentPanel.removeAll(); // Remove all existing components from the main content panel
                mainContentPanel.add(form); // Add the new form panel
                mainContentPanel.repaint(); // Repaint the main content panel
                mainContentPanel.revalidate(); // Revalidate the layout of the main content panel
        }

        /**
         * Initializes the GUI components.
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
                headerPanel = new Header(); // Create a new header panel
                mainContentPanel = new JPanel(); // Create a new main content panel
        }

        /**
         * Initializes the layout of the GUI components.
         */
        private void initializeLayout() {
                borderPanel.setBackground(new Color(242, 242, 242)); // Set the background color of the border panel
                headerPanel.setFont(new Font("sansserif", 0, 14)); // Set the font of the header panel
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
                                                                                .addComponent(headerPanel,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                965, Short.MAX_VALUE)
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
                                                                .addComponent(headerPanel, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
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
         * Sets the properties of the application, such as the default close operation
         * and resizability.
         */
        private void setApplicationProperties() {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the //
                setResizable(false); // Set the frame to be non-resizable
                pack(); // Pack the components into the frame
                setLocationRelativeTo(null); // Center the frame on the screen
                try {
                        ImageIcon icon = new ImageIcon(PROGRAM_ICON_PATH);
                        ImageNotFoundedException.imageNotFound(icon);
                        setIconImage(icon.getImage());
                } catch (ImageNotFoundedException e) {
                        e.printStackTrace();
                }
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
