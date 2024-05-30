package view.components.reservationComponents;

import controllers.Controller;
import model.User;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;
import view.components.Message;
import view.components.OurButton;
import view.components.items.MyTextField;
import view.components.roomComponents.CounterPanel;
import view.components.sacrollBar.ScrollBar;
import view.components.table.Table;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

public class ReservationPanelGuest extends JPanel {
        private final LinkedList<String> confirmedRequests=new LinkedList<>();
        private final Table requestsTable;
        private final Table reservationsTable;
        private final Message msg = new Message();
        private String[] getRequestColumnNames() {
            return new String[] { "roomNumber","email","phone", "checkIn", "checkOut","adults", "children", "price","isPaid", "isConfirmed"};
        }
        private String[] getReservationColumnNames() {
            return new String[] { "roomNumber","email","phone", "checkIn", "checkOut","adults", "children", "price","isPaid"};
        }
    public ReservationPanelGuest(){
            setLayout(new MigLayout("wrap 2, insets 20 0 20 0,center", "push[100%,fill]push", "push[]push"));
            MigLayout layout=new MigLayout("wrap 1,center", "push[95%,fill]push", "20[]10[]20");
            JPanel panelToScroll=new JPanel(layout);
            panelToScroll.setBackground(new Color(242, 242, 242));


        JPanel mainPanel = new JPanel(new MigLayout("wrap 2, insets 20", "[65%,fill][30%,fill]"));

        // Creating user input panel with MigLayout
        JPanel reservationInputPanel = new JPanel(new MigLayout("wrap 2, insets 0", "[30%][grow]"));
        // Creating titled border for user input panel
        Border border = BorderFactory.createTitledBorder("Request information:");
        reservationInputPanel.setBorder(border);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Inter", Font.PLAIN, 15));
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Inter", Font.PLAIN, 15));
        JLabel checkInLabel = new JLabel("Check In:");
        checkInLabel.setFont(new Font("Inter", Font.PLAIN, 15));
        JLabel checkOutLabel = new JLabel("Check Out:");
        checkOutLabel.setFont(new Font("Inter", Font.PLAIN, 15));
        JLabel adultsLabel = new JLabel("Adults:");
        adultsLabel.setFont(new Font("Inter", Font.PLAIN, 15));
        JLabel childrenLabel = new JLabel("Children:");
        childrenLabel.setFont(new Font("Inter", Font.PLAIN, 15));
        JLabel priceLabel = new JLabel("Cost:");
        priceLabel.setFont(new Font("Inter", Font.PLAIN, 15));

        MyTextField emailField = new MyTextField("email", null);
        emailField.setEnabled(false);
        MyTextField phoneField = new MyTextField("0123456789", null);
        phoneField.setEnabled(false);
        JXDatePicker checkOut = new JXDatePicker();
        checkOut.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        checkOut.setBorder(null); // Remove border
        checkOut.setEnabled(false);
        JXDatePicker checkIn = new JXDatePicker();
        checkIn.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        checkIn.setBorder(null); // Remove border
        checkIn.setEnabled(false);
        CounterPanel adultsField = new CounterPanel("Adults");
        adultsField.getIncrementButton().setEnabled(false);
        adultsField.getDecrementButton().setEnabled(false);
        CounterPanel childrenField = new CounterPanel("Children");
        childrenField.getIncrementButton().setEnabled(false);
        childrenField.getDecrementButton().setEnabled(false);
        MyTextField priceField = new MyTextField("0.0/Night", null);
        priceField.setEnabled(false);

        reservationInputPanel.add(emailLabel, "gap 10 10 10 10");
        reservationInputPanel.add(emailField, "gap 10 10 10 10, grow");
        reservationInputPanel.add(phoneLabel, "gap 10 10 10 10");
        reservationInputPanel.add(phoneField, "gap 10 10 10 10, grow");
        reservationInputPanel.add(checkInLabel, "gap 10 10 10 10");
        reservationInputPanel.add(checkIn, "gap 10 10 10 10, grow");
        reservationInputPanel.add(checkOutLabel, "gap 10 10 10 10");
        reservationInputPanel.add(checkOut, "gap 10 10 10 10, grow");
        reservationInputPanel.add(adultsLabel, "gap 10 10 10 10");
        reservationInputPanel.add(adultsField, "gap 10 10 10 10, grow");
        reservationInputPanel.add(childrenLabel, "gap 10 10 10 10");
        reservationInputPanel.add(childrenField, "gap 10 10 10 10, grow");
        reservationInputPanel.add(priceLabel, "gap 10 10 10 10");
        reservationInputPanel.add(priceField, "gap 10 10 10 10, grow");
        reservationInputPanel.setBackground(Color.WHITE);

        // Creating button panel with MigLayout
        JPanel buttonPanel = new JPanel(new MigLayout("wrap", "push[center]push", "push[][]10[]10[]10[]25[]push"));
        // Adding empty JLabel for spacing
        buttonPanel.add(new JLabel(), "wrap, height 20");

        OurButton confirmButton = new OurButton("Confirm");
        confirmButton.setButtonBgColor(new Color(0, 112, 255));
        buttonPanel.add(confirmButton, "w 60%, h 34");

        OurButton editButton = new OurButton("Edit");
        editButton.setButtonBgColor(new Color(0, 112, 255));
        buttonPanel.add(editButton, "w 60%, h 34");

        OurButton deleteButton = new OurButton("Cancel");
        deleteButton.setButtonBgColor(new Color(0xED1B24));
        buttonPanel.add(deleteButton, "w 60%, h 34");

        buttonPanel.setBackground(Color.WHITE);

        mainPanel.add(reservationInputPanel, "span 1 2, grow");
        mainPanel.add(buttonPanel, "span 1 2, grow");
        mainPanel.setBackground(Color.WHITE);

        JScrollPane scrollPaneRequests = new JScrollPane();
        requestsTable = new Table();
        requestsTable.setModel(new DefaultTableModel(new Object[][] {}, getRequestColumnNames()) {
            private final boolean[] canEdit = new boolean[getRequestColumnNames().length];
            {
                Arrays.fill(canEdit, false);
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        requestsTable.addRowSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent event) {
                        if (!event.getValueIsAdjusting()) {
                            int selectedRow = requestsTable.getSelectedRow();
                            if (selectedRow >= 0) {
                                DefaultTableModel model = (DefaultTableModel) requestsTable.getModel();
                                emailField.setText(model.getValueAt(selectedRow, 1).toString());
                                phoneField.setText(model.getValueAt(selectedRow, 2).toString());
                                String dateIn = model.getValueAt(selectedRow, 3).toString();
                                Date dateCheckIn = new Date();
                                try {
                                    dateCheckIn = new SimpleDateFormat("dd/MM/yyyy").parse(dateIn);
                                } catch (Exception e) {
                                    msg.displayMessage(Message.MessageType.ERROR, "Error parsing date", panelToScroll, layout);
                                }
                                checkIn.setDate(dateCheckIn);
                                String dateOut = model.getValueAt(selectedRow, 4).toString();
                                Date dateCheckOut = new Date();
                                try {
                                    dateCheckOut = new SimpleDateFormat("dd/MM/yyyy").parse(dateOut);
                                } catch (Exception e) {
                                    msg.displayMessage(Message.MessageType.ERROR, "Error parsing date", panelToScroll, layout);
                                }
                                checkOut.setDate(dateCheckOut);
                                adultsField.getCounterLabel().setText(model.getValueAt(selectedRow, 5).toString());
                                childrenField.getCounterLabel().setText(model.getValueAt(selectedRow, 6).toString());
                                priceField.setText(model.getValueAt(selectedRow, 7).toString());
                            }
                        }
                    }
                });

        Controller.updateReservation(editButton,requestsTable,checkIn,checkOut,adultsField,childrenField,phoneField,priceField,msg,panelToScroll,layout,deleteButton,confirmButton,confirmedRequests);
        Controller.cancelReservation(deleteButton,requestsTable,msg,panelToScroll,layout);


        scrollPaneRequests.setViewportView(requestsTable);
        scrollPaneRequests.setVerticalScrollBar(new ScrollBar());
        scrollPaneRequests.getVerticalScrollBar().setBackground(Color.WHITE);
        scrollPaneRequests.getViewport().setBackground(Color.WHITE);
        scrollPaneRequests.setBorder(BorderFactory.createEmptyBorder());

        JPanel corner1 = new JPanel();
        corner1.setBackground(Color.WHITE);
        scrollPaneRequests.setCorner(JScrollPane.UPPER_RIGHT_CORNER, corner1);
        scrollPaneRequests.setBackground(new Color(242, 242, 242));

        MigLayout layoutM=new MigLayout("wrap 1,center", "push[85%,fill]push", "20[]10[]20");
        JPanel container1=new JPanel(layoutM);
        container1.setBorder(BorderFactory.createTitledBorder("Requests"));
        container1.add(mainPanel, "center, w 95%!");
        container1.add(scrollPaneRequests, "push, grow");
        container1.setBackground(Color.WHITE);
        panelToScroll.add(container1, "push, grow");

        //////////////////////////////////////////


        JScrollPane scrollPaneReservations = new JScrollPane();
        scrollPaneReservations.setBackground(new Color(242, 242, 242));
        reservationsTable = new Table();
        reservationsTable.setModel(new DefaultTableModel(new Object[][] {}, getReservationColumnNames()) {
            private final boolean[] canEdit = new boolean[getReservationColumnNames().length];
            {
                Arrays.fill(canEdit, false);
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        scrollPaneReservations.setViewportView(reservationsTable);

        Controller.initTableResReq(getRequestColumnNames(),requestsTable,getReservationColumnNames(),reservationsTable);

        scrollPaneReservations.setVerticalScrollBar(new ScrollBar());
        scrollPaneReservations.getVerticalScrollBar().setBackground(Color.WHITE);
        scrollPaneReservations.getViewport().setBackground(Color.WHITE);
        scrollPaneReservations.setBorder(BorderFactory.createEmptyBorder());
        JPanel corner2 = new JPanel();
        corner2.setBackground(Color.WHITE);
        scrollPaneReservations.setCorner(JScrollPane.UPPER_RIGHT_CORNER, corner2);
        JPanel container2=new JPanel(new MigLayout("wrap 1,center", "push[85%,fill]push", "20[]10[]20"));
        container2.setBorder(BorderFactory.createTitledBorder("Reservations"));
        container2.add(scrollPaneReservations, "push, grow");
        container2.setBackground(Color.WHITE);
        panelToScroll.add(container2, "push, grow");

        Controller.confirmReservation(confirmButton,requestsTable,msg,panelToScroll,layout,confirmedRequests);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(panelToScroll);
        scrollPane.setVerticalScrollBar(new ScrollBar());
        scrollPane.setHorizontalScrollBar(new ScrollBar());
        scrollPane.getVerticalScrollBar().setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBackground(new Color(242, 242, 242));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JPanel corner3 = new JPanel();
        corner3.setBackground(Color.WHITE);
        scrollPaneReservations.setCorner(JScrollPane.UPPER_RIGHT_CORNER, corner3);

        setBackground(new Color(242, 242, 242));
        add(scrollPane, "push, grow");
    }
    /**
     * returns the tables in the panel, 1st table is the requests table and the 2nd table is the reservations table
     * */
    public Table[] getTables(){
        return new Table[]{requestsTable,reservationsTable};
    }
}
