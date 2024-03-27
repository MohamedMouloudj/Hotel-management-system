package view.managerUi;

import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;
import view.components.MyButton;

import javax.swing.border.Border;

import java.awt.event.*;

public class PanelTabs extends JLayeredPane {

    public MyButton btn3;

    private PanelShow panelShow;
    int a = 0;

    public PanelTabs(PanelShow panelShow) {
        initComponents();
        initRegister();
        this.panelShow = panelShow;

    }

    public void initRegister() {
        setLayout(new MigLayout("wrap", "push[center]push", "[]30[]20[]20[]20[]20[]20[]push"));

        JLabel title = new JLabel("L'Oasis");
        title.setFont(new Font("Ink free", Font.BOLD, 55));
        add(title, "alignx center, wrap, gaptop 20");

        MyButton btn1 = createStyledButton("Add Reciptionist");
        MyButton btn2 = createStyledButton("Delate Reciptionist");
        btn3 = createStyledButton("All Reciptionists");
        MyButton btn4 = createStyledButton("Add Room");
        MyButton btn5 = createStyledButton("Delate Room");
        MyButton btn6 = createStyledButton("All Rooms");

        add(btn1, "w 70%, h 38, alignx center");
        add(btn2, "w 70%, h 38, alignx center");
        add(btn3, "w 70%, h 38, alignx center");
        add(btn4, "w 70%, h 38, alignx center");
        add(btn5, "w 70%, h 38, alignx center");
        add(btn6, "w 70%, h 38, alignx center");

        btn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (a % 2 == 0) {
                    panelShow.addAllreceptionist();

                } else {
                    panelShow.removeAllComponents();
                }
                a++;

            }

        });

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

    private void initComponents() {
        setLayout(new java.awt.CardLayout());
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