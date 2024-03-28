package view.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import net.miginfocom.swing.MigLayout;
import view.components.MyButtonOutLine;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelRoom extends JPanel {

    private  String roomName;
    private  String description;
    private String available ;
    private double price ;
    private MyButtonOutLine bookButton;


    public PanelRoom(String roomName, String description , boolean available , double price) {
        this.roomName = roomName;
        this.description = description;
        this.price = price;
        if (available) {
            this.available = "available";
        } else {
            this.available = "not available";
        }


      // Allow background to show through for a cleaner look
        setPreferredSize(new Dimension(500,300 ));
        //String borderColor = "#C1A200";
       // setBorder(new LineBorder(Color.getHSBColor(50,100,76), 3, true));


            //  using MigLayout for labels
            MigLayout nameLayout = new MigLayout("wrap", "", "20[]20[]3[]40[]40[]3[]20[]");
            setLayout(nameLayout);

           ImageIcon icon = new ImageIcon("./view/icons/room.png");
           JLabel imageLabel = new JLabel(icon);
           add(imageLabel);

       /* BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("./view/icons/room.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        add(picLabel);*/

       // ImageIcon icon = new ImageIcon("");
       // Image resizedImage = image.getScaledInstance(this.screenWidth / 2, this.screenHeight, Image.SCALE_SMOOTH);
        // icon = new ImageIcon(resizedImage);


            // Background image (assuming tenbackgorund is an ImageIcon object)
             /*ImageIcon icon  = new ImageIcon("./view/icons/room.png");
             JLabel backgroundLabel = new JLabel(icon);
              add(backgroundLabel); // Left side, full cell, min size*/




            // Name label

            JLabel nameLabel = new JLabel(roomName);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            add(nameLabel, "align left");

// Available label with formatted text and custom border
            String availabilityText = this.available;
            JLabel availableLabel = new JLabel(availabilityText);
            availableLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            add(availableLabel, "align left");

// New MigLayout for description and price labels


// Description label with wrapped text
            String descriptionText = "This is a detailed description. It can span multiple lines if it's too lon sdcsdcsdcsdcsdcsdcsdcsdcsdcsdcsdcsdcsdcscsdcscg.";
            JLabel descriptionLabel = new JLabel(descriptionText);
            descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
            add(descriptionLabel, "wrap , align left");


// Price label with currency symbol and right alignment
            String currencySymbol = "DZD"; // Assuming USD, replace if needed
            String pricePerNight = currencySymbol + price + " per night";
            JLabel priceLabel = new JLabel(pricePerNight);
            priceLabel.setFont(new Font("Arial", Font.ITALIC, 12));

            // priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(priceLabel, "align left");
            // Add the descPricePanel to the main panel
            //button panel

            MyButton button = new MyButton(){
                @Override
                protected void paintComponent(Graphics g) {
                    g.setColor(getBackground());
                    g.fillRoundRect(0, 0, getWidth(), getHeight() , 15 , 15 );
                    super.paintComponent(g);
                }
            };
            button.setBackground(Color.BLUE); // Set blue background
            button.setContentAreaFilled(false); // Remove default button border
            button.setFocusPainted(false);     // Remove focus rectangle
            button.setText("book Now ");

            add(button, "wrap , width 250, height 40, align left");



        }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        int roundness = 40;
        g2d.setColor(Color.getHSBColor(30,25,100));
        g2d.fillRoundRect(0, 0, width - 1, height - 1, roundness, roundness);
    }



         /*ImageIcon tenbackgorund = new ImageIcon("./view/icons/test.jpg");
         // Background image (assuming tenbackgorund is an ImageIcon object)
        JLabel backgroundLabel = new JLabel(tenbackgorund);
        panel.add(backgroundLabel, "cell 0 0 , wmin 123, hmin 22"); // Left side, full cell, min size
       */

    public static void main(String[] args) {
        // Example usage (replace with your actual image and data)
        String roomName = "Deluxe Room";
        String description = "This spacious room offers a king-size bed, a private balcony, and stunning city views.";
        boolean availablity = true ; // Replace with your image path
        double price = 5000.32;

        JFrame frame = new JFrame("Room Card");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PanelRoom(roomName, description, availablity , price ));
        frame.pack();
        frame.setVisible(true);
    }
}


