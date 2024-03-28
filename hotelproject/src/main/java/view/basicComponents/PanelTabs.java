package view.basicComponents;

import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;
import view.login.loginComponents.MyButton;

import javax.swing.border.Border;

import java.awt.event.*;
import java.util.HashMap;

public class PanelTabs extends JLayeredPane {

    public PanelTabs(HashMap<String, ActionListener> map, String rowsConstraints) {
        setLayout(new java.awt.CardLayout());
        init(map, rowsConstraints);

    }

    private void init(HashMap<String, ActionListener> map, String rowsConstraints) {
        setLayout(new MigLayout("wrap", "push[center]push", rowsConstraints));

        JLabel title = new JLabel("L'Oasis");
        title.setFont(new Font("Ink free", Font.BOLD, 55));
        add(title, "alignx center, wrap, gaptop 20");

        for (String text : map.keySet()) {
            MyButton btn = createStyledButton(text);
            btn.addActionListener(map.get(text));
            // btn.addActionListener(new ActionListener() {

            // @Override
            // public void actionPerformed(ActionEvent arg0) {
            // // TODO Auto-generated method stub
            // contect.setidex();
            // }

            // });
            add(btn, "w 70%, h 38, alignx center");
        }

    }

    private MyButton createStyledButton(String text) {
        MyButton button = new MyButton() {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.white);
                } else {
                    g.setColor(new Color(0, 0, 0, 0));
                }
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };

        button.setText(text);
        button.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        button.setForeground(Color.white); // White font color
        button.setFocusPainted(false); // Remove the focus border
        button.setFont(new Font("Noto Sans", Font.PLAIN, 18));

        Border emptyBorder = BorderFactory.createEmptyBorder();
        button.setBorder(emptyBorder);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button.setForeground(Color.black);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.black);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.white);
            }
        });

        return button;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);

        Graphics2D g2 = (Graphics2D) grphcs;
        GradientPaint gra = new GradientPaint(0, 0, new Color(0, 112, 255), 0, getHeight(), new Color(0x00BFFF));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}

// MyButton btn1 = createStyledButton("Add Reciptionist");
// MyButton btn2 = createStyledButton("Delate Reciptionist");
// btn3 = createStyledButton("All Reciptionists");
// MyButton btn4 = createStyledButton("Add Room");
// MyButton btn5 = createStyledButton("Delate Room");
// MyButton btn6 = createStyledButton("All Rooms");

// add(btn1, "w 70%, h 38, alignx center");
// add(btn2, "w 70%, h 38, alignx center");
// add(btn3, "w 70%, h 38, alignx center");
// add(btn4, "w 70%, h 38, alignx center");
// add(btn5, "w 70%, h 38, alignx center");
// add(btn6, "w 70%, h 38, alignx center");

// btn3.addActionListener(new ActionListener() {

// @Override
// public void actionPerformed(ActionEvent arg0) {
// // if (a % 2 == 0) {
// // panelShow.addAllreceptionist();

// // } else {
// // panelShow.removeAllComponents();
// // }
// // a++;

// }

// });