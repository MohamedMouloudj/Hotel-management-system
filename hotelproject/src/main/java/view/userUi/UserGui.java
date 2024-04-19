package view.userUi;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import view.ImageNotFoundedException;

/**
 * UserGui class represents the main frame of the application, managing the user
 * interface components.
 */
public abstract class UserGui extends JFrame {

    // Constants for icon path and frame dimensions
    private static final String ICON_PATH = "hotelproject/src/main/java/view/icons/programIcon.jpg";
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 600;
    protected HiddenTabTitleUI layoTabTitleUI = new HiddenTabTitleUI();

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
            ImageIcon icon = new ImageIcon(ICON_PATH);
            ImageNotFoundedException.imageNotFound(icon);
            setIconImage(icon.getImage());
        } catch (ImageNotFoundedException e) {
            // Handle image not found exception
            e.printStackTrace();
        }
        // Set frame size, position, and default close operation
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    public abstract void initializeTabbedContent();

    public abstract void initializeSideBar();
}

class HiddenTabTitleUI extends BasicTabbedPaneUI {
    private boolean hideTabTitles = true;
    private boolean hideTabArea = true;

    public void setHideTabTitles(boolean hideTabTitles) {
        this.hideTabTitles = hideTabTitles;
        tabPane.revalidate();
        tabPane.repaint();
    }

    public void setHideTabArea(boolean hideTabArea) {
        this.hideTabArea = hideTabArea;
        tabPane.revalidate();
        tabPane.repaint();
    }

    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        return hideTabTitles ? 0
                : super.calculateTabHeight(tabPlacement, tabIndex, fontHeight);
    }

    @Override
    protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
        return hideTabArea ? 0
                : super.calculateTabAreaHeight(tabPlacement, horizRunCount, maxTabHeight);
    }
}
