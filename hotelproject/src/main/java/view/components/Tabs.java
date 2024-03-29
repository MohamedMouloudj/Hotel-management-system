package view.components;

import java.awt.event.ActionListener;

public class Tabs {

    private ActionListener actionListener;
    private String text;

    public Tabs(String text, ActionListener actionListener) {
        this.actionListener = actionListener;
        this.text = text;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
