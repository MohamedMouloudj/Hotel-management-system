package view.basicComponents;

import javax.swing.*;
import java.awt.*;

public class OurButton extends JButton {
    /**
     * Create a button with the primary color
     * @param text String
     *             The text of the button
     * */
    public OurButton(String text){
        super(text);
        setFont(new Font("Inter", Font.PLAIN, 16));
        setForeground(Color.WHITE);
        setBackground(new Color(0x1E90FF));
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setHorizontalAlignment(JButton.CENTER);
    }

    /**
     * Set the icon to the button
     * @param path String
     *             The path of the icon
     * @param gap int
     *            The space between the text and the icon
     * @param horizontalTextPosition int
     *                               The position of the text in relation to the icon
     *                               LEFT=2, RIGHT=4, CENTER=0
     * @author Mouloudj
     * */
    public void setIconToButton(String path,int gap, int horizontalTextPosition){
        ImageIcon icon = new ImageIcon(path);
        setIcon(icon);
        setIconTextGap(gap);
        setHorizontalTextPosition(horizontalTextPosition);
    }
    public void setBgColor(Color color){
        setBackground(color);
    }
    public void setFgColor(Color color){
        setForeground(color);
    }
    public void setFontStyle(String fontName, int style, int size){
        setFont(new Font(fontName,style,size));
    }
    public void setWidth(int width){
        setPreferredSize(new Dimension(width, 60));
    }
}
