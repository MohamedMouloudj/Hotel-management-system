package view.components.sideBarComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SideButton extends JButton implements MouseListener {
    /**
     * To create side tab that must be added to a container (Like tab  container)
     *
     * @param text tab name title.
     */
    public SideButton(String text) {
        setText(text);
        setFocusable(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setForeground(Color.WHITE);
        setFont(new Font("Inter", Font.ITALIC, 16));
        addMouseListener(this);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     *
     * When the label is clicked, the background color of that label is changed to white and the color is changed to black, and the other labels are changed to the primary color
     * @param e MouseEvent
     * */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this) {

            for (Component component : getParent().getComponents()) {
                component.setForeground(Color.white);
                component.setBackground(new Color(0,0,0,0));
                ((JButton) component).setContentAreaFilled(false);
            }
            this.setForeground(Color.BLACK);
            this.setBackground(Color.WHITE);
            setContentAreaFilled(true);
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setForeground(Color.black);
    }

    /**
     * When the mouse exits the label, the color of the label is changed to white, if the background is white, the color is changed to black
     * @param e MouseEvent
     * */
    @Override
    public void mouseExited(MouseEvent e) {
        setForeground(Color.white);
        if(getBackground().equals(Color.WHITE)){
            setForeground(Color.BLACK);
        }
    }
}
