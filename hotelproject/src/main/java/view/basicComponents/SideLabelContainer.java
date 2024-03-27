package view.basicComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class SideLabelContainer extends JPanel implements MouseListener {
    private final JLabel label;
    /**
     * To create side tab that must be added to a container (Like tab  container)
     * @param text tab name title.
     *
     * @author Mouloudj
     *
     * */
    public SideLabelContainer(String text){
        label = new JLabel();
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Inter", Font.PLAIN, 16));
        label.setPreferredSize(new Dimension(170, 30));
        label.addMouseListener(this);

        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setPreferredSize(new Dimension(170, 30));
        setBackground(new Color(0x1E90FF));
        add(label);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == label){

            for (Component component : getParent().getComponents()){
                component.setBackground(new Color(0x1E90FF));
                for (Component c : ((JPanel) component).getComponents()){
                    c.setForeground(Color.WHITE);
                }
            }
            label.setForeground(Color.BLACK);
            setBackground(Color.WHITE);
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
