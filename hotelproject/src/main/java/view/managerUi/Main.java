package view.managerUi;

import java.awt.*;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class Main extends JFrame {

    private MigLayout layout;
    private PanelShow panelShow;
    private PanelTabs panelTabs;
    private final double panelShowSize = 75;
    private final double loginSize = 25;
    private JLayeredPane bg;

    public Main() {
        initComponents();
        init();
    }

    private void init() {
        layout = new MigLayout("fill, insets 0");
        panelShow = new PanelShow();

        panelTabs = new PanelTabs(panelShow);
        panelTabs.addAncestorListener(null);

        bg.setLayout(layout);
        bg.add(panelShow, "width " + panelShowSize + "%, pos " + "1al" + " 0 n 100%");
        bg.add(panelTabs, "width " + loginSize + "%, pos " + "0al" + " 0 n 100%");
    }

    private void initComponents() {
        bg = new javax.swing.JLayeredPane();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        try {
            Image backgroundImage = new ImageIcon(getClass().getResource("user.png")).getImage();
            setIconImage(backgroundImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
                bgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 933, Short.MAX_VALUE));
        bgLayout.setVerticalGroup(
                bgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 537, Short.MAX_VALUE));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bg, GroupLayout.Alignment.TRAILING));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bg));

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}