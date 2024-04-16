package view.login.container;

import net.miginfocom.swing.MigLayout;
import view.login.loginComponents.MyButtonOutLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class PanelCover extends JPanel {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private ActionListener event;
    private MigLayout layout;
    private JLabel title;
    private JLabel description;
    private JLabel description1;
    private MyButtonOutLine signInbutton;
    private boolean isLogin;
    private Color blueColor = new Color(245, 245, 245);

    public PanelCover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        setLayout(layout);
        init();

    }

    private void init() {
        title = new JLabel("Welcome Back!");
        title.setFont(new Font("sanserif", 1, 33));
        title.setForeground(blueColor);
        add(title);
        description = new JLabel("To keep connected with us please");
        description.setForeground(blueColor);
        description.setFont(new Font("sanserif", 1, 12));
        add(description);
        description1 = new JLabel("login with your personal info");
        description1.setForeground(blueColor);
        description1.setFont(new Font("sanserif", 1, 12));

        add(description1);
        signInbutton = new MyButtonOutLine();
        signInbutton.setBackground(new Color(255, 255, 255));
        signInbutton.setForeground(new Color(255, 255, 255));
        signInbutton.setText("SIGN IN");
        signInbutton.setFont(new Font("sanserif", Font.BOLD, 15));
        signInbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.actionPerformed(ae);
            }
        });
        add(signInbutton, "w 60%, h 40");
    }

    // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 327, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        GradientPaint gra = new GradientPaint(0, 0, new Color(0, 112, 255), 0, getHeight(), new Color(0x00BFFF));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    public void addEvent(ActionListener event) {
        this.event = event;
    }

    public void registerLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    }

    public void registerRight(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    }

    public void loginLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
    }

    public void loginRight(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
    }

    public void login(boolean login) {
        if (this.isLogin != login) {
            if (login) {
                title.setText("Welcome to Our Hotel!");
                description.setText("Please provide your details");
                description1.setText("to start your journey with us.");
                signInbutton.setText("SIGN UP");
            } else {
                title.setText("Welcome Back!");
                description.setText("To keep connected with us please");
                description1.setText("login with your personal info");
                signInbutton.setText("SIGN IN");
            }
            this.isLogin = login;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}