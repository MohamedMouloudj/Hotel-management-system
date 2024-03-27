package view.managerUi.container;

import java.awt.*;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class ManagerUI extends JFrame {

    private MigLayout layout;
    private PanelShow cover;
    private PanelTabs loginAndRegister;
    private boolean isLogin = true;
    private final double coverSize = 75;
    private final double loginSize = 25;
    private JLayeredPane bg;

    public ManagerUI() {
        initComponents();
        init();
    }

    private void init() {
        layout = new MigLayout("fill, insets 0");
        cover = new PanelShow();
        loginAndRegister = new PanelTabs(cover);

        loginAndRegister.addAncestorListener(null);

        bg.setLayout(layout);
        bg.add(cover, "width " + coverSize + "%, pos " + (isLogin ? "1al" : "0al") + " 0 n 100%");
        bg.add(loginAndRegister, "width " + loginSize + "%, pos " + (isLogin ? "0al" : "1al") + " 0 n 100%");
    }

    private void initComponents() {
        bg = new javax.swing.JLayeredPane();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        try {
            Image backgroundImage = new ImageIcon(getClass().getResource("/view/login/icon/user.png")).getImage();
            setIconImage(backgroundImage);

        } catch (NullPointerException e) {

            System.out.println(22);
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
                new ManagerUI().setVisible(true);
            }
        });
    }
}
