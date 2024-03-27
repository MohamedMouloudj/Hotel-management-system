package view.basicComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class SideLabel extends JLabel implements MouseListener {

    /**
     * To create side tab that must be added to a container (Like tab  container)
     *
     * @param text tab name title.
     * @author Mouloudj
     */
    public SideLabel(String text) {
        setText(text);
        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setForeground(Color.WHITE);
        setFont(new Font("Inter", Font.PLAIN, 16));
        setPreferredSize(new Dimension(180, 40));
        addMouseListener(this);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(new Color(0x1E90FF));
        setOpaque(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this) {

            for (Component component : getParent().getComponents()) {
                component.setBackground(new Color(0x1E90FF));
                component.setForeground(Color.white);
            }
            this.setForeground(Color.BLACK);
            this.setBackground(Color.WHITE);
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
        // Do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Do nothing
    }
}
