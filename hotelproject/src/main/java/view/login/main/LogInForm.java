package view.login.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import model.Guest;
import model.User;
import view.login.container.Message;
import view.login.container.PanelCover;
import view.login.container.PanelLoginAndRegister;
import view.login.container.Message.MessageType;

public class LogInForm extends JFrame {
    public class LogInForm extends JFrame {

        private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
        private MigLayout layout;
        private PanelCover cover;
        private PanelLoginAndRegister loginAndRegister;
        private boolean isLogin = true;
        private final double addSize = 30;
        private final double coverSize = 40;
        private final double loginSize = 60;
        private JLayeredPane bg;

        // private Image backgroundImage;
    public LogInForm() {

        public LogInForm() {
            initComponents();
            init();
        }

        private void init() {
            layout = new MigLayout("fill, insets 0");
            cover = new PanelCover();
            ActionListener actEventRegister = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    register();
                }

            };
            ActionListener actEventLogin = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    logIn();
                }

            };
            loginAndRegister = new PanelLoginAndRegister(actEventRegister, actEventLogin);

            TimingTarget target = new TimingTargetAdapter() {
                @Override
                public void timingEvent(float fraction) {
                    double fractionCover;
                    double fractionLogin;
                    double size = coverSize;
                    if (fraction <= 0.5f) {
                        size += fraction * addSize;
                    } else {
                        size += addSize - fraction * addSize;
                    }
                    if (isLogin) {
                        fractionCover = 1f - fraction;
                        fractionLogin = fraction;
                        if (fraction >= 0.5f) {
                            cover.registerRight(fractionCover * 100);
                        } else {
                            cover.loginRight(fractionLogin * 100);
                        }
                    } else {
                        fractionCover = fraction;
                        fractionLogin = 1f - fraction;
                        if (fraction <= 0.5f) {
                            cover.registerLeft(fraction * 100);
                        } else {
                            cover.loginLeft((1f - fraction) * 100);
                        }
                    }
                    if (fraction >= 0.5f) {
                        loginAndRegister.showRegister(isLogin);
                    }
                    fractionCover = Double.parseDouble(df.format(fractionCover));
                    fractionLogin = Double.parseDouble(df.format(fractionLogin));
                    layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                    layout.setComponentConstraints(loginAndRegister,
                            "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                    bg.revalidate();
                }

                @Override
                public void end() {
                    isLogin = !isLogin;
                }
            };
            loginAndRegister.addAncestorListener(null);

            Animator animator = new Animator(800, target);
            animator.setAcceleration(0.5f);
            animator.setDeceleration(0.5f);
            animator.setResolution(0); // for smooth animation
            bg.setLayout(layout);
            bg.add(cover, "width " + coverSize + "%, pos " + (isLogin ? "1al" : "0al") + " 0 n 100%");
            bg.add(loginAndRegister, "width " + loginSize + "%, pos " + (isLogin ? "0al" : "1al") + " 0 n 100%"); // 1al
                                                                                                                  // as
                                                                                                                  // 100%
            loginAndRegister.showRegister(!isLogin);
            cover.login(isLogin);
            cover.addEvent(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (!animator.isRunning()) {
                        animator.start();
                    }
                }
            });
        }

        private void initComponents() {

            bg = new javax.swing.JLayeredPane();
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            // setUndecorated(true);
            setResizable(false);

            try {
                Image backgroundImage = new ImageIcon(getClass().getResource("/view/login/icon/user.png")).getImage();
                setIconImage(backgroundImage);

            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(this, "Image not found: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
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

        public void register() {
            // Guest user = loginAndRegister.guest;
            // Guest guest = loginAndRegister.getLogInData();
            User guest = loginAndRegister.getRegisterData();
            if (guest != null) {

                showMessage(MessageType.SUCCESS, "nice test");
                guest.inser();
                loginAndRegister.setRegisterData(null);
            } else {
                showMessage(MessageType.ERROR, loginAndRegister.getRegisterErreurMsg());

            }
        }

        public void logIn() {
            if (loginAndRegister.getLogInData() != null) {
                showMessage(MessageType.SUCCESS, "Welcome back " + loginAndRegister.getLogInData().getFirstName());
                loginAndRegister.setLogInData(null);
            } else {
                showMessage(MessageType.ERROR, loginAndRegister.getLoginErreurMsg());

            }
        }

        private void showMessage(Message.MessageType messageType, String message) {
            Message ms = new Message();
            ms.showMessage(messageType, message);
            TimingTarget target = new TimingTargetAdapter() {
                @Override
                public void begin() {
                    if (!ms.isShow()) {
                        bg.add(ms, "pos 0.5al -30", 0); // Insert to bg fist index 0
                        ms.setVisible(true);
                        bg.repaint();
                    }
                }

                @Override
                public void timingEvent(float fraction) {
                    float f;
                    if (ms.isShow()) {
                        f = 40 * (1f - fraction);
                    } else {
                        f = 40 * fraction;
                    }
                    layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                    bg.repaint();
                    bg.revalidate();
                }

                @Override
                public void end() {
                    if (ms.isShow()) {
                        bg.remove(ms);
                        bg.repaint();
                        bg.revalidate();
                    } else {
                        ms.setShow(true);
                    }
                }
            };
            Animator animator = new Animator(300, target);
            animator.setResolution(0);
            animator.setAcceleration(0.5f);
            animator.setDeceleration(0.5f);
            animator.start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        animator.start();
                    } catch (InterruptedException e) {
                        System.err.println(e);
                    }
                }
            }).start();
        }

        public static void runForm() {

            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(LogInForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                        ex);
                java.util.logging.Logger.getLogger(LogInForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                        ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(LogInForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                        ex);
                java.util.logging.Logger.getLogger(LogInForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                        ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(LogInForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                        ex);
                java.util.logging.Logger.getLogger(LogInForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                        ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(LogInForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                        ex);
                java.util.logging.Logger.getLogger(LogInForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                        ex);
            }

            /* Create and display the form */
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new LogInForm().setVisible(true);
                    new LogInForm().setVisible(true);
                }
            });
        }

}
