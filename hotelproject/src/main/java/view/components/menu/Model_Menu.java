package view.components.menu;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Model_Menu {
    private String icon;
    private String name;
    private MenuType type;

    public Model_Menu(String icon, String name, MenuType type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public Model_Menu() {
    }

    public Icon toIcon() {
        ImageIcon iconP = new ImageIcon("hotelproject/src/main/java/view/icons/" + icon + ".png");
        iconP=new ImageIcon(iconP.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
        return iconP;
    }

    public static enum MenuType {
        TITLE, MENU, EMPTY
    }
}