package view.components.reservationComponents;

import controllers.Controller;
import net.miginfocom.swing.MigLayout;
import view.components.Message;
import view.components.OurButton;
import view.components.sacrollBar.ScrollBar;
import view.components.table.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

public class ReservationPanelReceptionist extends JPanel {
    private final Table requestsTable;
    private final Table reservationsTable;
    private final Message msg = new Message();
    private String[] getRequestColumnNames() {
        return new String[] { "roomNumber","email","phone", "checkIn", "checkOut","adults", "children", "price","isPaid", "status"};
    }
    private String[] getReservationColumnNames() {
        return new String[] { "roomNumber","email","phone", "checkIn", "checkOut","adults", "children", "price","isPaid"};
    }
    public ReservationPanelReceptionist(){
        setLayout(new MigLayout("wrap 2, insets 20 0 20 0,center", "push[100%,fill]push", "push[]push"));
        JPanel panelToScroll=new JPanel(new MigLayout("wrap 1,center", "push[95%,fill]push", "20[]10[]20"));
        panelToScroll.setBackground(new Color(242, 242, 242));

        OurButton confirmReservation = new OurButton("add Reservation");
        confirmReservation.setButtonBgColor(new Color(0, 112, 255));

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

        scrollPaneRequests.setViewportView(requestsTable);
        scrollPaneRequests.setVerticalScrollBar(new ScrollBar());
        scrollPaneRequests.getVerticalScrollBar().setBackground(Color.WHITE);
        scrollPaneRequests.getViewport().setBackground(Color.WHITE);
        scrollPaneRequests.setBorder(BorderFactory.createEmptyBorder());

        JPanel corner1 = new JPanel();
        corner1.setBackground(Color.WHITE);
        scrollPaneRequests.setCorner(JScrollPane.UPPER_RIGHT_CORNER, corner1);
        scrollPaneRequests.setBackground(new Color(242, 242, 242));

        MigLayout layout=new MigLayout("wrap 1,center", "push[85%,fill]push", "20[]10[]20");
        JPanel container1=new JPanel(layout);
        container1.setBorder(BorderFactory.createTitledBorder("Requests"));
        container1.add(confirmReservation, "center, w 40%!");
        container1.add(scrollPaneRequests, "push, grow");
        container1.setBackground(Color.WHITE);
        panelToScroll.add(container1, "push, grow");

        //////////////////////////////////////////


        OurButton confirmPay = new OurButton("Set as Paid");
        confirmPay.setButtonBgColor(new Color(33, 168, 12));

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

        Controller.initTableResReqRecV(getRequestColumnNames(),requestsTable,getReservationColumnNames(),reservationsTable);

        scrollPaneReservations.setVerticalScrollBar(new ScrollBar());
        scrollPaneReservations.getVerticalScrollBar().setBackground(Color.WHITE);
        scrollPaneReservations.getViewport().setBackground(Color.WHITE);
        scrollPaneReservations.setBorder(BorderFactory.createEmptyBorder());
        JPanel corner2 = new JPanel();
        corner2.setBackground(Color.WHITE);
        scrollPaneReservations.setCorner(JScrollPane.UPPER_RIGHT_CORNER, corner2);
        JPanel container2=new JPanel(new MigLayout("wrap 1,center", "push[85%,fill]push", "20[]10[]20"));
        container2.setBorder(BorderFactory.createTitledBorder("Reservations"));
        container2.add(confirmPay, "center, w 40%!");
        container2.add(scrollPaneReservations, "push, grow");
        container2.setBackground(Color.WHITE);
        panelToScroll.add(container2, "push, grow");

        Controller.addReservationRecV(confirmReservation,requestsTable,reservationsTable,msg,panelToScroll,layout);
        Controller.payReservation(confirmPay,reservationsTable);

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
}
