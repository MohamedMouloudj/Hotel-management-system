package view;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 * UserGui class represents the main frame of the application, managing the user
 * interface components.
 */
public abstract class UserGui extends JFrame {

    // Constants for icon path and frame dimensions
    private static final String PROGRAM_ICON = "hotelproject/src/main/java/view/icons/programIcon.jpg";
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 600;
    private static final int WIDTH_GAP = 5;
    private static final int HEIGHT_GAP = 0;
    protected HiddenTabTitleUI hideTabs = new HiddenTabTitleUI();

    /**
     * Constructs a new UserGui object and initializes its components.
     */
    public UserGui() {
        initializeFrame();
        initializeTabbedContent();
        initializeSideBar();
        setVisible(true);
    }

    /**
     * Initializes the main frame.
     */
    private void initializeFrame() {
        try {
            // Set program icon
            ImageIcon icon = new ImageIcon(PROGRAM_ICON);
            if (icon.getImageLoadStatus()== MediaTracker.ERRORED) {
                throw new ImageNotFoundException("Program icon is not found");
            }
            setIconImage(icon.getImage());
        } catch (ImageNotFoundException e) {
            // Handle image not found exception
            e.printStackTrace();
        }
        // Set frame size, position, and default close operation
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(WIDTH_GAP, HEIGHT_GAP));
    }

    public abstract void initializeTabbedContent();

    public abstract void initializeSideBar();
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