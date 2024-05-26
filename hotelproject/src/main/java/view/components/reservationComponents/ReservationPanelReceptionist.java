package view.components.reservationComponents;

import controllers.Controller;
import net.miginfocom.swing.MigLayout;
import view.components.sacrollBar.ScrollBar;
import view.components.table.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

public class ReservationPanelReceptionist extends JPanel {
    private Table requestsTable;
    private Table reservationsTable;
    private String[] getColumnNames() {
        return new String[] { "roomNumber","email","phone", "checkIn", "checkOut","adults", "children", "price" };
    }
    public ReservationPanelReceptionist(){
        setLayout(new MigLayout("wrap 2, insets 20 0 20 0,center", "push[100%,fill]push", "push[]push"));
        JPanel panelToScroll=new JPanel(new MigLayout("wrap 1,center", "push[95%,fill]push", "20[]10[]20"));
        panelToScroll.setBackground(new Color(242, 242, 242));

        JScrollPane scrollPaneRequests = new JScrollPane();
        requestsTable = new Table();
        requestsTable.setModel(new DefaultTableModel(new Object[][] {}, getColumnNames()) {
            private final boolean[] canEdit = new boolean[getColumnNames().length];
            {
                Arrays.fill(canEdit, false);
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });


        Controller.initTableResReq(2,getColumnNames(),reservationsTable);

        scrollPaneRequests.setViewportView(requestsTable);
        scrollPaneRequests.setVerticalScrollBar(new ScrollBar());
        scrollPaneRequests.getVerticalScrollBar().setBackground(Color.WHITE);
        scrollPaneRequests.getViewport().setBackground(Color.WHITE);
        scrollPaneRequests.setBorder(BorderFactory.createEmptyBorder());
        JPanel corner1 = new JPanel();
        corner1.setBackground(Color.WHITE);
        scrollPaneRequests.setCorner(JScrollPane.UPPER_RIGHT_CORNER, corner1);
        JPanel container1=new JPanel(new MigLayout("wrap 1,center", "push[65%,fill]push", "20[]10[]20"));
        container1.setBorder(BorderFactory.createTitledBorder("Requests"));
        container1.add(scrollPaneRequests, "push, grow");
        container1.setBackground(Color.WHITE);
        panelToScroll.add(container1, "push, grow");

        //request scroll pane
        JScrollPane scrollPaneReservations = new JScrollPane();
        reservationsTable = new Table();
        //TODO: use Controller.initTableResReq("Reservations",getReservationsColumnNames(),reservationsTable);

        reservationsTable.setModel(new DefaultTableModel(new Object[][] {}, getColumnNames()) {
            private final boolean[] canEdit = new boolean[getColumnNames().length];
            {
                Arrays.fill(canEdit, false);
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        Controller.initTableResReq(1,getColumnNames(),reservationsTable);

        scrollPaneReservations.setViewportView(reservationsTable);
        scrollPaneReservations.setVerticalScrollBar(new ScrollBar());
        scrollPaneReservations.getVerticalScrollBar().setBackground(Color.WHITE);
        scrollPaneReservations.getViewport().setBackground(Color.WHITE);
        scrollPaneReservations.setBorder(BorderFactory.createEmptyBorder());
        JPanel corner2 = new JPanel();
        corner2.setBackground(Color.WHITE);
        scrollPaneReservations.setCorner(JScrollPane.UPPER_RIGHT_CORNER, corner2);
        JPanel container2=new JPanel(new MigLayout("wrap 1,center", "push[65%,fill]push", "20[]10[]20"));
        container2.setBorder(BorderFactory.createTitledBorder("Reservations"));
        container2.add(scrollPaneReservations, "push, grow");
        container2.setBackground(Color.WHITE);
        panelToScroll.add(container2, "push, grow");

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
        //this one is for updating the tables


        System.out.println(reservationsTable);


        setBackground(new Color(242, 242, 242));
        add(scrollPane, "push, grow");
    }

//    public void updateReservationTables() {
//        // Clear the tables
//        DefaultTableModel requestsTableModel = (DefaultTableModel) requestsTable.getModel();
//        requestsTableModel.setRowCount(0);
//        DefaultTableModel reservationsTableModel = (DefaultTableModel) reservationsTable.getModel();
//        reservationsTableModel.setRowCount(0);
//
//        // Iterate over the reservations in the Hotel class
//        for (Map.Entry<String, Reservation> entry :  Hotel.getReservationRequests().entrySet()) {
//            Reservation reservation = entry.getValue();
//
//            // Add the reservation to the requests table
//            if (!entry.getValue().isConfirmed()){
//                System.out.print("Adding reservation 1");
//                requestsTableModel.addRow(new Object[] { reservation.getRoomNumber(), reservation.getGuestEmail(), reservation.getPhoneNumber(), reservation.getCheckInDate(), reservation.getCheckOutDate(), reservation.getAdults(), reservation.getChildren(), reservation.getTotalCost() });
//            }else{
//                System.out.print("Adding reservation 2");
//                reservationsTableModel.addRow(new Object[] { reservation.getRoomNumber(), reservation.getGuestEmail(), reservation.getPhoneNumber(), reservation.getCheckInDate(), reservation.getCheckOutDate(), reservation.getAdults(), reservation.getChildren(), reservation.getTotalCost() });
//            }
//        }
//
////        for (Map.Entry<String, Reservation> entry : Hotel.confirmedReservations.entrySet()) {
////            Reservation reservation = entry.getValue();
////            // Add the reservation to the reservations table
////            reservationsTableModel.addRow(new Object[] { reservation.getRoomNumber(), reservation.getEmail(), reservation.getPhone(), reservation.getCheckIn(), reservation.getCheckOut(), reservation.getAdults(), reservation.getChildren(), reservation.getPrice() });
////        }
//    }
}
