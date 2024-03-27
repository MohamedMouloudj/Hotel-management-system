package view.managerUi.container;

import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;
import view.login.loginComponents.MyButton;

import javax.swing.border.Border;

import java.awt.event.*;

public class PanelTabs extends JLayeredPane {

    public PanelShow cover;
    public MyButton btn3;

    public PanelTabs(PanelShow cover) { // Receive panelShow reference
        initComponents();
        initRegister();
        this.cover = cover;

    }

    public void initRegister() {
        setLayout(new MigLayout("wrap", "push[center]push", "[]30[]20[]20[]20[]20[]20[]push"));

        JLabel title = new JLabel("L'Oasis");
        title.setFont(new Font("Ink free", Font.BOLD, 55));
        add(title, "alignx center, wrap, gaptop 20"); // Added gaptop 20 for 20px top margin

        MyButton btn1 = createStyledButton("Add Reciptionist");
        MyButton btn2 = createStyledButton("Delate Reciptionist");
        btn3 = createStyledButton("All Reciptionists");
        MyButton btn4 = createStyledButton("Add Room");
        MyButton btn5 = createStyledButton("Delete Room");
        MyButton btn6 = createStyledButton("All Rooms");

        add(btn1, "w 70%, h 38, alignx right");
        add(btn2, "w 70%, h 38, alignx right");
        add(btn3, "w 70%, h 38, alignx right");
        add(btn4, "w 70%, h 38, alignx right");
        add(btn5, "w 70%, h 38, alignx right");
        add(btn6, "w 70%, h 38, alignx right");

        btn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                cover.add(cover.scrollPane, "grow");
                cover.revalidate();
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
                    g.setColor(new Color(0, 0, 0, 0)); // Transparent background
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
                for (Component component : getParent().getComponents()){
                    component.setBackground(new Color(0x1E90FF));
                    component.setForeground(Color.WHITE);
                }
                button.setForeground(Color.BLACK);
                setBackground(Color.WHITE);
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
